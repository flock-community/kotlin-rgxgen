package community.flock.kotlinx.rgxgen

import community.flock.kotlinx.rgxgen.RgxGen.Companion.parse
import community.flock.kotlinx.rgxgen.config.RgxGenOption
import community.flock.kotlinx.rgxgen.config.RgxGenProperties
import community.flock.kotlinx.rgxgen.data.TestPatternCaseInsensitive
import community.flock.kotlinx.rgxgen.testutil.TestingUtilities.iteratorToList
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assumptions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.Arrays
import java.util.regex.Pattern
import java.util.stream.Collectors
import java.util.stream.Stream
import java.util.stream.StreamSupport

class CombinedCaseInsensitiveTests : CombinedTestTemplate<TestPatternCaseInsensitive?>() {
    @ParameterizedTest
    @MethodSource("getAllPatterns")
    fun countTest(testPattern: TestPatternCaseInsensitive) {
        Assumptions.assumeTrue(testPattern.hasEstimatedCount())
        val properties: RgxGenProperties<Any> = RgxGenProperties<Any>()
        RgxGenOption.CASE_INSENSITIVE.setInProperties(properties, true)
        val rgxGen = parse(properties, testPattern.getPattern())
        Assertions.assertEquals(testPattern.getEstimatedCount(), rgxGen.uniqueEstimation)
    }

    @ParameterizedTest
    @MethodSource("getAllPatterns")
    fun generateUniqueTest(testPattern: TestPatternCaseInsensitive) {
        Assumptions.assumeTrue(testPattern.hasAllUniqueValues())
        val properties: RgxGenProperties<Any> = RgxGenProperties<Any>()
        RgxGenOption.CASE_INSENSITIVE.setInProperties(properties, true)
        val rgxGen = parse(properties, testPattern.getPattern())
        Assertions.assertEquals(testPattern.getAllUniqueValues(), iteratorToList<String?>(rgxGen.iterateUnique()!!))
    }

    @ParameterizedTest
    @MethodSource("getAllPatterns")
    fun classRgxGenCaseInsensitiveTest(testPattern: TestPatternCaseInsensitive) {
        val properties: RgxGenProperties<Any> = RgxGenProperties<Any>()
        RgxGenOption.CASE_INSENSITIVE.setInProperties(properties, true)
        val rgxGen = parse(properties, testPattern.getPattern())
        val iterator = rgxGen.stream()
        val iterable = Iterable { iterator }
        val strings = StreamSupport.stream(iterable.spliterator(), false)
            .limit(1000)
            .collect(Collectors.toList())
        val caseSensitivePattern = Pattern.compile(testPattern.getPattern())
        var atLeastOneCaseSensitiveMismatch = false
        for (generated in strings) {
            val result = isValidGenerated(testPattern, generated, Pattern.CASE_INSENSITIVE)
            val caseSensitiveMatches = !caseSensitivePattern.matcher(generated).matches()
            Assertions.assertTrue(
                result,
                "Text: '" + generated + "' does not match pattern " + testPattern.getPattern()
            )
            atLeastOneCaseSensitiveMismatch = atLeastOneCaseSensitiveMismatch || caseSensitiveMatches
        }
        Assertions.assertTrue(atLeastOneCaseSensitiveMismatch)
    }

    companion object {
        @JvmStatic
        val allPatterns: Stream<TestPatternCaseInsensitive>
            get() = Arrays.stream(TestPatternCaseInsensitive.entries.toTypedArray())
    }
}
