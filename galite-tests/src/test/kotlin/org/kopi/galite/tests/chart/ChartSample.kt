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
package org.kopi.galite.tests.chart

import org.kopi.galite.chart.Chart
import org.kopi.galite.domain.Domain
import java.util.Locale

object ChartSample: Chart()  {
  override val locale = Locale.FRANCE
  override val title = "area/population per city"

  val area = measure(Domain<Int>(10)) {
    label = "area (ha)"
  }

  val population = measure(Domain<Int>(10)) {
    label = "population"
  }

  val city = dimension(Domain<String>(10)) {
    label = "dimension"
  }

  init {
    city.add("Tunis") {
      this[area] = 34600
      this[population] = 1056247
    }

    city.add("Kasserine") {
      this[area] = 806600
      this[population] = 439243
    }

    city.add("Bizerte") {
      this[population] = 368500
      this[area] = 568219
    }
  }
}
