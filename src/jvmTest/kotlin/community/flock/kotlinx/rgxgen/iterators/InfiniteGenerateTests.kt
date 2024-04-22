package community.flock.kotlinx.rgxgen.iterators

import community.flock.kotlinx.rgxgen.RgxGen.Companion.parse
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.regex.Pattern
import java.util.stream.Stream

class InfiniteGenerateTests {
    @ParameterizedTest
    @MethodSource("data")
    fun generateTest(
        aRegex: String?,
        aUseFind: Boolean
    ) {
        val p = Pattern.compile(aRegex)
        val rgxGen = parse(aRegex)
        for (i in 0 until ITERATIONS) {
            val s = rgxGen.generate()
            if (aUseFind) {
                Assertions.assertTrue(
                    p.matcher(s)
                        .find(), s
                )
            } else {
                Assertions.assertTrue(
                    p.matcher(s)
                        .matches(), s
                )
            }
        }
    }

    @ParameterizedTest
    @MethodSource("data")
    fun aLotOfValuesAvailableTest(
        aRegex: String?,
        aUseFind: Boolean
    ) {
        val p = Pattern.compile(aRegex)
        val rgxGen = parse(aRegex)
        val stringIterator = rgxGen.iterateUnique()
        val set: MutableSet<String?> = HashSet()

        for (i in 0 until ITERATIONS) {
            val next = stringIterator!!.next()
            Assertions.assertTrue(stringIterator.hasNext())
            if (aUseFind) {
                Assertions.assertTrue(
                    p.matcher(next)
                        .find(), next
                )
            } else {
                Assertions.assertTrue(
                    p.matcher(next)
                        .matches(), next
                )
            }
            Assertions.assertFalse(set.contains(next), "Duplicate value: $next")
            set.add(next)
        }
    }

    companion object {
        private const val ITERATIONS = 100

        @JvmStatic
        fun data(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("a*", false),
                Arguments.of("aa+", false),
                Arguments.of("a.*", false),
                Arguments.of("a+", false),
                Arguments.of("za*", false),
                Arguments.of("za+", false),
                Arguments.of("foo(?!bar)", true),
                Arguments.of("(?<!not)foo", true)
            )
        }
    }
}
