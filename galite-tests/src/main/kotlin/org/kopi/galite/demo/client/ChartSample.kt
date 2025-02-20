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
package org.kopi.galite.demo.client

import java.util.Locale

import org.kopi.galite.chart.Chart
import org.kopi.galite.chart.VChartType
import org.kopi.galite.chart.VColumnFormat
import org.kopi.galite.domain.DECIMAL
import org.kopi.galite.domain.INT
import org.kopi.galite.domain.STRING
import org.kopi.galite.type.Decimal
import org.kopi.galite.visual.VColor

class ChartSample: Chart() {
  override val locale = Locale.UK
  override val title = "Area/population per city"
  override val help = "This chart presents the area/population per city"

  val action = menu("Action")

  val area = measure(DECIMAL(width = 10, scale = 5)) {
    label = "area (ha)"

    color {
      VColor.GREEN
    }
  }

  val population = measure(INT(10)) {
    label = "population"
  }

  val city = dimension(STRING(10)) {
    label = "dimension"

    format {
      object : VColumnFormat() {
        override fun format(value: Any?): String {
          return (value as String).toUpperCase()
        }
      }
    }
  }

  // You can either change the chart type in INIT or CHARTTYPE trigger
  val init = trigger(INITCHART) {
    chartType = VChartType.BAR
  }

  // This is the type that will be taken because CHARTTYPE is executed after INIT
  val type = trigger(CHARTTYPE) {
    VChartType.BAR
  }

  init {
    city.add("Tunis") {
      this[area] = Decimal("34600")
      this[population] = 1056247
    }

    city.add("Kasserine") {
      this[area] = Decimal("806600")
      this[population] = 439243
    }

    city.add("Bizerte") {
      this[area] = Decimal("568219")
      this[population] = 368500
    }
  }
}
