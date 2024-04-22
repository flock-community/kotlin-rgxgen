package community.flock.kotlinx.rgxgen

import community.flock.kotlinx.rgxgen.config.RgxGenOption
import community.flock.kotlinx.rgxgen.config.RgxGenProperties
import community.flock.kotlinx.rgxgen.model.MatchType
import community.flock.kotlinx.rgxgen.model.SymbolRange.Companion.range
import community.flock.kotlinx.rgxgen.nodes.Choice
import community.flock.kotlinx.rgxgen.nodes.FinalSymbol
import community.flock.kotlinx.rgxgen.nodes.Group
import community.flock.kotlinx.rgxgen.nodes.GroupRef
import community.flock.kotlinx.rgxgen.nodes.Node
import community.flock.kotlinx.rgxgen.nodes.NotSymbol
import community.flock.kotlinx.rgxgen.nodes.Repeat
import community.flock.kotlinx.rgxgen.nodes.SymbolSet.Companion.ofAsciiRanges
import community.flock.kotlinx.rgxgen.parsing.NodeTreeBuilder
import community.flock.kotlinx.rgxgen.parsing.dflt.DefaultTreeBuilder
import community.flock.kotlinx.rgxgen.util.Util.newRandom
import community.flock.kotlinx.rgxgen.visitors.NotMatchingGenerationVisitor
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.Arrays
import java.util.regex.Pattern
import java.util.stream.IntStream
import java.util.stream.Stream

class NotMatchingGenerationTests {
    @ParameterizedTest
    @MethodSource("getTestData")
    fun nodeVisitingWorksTest(pattern: String, expectedNode: Node, seed: Int) {
        val builder: NodeTreeBuilder = DefaultTreeBuilder(pattern, null)
        val node = builder.get()
        // Verify that nodes are correct
        Assertions.assertEquals(expectedNode.toString(), node.toString())

        val visitor = NotMatchingGenerationVisitor.builder()
            .withRandom(newRandom(seed))
            .get()
        node!!.visit(visitor)
        val matches = Pattern.compile(pattern)
            .matcher(visitor.string)
            .matches()
        Assertions.assertFalse(matches, "Should not match " + pattern + " got " + visitor.string)
    }

    @ParameterizedTest
    @MethodSource("getTestData")
    fun caseInsensitiveVisitingWorksTest(pattern: String, expectedNode: Node, seed: Int) {
        val builder: NodeTreeBuilder = DefaultTreeBuilder(pattern, null)
        val node = builder.get()
        // Verify that nodes are correct
        Assertions.assertEquals(expectedNode.toString(), node.toString())

        val properties: RgxGenProperties<Any> = RgxGenProperties<Any>()
        RgxGenOption.CASE_INSENSITIVE.setInProperties(properties, true)
        val visitor = NotMatchingGenerationVisitor.builder()
            .withRandom(newRandom(seed))
            .withProperties(properties)
            .get()
        node!!.visit(visitor)
        val matches = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE)
            .matcher(visitor.string)
            .matches()
        Assertions.assertFalse(matches, "Should not match " + pattern + " got " + visitor.string)
    }

    companion object {
        private fun initialData(): Collection<Array<Any>> {
            return Arrays.asList(
                *arrayOf(
                    arrayOf(
                        "[a-z0-5]",
                        ofAsciiRanges("[a-z0-5]", Arrays.asList(range('a', 'z'), range('0', '5')), MatchType.POSITIVE)
                    ),
                    arrayOf("abc|def", Choice("abc|def", FinalSymbol("abc"), FinalSymbol("def"))),
                    arrayOf("helloworld", FinalSymbol("helloworld")),
                    arrayOf("a{2,3}", Repeat("a{2,3}", FinalSymbol("a"), 2, 3)),
                    arrayOf(
                        "a[a-z]", community.flock.kotlinx.rgxgen.nodes.Sequence(
                            "a[a-z]", FinalSymbol("a"),
                            ofAsciiRanges("[a-z]", listOf(range('a', 'z')), MatchType.POSITIVE)
                        )
                    ),
                    arrayOf(
                        "([a-z])\\1", community.flock.kotlinx.rgxgen.nodes.Sequence(
                            "([a-z])\\1", Group(
                                "([a-z])", 1,
                                ofAsciiRanges("[a-z]", listOf(range('a', 'z')), MatchType.POSITIVE)
                            ),
                            GroupRef("\\1", 1)
                        )
                    ),
                    arrayOf(
                        "foo(?!bar)", community.flock.kotlinx.rgxgen.nodes.Sequence(
                            "foo(?!bar)",
                            FinalSymbol("foo"),
                            NotSymbol("bar", FinalSymbol("bar"))
                        )
                    )
                )
            )
        }

        @JvmStatic
        val testData: Stream<Arguments>
            get() = initialData()
                .stream()
                .flatMap { arr: Array<Any> ->
                    IntStream.range(0, 100)
                        .mapToObj { i: Int ->
                            Arguments.of(
                                arr[0], arr[1], i
                            )
                        }
                }
    }
}
