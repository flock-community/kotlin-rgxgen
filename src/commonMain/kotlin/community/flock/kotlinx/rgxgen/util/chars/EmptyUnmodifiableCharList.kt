package community.flock.kotlinx.rgxgen.util.chars

import community.flock.kotlinx.rgxgen.parsing.dflt.ConstantsProvider.ZERO_LENGTH_CHARACTER_ARRAY
import java.util.stream.Stream

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

class EmptyUnmodifiableCharList : CharList() {
    override fun copy(): CharList {
        return this
    }

    override fun add(c: Int) {
        throw UnsupportedOperationException("Unmodifiable CharList!")
    }

    override fun stream(): Stream<Char> {
        return Stream.empty()
    }

    override fun addAll(originalSymbols: CharList) {
        throw UnsupportedOperationException("Unmodifiable CharList!")
    }

    override fun addAll(characters: CharArray) {
        throw UnsupportedOperationException("Unmodifiable CharList!")
    }

    override fun addAll(srcArr: CharArray?, srcLength: Int) {
        throw UnsupportedOperationException("Unmodifiable CharList!")
    }

    override fun size(): Int {
        return 0
    }

    override fun get(index: Int): Char {
        throw NoSuchElementException("Empty unmodifiable CharList")
    }

    override val isEmpty: Boolean
        get() = true

    override fun sort() {
    }

    override fun except(predicate: CharPredicate): CharList {
        return this
    }

    override fun contains(i: Char): Boolean {
        return false
    }

    override fun appendTo(targetList: CharList) {
    }

    override fun toString(): String {
        return ZERO_LENGTH_CHARACTER_ARRAY.contentToString()
    }
}
