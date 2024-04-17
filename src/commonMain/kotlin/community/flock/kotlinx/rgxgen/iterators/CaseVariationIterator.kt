package community.flock.kotlinx.rgxgen.iterators

import community.flock.kotlinx.rgxgen.util.Util
import java.util.*

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

class CaseVariationIterator(private val aOriginalValue: String) : StringIterator {
    private val aValue = StringBuilder(aOriginalValue.lowercase(Locale.getDefault()))
    private val aSwitchableCharPositions = TreeMap<Int, Boolean>() // true - lower, false - upper case

    private var aCurrentPos = 0
    private var hasNext = false


    init {
        var currentPos = Util.indexOfNextCaseSensitiveCharacter(aValue, 0)
        while (currentPos.isPresent) {
            aSwitchableCharPositions[currentPos.asInt] = true
            currentPos = Util.indexOfNextCaseSensitiveCharacter(aValue, currentPos.asInt + 1)
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
            if (Character.isLowerCase(currentChar)) {
                aValue.setCharAt(aCurrentPos, currentChar.uppercaseChar())
                aSwitchableCharPositions[aCurrentPos] = false

                hasNext = aSwitchableCharPositions.values
                    .stream()
                    .anyMatch { v: Boolean? -> v!! }
            } else {
                while (Character.isUpperCase(aValue[aCurrentPos])) {
                    aValue.setCharAt(aCurrentPos, aValue[aCurrentPos].lowercaseChar())
                    aSwitchableCharPositions[aCurrentPos] = true
                    aCurrentPos = aSwitchableCharPositions.ceilingKey(aCurrentPos + 1)
                }
                aValue.setCharAt(aCurrentPos, aValue[aCurrentPos].uppercaseChar())
                aSwitchableCharPositions[aCurrentPos] = false
                aCurrentPos = aSwitchableCharPositions.firstKey()
            }
            return aValue.toString()
        }
    }

    override fun reset() {
        aValue.replace(0, aOriginalValue.length, aOriginalValue.lowercase(Locale.getDefault()))
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
