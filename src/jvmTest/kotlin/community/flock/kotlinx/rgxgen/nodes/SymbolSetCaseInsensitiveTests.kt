package community.flock.kotlinx.rgxgen.nodes

import community.flock.kotlinx.rgxgen.model.MatchType
import community.flock.kotlinx.rgxgen.nodes.SymbolSet.Companion.ofAsciiCharacters
import community.flock.kotlinx.rgxgen.parsing.dflt.ConstantsProvider.makeAsciiCharacterArray
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.Arrays
import java.util.stream.Stream

class SymbolSetCaseInsensitiveTests {
    @ParameterizedTest
    @MethodSource("parameters")
    fun positiveSetTest(aOriginalString: String, aExpectedCaseInsensitive: String) {
        val symbolSet = ofAsciiCharacters(aOriginalString, aOriginalString.toCharArray(), MatchType.POSITIVE)
        val actual = symbolSet.caseInsensitiveSymbolSetIndexer!!.all
        val expected = aExpectedCaseInsensitive.toCharArray()
        Assertions.assertArrayEquals(
            expected, actual, """
     
     $expected
     expected vs got
     $actual
     
     """.trimIndent()
        )
    }

    @ParameterizedTest
    @MethodSource("parameters")
    fun negativeSetTest(aOriginalString: String, aExpectedCaseInsensitive: String) {
        val symbolSet = ofAsciiCharacters(aOriginalString, aOriginalString.toCharArray(), MatchType.NEGATIVE)
        val actual = symbolSet.caseInsensitiveSymbolSetIndexer!!.all
        val expected = excluding(aExpectedCaseInsensitive)
        Assertions.assertArrayEquals(
            expected, actual, """
     
     ${expected}
     expected vs got
     ${actual}
     
     """.trimIndent()
        )
    }

    companion object {
        fun excluding(chars: String): CharArray {
            val allSymbols = makeAsciiCharacterArray()
            val result = CharArray(allSymbols.size - chars.length)
            var target = 0
            for (sym in allSymbols) {
                if (chars.indexOf(sym) == -1) {
                    result[target] = sym
                    ++target
                }
            }
            return result
        }

@JvmStatic
        fun parameters(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("a", "Aa"),
                Arguments.of("abc", "ABCabc"),
                Arguments.of("123", "123"),
                Arguments.of("123ab", "123ABab"),
                Arguments.of("A", "Aa")
            )
        }
    }
}
