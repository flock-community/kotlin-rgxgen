package community.flock.kotlinx.rgxgen

import community.flock.kotlinx.rgxgen.RgxGen.Companion.parse
import community.flock.kotlinx.rgxgen.util.Util.newRandom
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.Arrays
import java.util.regex.Pattern
import java.util.stream.IntStream
import java.util.stream.Stream

@Disabled("Temporary not working tests")
class TmpCompleteTests {
    @ParameterizedTest(name = "{index}: {0}")
    @MethodSource("getTestData")
    fun generateTest(name: String?, useFind: Boolean, pattern: String, seed: Int) {
        val rgxGen = parse(pattern)
        val s = rgxGen.generate(newRandom(seed))
        Assertions.assertTrue(matches(s, pattern, useFind), "Text: '$s' does not match pattern $pattern")
    }

    @ParameterizedTest(name = "{index}: {0}")
    @MethodSource("getTestData")
    fun generateNotMatchingTest(name: String?, useFind: Boolean, pattern: String, seed: Int) {
        val rgxGen = parse(pattern)
        val s = rgxGen.generateNotMatching(newRandom(seed))
        Assertions.assertFalse(matches(s, pattern, useFind), "Text: '$s' does not match pattern $pattern")
    }

    companion object {
        @JvmStatic
        val testData: Stream<Arguments>
            get() = Arrays.stream(
                arrayOf(
                    // Fails infrequently...
                    arrayOf<Any>(
                        "Domain name",
                        java.lang.Boolean.TRUE,
                        "(?!w{1,}\\.)(\\w+\\.?)([a-zA-Z]+)(\\.\\w+)"
                    ),  // Negative lookahead
                    //   {"ISO-8601 Date", Boolean.TRUE, "^(?![+-]?\\d{4,5}-?(?:\\d{2}|W\\d{2})T)(?:|(\\d{4}|[+-]\\d{5})-?(?:|(0\\d|1[0-2])(?:|-?([0-2]\\d|3[0-1]))|([0-2]\\d{2}|3[0-5]\\d|36[0-6])|W([0-4]\\d|5[0-3])(?:|-?([1-7])))(?:(?!\\d)|T(?=\\d)))(?:|([01]\\d|2[0-4])(?:|:?([0-5]\\d)(?:|:?([0-5]\\d)(?:|\\.(\\d{3})))(?:|[zZ]|([+-](?:[01]\\d|2[0-4]))(?:|:?([0-5]\\d)))))$"},
                    // Not matching generation fails

                    arrayOf<Any>(
                        "Unix Path",
                        java.lang.Boolean.TRUE,
                        "/|((?=/)|\\.|\\.\\.|~|~(?=/))(/(?=[^/])[^/]+)*/?"
                    ),  // This partially fails

                    arrayOf<Any>(
                        "Hashtags",
                        java.lang.Boolean.TRUE,
                        "\\B#([a-z0-9]{2,})(?![~!@#$%^&*()=+_`\\-\\|\\/'\\[\\]\\{\\}]|[?.,]*\\w)"
                    ),  // This hangs
                    // {"HTML Tags", Boolean.FALSE, "(<script(\\s|\\S)*?</script>)|(<style(\\s|\\S)*?</style>)|(<!--(\\s|\\S)*?-->)|(</?(\\s|\\S)*?>)"},

                )
            )
                .flatMap { arr: Array<Any> ->
                    IntStream.range(0, 100)
                        .mapToObj { index: Int ->
                            Arguments.of(
                                arr[0], arr[1], arr[2], index
                            )
                        }
                }

        private fun matches(text: String, pattern: String, useFind: Boolean): Boolean {
            val matcher = Pattern.compile(pattern).matcher(text)
            return if (useFind) matcher.find() else matcher.matches()
        }
    }
}
