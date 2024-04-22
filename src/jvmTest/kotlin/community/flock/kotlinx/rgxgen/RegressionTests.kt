package community.flock.kotlinx.rgxgen

import community.flock.kotlinx.rgxgen.RgxGen.Companion.parse
import community.flock.kotlinx.rgxgen.parsing.dflt.ConstantsProvider.LONG_TWO
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.regex.Pattern

class RegressionTests {
    @Test
    fun bug23_parseEscapeCharacterInSquareBracketsTest() {
        val pattern = "[A-Z0-9'\\-/\\.\\s]{0,2}"
        val compile = Pattern.compile(pattern)
        val rgxGen = parse(pattern)
        Assertions.assertNotNull(rgxGen) // Not throwing an exception is a success
        val stringIterator = rgxGen.iterateUnique()
        while (stringIterator!!.hasNext()) {
            Assertions.assertTrue(
                compile.matcher(stringIterator.next())
                    .matches()
            )
        }
    }

    @Test
    fun bug31_topLevelChoiceIsNotRecognizedTest() {
        val pattern = "1|2"
        val rgxGen = parse(pattern)
        Assertions.assertNotNull(rgxGen) // Not throwing an exception is a success
        val stringIterator = rgxGen.iterateUnique()
        Assertions.assertEquals(LONG_TWO, rgxGen.uniqueEstimation)
        Assertions.assertEquals("1", stringIterator!!.next())
        Assertions.assertEquals("2", stringIterator.next())
        Assertions.assertFalse(stringIterator.hasNext())
    }

    @Test
    fun bug32_capAndDollarInTheMiddleAreNotHandledTest() {
        val pattern = "(^x|y$)"
        val rgxGen = parse(pattern)
        Assertions.assertNotNull(rgxGen) // Not throwing an exception is a success
        val stringIterator = rgxGen.iterateUnique()
        Assertions.assertEquals(2, rgxGen.uniqueEstimation)
        Assertions.assertEquals("x", stringIterator!!.next())
        Assertions.assertEquals("y", stringIterator.next())
        Assertions.assertFalse(stringIterator.hasNext())
    }

    @Test
    fun bug53_incorrectHandlingOfDashInSquareBracketsTest() {
        val pattern = "^[a-zA-Z0-9-._:]*$"
        val compile = Pattern.compile(pattern)
        val rgxGen = parse(pattern)
        Assertions.assertNotNull(rgxGen) // Not throwing an exception is a success
        for (i in 0..99) {
            val generated = rgxGen.generate()
            Assertions.assertTrue(
                compile.matcher(generated)
                    .matches(), "'$generated' for pattern '$pattern'"
            )
        }
    }

    @Test
    fun bug53_incorrectHandlingOfDashInSquareBracketsVariation1Test() {
        val pattern = "[\\s-a]"
        val compile = Pattern.compile(pattern)
        val rgxGen = parse(pattern)
        Assertions.assertNotNull(rgxGen) // Not throwing an exception is a success
        for (i in 0..99) {
            val generated = rgxGen.generate()
            Assertions.assertTrue(
                compile.matcher(generated)
                    .matches(), "'$generated' for pattern '$pattern'"
            )
        }
    }

    @Test
    fun bug61_iterateUniqueProducesIncorrectPatternTest() {
        val rgxGen = parse("a?b|c")
        val noGroupIterator = rgxGen.iterateUnique()
        val rgxGen1 = parse("(a?b)|c")
        val withGroupIterator = rgxGen1.iterateUnique()
        while (noGroupIterator!!.hasNext()) {
            Assertions.assertTrue(withGroupIterator!!.hasNext())
            val next = noGroupIterator.next()
            val next1 = withGroupIterator.next()
            println("'$next' : '$next1'")
            Assertions.assertEquals(next, next1)
        }

        Assertions.assertFalse(withGroupIterator!!.hasNext())
    }
}
