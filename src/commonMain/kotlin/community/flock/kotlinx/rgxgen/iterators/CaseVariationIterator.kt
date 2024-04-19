package community.flock.kotlinx.rgxgen.iterators

import community.flock.kotlinx.rgxgen.util.Util
import kotlin.NoSuchElementException

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

class KeyListMap: MutableMap<Int, Boolean> by mutableMapOf(){

    fun firstKey(): Int{
        return keys.minOf { it }
    }

    fun ceilingKey(key: Int): Int{
        return keys.sorted().find { it >= key } ?: error("Not found")
    }

}

class CaseVariationIterator(private val aOriginalValue: String) : StringIterator {
    private val aValue = StringBuilder(aOriginalValue.lowercase())
    private val aSwitchableCharPositions = KeyListMap() // true - lower, false - upper case

    private var aCurrentPos = 0
    private var hasNext = false


    init {
        var currentPos = Util.indexOfNextCaseSensitiveCharacter(aValue, 0)
        while (currentPos != null) {
            aSwitchableCharPositions[currentPos] = true
            currentPos = Util.indexOfNextCaseSensitiveCharacter(aValue, currentPos + 1)
        }
        reset()
    }

    override fun next(): String? {
        if (!hasNext) {
            throw NoSuchElementException("No more variations")
        }

        if (aSwitchableCharPositions.isEmpty()) {        // Only one possible value. No variations...
            hasNext = false
            return aOriginalValue
        } else if (aCurrentPos < 0) {       // First time return all lower-case value
            aCurrentPos = aSwitchableCharPositions.firstKey()
            return aValue.toString()
        } else {        // All other
            val currentChar = aValue[aCurrentPos]
            if (currentChar.isLowerCase()) {
                aValue.set(aCurrentPos, currentChar.uppercaseChar())
                aSwitchableCharPositions.put(aCurrentPos, false)

                hasNext = aSwitchableCharPositions.values
                    .any { v: Boolean? -> v!! }
            } else {
                while (aValue[aCurrentPos].isUpperCase()) {
                    aValue.set(aCurrentPos, aValue[aCurrentPos].lowercaseChar())
                    aSwitchableCharPositions.put(aCurrentPos, true)
                    aCurrentPos = aSwitchableCharPositions.ceilingKey(aCurrentPos + 1)
                }
                aValue.set(aCurrentPos, aValue[aCurrentPos].uppercaseChar())
                aSwitchableCharPositions.put(aCurrentPos, false)
                aCurrentPos = aSwitchableCharPositions.firstKey()
            }
            return aValue.toString()
        }
    }

    override fun reset() {
        aValue.clear()
        aValue.append(aOriginalValue.lowercase())
        hasNext = true
        aCurrentPos = -1
    }

    override fun current(): String? {
        return aValue.toString()
    }

    override fun hasNext(): Boolean {
        return hasNext
    }
}
