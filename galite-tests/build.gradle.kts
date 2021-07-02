/*
 * Copyright (c) 2013-2020 kopiLeft Services SARL, Tunis TN
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License version 2.1 as published by the Free Software Foundation.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */

import org.kopi.galite.gradle.Versions

plugins {
  kotlin("jvm") apply true
  id("org.springframework.boot") version Versions.spring
  id("io.spring.dependency-management") version Versions.springDependencyManagement
  id("com.vaadin") version "0.17.0.1"
}

vaadin {
  pnpmEnable = true
}

dependencies {
  implementation(project(":galite-core"))

  implementation(kotlin("test-junit"))

  implementation("com.vaadin", "vaadin-core") {
    excludeWebJars()
  }
  implementation("com.vaadin", "vaadin-spring-boot-starter") {
    excludeWebJars()
  }
  implementation("org.springframework.boot", "spring-boot-devtools") {
    excludeWebJars()
  }

  // UI tests dependencies
  implementation("com.github.mvysny.kaributesting", "karibu-testing-v10", Versions.karibuTesting)

  implementation("org.jdom", "jdom2", Versions.jdom)

  // Exposed dependencies
  testImplementation ("org.jetbrains.exposed", "exposed-jdbc", Versions.exposed)

  testImplementation("com.h2database", "h2", Versions.h2)
  testImplementation("com.impossibl.pgjdbc-ng", "pgjdbc-ng", Versions.postgresNG)

  //Apache POI
  testImplementation("org.apache.poi", "poi", Versions.apachePoi)
  testImplementation("org.apache.poi", "poi-ooxml", Versions.apachePoi)
}

tasks {
  compileTestKotlin {
    kotlinOptions.jvmTarget = "1.8"
  }
}

dependencyManagement {
  imports {
    mavenBom("com.vaadin:vaadin-bom:${Versions.vaadin}")
  }
}

fun ExternalModuleDependency.excludeWebJars() {
  listOf("com.vaadin.webjar", "org.webjars.bowergithub.insites",
         "org.webjars.bowergithub.polymer", "org.webjars.bowergithub.polymerelements",
         "org.webjars.bowergithub.vaadin", "org.webjars.bowergithub.webcomponents")
          .forEach { group -> exclude(group = group) }
}
