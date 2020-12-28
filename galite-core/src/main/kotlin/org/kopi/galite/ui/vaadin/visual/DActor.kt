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
package org.kopi.galite.ui.vaadin.visual

import org.kopi.galite.ui.vaadin.actor.Actor
import org.kopi.galite.ui.vaadin.base.Utils
import org.kopi.galite.visual.UActor
import org.kopi.galite.visual.VActor
import java.awt.event.KeyEvent

/**
 * The `DActor` is the vaadin implementation of
 * the [UActor]. The actor can be represented by a [Button]
 * if it has a valid icon name.
 *
 * The actor action is handled by a [ShortcutListener] registered
 * of the [DWindow] which is the receiver of all actors actions.
 *
 * @param model The actor model.

 *
 */
class DActor(private var model: VActor) : Actor(model.menuItem,
                                                Utils.createTooltip(getDescription(model)),
                                                model.menuName,
                                                Utils.getFontAwesomeIcon(model.iconName),
                                                correctAcceleratorKey(model.acceleratorKey),
                                                correctAcceleratorModifier(model.acceleratorModifier)), UActor {
  // --------------------------------------------------
  // ACCESSORS
  // --------------------------------------------------
  override fun setModel(model: VActor) {
    this.model = model
  }

  override fun getModel(): VActor {
    return model
  }

  override fun setEnabled(enabled: Boolean) {
    TODO()
  }

  companion object {
    // --------------------------------------------------
    // PRIVATE METHODS
    // --------------------------------------------------
    /**
     * Creates the actor description.
     * @param model The actor model.
     * @return The actor description.
     */
    private fun getDescription(model: VActor): String {
      return if (model.acceleratorKey > 0) {
        if (model.acceleratorModifier == 0) {
          model.help.toString() + " [" + KeyEvent.getKeyText(model.acceleratorKey) + "]"
        } else {
          model.help.toString() + " [" + KeyEvent.getKeyModifiersText(
                  model.acceleratorModifier) + "-" + KeyEvent.getKeyText(model.acceleratorKey) + "]"
        }
      } else {
        model.help!!
      }
    }

    /**
     * Returns the corrected accelerator key.
     * @param acceleratorKey The original accelerator key.
     * @return The corrected accelerator key.
     */
    private fun correctAcceleratorKey(acceleratorKey: Int): Int {
      return if (acceleratorKey == 10) 13 else acceleratorKey
    }

    /**
     * Returns the corrected modifier accelerator key.
     * @param acceleratorModifier The original modifier accelerator key.
     * @return The corrected modifier accelerator key.
     */
    private fun correctAcceleratorModifier(acceleratorModifier: Int): Int {
      TODO()
    }
  }
}
