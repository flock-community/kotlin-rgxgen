package community.flock.kotlinx.rgxgen

import community.flock.kotlinx.rgxgen.RgxGen.Companion.parse
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.regex.Pattern
import java.util.stream.Stream

/**
 * These tests are the tests which require find() instead of matches() to verify the generated patter.
 * Apparently lookahead and lookbehind does not match
 */
class LookaroundTests {
    @ParameterizedTest
    @MethodSource("getTestData")
    fun generateTest(name: String?, pattern: String) {
        val rgxGen = parse(pattern)
        for (i in 0..99) {
            val s = rgxGen.generate()
            Assertions.assertTrue(
                Pattern.compile(pattern)
                    .matcher(s)
                    .find(), "Text: '$s'does not match pattern $pattern"
            )
        }
    }

    @ParameterizedTest
    @MethodSource("getTestData")
    fun generateInfiniteTest(name: String?, pattern: String) {
        val rgxGen = parse(pattern)
        val stringIterator = rgxGen.iterateUnique()
        var i = 0
        while (i < 100 && stringIterator!!.hasNext()) {
            val s = stringIterator.next()
            Assertions.assertTrue(
                Pattern.compile(pattern)
                    .matcher(s)
                    .find(), "Text: '$s'does not match pattern $pattern"
            )
            i++
        }
    }

    companion object {

        @JvmStatic
        val testData: Stream<Arguments>
            get() = Stream.of(
                Arguments.of("Positive lookahead", "foo(?=b)"),
                Arguments.of("Negative lookahead", "foo(?!bab)"),
                Arguments.of("Positive lookbehind", "(?<=foo)bar"),
                Arguments.of("Negative lookbehind", "(?<!not)fof")
            )
    }
}
