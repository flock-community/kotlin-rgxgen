package community.flock.kotlinx.rgxgen.iterators

import community.flock.kotlinx.rgxgen.iterators.suppliers.Supplier
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.Arrays
import java.util.stream.Stream


class IteratorResettingTests {
    private class TestIterator : StringIterator {
        private var ok = true

        private var aValue = "a"

        constructor()

        constructor(value: String) {
            aValue = value
        }

        override fun next(): String? {
            if (ok) {
                ok = false
                return aValue
            } else {
                throw NoSuchElementException()
            }
        }

        override fun reset() {
            ok = true
        }

        override fun current(): String? {
            return aValue
        }

        override fun hasNext(): Boolean {
            return ok
        }

        override fun toString(): String {
            return "TestIterator{} "
        }
    }

    private class TestBiIterator : StringIterator {
        private var aCurrent: String? = null

        private var ok = 0

        override fun next(): String? {
            if (hasNext()) {
                ++ok
                if (ok == 1) {
                    aCurrent = "x"
                    return "x"
                } else if (ok == 2) {
                    aCurrent = "y"
                    return "y"
                } else {
                    aCurrent = "ERROR"
                    return "ERROR"
                }
            } else {
                throw NoSuchElementException()
            }
        }

        override fun reset() {
            ok = 0
        }

        override fun current(): String? {
            return aCurrent
        }

        override fun hasNext(): Boolean {
            return ok < 2
        }

        override fun toString(): String {
            return "TestBiIterator{} "
        }
    }

    @ParameterizedTest(name = "{0}:{2}/{3}")
    @MethodSource("data")
    fun test(
        aName: String?,
        aIterator: StringIterator,
        aResetAfter: Int,
        aExpectedValues: List<String?>
    ) {
        for (i in 1..aExpectedValues.size) {
            var next = if (aIterator.hasNext()) {
                aIterator.next()
            } else {
                null
            }

            Assertions.assertEquals(aExpectedValues[i - 1], next)
            if (i % aResetAfter == 0) {
                aIterator.reset()
            }
        }
    }

    companion object {
        @JvmStatic
        fun data(): Stream<Arguments> {
            return Stream.of(
                Arguments.arguments("Arr", ArrayIterator(charArrayOf('a', 'b')), 1, mutableListOf("a", "a", "a", "a")),
                Arguments.arguments("Arr", ArrayIterator(charArrayOf('a', 'b')), 3, mutableListOf("a", "b", null)),
                Arguments.arguments("Arr", ArrayIterator(charArrayOf('a', 'b')), 2, mutableListOf("a", "b", "a", "b")),

                Arguments.arguments("Single", SingleValueIterator(), 1, mutableListOf("", "")),
                Arguments.arguments("Single", SingleValueIterator(), 2, mutableListOf("", null)),
                Arguments.arguments("Single", SingleValueIterator("s"), 1, mutableListOf("s", "s")),
                Arguments.arguments("Single", SingleValueIterator("s"), 2, mutableListOf("s", null, "s")),


                Arguments.arguments(
                    "IncSingle",
                    IncrementalLengthIterator(stringIteratorSupplier(), 1, 2),
                    1,
                    mutableListOf("a", "a", "a")
                ),
                Arguments.arguments(
                    "IncSingle",
                    IncrementalLengthIterator(stringIteratorSupplier(), 1, 2),
                    2,
                    mutableListOf("a", "aa", "a")
                ),
                Arguments.arguments(
                    "IncSingle",
                    IncrementalLengthIterator(stringIteratorSupplier(), 1, 2),
                    3,
                    mutableListOf("a", "aa", null)
                ),

                Arguments.arguments(
                    "IncBi",
                    IncrementalLengthIterator(stringBiIteratorSupplier(), 1, 2),
                    3,
                    mutableListOf("x", "y", "xx", "x")
                ),
                Arguments.arguments(
                    "IncBi",
                    IncrementalLengthIterator(stringBiIteratorSupplier(), 1, 2),
                    10,
                    mutableListOf("x", "y", "xx", "xy", "yx", "yy", null)
                ),

                Arguments.arguments(
                    "Perm", PermutationsIterator(
                        Arrays.asList(
                            stringIteratorSupplier(), stringIteratorSupplier("b")
                        )
                    ), 1, mutableListOf("ab", "ab", "ab")
                ),
                Arguments.arguments(
                    "Perm", PermutationsIterator(
                        Arrays.asList(
                            stringIteratorSupplier(), stringIteratorSupplier("b")
                        )
                    ), 2, mutableListOf("ab", null, "ab")
                ),
                Arguments.arguments(
                    "Perm", PermutationsIterator(
                        Arrays.asList(
                            stringBiIteratorSupplier(),
                            stringBiIteratorSupplier()
                        )
                    ), 5, mutableListOf("xx", "xy", "yx", "yy", null, "xx")
                ),

                Arguments.arguments(
                    "Choice",
                    ChoiceIterator(arrayOf(TestIterator(), TestBiIterator())),
                    1,
                    mutableListOf("a", "a", "a")
                ),
                Arguments.arguments(
                    "Choice",
                    ChoiceIterator(arrayOf(TestIterator(), TestBiIterator())),
                    2,
                    mutableListOf("a", "x", "a")
                ),
                Arguments.arguments(
                    "Choice",
                    ChoiceIterator(arrayOf(TestIterator(), TestBiIterator())),
                    3,
                    mutableListOf("a", "x", "y")
                ),
                Arguments.arguments(
                    "Choice",
                    ChoiceIterator(arrayOf(TestIterator(), TestBiIterator())),
                    4,
                    mutableListOf("a", "x", "y", null)
                ),
                Arguments.arguments(
                    "Choice",
                    ChoiceIterator(arrayOf(TestIterator(), TestBiIterator())),
                    4,
                    mutableListOf("a", "x", "y", null, "a")
                ),

                Arguments.arguments(
                    "Case Variations",
                    CaseVariationIterator("a"),
                    1,
                    mutableListOf("a", "a", "a", "a")
                ),
                Arguments.arguments(
                    "Case Variations",
                    CaseVariationIterator("a"),
                    2,
                    mutableListOf("a", "A", "a", "A")
                ),
                Arguments.arguments(
                    "Case Variations",
                    CaseVariationIterator("a"),
                    3,
                    mutableListOf("a", "A", null, "a")
                ),
                Arguments.arguments(
                    "Case Variations",
                    CaseVariationIterator("ab"),
                    3,
                    mutableListOf("ab", "Ab", "aB", "ab")
                )
            )
        }

        private fun stringIteratorSupplier(str: String? = null): Supplier<StringIterator> {
            return object : Supplier<StringIterator> {
                override fun get(): StringIterator {
                    return str?.let { TestIterator(it) } ?: TestIterator()
                }
            }
        }

        private fun stringBiIteratorSupplier(): Supplier<StringIterator> {
            return object : Supplier<StringIterator> {
                override fun get(): TestBiIterator {
                    return TestBiIterator()
                }
            }
        }
    }
}
