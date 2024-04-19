package community.flock.kotlinx.rgxgen.iterators

import kotlin.jvm.JvmOverloads

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

class SingleValueIterator @JvmOverloads constructor(private val aValue: String = "") : StringIterator {
    private var hasNext = true

    override fun hasNext(): Boolean {
        return hasNext
    }

    override fun next(): String? {
        if (!hasNext) {
            throw NoSuchElementException("Cannot return a value second time.")
        }
        hasNext = false
        return aValue
    }

    override fun reset() {
        hasNext = true
    }

    override fun current(): String? {
        return aValue
    }
}
