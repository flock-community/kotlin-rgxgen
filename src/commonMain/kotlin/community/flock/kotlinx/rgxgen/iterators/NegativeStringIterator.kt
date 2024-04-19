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

class NegativeStringIterator(private val aIterator: StringIterator, private val aPattern: String) : StringIterator {

    private var aValue: String? = null

    val regex = aPattern.toRegex()
    override fun next(): String? {
        aValue = aIterator.asSequence().find {
            it?.let { !regex.matches(it) } ?: false
        }
        return aValue
    }

    override fun reset() {
        // Nothing to do
    }

    override fun current(): String? {
        return aValue
    }

    override fun hasNext(): Boolean {
        return true
    }
}
