package community.flock.kotlinx.rgxgen.config;

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
/* **************************************************************************/


import community.flock.kotlinx.rgxgen.RgxGen;
import community.flock.kotlinx.rgxgen.model.RgxGenCharsDefinition;
import community.flock.kotlinx.rgxgen.model.UnicodeCategory;
import community.flock.kotlinx.rgxgen.util.Util;
import kotlin.random.Random;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class DotMatchesOnlyOptionTest {


    public static final int COUNT_OF_ITERATIONS = 1000;

    @Test
    void doesNotFailWithoutPropertySet() {
        RgxGen rgxGen = assertDoesNotThrow(() -> RgxGen.parse("."));
        assertDoesNotThrow(() -> rgxGen.generate());
        assertDoesNotThrow(() -> rgxGen.generateNotMatching());
        assertDoesNotThrow(rgxGen::getUniqueEstimation);
    }

    @Test
    void verifyGenerationTest() {
        RgxGenProperties properties = new RgxGenProperties();
        String permittedCharacters = "abc";
        RgxGenOption.DOT_MATCHES_ONLY.setInProperties(properties, RgxGenCharsDefinition.of(permittedCharacters));
        Random random = Util.newRandom(100500);
        RgxGen rgxGen = RgxGen.parse(properties, ".");
        for (int i = 0; i < COUNT_OF_ITERATIONS; i++) {
            String generatedValue = rgxGen.generate(random);
            assertTrue(permittedCharacters.contains(generatedValue));
        }
    }

    @Test
    void verifyUnicodeGenerationTest() {
        RgxGenProperties properties = new RgxGenProperties();
        Pattern pattern = Pattern.compile("\\p{InCyrillic}");
        RgxGenOption.DOT_MATCHES_ONLY.setInProperties(properties, RgxGenCharsDefinition.of(UnicodeCategory.IN_CYRILLIC));
        Random random = Util.newRandom(100500);
        RgxGen rgxGen = RgxGen.parse(properties, ".");
        for (int i = 0; i < COUNT_OF_ITERATIONS; i++) {
            String generatedValue = rgxGen.generate(random);
            assertTrue(pattern.matcher(generatedValue).matches());
        }
    }

    @Test
    void verifyUnicodeGenerationNotMatchingTest() {
        RgxGenProperties properties = new RgxGenProperties();
        Pattern pattern = Pattern.compile("\\p{InCyrillic}");
        RgxGenOption.DOT_MATCHES_ONLY.setInProperties(properties, RgxGenCharsDefinition.of(UnicodeCategory.IN_CYRILLIC));
        Random random = Util.newRandom(100500);
        RgxGen rgxGen = RgxGen.parse(properties, ".");
        for (int i = 0; i < COUNT_OF_ITERATIONS; i++) {
            String generatedValue = rgxGen.generateNotMatching(random);
            assertFalse(pattern.matcher(generatedValue).matches());
        }
    }

    @Test
    void verifyCaseInsensitiveGenerationOptionMattersTest() {
        RgxGenProperties properties = new RgxGenProperties();
        String permittedCharacters = "abcABC";
        RgxGenOption.DOT_MATCHES_ONLY.setInProperties(properties, RgxGenCharsDefinition.of("abc"));
        RgxGenOption.CASE_INSENSITIVE.setInProperties(properties, true);
        Random random = Util.newRandom(100500);
        RgxGen rgxGen = RgxGen.parse(properties, ".");
        for (int i = 0; i < COUNT_OF_ITERATIONS; i++) {
            String generatedValue = rgxGen.generate(random);
            assertTrue(permittedCharacters.contains(generatedValue));
        }
    }

    @Test
    void verifyCorrectlyEstimatesCountTest() {
        RgxGenProperties properties = new RgxGenProperties();
        String permittedCharacters = "abc";
        RgxGenOption.DOT_MATCHES_ONLY.setInProperties(properties, RgxGenCharsDefinition.of(permittedCharacters));
        RgxGen rgxGen = RgxGen.parse(properties, ".");
        assertEquals(Long.valueOf(3), rgxGen.getUniqueEstimation());
    }

    @Test
    void verifyCorrectlyEstimatesCaseInsensitiveCountTest() {
        RgxGenProperties properties = new RgxGenProperties();
        String permittedCharacters = "abc";
        RgxGenOption.DOT_MATCHES_ONLY.setInProperties(properties, RgxGenCharsDefinition.of(permittedCharacters));
        RgxGenOption.CASE_INSENSITIVE.setInProperties(properties, true);
        RgxGen rgxGen = RgxGen.parse(properties, ".");
        assertEquals(Long.valueOf(6), rgxGen.getUniqueEstimation());
    }
}