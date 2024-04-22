package community.flock.kotlinx.rgxgen

import community.flock.kotlinx.rgxgen.RgxGen.Companion.parse
import community.flock.kotlinx.rgxgen.config.RgxGenOption
import community.flock.kotlinx.rgxgen.config.RgxGenProperties
import community.flock.kotlinx.rgxgen.data.DataInterface
import community.flock.kotlinx.rgxgen.data.TestPattern
import community.flock.kotlinx.rgxgen.parsing.NodeTreeBuilder
import community.flock.kotlinx.rgxgen.parsing.dflt.DefaultTreeBuilder
import community.flock.kotlinx.rgxgen.testutil.NodePatternVerifyingVisitor
import community.flock.kotlinx.rgxgen.testutil.TestingUtilities.iteratorToList
import community.flock.kotlinx.rgxgen.util.Util.newRandom
import community.flock.kotlinx.rgxgen.visitors.UniqueGenerationVisitor
import community.flock.kotlinx.rgxgen.visitors.UniqueValuesCountingVisitor
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assumptions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.Arrays
import java.util.regex.Pattern
import java.util.stream.Stream

class CombinedTests : CombinedTestTemplate<TestPattern?>() {
    @ParameterizedTest
    @MethodSource("getPatterns")
    fun parseTest(testPattern: TestPattern) {
        val defaultTreeBuilder: NodeTreeBuilder = DefaultTreeBuilder(testPattern.getPattern()!!, null)
        val node = defaultTreeBuilder.get()
        Assertions.assertEquals(testPattern.getResultNode().toString(), node.toString())
        val visitor = NodePatternVerifyingVisitor(testPattern.getResultNode()?: error("No node"))
        node!!.visit(visitor)
        Assertions.assertTrue(visitor.errors.isEmpty(), visitor.errors.toString())
    }

    @ParameterizedTest
    @MethodSource("getPatterns")
    fun countUniqueUsingVisitorTest(testPattern: TestPattern) {
        Assumptions.assumeTrue(testPattern.hasEstimatedCount())
        val v = UniqueValuesCountingVisitor(RgxGenProperties<Any?>())
        testPattern.getResultNode()!!.visit(v)
        Assertions.assertEquals(testPattern.getEstimatedCount(), v.estimation)
    }

    @ParameterizedTest
    @MethodSource("getPatterns")
    fun countUniqueTest(testPattern: TestPattern) {
        Assumptions.assumeTrue(testPattern.hasEstimatedCount())
        val rgxGen = parse(testPattern.getPattern())
        Assertions.assertEquals(testPattern.getEstimatedCount(), rgxGen.uniqueEstimation)
    }

    @ParameterizedTest
    @MethodSource("getPatterns")
    fun generateUniqueTest(testPattern: TestPattern) {
        Assumptions.assumeTrue(testPattern.hasAllUniqueValues())

        val v = UniqueGenerationVisitor(RgxGenProperties<Any?>())
        testPattern.getResultNode()!!.visit(v)
        Assertions.assertEquals(testPattern.getAllUniqueValues(), iteratorToList<String?>(v.uniqueStrings))
    }

    @ParameterizedTest
    @MethodSource("getPatterns")
    fun classRgxGenTest(testPattern: TestPattern) {
        val rgxGen = parse(testPattern.getPattern())
        if (testPattern.hasEstimatedCount()) {
            Assertions.assertEquals(testPattern.getEstimatedCount(), rgxGen.uniqueEstimation)
        }
        for (i in 0..99) {
            val rand = newRandom(i)
            for (j in 0..9) {
                val generated = rgxGen.generate(rand)
                val result = isValidGenerated(testPattern, generated, 0)
                Assertions.assertTrue(result, createMessage(generated, testPattern, i, j))
            }
        }
    }

    @ParameterizedTest
    @MethodSource("getPatterns")
    fun classRgxGenCaseInsensitiveTest(testPattern: TestPattern) {
        val properties: RgxGenProperties<Any> = RgxGenProperties<Any>()
        RgxGenOption.CASE_INSENSITIVE.setInProperties(properties, true)
        val rgxGen = parse(properties, testPattern.getPattern())

        for (i in 0..99) {
            val random = newRandom(i)
            for (j in 0..9) {
                val generated = rgxGen.generate(random)
                val result = isValidGenerated(testPattern, generated, Pattern.CASE_INSENSITIVE)
                Assertions.assertTrue(result, createMessage(generated, testPattern, i, j))
            }
        }
    }

    companion object {
        @JvmStatic
        val patterns: Stream<TestPattern>
            get() = Arrays.stream(TestPattern.entries.toTypedArray())


        private fun createMessage(generated: String, pattern: DataInterface, i: Int, j: Int): String {
            return "Text: '" + generated + "' does not match pattern '" + pattern.getPattern() + "'. Seed used = " + i + ',' + j
        }
    }
}
