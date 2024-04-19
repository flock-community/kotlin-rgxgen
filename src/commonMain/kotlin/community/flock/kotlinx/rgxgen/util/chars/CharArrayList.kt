package community.flock.kotlinx.rgxgen.util.chars

import kotlin.math.max
import kotlin.math.min

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

class CharArrayList : CharList {
    private var elementData: CharArray?
    private var size: Int

    constructor(arr: CharArray) {
        elementData = arr
        size = arr.size
    }

    constructor(size: Int) {
        elementData = CharArray(size)
        this.size = 0
    }

    constructor(arr: CharArray?, size: Int) {
        elementData = arr
        this.size = size
    }

    override fun copy(): CharList {
        val arr = CharArray(size)
//        System.arraycopy(elementData, 0, arr, 0, size)

        elementData?.copyInto(arr, 0, 0, size)
        return CharArrayList(arr)
    }

    override fun add(c: Int) {
        if (size == elementData!!.size) {
            grow()
        }
        elementData!![size] = c.toChar()
        size += 1
    }

    override fun list(): List<Char> {
        return elementData!!.concatToString(0, 0 + size).toList()
    }

    override fun addAll(originalSymbols: CharList) {
        originalSymbols.appendTo(this)
    }

    override fun addAll(characters: CharArray) {
        addAll(characters, characters.size)
    }

    override fun addAll(srcArr: CharArray?, srcLength: Int) {
        if (srcLength > elementData!!.size - size) {
            grow(size + srcLength)
        }
        srcArr?.copyInto(elementData!!, size, 0, srcLength )
        size += srcLength
    }

    override fun size(): Int {
        return size
    }

    override fun get(index: Int): Char {
        return elementData!![index]
    }

    override val isEmpty: Boolean
        get() = size == 0

    override fun sort() {
        elementData?.sort(0, size)
    }

    override fun except(predicate: CharPredicate): CharList {
        val arr = CharArray(size)
        var filteredSize = 0
        for (e in elementData!!) {
            if (!predicate.test(e)) {
                arr[filteredSize] = e
                filteredSize += 1
            }
        }
        return CharArrayList(arr, filteredSize)
    }

    override fun contains(i: Char): Boolean {
        for (e in elementData!!) {
            if (e == i) {
                return true
            }
        }
        return false
    }

    override fun appendTo(targetList: CharList) {
        targetList.addAll(elementData, size)
    }

    private fun grow(minCapacity: Int = size + 1) {
        val oldCapacity = elementData!!.size
        val newCapacity = newLength(oldCapacity, minCapacity - oldCapacity, oldCapacity shr 1)
        elementData = elementData!!.copyOf(newCapacity)
    }

    override fun toString(): String {
        if (elementData == null) {
            return "null"
        }
        val iMax = size - 1
        if (iMax == -1) {
            return "[]"
        }

        val b = StringBuilder()
        b.append('[')
        var i = 0
        while (true) {
            b.append(elementData!![i])
            if (i == iMax) {
                return b.append(']').toString()
            }
            b.append(", ")
            i++
        }
    }

    companion object {
        private fun newLength(oldLength: Int, minGrowth: Int, prefGrowth: Int): Int {
            val prefLength = (oldLength + max(minGrowth.toDouble(), prefGrowth.toDouble())).toInt() // might overflow
            return if (prefLength <= Int.MAX_VALUE - 8) {
                prefLength
            } else {
                (oldLength + min(minGrowth.toDouble(), prefLength.toDouble())).toInt()
            }
        }
    }
}
