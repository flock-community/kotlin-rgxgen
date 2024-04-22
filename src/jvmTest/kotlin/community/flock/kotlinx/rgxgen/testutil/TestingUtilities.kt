package community.flock.kotlinx.rgxgen.testutil

import community.flock.kotlinx.rgxgen.parsing.dflt.ConstantsProvider.MAX_UNICODE_CHARACTER
import community.flock.kotlinx.rgxgen.parsing.dflt.ConstantsProvider.SPACE_ASCII_CODE
import java.util.stream.IntStream

object TestingUtilities {
    const val BIG_INTEGER_MINUS_ONE: Long = -1

    @JvmStatic
    fun <T> iteratorToList(it: Iterator<T>): List<T> {
        val lst: MutableList<T> = ArrayList(100)

        while (it.hasNext()) {
            val next = it.next()
            lst.add(next)
        }

        return lst
    }

//    val allDigits: Array<Char>
//        get() = IntStream.rangeClosed('0'.code, '9'.code)
//            .mapToObj<Char> { i: Int -> i.toChar() }
//            .toArray<Char> { _Dummy_.__Array__() }

    fun makeUnicodeCharacterArray(): Array<Char?> {
        val characters = arrayOfNulls<Char>(MAX_UNICODE_CHARACTER - SPACE_ASCII_CODE)
        for (i in SPACE_ASCII_CODE until MAX_UNICODE_CHARACTER) {
            characters[i - SPACE_ASCII_CODE] = i.toChar()
        }
        return characters
    }
}
