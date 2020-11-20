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
package org.kopi.galite.form.dsl

import org.kopi.galite.form.BlockAlignment

/**
 * This class describe the alignment of multi blocks
 *
 * This class describe the alignment of multi blocks
 *
 * @param block                 block alignment
 * @param target                the target column vector
 * @param source                the source column vector
 */
class FormBlockAlign(private val block: FormBlock,
                     private val target: MutableSet<Int>,
                     private val source: MutableSet<Int>) {

  fun genTargetPositions() : IntArray {
    val columnCount: Int = block.blockFields.size
    val target : ArrayList<Int>? = null
    var pos = 0

    for (i in 0 until columnCount) {
      if (source.elementAt(pos) != i + 1) {
        target!!.add(-1)
      } else {
        target!!.add(target[pos] - 1)
        pos += 1
      }
    }
    return target!!.toTypedArray().toIntArray()
  }

  fun getBlockAlignModel() : BlockAlignment = BlockAlignment(block.vBlock, genTargetPositions())
}
