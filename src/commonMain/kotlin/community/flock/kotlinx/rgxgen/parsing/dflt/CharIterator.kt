package community.flock.kotlinx.rgxgen.parsing.dflt

import community.flock.kotlinx.rgxgen.util.Util.repeatChar
import kotlin.jvm.JvmOverloads
import kotlin.math.max
import kotlin.math.min

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
 * Class incorporates functionality to iterate String char by char
 */
class CharIterator(private val aValue: String) {
    private var aBoundIndex = aValue.length
    private var aCurrentIndex = 0

    fun hasNext(): Boolean {
        return aCurrentIndex < aBoundIndex
    }

    /**
     * Skip next `n` characters
     *
     * @param n number of characters to skip
     */
    /**
     * Skip next character
     */
    @JvmOverloads
    fun skip(n: Int = 1) {
        aCurrentIndex += n
    }


    /**
     * Return character by offset from the next, without advancing cursor
     *
     * @param offset offset value.
     * @return character by offset from next
     */
    /**
     * Return next character, without advancing cursor
     *
     * @return next character
     */
    @JvmOverloads
    fun peek(offset: Int = 0): Char {
        val pos = aCurrentIndex + offset
        if (pos < 0 || pos >= aBoundIndex) {
            return Char(0x00)
        }
        return aValue[pos]
    }

    /**
     * Returns next character and advances the cursor
     */
    fun next(): Char {
        try {
            val c = aValue[aCurrentIndex]
            aCurrentIndex++
            return c
        } catch (e: Exception) {
            val noSuchElementException = NoSuchElementException(e.message)
            throw noSuchElementException
        }
    }

    /**
     * Return next `length` characters as a substring and advance cursor
     *
     * @param length number of characters to return
     * @return substring start from next of `length` characters
     */
    fun next(length: Int): String {
        var upTo = aCurrentIndex + length
        if (upTo > aBoundIndex) {
            upTo = aBoundIndex
        }
        val substring = aValue.substring(aCurrentIndex, upTo)
        aCurrentIndex = upTo
        return substring
    }

    /**
     * Returns context around passed index
     *
     * @param index center point of context
     * @return substring [-5,+5) chars from index
     */
    /**
     * Returns context of cursor (text around the cursor)
     *
     * @return substring [-5,+5) chars from current position
     */
    @JvmOverloads
    fun context(index: Int = aCurrentIndex - 1): String {
        val start = max(0.0, (index - 5).toDouble()).toInt()
        val end = min(aBoundIndex.toDouble(), (index + 5).toDouble()).toInt()
        val offsetOfPointer = if (start == 0
        ) index
        else 5
        return """
         |
         |'${aValue.substring(start, end)}'
         |${repeatChar(' ', 1 + offsetOfPointer)}^
         """.trimMargin()
    }

    /**
     * Calculate number of characters remaining to iterate over
     *
     * @return num of characters
     */
    fun remaining(): Int {
        return aBoundIndex - aCurrentIndex
    }

    /**
     * Returns substring from 'next' character UP TO first not escaped character `c`
     * Cursor is advanced to a position after character `c`
     *
     *
     * Example:
     * For text `'0123456789'`, `nextUntil('8')` will return `'01234567'` and put cursor before `'9'`
     *
     * @param c character to search for
     * @return substring from next character up to next not escaped character `c`
     * @throws NoSuchElementException if no such character present after next character
     */
    fun nextUntil(c: Char): String {
        return nextUntil({ str: String, fromIdx: Int? -> str.indexOf(c, fromIdx!!) }, 1, true)
    }

    /**
     * Returns substring from next character up to next occurrence of `s`
     * Cursor is advanced to a position after last character in `s`
     *
     *
     * Example:
     * For text `'0123456789'`, `nextUntil("456")` will return `'0123'` and put cursor before `'7'`
     *
     * @param s string to search for
     * @return substring from next character up to next not escaped occurrence of s.
     * if string not found - returns all remaining characters
     */
    fun nextUntil(s: String): String {
        return nextUntil({ str: String, fromIdx: Int? -> str.indexOf(s, fromIdx!!) }, s.length, false)
    }

    private fun nextUntil(indexOf: (String, Int) -> Int, len: Int, mustExist: Boolean): String {
        val startIndex = aCurrentIndex
        val substringEnd: Int
        while (true) {
            // Find ending character
            aCurrentIndex = indexOf(aValue, aCurrentIndex)
            // Found, but outside of the bounds...
            if (aCurrentIndex + len > aBoundIndex) {
                aCurrentIndex = aBoundIndex
                substringEnd = aCurrentIndex
                break
            } else if (aCurrentIndex == -1) {
                // Not present in text
                if (mustExist) {
                    throw NoSuchElementException(
                        "Could not find termination sequence/character in string: '" + aValue.substring(
                            startIndex
                        )
                    )
                } else {
                    aCurrentIndex = aBoundIndex
                    substringEnd = aCurrentIndex
                    break
                }
            }
            var cnt = 1 // One, because we subtract it from aCurrentIndex. Just to avoid extra subtraction
            // Count how many backslashes there are -
            // Even number means that they all are escaped
            // Odd number means that the {@code c} is escaped
            while (aValue[aCurrentIndex - cnt] == '\\') {
                ++cnt
            }

            // initially count was 1, not 0 - we do != comparison
            if (cnt % 2 != 0) {
                substringEnd = aCurrentIndex
                aCurrentIndex += len
                break
            }

            // Otherwise we will find the same {@code c} at same position on next iteration
            ++aCurrentIndex
        }

        return aValue.substring(startIndex, substringEnd)
    }

    /**
     * Create substring starting from next character while `condition` is true
     * Cursor is advanced to the first character which does not match condition
     *
     * @param condition condition to test each character with
     * @return substring of characters that matches condition
     */
    fun takeWhile(condition: (Char) -> Boolean): String {
        val startIndex = aCurrentIndex
        while (hasNext()) {
            if (!condition(next())) {
                --aCurrentIndex
                break
            }
        }

        return aValue.substring(startIndex, aCurrentIndex)
    }

    /**
     * Returns last character that would be iterated over
     *
     * @return last character that would be iterated over
     */
    fun lastChar(): Char {
        return aValue[aBoundIndex - 1]
    }

    /**
     * Move the bound until which iterator will iterate
     *
     * @param offset offset in respect to current bound
     */
    fun modifyBound(offset: Int) {
        aBoundIndex += offset
    }

    /**
     * Return position of last symbol returned by next()
     *
     * @return index
     */
    fun prevPos(): Int {
        return aCurrentIndex - 1
    }

    fun substringToCurrPos(pos: Int): String {
        return aValue.substring(pos, aCurrentIndex)
    }
}
