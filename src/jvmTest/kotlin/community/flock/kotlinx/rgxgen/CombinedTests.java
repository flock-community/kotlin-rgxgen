package community.flock.kotlinx.rgxgen;

import community.flock.kotlinx.rgxgen.config.RgxGenOption;
import community.flock.kotlinx.rgxgen.config.RgxGenProperties;
import community.flock.kotlinx.rgxgen.data.DataInterface;
import community.flock.kotlinx.rgxgen.data.TestPattern;
import community.flock.kotlinx.rgxgen.nodes.Node;
import community.flock.kotlinx.rgxgen.parsing.NodeTreeBuilder;
import community.flock.kotlinx.rgxgen.parsing.dflt.DefaultTreeBuilder;
import community.flock.kotlinx.rgxgen.testutil.NodePatternVerifyingVisitor;
import community.flock.kotlinx.rgxgen.testutil.TestingUtilities;
import community.flock.kotlinx.rgxgen.util.Util;
import community.flock.kotlinx.rgxgen.visitors.UniqueGenerationVisitor;
import community.flock.kotlinx.rgxgen.visitors.UniqueValuesCountingVisitor;
import kotlin.random.Random;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class CombinedTests extends CombinedTestTemplate<TestPattern> {
    public static Stream<TestPattern> getPatterns() {
        return Arrays.stream(TestPattern.values());
    }


    @ParameterizedTest
    @MethodSource("getPatterns")
    public void parseTest(TestPattern testPattern) {
        NodeTreeBuilder defaultTreeBuilder = new DefaultTreeBuilder(testPattern.getPattern(), null);
        Node node = defaultTreeBuilder.get();
        Assertions.assertEquals(testPattern.getResultNode().toString(), node.toString());
        NodePatternVerifyingVisitor visitor = new NodePatternVerifyingVisitor(testPattern.getResultNode());
        node.visit(visitor);
        assertTrue(visitor.getErrors().isEmpty(), visitor.getErrors().toString());
    }

    @ParameterizedTest
    @MethodSource("getPatterns")
    public void countUniqueUsingVisitorTest(TestPattern testPattern) {
        assumeTrue(testPattern.hasEstimatedCount());
        UniqueValuesCountingVisitor v = new UniqueValuesCountingVisitor(new RgxGenProperties());
        testPattern.getResultNode().visit(v);
        assertEquals(testPattern.getEstimatedCount(), v.getEstimation());
    }

    @ParameterizedTest
    @MethodSource("getPatterns")
    public void countUniqueTest(TestPattern testPattern) {
        assumeTrue(testPattern.hasEstimatedCount());
        RgxGen rgxGen = RgxGen.parse(testPattern.getPattern());
        assertEquals(testPattern.getEstimatedCount(), rgxGen.getUniqueEstimation());
    }

    @ParameterizedTest
    @MethodSource("getPatterns")
    public void generateUniqueTest(TestPattern testPattern) {
        assumeTrue(testPattern.hasAllUniqueValues());

        UniqueGenerationVisitor v = new UniqueGenerationVisitor(new RgxGenProperties());
        testPattern.getResultNode().visit(v);
        assertEquals(testPattern.getAllUniqueValues(), TestingUtilities.iteratorToList(v.getUniqueStrings()));
    }

    @ParameterizedTest
    @MethodSource("getPatterns")
    public void classRgxGenTest(TestPattern testPattern) {
        RgxGen rgxGen = RgxGen.parse(testPattern.getPattern());
        if (testPattern.hasEstimatedCount()) {
            assertEquals(testPattern.getEstimatedCount(), rgxGen.getUniqueEstimation());
        }
        for (int i = 0; i < 100; i++) {
            Random rand = Util.newRandom(i);
            for (int j = 0; j < 10; j++) {
                String generated = rgxGen.generate(rand);
                boolean result = isValidGenerated(testPattern, generated, 0);
                assertTrue(result, createMessage(generated, testPattern, i, j));
            }
        }
    }

    @ParameterizedTest
    @MethodSource("getPatterns")
    public void classRgxGenCaseInsensitiveTest(TestPattern testPattern) {
        RgxGenProperties properties = new RgxGenProperties();
        RgxGenOption.CASE_INSENSITIVE.setInProperties(properties, true);
        RgxGen rgxGen = RgxGen.parse(properties, testPattern.getPattern());

        for (int i = 0; i < 100; i++) {
            Random random = Util.newRandom(i);
            for (int j = 0; j < 10; j++) {
                String generated = rgxGen.generate(random);
                boolean result = isValidGenerated(testPattern, generated, Pattern.CASE_INSENSITIVE);
                assertTrue(result, createMessage(generated, testPattern, i, j));
            }
        }
    }

    private static String createMessage(String generated, DataInterface pattern, int i, int j) {
        return "Text: '" + generated + "' does not match pattern '" + pattern.getPattern() + "'. Seed used = " + i + ',' + j;
    }
}
