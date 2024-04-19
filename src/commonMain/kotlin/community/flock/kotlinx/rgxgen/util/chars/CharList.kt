package community.flock.kotlinx.rgxgen.util.chars

import community.flock.kotlinx.rgxgen.model.SymbolRange
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


abstract class CharList {
    abstract fun copy(): CharList

    abstract fun add(c: Int)

    abstract fun list(): List<Char>

    abstract fun addAll(originalSymbols: CharList)

    abstract fun addAll(characters: CharArray)

    abstract fun addAll(srcArr: CharArray?, srcLength: Int)

    abstract fun size(): Int

    abstract operator fun get(index: Int): Char

    abstract val isEmpty: Boolean

    abstract fun sort()

    abstract fun except(predicate: CharPredicate): CharList

    abstract fun contains(i: Char): Boolean

    /**
     * Add elements of this list into the targetList
     *
     * @param targetList list that should be appended with elements of this list
     */
    abstract fun appendTo(targetList: CharList)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other !is CharList) {
            return false
        }

        val that = other

        if (size() != that.size()) {
            return false
        }

        for (i in 0 until size()) {
            if (get(i) != that.get(i)) {
                return false
            }
        }

        return true
    }

    override fun hashCode(): Int {
        var result = 1
        for (i in 0 until size()) {
            result = 31 * result + get(i).code
        }

        result = 31 * result + size()
        return result
    }

    companion object {
        @JvmStatic
        fun rangeClosed(from: Int, to: Int): CharList {
            val arr = CharArray(to - from + 1)
            for (i in arr.indices) {
                arr[i] = (from + i).toChar()
            }
            return CharArrayList(arr)
        }

        @JvmStatic
        fun charList(symbolRanges: List<SymbolRange>, vararg symbols: Char): CharList {
            val size = symbols.size + symbolRanges.map { obj: SymbolRange -> obj.size() }.sum()
            val arr = CharArray(size)
            symbols.copyInto(arr, 0, 0, symbols.size)
            var index = symbols.size
            for (symbolRange in symbolRanges) {
                for (i in symbolRange.from..symbolRange.to) {
                    arr[index] = i.toChar()
                    index += 1
                }
            }
            return CharArrayList(arr)
        }

        @JvmStatic
        fun charList(vararg chars: Char): CharList {
            return CharArrayList(chars)
        }

        fun ofCapacity(size: Int): CharList {
            return CharArrayList(size)
        }

        @JvmStatic
        fun charList(characterString: String): CharList {
            return CharArrayList(characterString.toCharArray())
        }

        @JvmStatic
        fun emptyUnmodifiable(): CharList {
            return EmptyUnmodifiableCharList()
        }

        @JvmStatic
        fun empty(): CharList {
            return CharArrayList(10)
        }
    }
}
