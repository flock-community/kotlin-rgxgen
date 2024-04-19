package community.flock.kotlinx.rgxgen.iterators

import community.flock.kotlinx.rgxgen.iterators.suppliers.Supplier
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

class IncrementalLengthIterator(private val aSupplier: Supplier<StringIterator>, private val aMin: Int, max: Int) :
    StringIterator {
    private val aMax: Int

    private var aCurrentLength: Int
    private var aCurrentIterators: Array<StringIterator?> = emptyArray()
    private var aInit = true


    // (a|b){1} -> "a", "b" --> "a", "b"
    // (a|b){2} -> "a", "b" --> "aa", "ab", "ba", "bb"
    // (a|b){1,2} -> "a", "b" --> "a", "b", "aa", "ab", "ba", "bb"
    // (a|b){0,2} -> "a", "b" --> "", "a", "b", "aa", "ab", "ba", "bb"
    // Take 0 from list
    // Take 1 from list
    // Take and concatenate 2 from list
    // ...
    init {
        aCurrentLength = aMin
        aMax = max
        reset()
    }

    private fun lengthCanGrow(): Boolean {
        return aCurrentLength < aMax || aCurrentIterators.size < aCurrentLength || aMax < 0
    }

    private fun hasMoreForCurrentLength(): Boolean {
        return aCurrentIterators
            .any { obj: StringIterator? -> obj!!.hasNext() }
    }

    override fun hasNext(): Boolean {
        return lengthCanGrow() || hasMoreForCurrentLength()
    }

    private fun extendIterators() {
        val tmp = arrayOfNulls<StringIterator>(aCurrentLength)
        for (i in aCurrentIterators.indices) {
            tmp[i] = aCurrentIterators[i]
            tmp[i]!!.reset()
        }
        tmp[aCurrentLength - 1] = aSupplier.get()
        aCurrentIterators = tmp
        aInit = false
        for (i in 0 until aCurrentLength) {
            aCurrentIterators[i]!!.next()
        }
    }

    override fun next(): String? {
        if (aCurrentLength == 0) {
            ++aCurrentLength
            return ""
        } else if (aInit) {
            extendIterators()
            return current()
        } else {
            // Advance one of iterators
            for (i in aCurrentLength - 1 downTo 0) {
                if (aCurrentIterators[i]!!.hasNext()) {
                    aCurrentIterators[i]!!.next()
                    break
                } else if (i == 0) {
                    if (aCurrentLength < aMax || aMax < 0) {
                        ++aCurrentLength
                        extendIterators()
                    } else {
                        // We can only increase length up to max
                        throw NoSuchElementException("No more unique values")
                    }
                } else {
                    aCurrentIterators[i]!!.reset()
                    aCurrentIterators[i]!!.next()
                }
            }
        }

        return current()
    }

    override fun reset() {
        aCurrentLength = aMin
        aInit = true
        aCurrentIterators = arrayOfNulls(aCurrentLength)
        for (i in 0 until aCurrentLength) {
            aCurrentIterators[i] = aSupplier.get()
        }
    }

    override fun current(): String? {
        return aCurrentIterators
            .map { obj: StringIterator? -> obj!!.current() }
            .fold("") { obj: String?, str: String? -> obj + str }
    }
}
