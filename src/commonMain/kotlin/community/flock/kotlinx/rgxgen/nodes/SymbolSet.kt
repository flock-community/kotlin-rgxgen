package community.flock.kotlinx.rgxgen.nodes

import community.flock.kotlinx.rgxgen.config.RgxGenOption
import community.flock.kotlinx.rgxgen.config.RgxGenProperties
import community.flock.kotlinx.rgxgen.model.MatchType
import community.flock.kotlinx.rgxgen.model.RgxGenCharsDefinition
import community.flock.kotlinx.rgxgen.model.SymbolRange
import community.flock.kotlinx.rgxgen.model.UnicodeCategory
import community.flock.kotlinx.rgxgen.parsing.dflt.ConstantsProvider
import community.flock.kotlinx.rgxgen.util.Util
import community.flock.kotlinx.rgxgen.util.chars.CharList
import community.flock.kotlinx.rgxgen.visitors.NodeVisitor
import community.flock.kotlinx.rgxgen.visitors.helpers.SymbolSetIndexer
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
 * Generate Any printable character.
 */
class SymbolSet(
    pattern: String,
    private val positiveGenerationChars: RgxGenCharsDefinition, val negativeMatchExclusionChars: RgxGenCharsDefinition?,
    type: MatchType, protected val universeCharacters: SymbolRange
) : Node(pattern) {
    private val originalMatchType: MatchType
    @JvmField
    val isAscii: Boolean = universeCharacters === ConstantsProvider.ASCII_SYMBOL_RANGE
    @JvmField
    var symbolRanges: MutableList<SymbolRange> = mutableListOf()
    @JvmField
    var symbols: CharList = CharList.empty()
    var symbolSetIndexer: SymbolSetIndexer? = null
        get() {
            if (field == null) {
                field = SymbolSetIndexer(this)
            }
            return field
        }
        private set
    var caseInsensitiveSymbolSetIndexer: SymbolSetIndexer? = null
        get() {
            if (field == null) {
                val caseInsensitiveSymbols = positiveGenerationChars.characters.copy()
                val characters = positiveGenerationChars.characters
                for (i in 0 until characters.size()) {
                    addIfChangedCase(caseInsensitiveSymbols, characters[i])
                }
                for (originalSymbolRange in positiveGenerationChars.rangeList) {
                    for (c in originalSymbolRange.from.toChar().code..originalSymbolRange.to) {
                        addIfChangedCase(caseInsensitiveSymbols, c.toChar())
                    }
                }
                field = SymbolSetIndexer(
                    SymbolSet(
                        pattern,
                        positiveGenerationChars.rangeList,
                        caseInsensitiveSymbols,
                        originalMatchType,
                        universeCharacters
                    )
                )
            }
            return field
        }
        private set

    constructor(
        pattern: String,
        symbolRanges: List<SymbolRange>?,
        symbols: CharList?,
        type: MatchType,
        universeCharacters: SymbolRange
    ) : this(pattern, RgxGenCharsDefinition.of(symbolRanges, symbols!!), null, type, universeCharacters)

    /**
     * Create SymbolSet from ranges and symbols according to type
     *
     * @param pattern                 original pattern for the reference
     * @param positiveGenerationChars characters to generate when `type` is POSITIVE
     * @param negativeMatchExclusion  characters to exclude when `type` is NEGATIVE; null = use same as `positiveGenerationChars`
     * @param type                    POSITIVE - include, NEGATIVE - exclude
     * @param universeCharacters      characters to exclude from when `type` is NEGATIVE
     */
    init {
        if (type == MatchType.POSITIVE) {
            val compactedRanges: MutableList<SymbolRange> = ArrayList(
                positiveGenerationChars.rangeList.size
            )
            val compactedCharacters = CharList.ofCapacity(positiveGenerationChars.characters.size())
            Util.compactOverlappingRangesAndSymbols(
                positiveGenerationChars.rangeList,
                positiveGenerationChars.characters,
                compactedRanges,
                compactedCharacters
            )
            symbolRanges = compactedRanges
            symbols = compactedCharacters
        } else {
            symbolRanges = ArrayList()
            symbols = CharList.empty()
            val defsToUse = negativeMatchExclusionChars ?: positiveGenerationChars
            val compactedRanges: MutableList<SymbolRange> = ArrayList(defsToUse.rangeList.size)
            val compactedCharacters = CharList.ofCapacity(defsToUse.characters.size())
            Util.compactOverlappingRangesAndSymbols(
                defsToUse.rangeList,
                defsToUse.characters,
                compactedRanges,
                compactedCharacters
            )
            Util.invertSymbolsAndRanges(
                compactedRanges,
                compactedCharacters,
                universeCharacters,
                symbolRanges,
                symbols
            )
        }
        originalMatchType = type
    }

    val invertedNode: SymbolSet
        get() = if (isAscii) {
            ofAscii("[^" + pattern.substring(1), symbolRanges, symbols, MatchType.NEGATIVE)
        } else {
            ofUnicode("[^" + pattern.substring(1), symbolRanges, symbols, MatchType.NEGATIVE)
        }

    override fun visit(visitor: NodeVisitor) {
        visitor.visit(this)
    }

    override fun toString(): String {
        return "SymbolSet{" +
                "originalMatchType=" + originalMatchType +
                ", positiveGenerationChars=" + positiveGenerationChars +
                ", negativeMatchExclusion=" + negativeMatchExclusionChars +
                ", isAscii=" + isAscii +
                ", symbolRanges=" + symbolRanges +
                ", symbols=" + symbols +
                "} "
    }

    fun hasModifiedExclusionChars(): Boolean {
        return negativeMatchExclusionChars != null
    }

    companion object {
        @JvmStatic
        fun ofDotPattern(properties: RgxGenProperties<*>?): SymbolSet {
            val charsDefinition = RgxGenOption.DOT_MATCHES_ONLY.getFromProperties(properties)
            if (charsDefinition != null) {
                val isAscii = charsDefinition.isAsciiOnly
                return if (isAscii) {
                    ofAscii(".", charsDefinition.rangeList, charsDefinition.characters, MatchType.POSITIVE)
                } else {
                    ofUnicode(".", charsDefinition.rangeList, charsDefinition.characters, MatchType.POSITIVE)
                }
            } else {
                return ofAscii(".", listOf(ConstantsProvider.ASCII_SYMBOL_RANGE), CharList.empty(), MatchType.POSITIVE)
            }
        }

        @JvmStatic
        fun ofAsciiCharacters(pattern: String, symbols: CharArray, type: MatchType?): SymbolSet {
            return SymbolSet(
                pattern,
                emptyList(),
                CharList.charList(*symbols),
                type!!,
                ConstantsProvider.ASCII_SYMBOL_RANGE
            )
        }

        @JvmStatic
        fun ofUnicodeCharacterClass(pattern: String, unicodeCategory: UnicodeCategory, type: MatchType?): SymbolSet {
            return SymbolSet(
                pattern,
                unicodeCategory.symbolRanges,
                CharList.charList(*unicodeCategory.symbols),
                type!!,
                ConstantsProvider.UNICODE_SYMBOL_RANGE
            )
        }

        @JvmStatic
        fun ofUnicode(
            pattern: String,
            symbolRanges: List<SymbolRange>?,
            symbols: CharList?,
            matchType: MatchType
        ): SymbolSet {
            return SymbolSet(pattern, symbolRanges, symbols, matchType, ConstantsProvider.UNICODE_SYMBOL_RANGE)
        }

        @JvmStatic
        fun ofUnicode(
            pattern: String,
            positiveMatchDefinitions: RgxGenCharsDefinition,
            negativeMatchDefinitions: RgxGenCharsDefinition?,
            matchType: MatchType
        ): SymbolSet {
            return SymbolSet(
                pattern,
                positiveMatchDefinitions,
                negativeMatchDefinitions,
                matchType,
                ConstantsProvider.UNICODE_SYMBOL_RANGE
            )
        }

        @JvmStatic
        fun ofAscii(
            pattern: String,
            symbolRanges: List<SymbolRange>?,
            charList: CharList?,
            type: MatchType
        ): SymbolSet {
            return SymbolSet(pattern, symbolRanges, charList, type, ConstantsProvider.ASCII_SYMBOL_RANGE)
        }

        @JvmStatic
        fun ofAsciiRanges(pattern: String, symbolRanges: List<SymbolRange>?, type: MatchType): SymbolSet {
            return SymbolSet(pattern, symbolRanges, CharList.empty(), type, ConstantsProvider.ASCII_SYMBOL_RANGE)
        }

        @JvmStatic
        fun ofAscii(
            pattern: String,
            positiveMatchDefinitions: RgxGenCharsDefinition,
            negativeMatchDefinitions: RgxGenCharsDefinition?,
            matchType: MatchType
        ): SymbolSet {
            return SymbolSet(
                pattern,
                positiveMatchDefinitions,
                negativeMatchDefinitions,
                matchType,
                ConstantsProvider.ASCII_SYMBOL_RANGE
            )
        }

        private fun addIfChangedCase(caseInsensitiveSymbols: CharList, c: Char) {
            if (c.isUpperCase()) {
                caseInsensitiveSymbols.add(c.lowercaseChar().code)
            } else if (c.isLowerCase()) {
                caseInsensitiveSymbols.add(c.uppercaseChar().code)
            }
        }
    }
}
