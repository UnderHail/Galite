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

package org.kopi.galite.domain

/**
 * A domain is used to specify the type of values that a [Field] can hold. It allow to specify
 * the set of values that a [Field] can hold. You can also do some checks on these values.
 *
 * @param length the maximum length of the value that can be passed
 */
open class Domain<T: Comparable<T>>(val length: Int? = null) {

    /** Override it if you want to check values when they are passed. */
    open val check: ((value: T) -> Boolean)? = null
}
