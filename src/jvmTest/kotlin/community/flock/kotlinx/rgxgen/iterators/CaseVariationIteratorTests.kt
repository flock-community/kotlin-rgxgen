package community.flock.kotlinx.rgxgen.iterators

import community.flock.kotlinx.rgxgen.testutil.TestingUtilities
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class CaseVariationIteratorTests {
    @ParameterizedTest
    @MethodSource("parameters")
    fun test(aInput: String?, aExpected: List<String?>?) {
        val caseVariationIterator = CaseVariationIterator(aInput!!)
        val strings = TestingUtilities.iteratorToList(caseVariationIterator)
        Assertions.assertEquals(aExpected, strings)
    }

    @ParameterizedTest
    @MethodSource("parameters")
    fun testThrows(aInput: String?, aExpected: List<String?>?) {
        val caseVariationIterator = CaseVariationIterator(aInput!!)
        val strings = TestingUtilities.iteratorToList(caseVariationIterator)
        Assertions.assertFalse(caseVariationIterator.hasNext())
        Assertions.assertThrows(NoSuchElementException::class.java) { caseVariationIterator.next() }
    }

    companion object {
        @JvmStatic
        fun parameters(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("A", mutableListOf("a", "A")),
                Arguments.of("a", mutableListOf("a", "A")),
                Arguments.of("1", listOf("1")),
                Arguments.of("1a", mutableListOf("1a", "1A")),
                Arguments.of("1ab", mutableListOf("1ab", "1Ab", "1aB", "1AB")),
                Arguments.of("a1b", mutableListOf("a1b", "A1b", "a1B", "A1B")),
                Arguments.of("A1B", mutableListOf("a1b", "A1b", "a1B", "A1B")),
                Arguments.of("abc", mutableListOf("abc", "Abc", "aBc", "ABc", "abC", "AbC", "aBC", "ABC")),
                Arguments.of("AbC", mutableListOf("abc", "Abc", "aBc", "ABc", "abC", "AbC", "aBC", "ABC")),
                Arguments.of("ABC", mutableListOf("abc", "Abc", "aBc", "ABc", "abC", "AbC", "aBC", "ABC"))
            )
        }
    }
}
