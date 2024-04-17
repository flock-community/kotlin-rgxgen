package community.flock.kotlinx.rgxgen.iterators

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

class ChoiceIterator(private val aIterators: Array<StringIterator>) : StringIterator {
    private var aCurrentIteratorIndex = 0

    override fun hasNext(): Boolean {
        return aIterators[aCurrentIteratorIndex].hasNext() || aCurrentIteratorIndex + 1 < aIterators.size
    }

    override fun next(): String? {
        if (!aIterators[aCurrentIteratorIndex].hasNext()) {
            ++aCurrentIteratorIndex
            if (aCurrentIteratorIndex >= aIterators.size) {
                throw NoSuchElementException("No more values")
            }
        }

        return aIterators[aCurrentIteratorIndex].next()
    }

    override fun current(): String? {
        return aIterators[aCurrentIteratorIndex].current()
    }

    override fun reset() {
        aCurrentIteratorIndex = 0
        for (iterator in aIterators) {
            iterator.reset()
        }
    }
}
