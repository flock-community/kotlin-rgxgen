package community.flock.kotlinx.rgxgen.visitors.helpers

import community.flock.kotlinx.rgxgen.model.SymbolRange
import community.flock.kotlinx.rgxgen.nodes.SymbolSet
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


class SymbolSetIndexer(symbolSet: SymbolSet) {
    private val symbols: CharList? = symbolSet.symbols
    private val symbolRanges: List<SymbolRange> = symbolSet.symbolRanges
    private val rangeOffsets: IntArray
    private val size: Int

    init {
        var tmpSize = symbols?.size() ?: 0

        val rangesCount = symbolRanges.size
        if (rangesCount > 0) {
            rangeOffsets = IntArray(rangesCount)
            var currentOffset = 0
            rangeOffsets[0] = currentOffset
            for (i in 0 until rangesCount - 1) {
                val symbolRange = symbolRanges[i]
                val rangeSize = symbolRange.size()
                tmpSize += rangeSize
                val offset = currentOffset + rangeSize
                rangeOffsets[i + 1] = offset
                currentOffset = offset
            }
            tmpSize += symbolRanges[rangesCount - 1].size()
        } else {
            rangeOffsets = IntArray(0)
        }
        size = tmpSize
    }

    fun size(): Int {
        return size
    }

    val all: CharArray
        get() {
            val chars = CharArray(size)
            for (i in 0 until size) {
                chars[i] = get(i)
            }
            return chars
        }

    fun get(seed: Int): Char {
        var seed = seed
        if (symbols != null && seed < symbols.size()) {
            return symbols.get(seed)
        }

        seed -= symbols?.size() ?: 0

        if (seed == 0) {
            return symbolRanges[0].from.toChar()
        }
        val i = findRangeIndex(seed)
        val offset = rangeOffsets[i]
        return (symbolRanges[i].from + seed - offset).toChar()
    }

    private fun findRangeIndex(seed: Int): Int {
        val i = rangeOffsets.binarySearch(seed)
        return if (i > 0) {
            i
        } else {
            -i - 2
        }
    }
}
