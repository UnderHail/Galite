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
package org.kopi.galite.demo.stock

import org.jetbrains.exposed.sql.JoinType
import java.util.Locale

import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.kopi.galite.demo.Product
import org.kopi.galite.demo.Provider

import org.kopi.galite.demo.Stock
import org.kopi.galite.domain.INT
import org.kopi.galite.domain.STRING
import org.kopi.galite.form.dsl.Key
import org.kopi.galite.report.FieldAlignment
import org.kopi.galite.report.Report
import org.kopi.galite.report.VReport

/**
 * Stock Report
 */
class StockR : Report() {
  override val locale = Locale.UK

  override val title = "Stocks"

  val action = menu("Action")

  val csv = actor(
          ident = "CSV",
          menu = action,
          label = "CSV",
          help = "CSV Format",
  ) {
    key = Key.F8          // key is optional here
    icon = "exportCsv"  // icon is optional here
  }

  val xls = actor(
          ident = "XLS",
          menu = action,
          label = "XLS",
          help = "Excel (XLS) Format",
  ) {
    key = Key.SHIFT_F8          // key is optional here
    icon = "exportXlsx"  // icon is optional here
  }

  val xlsx = actor(
          ident = "XLSX",
          menu = action,
          label = "XLSX",
          help = "Excel (XLSX) Format",
  ) {
    key = Key.SHIFT_F8          // key is optional here
    icon = "export"  // icon is optional here
  }

  val pdf = actor(
          ident = "PDF",
          menu = action,
          label = "PDF",
          help = "PDF Format",
  ) {
    key = Key.F9          // key is optional here
    icon = "exportPdf"  // icon is optional here
  }

  val cmdCSV = command(item = csv) {
    action = {
      model.export(VReport.TYP_CSV)
    }
  }

  val cmdPDF = command(item = pdf) {
    action = {
      model.export(VReport.TYP_PDF)
    }
  }

  val cmdXLS = command(item = xls) {
    action = {
      model.export(VReport.TYP_XLS)
    }
  }

  val cmdXLSX = command(item = xlsx) {
    action = {
      model.export(VReport.TYP_XLSX)
    }
  }

  val description = field(STRING(25)) {
    label = "Description"
    help = "The product description"
    align = FieldAlignment.LEFT
  }
  val nameProvider = field(STRING(25)) {
    label = "Provider name"
    help = "The provider name"
    align = FieldAlignment.LEFT
  }
  val minAlert = field(INT(25)) {
    label = "Min Alert"
    help = "The stock's min alert"
    align = FieldAlignment.LEFT
  }

  val stocks = Stock.join(Provider, JoinType.INNER, Stock.idStckProv, Provider.idProvider)
          .join(Product, JoinType.INNER, Stock.idStckProv, Product.idPdt)
          .slice(Stock.minAlert, Product.description, Provider.nameProvider)
          .selectAll()

  init {
    transaction {
      stocks.forEach { result ->
        add {
          this[minAlert] = result[Stock.minAlert]
          this[description] = result[Product.description]
          this[nameProvider] = result[Provider.nameProvider]
        }
      }
    }
  }
}
