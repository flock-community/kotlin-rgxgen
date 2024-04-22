package community.flock.kotlinx.rgxgen.iterators

import community.flock.kotlinx.rgxgen.iterators.suppliers.Supplier
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.Arrays
import java.util.stream.Stream


class IteratorsLimitTests {
    @ParameterizedTest
    @MethodSource("data")
    fun whenDrainedAllElementsThrowsException(aName: String?, aIterator: StringIterator) {
        while (aIterator.hasNext()) {
            aIterator.next()
        }

        Assertions.assertThrows(NoSuchElementException::class.java) { aIterator.next() }
    }

    companion object {
        @JvmStatic
        fun data(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("ArrayIterator", ArrayIterator(charArrayOf('a'))),
                Arguments.of("CaseVariationIterator", CaseVariationIterator("a")),
                Arguments.of(
                    "ChoiceIterator",
                    ChoiceIterator(arrayOf(SingleValueIterator("a"), SingleValueIterator("a")))
                ),
                Arguments.of(
                    "IncrementalLength",
                    IncrementalLengthIterator(singleValueIteratorSupplier("a"), 0, 1)
                ),
                Arguments.of(
                    "PermutationIterator", PermutationsIterator(
                        Arrays.asList(
                            singleValueIteratorSupplier("a"),
                            singleValueIteratorSupplier("a")
                        )
                    )
                ),
                Arguments.of("SingleValueIterator", SingleValueIterator("a"))
            )
        }
        private fun singleValueIteratorSupplier(str:String): Supplier<StringIterator> {
            return object : Supplier<StringIterator> {
                override fun get(): SingleValueIterator {
                    return SingleValueIterator(str)
                }
            }
        }
    }
}
