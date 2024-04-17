package community.flock.kotlinx.rgxgen;

import community.flock.kotlinx.rgxgen.RgxGen;
import community.flock.kotlinx.rgxgen.config.RgxGenOption;
import community.flock.kotlinx.rgxgen.config.RgxGenProperties;
import community.flock.kotlinx.rgxgen.testutil.TestingUtilities;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class InfinitePatternConfigTests {

    @Test
    public void maxLengthIsRespectedTest() {
        RgxGenProperties properties_3 = new RgxGenProperties();
        RgxGenOption.INFINITE_PATTERN_REPETITION.setInProperties(properties_3, 2);
        RgxGen rgxGen_3 = RgxGen.parse(properties_3, "x*");
        for (int i = 0; i < 100000; i++) {
            String value = rgxGen_3.generate(TestingUtilities.newRandom(i));
            assertTrue(isCorrect(value), "Expected to have either empty, or 'x' or 'xx' string. But got " + value);
        }
    }

    private static boolean isCorrect(String value) {
        return value != null && value.isEmpty()
                || "x".equals(value) || "xx".equals(value);
    }
}
