package community.flock.kotlinx.rgxgen.parsing.dflt

import community.flock.kotlinx.rgxgen.model.SymbolRange
import community.flock.kotlinx.rgxgen.model.SymbolRange.Companion.range
import community.flock.kotlinx.rgxgen.nodes.Node
import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic

/* **************************************************************************
  Copyright 2019 Vladislavs Varslavans

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
/ * **************************************************************************/


/**
 * Helper class for lazy initialization and reuse of some constants that are re-used.
 * Use with caution - don't modify values inside those!!!
 */
object ConstantsProvider {
    @JvmField
    val SMALL_LATIN_LETTERS: SymbolRange = range('a', 'z')
    @JvmField
    val CAPITAL_LATIN_LETTERS: SymbolRange = range('A', 'Z')
    @JvmField
    val DIGITS: SymbolRange = range('0', '9')
    @JvmField
    val ZERO_LENGTH_CHARACTER_ARRAY: CharArray = CharArray(0)
    const val LONG_ZERO: Long = 0L
    const val LONG_ONE: Long = 1L
    const val LONG_TWO: Long = 2L
    const val SPACE_ASCII_CODE: Int = 32 // First printable character in ASCII table
    const val DEL_ASCII_CODE: Int = 127 // Bound for printable characters in ASCII table
    const val MAX_UNICODE_CHARACTER: Int =
        0xD800 - 1 // The start of IN_HIGH_SURROGATES - which cause errors when trying to write those values to file
    @JvmField
    val ASCII_SYMBOL_RANGE: SymbolRange =
        range(SPACE_ASCII_CODE, DEL_ASCII_CODE - 1) // -1 because we exclude DEL symbol
    @JvmField
    val UNICODE_SYMBOL_RANGE: SymbolRange = range(SPACE_ASCII_CODE, MAX_UNICODE_CHARACTER)
    const val HEX_RADIX: Int = 16
    val EMPTY_NODES_ARR: Array<Node?> = arrayOfNulls(0)

    val ASCII_DIGITS: SymbolRange = range('0', '9')

    val asciiWhitespaces: CharArray
        get() = charArrayOf('\t', '\n', '\u000B', '\u000C', '\r', ' ')

    val asciiWordCharRanges: List<SymbolRange>
        get() = listOf(SMALL_LATIN_LETTERS, CAPITAL_LATIN_LETTERS, DIGITS)

    @JvmStatic
    fun makeAsciiCharacterArray(): CharArray {
        val characters = CharArray(DEL_ASCII_CODE - SPACE_ASCII_CODE)
        for (i in SPACE_ASCII_CODE until DEL_ASCII_CODE) {
            characters[i - SPACE_ASCII_CODE] = i.toChar()
        }
        return characters
    }
}
