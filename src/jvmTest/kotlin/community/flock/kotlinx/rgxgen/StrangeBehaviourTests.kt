package community.flock.kotlinx.rgxgen

import community.flock.kotlinx.rgxgen.util.Util.newRandom
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test


class StrangeBehaviourTests {
    @Test
    @Disabled("Manual test")
    fun randomIsNotSoRandomTest() {
        // Note! This works only when LIMIT_VALUE is a power of 2!
        // To overcome this issue in tests use TestingUtilities::newRandom
        val LIMIT_VALUE = 32
        for (seed in 0..9) {
            // Each time have different seed!!!!
            var random = newRandom(seed)
            // NOTE: This value will be always the same
            println(random.nextInt(LIMIT_VALUE))
            random = newRandom(seed)
            // Here again - first value is always the same, while second value changes.
            println(random.nextInt(LIMIT_VALUE).toString() + "->" + random.nextInt(LIMIT_VALUE))
        }
    }
}
