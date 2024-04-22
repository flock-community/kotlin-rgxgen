package community.flock.kotlinx.rgxgen

import community.flock.kotlinx.rgxgen.RgxGen.Companion.parse
import community.flock.kotlinx.rgxgen.config.RgxGenOption
import community.flock.kotlinx.rgxgen.config.RgxGenProperties
import community.flock.kotlinx.rgxgen.util.Util.newRandom
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class InfinitePatternConfigTests {
    @Test
    fun maxLengthIsRespectedTest() {
        val properties_3: RgxGenProperties<Any> = RgxGenProperties<Any>()
        RgxGenOption.INFINITE_PATTERN_REPETITION.setInProperties(properties_3, 2)
        val rgxGen_3 = parse(properties_3, "x*")
        for (i in 0..99999) {
            val value = rgxGen_3.generate(newRandom(i))
            Assertions.assertTrue(
                isCorrect(value),
                "Expected to have either empty, or 'x' or 'xx' string. But got $value"
            )
        }
    }

    companion object {
        private fun isCorrect(value: String?): Boolean {
            return value != null && value.isEmpty() || "x" == value || "xx" == value
        }
    }
}
