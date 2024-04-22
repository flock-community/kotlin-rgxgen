package community.flock.kotlinx.rgxgen

import community.flock.kotlinx.rgxgen.RgxGen.Companion.parse
import community.flock.kotlinx.rgxgen.data.TestPattern
import community.flock.kotlinx.rgxgen.util.Util.newRandom
import community.flock.kotlinx.rgxgen.visitors.GenerationVisitor
import community.flock.kotlinx.rgxgen.visitors.NotMatchingGenerationVisitor
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Timeout
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.Arrays
import java.util.stream.IntStream
import java.util.stream.Stream

class CombinedRepeatableTests : CombinedTestTemplate<TestPattern?>() {
    @ParameterizedTest(name = "{1}: {0}")
    @MethodSource("getPatterns")
    fun generateTest(aSeed: Int, testPattern: TestPattern) {
        val generationVisitor = GenerationVisitor.builder()
            .withRandom(newRandom(aSeed))
            .get()
        testPattern.getResultNode()!!.visit(generationVisitor)
        val result = isValidGenerated(testPattern, generationVisitor.string, 0)
        Assertions.assertTrue(
            result,
            "Text: '" + generationVisitor.string + "'does not match pattern " + testPattern.getPattern()
        )
    }

    @ParameterizedTest(name = "{1}: {0}")
    @MethodSource("getPatterns")
    fun repeatableGenerationTest(aSeed: Int, testPattern: TestPattern) {
        val rnd1 = newRandom(aSeed)
        val rnd2 = newRandom(aSeed)

        val rgxGen_1 = parse(testPattern.getPattern())
        val rgxGen_2 = parse(testPattern.getPattern())
        Assertions.assertEquals(rgxGen_1.generate(rnd1), rgxGen_2.generate(rnd2))
    }

    @ParameterizedTest(name = "{1}: {0}")
    @MethodSource("getPatterns")
    @Timeout(5000)
    fun generateNotMatchingTest(aSeed: Int, testPattern: TestPattern) {
        val generationVisitor = NotMatchingGenerationVisitor.builder()
            .withRandom(newRandom(aSeed))
            .get()
        testPattern.getResultNode()!!.visit(generationVisitor)
        val result = isValidGenerated(testPattern, generationVisitor.string, 0)
        Assertions.assertFalse(
            result,
            "Text: '" + generationVisitor.string + "' matches pattern " + testPattern.getPattern()
        )
    }

    @ParameterizedTest(name = "{1}: {0}")
    @MethodSource("getPatterns")
    @Timeout(5000)
    fun repeatableNotMatchingGenerationTest(aSeed: Int, testPattern: TestPattern) {
        val rnd1 = newRandom(aSeed)
        val rnd2 = newRandom(aSeed)

        val rgxGen_1 = parse(testPattern.getPattern())
        val rgxGen_2 = parse(testPattern.getPattern())
        Assertions.assertEquals(rgxGen_1.generateNotMatching(rnd1), rgxGen_2.generateNotMatching(rnd2))
    }

    companion object {

        @JvmStatic
        val patterns: Stream<Arguments>
            get() = Arrays.stream(TestPattern.entries.toTypedArray())
                .flatMap { testPattern: TestPattern? ->
                    IntStream.range(0, 100)
                        .mapToObj { index: Int -> Arguments.of(index, testPattern) }
                }
    }
}
