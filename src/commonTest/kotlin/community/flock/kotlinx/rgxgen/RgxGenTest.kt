package community.flock.kotlinx.rgxgen

import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals

class RgxGenTest {

    @Test
    fun basicTest() {
        val seed = Random(0L)
        val rgxGen = RgxGen.parse("Willem|Jerre")
        assertEquals("Jerre", rgxGen.generate(seed))
        assertEquals("Willem", rgxGen.generate(seed))
    }
}