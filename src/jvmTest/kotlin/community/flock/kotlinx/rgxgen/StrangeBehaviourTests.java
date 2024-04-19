package community.flock.kotlinx.rgxgen;


import community.flock.kotlinx.rgxgen.util.Util;
import kotlin.random.Random;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class StrangeBehaviourTests {

    @Test
    @Disabled("Manual test")
    public void randomIsNotSoRandomTest() {
        // Note! This works only when LIMIT_VALUE is a power of 2!
        // To overcome this issue in tests use TestingUtilities::newRandom
        final int LIMIT_VALUE = 32;
        for (int seed = 0; seed < 10; seed++) {
            // Each time have different seed!!!!
            Random random = Util.newRandom(seed);
            // NOTE: This value will be always the same
            System.out.println(random.nextInt(LIMIT_VALUE));
            random = Util.newRandom(seed);
            // Here again - first value is always the same, while second value changes.
            System.out.println(random.nextInt(LIMIT_VALUE) + "->" + random.nextInt(LIMIT_VALUE));
        }
    }
}
