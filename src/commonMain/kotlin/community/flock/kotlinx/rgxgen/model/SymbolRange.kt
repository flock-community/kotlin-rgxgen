package community.flock.kotlinx.rgxgen.model

import community.flock.kotlinx.rgxgen.util.chars.CharList

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
 * Range of symbols
 */
class SymbolRange private constructor(val from: Int, val to: Int) {
    fun size(): Int {
        return to - from + 1
    }

    fun chars(): CharList {
        return CharList.rangeClosed(from, to)
    }

    fun contains(c: Int): Boolean {
        return from <= c && c <= to
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other == null || javaClass != other.javaClass) {
            return false
        }

        val range = other as SymbolRange

        if (from != range.from) {
            return false
        }
        return to == range.to
    }

    override fun hashCode(): Int {
        var result = from
        result = 31 * result + to
        return result
    }

    override fun toString(): String {
        return "SymbolRange{" +
                "from=" + from +
                ", to=" + to +
                '}'
    }

    companion object {
        /**
         * Create inclusive range of symbols.
         *
         * @param from min character; shall be less than `to`
         * @param to   max character; shall be greater than `from`
         * @apiNote No verifications are done!
         */
        @JvmStatic
        fun range(from: Int, to: Int): SymbolRange {
            return SymbolRange(from, to)
        }

        /**
         * Create inclusive range of symbols.
         *
         * @param from min character; shall be less than `to`
         * @param to   max character; shall be greater than `from`
         * @apiNote No verifications are done!
         */
        @JvmStatic
        fun range(from: Char, to: Char): SymbolRange {
            return range(from.code, to.code)
        }
    }
}
