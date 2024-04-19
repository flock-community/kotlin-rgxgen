package community.flock.kotlinx.rgxgen.util

import community.flock.kotlinx.rgxgen.model.SymbolRange
import community.flock.kotlinx.rgxgen.model.SymbolRange.Companion.range
import community.flock.kotlinx.rgxgen.parsing.dflt.ConstantsProvider
import community.flock.kotlinx.rgxgen.util.chars.CharList
import kotlin.jvm.JvmStatic
import kotlin.math.pow
import kotlin.random.Random

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

object Util {
    /**
     * Repeats text multiple times
     *
     * @param c     character to repeat
     * @param times number of times. Values less or equal to zero will result in empty string
     * @return text repeated multiple times
     */
    @JvmStatic
    fun repeatChar(c: Char, times: Int): String {
        if (times < 0) {
            return ""
        }
        val result = CharArray(times)
        result.fill(c)

        return result.concatToString()
    }

    /**
     * Randomly change case for the letters in a string
     *
     * @param rnd   random to be used
     * @param input input string to randomize
     * @return string with random characters changed case.
     */
    @JvmStatic
    fun randomlyChangeCase(rnd: Random, input: String): String {
        val sb = StringBuilder(input)
        for (i in 0 until sb.length) {
            val currentChar = sb[i]
            if (currentChar.isUpperCase() && rnd.nextBoolean()) {
                sb.set(i, currentChar.lowercaseChar())
            } else if (currentChar.isLowerCase() && rnd.nextBoolean()) {
                sb.set(i, currentChar.uppercaseChar())
            }
        }

        return sb.toString()
    }

    /**
     * Count number of variation of words in case insensitive manner.
     * For example for word "a" - there are 2 variation ("a" and "A").
     * For word "1a" - there are also 2 variations ("1a" and "1A")
     * For word "AB" - there are 4 variations: ("ab", "aB", "Ab", "BB")
     *
     * @param value word to calculate variations
     * @return number of variations.
     */
    @JvmStatic
    fun countCaseInsensitiveVariations(value: String): Long {
        val switchableCase = value.toList()
            .map { c -> if (c.isUpperCase() || c.isLowerCase()) 1 else 0 }
            .sum()
        return ConstantsProvider.LONG_TWO.pow(switchableCase)
    }

    /**
     * Finds next case sensitive character.
     * Case sensitive character is either lower-case or upper-case character.
     *
     * @param text       text to be analyzed
     * @param startIndex start search from index.
     * @return index of next case sensitive character or `empty` if no such character present
     */
    @JvmStatic
    fun indexOfNextCaseSensitiveCharacter(text: CharSequence, startIndex: Int): Int? {
        for (i in startIndex until text.length) {
            val c = text[i]
            if (c.isLowerCase() || c.isUpperCase()) {
                return i
            }
        }
        return null
    }

    /**
     * Method creates variation by replacing characterToReplace with all allowedReplacements (one at a time)
     *
     * @param originalTexts       texts from which variation are to be made
     * @param characterToReplace  make variations by replacing this character in originalText
     * @param allowedReplacements replace characterToReplace with each of these characters
     * @return all unique variations
     */
    @JvmStatic
    fun makeVariations(
        originalTexts: List<String>,
        characterToReplace: Char,
        vararg allowedReplacements: Char
    ): Set<String> {
        val result: MutableSet<String> = HashSet()
        for (originalText in originalTexts) {
            result.add(originalText)
            for (replacement in allowedReplacements) {
                result.add(originalText.replace(characterToReplace, replacement))
            }
        }
        return result
    }

    /**
     * In terms of sets = invertedRanges and invertedCharacters = allCharacterRange - symbols and symbolRanges
     * i.e. in invertedRanges and invertedCharacters are all characters that are not in symbols and symbolRanges
     *
     * @param symbolRanges
     * @param symbols
     * @param allCharactersRange
     * @param invertedRanges
     * @param invertedCharacters
     */
    @JvmStatic
    fun invertSymbolsAndRanges(
        symbolRanges: List<SymbolRange>,
        symbols: CharList,
        allCharactersRange: SymbolRange,
        invertedRanges: MutableList<SymbolRange>,
        invertedCharacters: CharList
    ) {
        val firstCharInRange = allCharactersRange.from
        val lastCharInRange = allCharactersRange.to

        val sortedRanges = getApplicableSortedUniqueRanges(symbolRanges, symbols, allCharactersRange)

        var start = firstCharInRange
        for (range in sortedRanges) {
            val from = range.from
            val to = range.to

            if (start <= from) {
                if (start + 1 == from) {
                    invertedCharacters.add(start)
                } else if (start != from) {
                    invertedRanges.add(range(start, from - 1))
                }
            }
            start = to + 1
            if (start > lastCharInRange) {
                return
            }
        }

        if (start < lastCharInRange) {
            invertedRanges.add(range(start, lastCharInRange))
        } else if (start == lastCharInRange) {
            invertedCharacters.add(start)
        }
    }

    private fun getApplicableSortedUniqueRanges(
        symbolRanges: List<SymbolRange>,
        symbols: CharList,
        allowedRange: SymbolRange
    ): List<SymbolRange> {
        val firstCharInRange = allowedRange.from
        val lastCharInRange = allowedRange.to
        val list: MutableList<SymbolRange> = ArrayList(symbolRanges.size + symbolRanges.size)

        symbolRanges
            .filter { range: SymbolRange -> range.to >= firstCharInRange && range.from <= lastCharInRange }
            .forEach { e: SymbolRange -> list.add(e) }
        symbols
            .list()
            .filter { c: Char -> firstCharInRange <= c.code && c.code <= lastCharInRange }
            .map { symbol: Char? ->
                range(
                    symbol!!, symbol
                )
            }
            .forEach { e: SymbolRange -> list.add(e) }

        list.sortBy { it.from }
        return list
    }

    @JvmStatic
    fun compactOverlappingRangesAndSymbols(
        originalSymbolRanges: List<SymbolRange>, originalSymbols: CharList,
        compactedRanges: MutableList<SymbolRange>, compactedSymbols: CharList
    ) {
        val sortedRanges = (
                originalSymbolRanges + originalSymbols.list().map { symbol: Char? ->
                    range(
                        symbol!!, symbol
                    )
                }
            )
            .sortedBy { it.from }
            .toMutableList()

        if (sortedRanges.size == 1) {
            compactedSymbols.addAll(originalSymbols)
            compactedRanges.addAll(originalSymbolRanges)
            return
        }

        var i = 1
        while (i < sortedRanges.size) {
            val a = sortedRanges[i - 1]
            val b = sortedRanges[i]
            if (isRightWithinLeft(a, b)) {
                sortedRanges.removeAt(i)
            } else if (isRightWithinLeft(b, a)) {
                sortedRanges.removeAt(i - 1)
            } else if (isRightCanContinueLeft(a, b)) {
                sortedRanges.removeAt(i)
                sortedRanges[i - 1] = range(a.from, b.to)
            } else {
                ++i
            }
        }

        for (range in sortedRanges) {
            if (range.to == range.from) {
                compactedSymbols.add(range.from.toChar().code)
            } else {
                compactedRanges.add(range)
            }
        }
    }

    @JvmStatic
    fun isRightCanContinueLeft(left: SymbolRange, right: SymbolRange): Boolean {
        return left.from <= right.from && right.from <= left.to + 1 && left.to < right.to
    }

    @JvmStatic
    fun isRightWithinLeft(left: SymbolRange, right: SymbolRange): Boolean {
        return left.from <= right.from && left.to >= right.to
    }

    /**
     * This method helps to overcome the issue that is described here: StrangeBehaviourTests::randomIsNotSoRandomTest
     *
     * @param seed seed value
     * @return new Random()
     */
    @JvmStatic
    fun newRandom(seed: Int): Random {
        return Random(seed)
    }

    fun Long.pow(exponent: Long): Long = toDouble().pow(exponent.toDouble()).toLong()
    fun Long.pow(exponent: Int): Long = toDouble().pow(exponent.toDouble()).toLong()

}
