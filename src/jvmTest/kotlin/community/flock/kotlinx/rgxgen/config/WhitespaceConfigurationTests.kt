package community.flock.kotlinx.rgxgen.config

import community.flock.kotlinx.rgxgen.RgxGen
import community.flock.kotlinx.rgxgen.model.WhitespaceChar
import community.flock.kotlinx.rgxgen.util.Util
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.Arrays
import java.util.regex.Pattern

class WhitespaceConfigurationTests {
    @Test
    fun byDefaultOnlySpaceAndTabIsGeneratedTest() {
        val random = Util.newRandom(100500)
        val pattern = Pattern.compile("[ \t]")
        for (i in 0 until COUNT_OF_ITERATIONS) {
            val generated = RgxGen.parse("\\s").generate(random)
            Assertions.assertTrue(pattern.matcher(generated).matches(), "Failed for value '$generated'")
        }
    }

    @Test
    fun configuredWhitespaceGenerationTest() {
        val properties: RgxGenProperties<Any> = RgxGenProperties()
        RgxGenOption.WHITESPACE_DEFINITION.setInProperties(
            properties,
            Arrays.asList(WhitespaceChar.FORM_FEED, WhitespaceChar.VERTICAL_TAB)
        )
        val pattern = Pattern.compile("[\u000B\u000c]")
        val random = Util.newRandom(100500)
        for (i in 0 until COUNT_OF_ITERATIONS) {
            val generated = RgxGen.parse(properties, "\\s").generate(random)
            Assertions.assertTrue(pattern.matcher(generated).matches(), "Failed for value '$generated'")
        }
    }

    @Test
    fun notMatchingGenerationAlwaysExcludesAllWhitespacesTest() {
        val random = Util.newRandom(100500)
        val pattern = Pattern.compile("\\s")
        for (i in 0 until LARGE_COUNT_OF_ITERATIONS) {
            val generated = RgxGen.parse("\\s").generateNotMatching(random)
            Assertions.assertFalse(pattern.matcher(generated).matches(), "Failed for value '$generated'")
        }
    }

    @Test
    fun notWhitespaceCharacterAlwaysExcludesAllWhitespacesTest() {
        val random = Util.newRandom(100500)
        val pattern = Pattern.compile("\\s")
        for (i in 0 until LARGE_COUNT_OF_ITERATIONS) {
            val generated = RgxGen.parse("\\S").generate(random)
            Assertions.assertFalse(pattern.matcher(generated).matches(), "Failed for value '$generated'")
        }
    }

    @Test
    fun matchingGenerationWithinSquareBracketsTest() {
        val random = Util.newRandom(100500)
        val pattern = Pattern.compile("[a \t]")
        for (i in 0 until COUNT_OF_ITERATIONS) {
            val generated = RgxGen.parse("[a\\s]").generate(random)
            Assertions.assertTrue(pattern.matcher(generated).matches(), "Failed for value '$generated'")
        }
    }

    @Test
    fun notMatchingGenerationWithinSquareBracketsTest() {
        val random = Util.newRandom(100500)
        val pattern = Pattern.compile("[^a\\s]")
        for (i in 0 until COUNT_OF_ITERATIONS) {
            val generated = RgxGen.parse("[a\\s]").generateNotMatching(random)
            Assertions.assertTrue(pattern.matcher(generated).matches(), "Failed for value '$generated'")
        }
    }

    @Test
    fun matchingGenerationNegativeWithinSquareBracketsTest() {
        val random = Util.newRandom(100500)
        val pattern = Pattern.compile("[^a\\s]")
        for (i in 0 until COUNT_OF_ITERATIONS) {
            val generated = RgxGen.parse("[^a\\s]").generate(random)
            Assertions.assertTrue(pattern.matcher(generated).matches(), "Failed for value '$generated'")
        }
    }

    companion object {
        const val COUNT_OF_ITERATIONS: Int = 1000
        const val LARGE_COUNT_OF_ITERATIONS: Int = 1000
    }
}
