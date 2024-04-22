package community.flock.kotlinx.rgxgen.iterators

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.function.Supplier
import java.util.stream.Collectors
import java.util.stream.Stream
import java.util.stream.StreamSupport


class ChoiceIteratorTests {
    @ParameterizedTest
    @MethodSource("data")
    fun countTest(
        aExpression: String,
        aIterators: Supplier<Array<StringIterator>>,
        aExpectedValues: List<String>
    ) {
        val stringIterator: StringIterator = ChoiceIterator(aIterators.get())
        val i = Iterable<String?> { stringIterator }

        val stream = StreamSupport.stream(i.spliterator(), false)
        Assertions.assertEquals(aExpectedValues.size.toLong(), stream.count())
    }

    @ParameterizedTest
    @MethodSource("data")
    fun valuesTest(
        aExpression: String?,
        aIterators: Supplier<Array<StringIterator>>,
        aExpectedValues: List<String>
    ) {
        val stringIterator: StringIterator = ChoiceIterator(aIterators.get())
        val i = Iterable<String?> { stringIterator }
        val stream = StreamSupport.stream(i.spliterator(), false)
        Assertions.assertEquals(aExpectedValues, stream.collect(Collectors.toList()))
    }

    companion object {
        @JvmStatic
        fun data(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    "(A|B)",
                    Supplier {
                        arrayOf<StringIterator>(
                            SingleValueIterator("A"),
                            SingleValueIterator("B")
                        )
                    },
                    mutableListOf("A", "B")
                ),
                Arguments.of(
                    "(A|B|C|D|E|F)",
                    Supplier {
                        arrayOf<StringIterator>(
                            SingleValueIterator("A"),
                            SingleValueIterator("B"),
                            SingleValueIterator("C"),
                            SingleValueIterator("D"),
                            SingleValueIterator("E"),
                            SingleValueIterator("F")
                        )
                    },
                    mutableListOf("A", "B", "C", "D", "E", "F")
                )
            )
        }
    }
}
