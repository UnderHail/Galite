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
package org.kopi.galite.ui.vaadin.form

import com.vaadin.flow.component.Component
import com.vaadin.flow.component.html.Div

/**
 * The form component.
 */
class Form(pageCount: Int, titles: Array<String?>) : Div() {

  /**
   * Adds a block to this form.
   * @param block The block to be added.
   * @param page The page index.
   * @param isFollow Is it a follow block ?
   * @param isChart Is it a chart block ?
   */
  fun addBlock(block: Component?, page: Int, isFollow: Boolean, isChart: Boolean) {
    // TODO
  }
}
