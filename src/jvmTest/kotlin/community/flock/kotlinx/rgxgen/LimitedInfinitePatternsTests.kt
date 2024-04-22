package community.flock.kotlinx.rgxgen

import community.flock.kotlinx.rgxgen.config.RgxGenProperties
import community.flock.kotlinx.rgxgen.data.stream
import community.flock.kotlinx.rgxgen.nodes.FinalSymbol
import community.flock.kotlinx.rgxgen.nodes.Node
import community.flock.kotlinx.rgxgen.nodes.Repeat
import community.flock.kotlinx.rgxgen.nodes.SymbolSet.Companion.ofDotPattern
import community.flock.kotlinx.rgxgen.parsing.dflt.ConstantsProvider.makeAsciiCharacterArray
import community.flock.kotlinx.rgxgen.testutil.TestingUtilities.iteratorToList
import community.flock.kotlinx.rgxgen.visitors.GenerationVisitor
import community.flock.kotlinx.rgxgen.visitors.UniqueGenerationVisitor
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.io.Serializable
import java.util.regex.Pattern
import java.util.stream.Collectors
import java.util.stream.IntStream
import java.util.stream.Stream

class LimitedInfinitePatternsTests {
    @ParameterizedTest
    @MethodSource("getTestData")
    fun generateTest(aRegex: String?, aNode: Node, aExpectedUnique: List<String?>?) {
        val p = Pattern.compile(aRegex)

        for (i in 0..99) {
            val generationVisitor = GenerationVisitor.builder()
                .get()
            aNode.visit(generationVisitor)
            Assertions.assertTrue(
                p.matcher(generationVisitor.string)
                    .matches()
            )
        }
    }

    @ParameterizedTest
    @MethodSource("getTestData")
    fun generateUniqueTest(aRegex: String?, aNode: Node, aExpectedUnique: List<String?>?) {
        val v = UniqueGenerationVisitor(RgxGenProperties<Any?>())
        aNode.visit(v)
        Assertions.assertEquals(aExpectedUnique, iteratorToList<String?>(v.uniqueStrings))
    }

    companion object {
        @JvmStatic
        val testData: Stream<Arguments>
            get() = Stream.of(
                Arguments.arguments(
                    "a*",  // If use unlimited repetition that will cause an error when trying to save all data in memory, thus we limit repetition times
                    Repeat("a*", FinalSymbol("a"), 0, 10),
                    IntStream.iterate(0) { value: Int -> value + 1 }
                        .limit(11)
                        .mapToObj { v: Int ->
                            Stream.generate { "a" }
                                .limit(v.toLong())
                                .reduce("") { obj: String, str: String -> obj + str }
                        }
                        .collect(Collectors.toList())
                ),
                Arguments.arguments(
                    "aa+",  // If use unlimited repetition that will cause an error when trying to save all data in memory, thus we limit repetition times
                    community.flock.kotlinx.rgxgen.nodes.Sequence(
                        "aa+",
                        FinalSymbol("a"),
                        Repeat("a+", FinalSymbol("a"), 1, 10)
                    ),
                    IntStream.iterate(1) { value: Int -> value + 1 }
                        .limit(10)
                        .mapToObj { v: Int ->
                            'a'.toString() + Stream.generate { "a" }
                                .limit(v.toLong())
                                .reduce("") { obj: String, str: String -> obj + str }
                        }
                        .collect(Collectors.toList())
                ),
                Arguments.arguments(
                    "a.*",  // If use unlimited repetition that will cause an error when trying to save all data in memory, thus we limit repetition times
                    community.flock.kotlinx.rgxgen.nodes.Sequence(
                        "a.*",
                        FinalSymbol("a"),
                        Repeat(".*", ofDotPattern(null), 0, 2)
                    ),
                    Stream.concat(
                        Stream.of(""), Stream.concat(stream(makeAsciiCharacterArray()),
                            stream(makeAsciiCharacterArray())
                                .flatMap { symbol: Char ->
                                    stream(makeAsciiCharacterArray())
                                        .map { v: Char -> symbol.toString() + v }
                                })
                    )
                        .map { v: Serializable -> "a$v" }
                        .collect(Collectors.toList())
                )
            )
    }
}
