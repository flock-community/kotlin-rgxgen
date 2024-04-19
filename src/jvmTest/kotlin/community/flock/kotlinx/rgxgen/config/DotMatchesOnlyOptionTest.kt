package community.flock.kotlinx.rgxgen.config

import community.flock.kotlinx.rgxgen.RgxGen
import community.flock.kotlinx.rgxgen.RgxGen.Companion.parse
import community.flock.kotlinx.rgxgen.model.RgxGenCharsDefinition.Companion.of
import community.flock.kotlinx.rgxgen.model.UnicodeCategory
import community.flock.kotlinx.rgxgen.util.Util.newRandom
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.regex.Pattern

/* **************************************************************************
  Copyright 2019 Vladislavs Varslavans

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
/ * **************************************************************************/


internal class DotMatchesOnlyOptionTest {
    @Test
    fun doesNotFailWithoutPropertySet() {
        val rgxGen = Assertions.assertDoesNotThrow<RgxGen> { parse(".") }
        Assertions.assertDoesNotThrow<String> { rgxGen.generate() }
        Assertions.assertDoesNotThrow<String> { rgxGen.generateNotMatching() }
        Assertions.assertDoesNotThrow(rgxGen::uniqueEstimation)
    }

    @Test
    fun verifyGenerationTest() {
        val properties: RgxGenProperties<Any> = RgxGenProperties()
        val permittedCharacters = "abc"
        RgxGenOption.DOT_MATCHES_ONLY.setInProperties(properties, of(permittedCharacters))
        val random = newRandom(100500)
        val rgxGen = parse(properties, ".")
        for (i in 0 until COUNT_OF_ITERATIONS) {
            val generatedValue = rgxGen.generate(random)
            Assertions.assertTrue(permittedCharacters.contains(generatedValue))
        }
    }

    @Test
    fun verifyUnicodeGenerationTest() {
        val properties: RgxGenProperties<Any> = RgxGenProperties()
        val pattern = Pattern.compile("\\p{InCyrillic}")
        RgxGenOption.DOT_MATCHES_ONLY.setInProperties(properties, of(UnicodeCategory.IN_CYRILLIC))
        val random = newRandom(100500)
        val rgxGen = parse(properties, ".")
        for (i in 0 until COUNT_OF_ITERATIONS) {
            val generatedValue = rgxGen.generate(random)
            Assertions.assertTrue(pattern.matcher(generatedValue).matches())
        }
    }

    @Test
    fun verifyUnicodeGenerationNotMatchingTest() {
        val properties: RgxGenProperties<Any> = RgxGenProperties()
        val pattern = Pattern.compile("\\p{InCyrillic}")
        RgxGenOption.DOT_MATCHES_ONLY.setInProperties(properties, of(UnicodeCategory.IN_CYRILLIC))
        val random = newRandom(100500)
        val rgxGen = parse(properties, ".")
        for (i in 0 until COUNT_OF_ITERATIONS) {
            val generatedValue = rgxGen.generateNotMatching(random)
            Assertions.assertFalse(pattern.matcher(generatedValue).matches())
        }
    }

    @Test
    fun verifyCaseInsensitiveGenerationOptionMattersTest() {
        val properties: RgxGenProperties<Any> = RgxGenProperties()
        val permittedCharacters = "abcABC"
        RgxGenOption.DOT_MATCHES_ONLY.setInProperties(properties, of("abc"))
        RgxGenOption.CASE_INSENSITIVE.setInProperties(properties, true)
        val random = newRandom(100500)
        val rgxGen = parse(properties, ".")
        for (i in 0 until COUNT_OF_ITERATIONS) {
            val generatedValue = rgxGen.generate(random)
            Assertions.assertTrue(permittedCharacters.contains(generatedValue))
        }
    }

    @Test
    fun verifyCorrectlyEstimatesCountTest() {
        val properties: RgxGenProperties<Any> = RgxGenProperties()
        val permittedCharacters = "abc"
        RgxGenOption.DOT_MATCHES_ONLY.setInProperties(properties, of(permittedCharacters))
        val rgxGen = parse(properties, ".")
        Assertions.assertEquals(3, rgxGen.uniqueEstimation)
    }

    @Test
    fun verifyCorrectlyEstimatesCaseInsensitiveCountTest() {
        val properties: RgxGenProperties<Any> = RgxGenProperties()
        val permittedCharacters = "abc"
        RgxGenOption.DOT_MATCHES_ONLY.setInProperties(properties, of(permittedCharacters))
        RgxGenOption.CASE_INSENSITIVE.setInProperties(properties, true)
        val rgxGen = parse(properties, ".")
        Assertions.assertEquals(6, rgxGen.uniqueEstimation)
    }

    companion object {
        const val COUNT_OF_ITERATIONS: Int = 1000
    }
}
