package community.flock.kotlinx.rgxgen.model

import community.flock.kotlinx.rgxgen.model.SymbolRange
import community.flock.kotlinx.rgxgen.parsing.dflt.ConstantsProvider
import community.flock.kotlinx.rgxgen.util.Util.compactOverlappingRangesAndSymbols
import community.flock.kotlinx.rgxgen.util.chars.CharList
import community.flock.kotlinx.rgxgen.util.chars.CharList.Companion.charList
import community.flock.kotlinx.rgxgen.util.chars.CharList.Companion.empty
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


class RgxGenCharsDefinition private constructor(rangeList: List<SymbolRange>?, characters: CharList) {
    val rangeList: MutableList<SymbolRange> = rangeList.orEmpty().toMutableList()
    val characters: CharList = characters.copy()

    fun withRanges(vararg ranges: SymbolRange): RgxGenCharsDefinition {
        rangeList.addAll(ranges.toList())
        return this
    }

    fun withRanges(ranges: List<SymbolRange>?): RgxGenCharsDefinition {
        rangeList.addAll(ranges!!)
        return this
    }

    fun withCharacters(vararg characters: Char): RgxGenCharsDefinition {
        this.characters.addAll(characters)
        return this
    }

    fun withCharacters(characters: CharList?): RgxGenCharsDefinition {
        this.characters.addAll(characters!!)
        return this
    }

    val isAsciiOnly: Boolean
        get() = (rangeList.map(SymbolRange::to) + characters.list().map { c: Char -> c.code })
            .none { i: Int -> i >= ConstantsProvider.DEL_ASCII_CODE }

    fun addAll(other: RgxGenCharsDefinition) {
        rangeList.addAll(other.rangeList)
        characters.addAll(other.characters)
    }

    override fun toString(): String {
        return "RgxGenCharsDefinition{" +
                "rangeList=" + rangeList +
                ", characters=" + characters +
                '}'
    }

    companion object {
        fun of(externalRanges: List<SymbolRange>?): RgxGenCharsDefinition {
            return of(externalRanges, empty())
        }

        @JvmStatic
        fun of(category: UnicodeCategory): RgxGenCharsDefinition {
            return RgxGenCharsDefinition(category.symbolRanges, charList(*category.symbols))
        }

        fun of(vararg ranges: SymbolRange): RgxGenCharsDefinition {
            return RgxGenCharsDefinition(ranges.toList(), empty())
        }

        @JvmStatic
        fun of(vararg characters: Char): RgxGenCharsDefinition {
            return RgxGenCharsDefinition(emptyList(), charList(*characters))
        }

        @JvmStatic
        fun of(characterString: String?): RgxGenCharsDefinition {
            val characterList = charList(characterString!!)
            val compactedRanges: MutableList<SymbolRange> = ArrayList()
            val compactedSymbols = empty()
            compactOverlappingRangesAndSymbols(emptyList(), characterList, compactedRanges, compactedSymbols)
            return RgxGenCharsDefinition(compactedRanges, compactedSymbols)
        }

        fun of(charList: CharList?): RgxGenCharsDefinition {
            return of(emptyList(), charList!!)
        }

        fun of(symbolRanges: List<SymbolRange>?, symbols: CharList): RgxGenCharsDefinition {
            return RgxGenCharsDefinition(symbolRanges, symbols)
        }

        fun of(other: RgxGenCharsDefinition): RgxGenCharsDefinition {
            return of(other.rangeList, other.characters)
        }
    }
}
