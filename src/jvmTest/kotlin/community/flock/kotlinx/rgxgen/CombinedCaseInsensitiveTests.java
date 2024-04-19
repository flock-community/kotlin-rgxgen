package community.flock.kotlinx.rgxgen;

import community.flock.kotlinx.rgxgen.config.RgxGenOption;
import community.flock.kotlinx.rgxgen.config.RgxGenProperties;
import community.flock.kotlinx.rgxgen.data.TestPatternCaseInsensitive;
import community.flock.kotlinx.rgxgen.testutil.TestingUtilities;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class CombinedCaseInsensitiveTests extends CombinedTestTemplate<TestPatternCaseInsensitive> {
    public static Stream<TestPatternCaseInsensitive> getAllPatterns() {
        return Arrays.stream(TestPatternCaseInsensitive.values());
    }

    @ParameterizedTest
    @MethodSource("getAllPatterns")
    public void countTest(TestPatternCaseInsensitive testPattern) {
        assumeTrue(testPattern.hasEstimatedCount());
        RgxGenProperties properties = new RgxGenProperties();
        RgxGenOption.CASE_INSENSITIVE.setInProperties(properties, true);
        RgxGen rgxGen = RgxGen.parse(properties, testPattern.getPattern());
        assertEquals(testPattern.getEstimatedCount(), rgxGen.getUniqueEstimation());
    }

    @ParameterizedTest
    @MethodSource("getAllPatterns")
    public void generateUniqueTest(TestPatternCaseInsensitive testPattern) {
        assumeTrue(testPattern.hasAllUniqueValues());
        RgxGenProperties properties = new RgxGenProperties();
        RgxGenOption.CASE_INSENSITIVE.setInProperties(properties, true);
        RgxGen rgxGen = RgxGen.parse(properties, testPattern.getPattern());
        assertEquals(testPattern.getAllUniqueValues(), TestingUtilities.iteratorToList(rgxGen.iterateUnique()));
    }

    @ParameterizedTest
    @MethodSource("getAllPatterns")
    public void classRgxGenCaseInsensitiveTest(TestPatternCaseInsensitive testPattern) {
        RgxGenProperties properties = new RgxGenProperties();
        RgxGenOption.CASE_INSENSITIVE.setInProperties(properties, true);
        RgxGen rgxGen = RgxGen.parse(properties, testPattern.getPattern());
        Iterator<String> iterator = rgxGen.stream();
        Iterable<String> iterable = () -> iterator;
        List<String> strings = StreamSupport.stream(iterable.spliterator(), false)
                .limit(1000)
                .collect(Collectors.toList());
        Pattern caseSensitivePattern = Pattern.compile(testPattern.getPattern());
        boolean atLeastOneCaseSensitiveMismatch = false;
        for (String generated : strings) {
            boolean result = isValidGenerated(testPattern, generated, Pattern.CASE_INSENSITIVE);
            boolean caseSensitiveMatches = !caseSensitivePattern.matcher(generated).matches();
            assertTrue(result, "Text: '" + generated + "' does not match pattern " + testPattern.getPattern());
            atLeastOneCaseSensitiveMismatch = atLeastOneCaseSensitiveMismatch || caseSensitiveMatches;
        }
        assertTrue(atLeastOneCaseSensitiveMismatch);
    }
}
