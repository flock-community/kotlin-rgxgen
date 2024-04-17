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

class ReferenceIterator : StringIterator {
    private var aOther: StringIterator? = null
    private var hasNext = true
    private var aLast: String? = null

    fun setOther(other: StringIterator?) {
        aOther = other
    }

    override fun next(): String? {
        if (!hasNext()) {
            throw NoSuchElementException("Cannot return value second time")
        }
        hasNext = false
        return aOther!!.current()
    }

    override fun current(): String? {
        aLast = aOther!!.current()
        return aOther!!.current()
    }

    override fun reset() {
        hasNext = true
    }

    override fun hasNext(): Boolean {
        return hasNext || aOther!!.current() != aLast
    }
}
