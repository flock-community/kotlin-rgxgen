package community.flock.kotlinx.rgxgen;

import community.flock.kotlinx.rgxgen.config.RgxGenOption;
import community.flock.kotlinx.rgxgen.config.RgxGenProperties;
import community.flock.kotlinx.rgxgen.model.MatchType;
import community.flock.kotlinx.rgxgen.model.SymbolRange;
import community.flock.kotlinx.rgxgen.nodes.*;
import community.flock.kotlinx.rgxgen.parsing.NodeTreeBuilder;
import community.flock.kotlinx.rgxgen.parsing.dflt.DefaultTreeBuilder;
import community.flock.kotlinx.rgxgen.util.Util;
import community.flock.kotlinx.rgxgen.visitors.GenerationVisitor;
import community.flock.kotlinx.rgxgen.visitors.NotMatchingGenerationVisitor;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;


public class NotMatchingGenerationTests {

    private static Collection<Object[]> initialData() {
        return Arrays.asList(new Object[][]{
                {"[a-z0-5]", SymbolSet.ofAsciiRanges("[a-z0-5]", Arrays.asList(SymbolRange.range('a', 'z'), SymbolRange.range('0', '5')), MatchType.POSITIVE)},
                {"abc|def", new Choice("abc|def", new FinalSymbol("abc"), new FinalSymbol("def"))},
                {"helloworld", new FinalSymbol("helloworld")},
                {"a{2,3}", new Repeat("a{2,3}", new FinalSymbol("a"), 2, 3)},
                {"a[a-z]", new Sequence("a[a-z]", new FinalSymbol("a"),
                                        SymbolSet.ofAsciiRanges("[a-z]", Collections.singletonList(SymbolRange.range('a', 'z')), MatchType.POSITIVE))},
                {"([a-z])\\1", new Sequence("([a-z])\\1", new Group("([a-z])", 1,
                                                                    SymbolSet.ofAsciiRanges("[a-z]", Collections.singletonList(SymbolRange.range('a', 'z')), MatchType.POSITIVE)),
                                            new GroupRef("\\1", 1)
                )},
                {"foo(?!bar)", new Sequence("foo(?!bar)",
                                            new FinalSymbol("foo"),
                                            new NotSymbol("bar", new FinalSymbol("bar"))
                )}
        });
    }

    public static Stream<Arguments> getTestData() {
        return initialData()
                .stream()
                .flatMap(arr -> IntStream.range(0, 100)
                                         .mapToObj(i -> Arguments.of(arr[0], arr[1], i)));
    }

    @ParameterizedTest
    @MethodSource("getTestData")
    public void nodeVisitingWorksTest(String pattern, Node expectedNode, int seed) {
        NodeTreeBuilder builder = new DefaultTreeBuilder(pattern, null);
        Node node = builder.get();
        // Verify that nodes are correct
        assertEquals(expectedNode.toString(), node.toString());

        GenerationVisitor visitor = NotMatchingGenerationVisitor.builder()
                                                                .withRandom(Util.newRandom(seed))
                                                                .get();
        node.visit(visitor);
        boolean matches = Pattern.compile(pattern)
                                 .matcher(visitor.getString())
                                 .matches();
        assertFalse(matches, "Should not match " + pattern + " got " + visitor.getString());
    }

    @ParameterizedTest
    @MethodSource("getTestData")
    public void caseInsensitiveVisitingWorksTest(String pattern, Node expectedNode, int seed) {
        NodeTreeBuilder builder = new DefaultTreeBuilder(pattern, null);
        Node node = builder.get();
        // Verify that nodes are correct
        assertEquals(expectedNode.toString(), node.toString());

        RgxGenProperties properties = new RgxGenProperties();
        RgxGenOption.CASE_INSENSITIVE.setInProperties(properties, true);
        GenerationVisitor visitor = NotMatchingGenerationVisitor.builder()
                                                                .withRandom(Util.newRandom(seed))
                                                                .withProperties(properties)
                                                                .get();
        node.visit(visitor);
        boolean matches = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE)
                                 .matcher(visitor.getString())
                                 .matches();
        assertFalse(matches, "Should not match " + pattern + " got " + visitor.getString());
    }
}
