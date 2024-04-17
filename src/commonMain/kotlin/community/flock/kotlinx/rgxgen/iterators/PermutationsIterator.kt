package community.flock.kotlinx.rgxgen.iterators

import java.util.*
import java.util.function.Supplier

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

class PermutationsIterator(iteratorsSuppliers: List<Supplier<StringIterator>>) : StringIterator {
    private val aIterators = arrayOfNulls<StringIterator>(iteratorsSuppliers.size)

    private var aInitialized: Boolean

    init {
        for (i in aIterators.indices) {
            val iterator = iteratorsSuppliers[i]
                .get()
            aIterators[i] = iterator
        }

        // Make sure it is null, because it's used for check later
        aInitialized = false
    }

    override fun hasNext(): Boolean {
        return !aInitialized || Arrays.stream(aIterators)
            .anyMatch { obj: StringIterator? -> obj!!.hasNext() }
    }

    override fun next(): String? {
        // Initialize all value
        if (aInitialized) {
            // Advance one of iterators
            for (i in aIterators.indices.reversed()) {
                if (aIterators[i]!!.hasNext()) {
                    aIterators[i]!!.next()
                    break
                } else if (i == 0) {
                    // We can only reset other iterators. Head iterator should use all it's values only once
                    throw NoSuchElementException("No more unique values")
                } else {
                    aIterators[i]!!.reset()
                    aIterators[i]!!.next()
                }
            }
        } else {
            for (iterator in aIterators) {
                iterator!!.next()
            }
            aInitialized = true
        }

        return current()
    }

    override fun reset() {
        aInitialized = false
        for (iterator in aIterators) {
            iterator!!.reset()
        }
    }

    override fun current(): String? {
        return Arrays.stream(aIterators)
            .map { obj: StringIterator? -> obj!!.current() }
            .reduce("") { obj: String?, str: String? -> obj + str }
    }
}
