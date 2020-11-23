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

package org.kopi.galite.form.dsl

import org.kopi.galite.common.*
import org.kopi.galite.form.VConstants
import org.kopi.galite.form.VForm
import java.io.File
import java.io.IOException

/**
 * Represents a form.
 */
abstract class Form : Window() {
  val _tokenSet_3_data_ = longArrayOf(0L, 90107314362647552L, 0L, 0L)

  /** Form's actors. */
  val actors = mutableListOf<Actor>()

  /** Form's blocks. */
  val formBlocks = mutableListOf<FormBlock>()

  /** Form's pages. */
  val pages = mutableListOf<FormPage>()

  /** the help text TODO: Move to super class */
  var help: String? = null

  /** Form's triggers. */
  var formTriggers = mutableListOf<Trigger>()

  /**
   * Adds a new actor to this form.
   *
   * @param menu                 the containing menu
   * @param label                the label
   * @param help                 the help
   */
  fun actor(menu: String, label: String, help: String, init: Actor.() -> Unit): Actor {
    val actor = Actor(menu, label, help)
    actor.init()
    actors.add(actor)
    return actor
  }

  /**
   * Adds a new block to this form.
   *
   * @param        buffer                 the buffer size of this block
   * @param        visible                the number of visible elements
   * @param        name                   the simple identifier of this block
   * @param        title                  the title of the block
   */
  fun block(buffer: Int, visible: Int, name: String, title: String, init: FormBlock.() -> Unit): FormBlock {
    val block = FormBlock(buffer, visible, name, title)
    block.init()
    block.initialize(this)
    formBlocks.add(block)
    return block
  }

  /**
   * Adds a new page to this form.
   *
   * @param        title                the title of the page
   */
  fun page(title: String, init: FormPage.() -> Unit): FormPage {
    val page = FormPage("Id\$${pages.size}", title)
    page.init()
    pages.add(page)
    return page
  }

  fun trigger(event: Int, index: Int, action: Action, init: Trigger.() -> Unit): Trigger {
   val trigger = Trigger(event, index, action)
   trigger.init()
    formTriggers.add(trigger)
   return trigger
  }

   fun init(initTrigger: Trigger. () -> Unit) : Trigger {
    val trigger = trigger(VConstants.TRG_INIT, 4, Action(initTrigger),initTrigger)
    formTriggers.add(trigger)
     return trigger
  }

   fun preform(preformTrigger: Trigger. () -> Unit) : Trigger {
    val trigger = trigger(VConstants.TRG_PREFORM, 5, Action(preformTrigger),preformTrigger)
    formTriggers.add(trigger)
     return trigger
  }

   fun postform(postformTrigger: Trigger. () -> Unit) : Trigger {
    val trigger = trigger(VConstants.TRG_POSTFORM, 5, Action(postformTrigger),postformTrigger)
    formTriggers.add(trigger)
     return trigger
  }

   fun reset(resetTrigger: Trigger.() -> Unit) : Trigger {
    val trigger = trigger(VConstants.TRG_RESET, 5, Action(resetTrigger),resetTrigger)
    formTriggers.add(trigger)
     return trigger
  }

   fun quit(quitTrigger: Trigger. () -> Unit) :Trigger {
    val trigger = trigger(VConstants.TRG_QUITFORM, 5, Action(quitTrigger),quitTrigger)
    formTriggers.add(trigger)
     return trigger
  }


  // ----------------------------------------------------------------------
  // ACCESSORS
  // ----------------------------------------------------------------------

  /**
   * Get block
   */
  open fun getFormElement(ident: String?): FormElement? {
    formBlocks.forEach { formBlock ->
      if (formBlock.ident == ident || formBlock.shortcut == ident) {
        return formBlock
      }
    }
    return null
  }

  // ----------------------------------------------------------------------
  // XML LOCALIZATION GENERATION
  // ----------------------------------------------------------------------

  fun genLocalization(destination: String? = null) {
    if (locale != null) {
      val baseName = this::class.simpleName
      requireNotNull(baseName)
      val destination = destination
              ?: this.javaClass.classLoader.getResource("")?.path +
              this.javaClass.packageName.replace(".", "/")
      try {
        val writer = FormLocalizationWriter()
        genLocalization(writer)
        writer.write(destination, baseName, locale!!)
      } catch (ioe: IOException) {
        ioe.printStackTrace()
        System.err.println("cannot write : $baseName")
      }
    }
  }

  fun genLocalization(writer: LocalizationWriter) {
    (writer as FormLocalizationWriter).genForm(title,
            pages.toTypedArray(),
            formBlocks.toTypedArray()
    )
  }

  /**
   * Returns the qualified source file name where this object is defined.
   */
  private val sourceFile: String
    get() {
      val basename = this.javaClass.packageName.replace(".", "/") + File.separatorChar
      return basename + this.javaClass.simpleName
    }

  /** Form model */
  override val model: VForm by lazy {
    genLocalization()

    object : VForm() {
      override fun init() {
        source = sourceFile
        pages = this@Form.pages.map {
          it.ident
        }.toTypedArray()
        blocks = formBlocks.map { formBlock ->
          formBlock.getBlockModel(this, source).also { vBlock ->
            vBlock.setInfo(formBlock.pageNumber)
          }
        }.toTypedArray()

        //TODO ----------begin-------------
        super.commands = arrayOf()
        var i : Int = 0
        VKT_Triggers = Array(5) { IntArray(36) }
    //    if( hasTrigger(it.event, it.index))

        formTriggers.forEach {

            VKT_Triggers[it.index][it.event]= it.index
              i++
        }

      }

    }


  }
  /*
  private fun vkFormTriggers(
          context: VKParseFormContext
  ) {
    var e: Long
    var t: VKAction?
    val sourceRef: TokenReference = buildTokenReference() // !!! add comments;
    run {
      var _cnt136 = 0
      _loop136@ do {
        if (org.kopi.vkopi.comp.form.FormParser._tokenSet_3.member(LA(1))) {
          e = vkFormEventList()
          t = vkTriggerAction()
          context.addTrigger(VKTrigger(sourceRef, e, t))
        } else {
          if (_cnt136 >= 1) {
            break@_loop136
          } else {
            throw NoViableAltException(LT(1), getFilename())
          }
        }
        _cnt136++
      } while (true)
    }
  }*/
}
