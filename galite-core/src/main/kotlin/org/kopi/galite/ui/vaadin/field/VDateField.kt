/*
 * Copyright (c) 2013-2020 kopiLeft Services SARL, Tunis TN
 * Copyright (c) 1990-2020 kopiRight Managed Solutions GmbH, Wien AT
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
package org.kopi.galite.ui.vaadin.field

import java.time.LocalDate
import java.time.format.DateTimeFormatter

import com.vaadin.flow.component.KeyNotifier
import com.vaadin.flow.component.datepicker.DatePicker
import com.vaadin.flow.component.dependency.CssImport

/**
 * An Date field.
 */
@CssImport(value = "./styles/galite/datetime.css", themeFor = "vaadin-date-picker-text-field")
class VDateField : InputTextField<DatePicker>(DatePicker()), KeyNotifier {

  init {
    internalField.isClearButtonVisible = true
    internalField.isAutoOpen = false
  }

  override fun setPresentationValue(newPresentationValue: String?) {
    content.value = if(newPresentationValue != null && newPresentationValue.isNotEmpty()) {
      LocalDate.parse(newPresentationValue, DateTimeFormatter.ofPattern("dd.MM.yyyy"))
    } else {
      null
    }
  }
}
