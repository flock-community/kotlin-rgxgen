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

class ArrayIterator(private val aStrings: CharArray) : StringIterator {
    private val aMaxIndex = aStrings.size - 1 // Because of prefix increment in nextImpl()

    private var aIndex = -1

    override fun hasNext(): Boolean {
        return aIndex < aMaxIndex
    }

    override fun next(): String? {
        ++aIndex
        if (aIndex >= aStrings.size) {
            throw NoSuchElementException("Not enough elements in arrays")
        } else {
            return aStrings[aIndex].toString()
        }
    }

    override fun reset() {
        aIndex = -1
    }

    override fun current(): String? {
        return aStrings[aIndex].toString()
    }
}
