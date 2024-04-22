package community.flock.kotlinx.rgxgen.model

import community.flock.kotlinx.rgxgen.RgxGen.Companion.parse
import community.flock.kotlinx.rgxgen.model.data.CategoryTestData
import community.flock.kotlinx.rgxgen.parsing.dflt.ConstantsProvider.UNICODE_SYMBOL_RANGE
import community.flock.kotlinx.rgxgen.util.Util.compactOverlappingRangesAndSymbols
import community.flock.kotlinx.rgxgen.util.Util.newRandom
import community.flock.kotlinx.rgxgen.util.chars.CharList
import community.flock.kotlinx.rgxgen.util.chars.CharList.Companion.empty
import community.flock.kotlinx.rgxgen.util.chars.CharPredicate
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Nested
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import org.junit.jupiter.params.provider.MethodSource
import java.util.regex.Pattern

internal class UnicodeCategoryTest {
    @ParameterizedTest
    @EnumSource(UnicodeCategory::class)
    fun keysAreDefinedForEachCategoryTest(unicodeCategory: UnicodeCategory) {
        Assertions.assertNotNull(unicodeCategory.keys)
        Assertions.assertFalse(unicodeCategory.keys.isEmpty())
    }

    @ParameterizedTest
    @EnumSource(UnicodeCategory::class)
    fun descriptionIsDefinedForEachCategoryTest(unicodeCategory: UnicodeCategory) {
        Assertions.assertNotNull(unicodeCategory.description)
    }

    @ParameterizedTest
    @EnumSource(UnicodeCategory::class)
    fun isValidCategoryTest(unicodeCategory: UnicodeCategory) {
        val rangesPresent = unicodeCategory.symbolRanges != null && !unicodeCategory.symbolRanges.isEmpty()
        val symbolsPresent = unicodeCategory.symbols != null && unicodeCategory.symbols.size != 0
        Assertions.assertTrue(rangesPresent || symbolsPresent)
    }

    @Nested
    inner class SymbolsInCategoryTest : UnicodeCategoryGenerateTestBase() {
        @ParameterizedTest(name = "{index}: {0}")
        @MethodSource("getCategoryTestData")
        fun correctSymbolsInCategoryTest(categoryTestData: CategoryTestData) {
            val characters = categoryTestData.categoryCharacters
            val wrongCharacters = empty()
            for (i in 0 until characters.size()) {
                val character = characters[i]
                val pattern = "\\p" + categoryTestData.key
                registerTestedCategory(categoryTestData.category)
                if (!Pattern.compile(pattern).matcher("" + character).matches()) {
                    wrongCharacters.add(character.code)
                }
            }

            if (!wrongCharacters.isEmpty) {
                printWrongCharacters(categoryTestData, wrongCharacters)
                Assertions.fail<Any>("There are multiple characters that do not belong to a category")
            }
        }
    }

    @Nested
    inner class SymbolsNotInCategoryTest : UnicodeCategoryGenerateTestBase() {
        @ParameterizedTest(name = "{index}: {0}")
        @MethodSource("getCategoryTestData")
        fun correctSymbolsNotInCategoryTest(categoryTestData: CategoryTestData) {
            val characters: CharList = UNICODE_SYMBOL_RANGE
                .chars()
                .except(CharPredicate { c: Char -> categoryTestData.category.contains(c) })

            val wrongCharacters = empty()

            registerTestedCategory(categoryTestData.category)
            for (i in 0 until characters.size()) {
                val character = characters[i]
                val pattern = "\\P" + categoryTestData.key
                registerTestedCategory(categoryTestData.category)
                if (!Pattern.compile(pattern).matcher("" + character).matches()) {
                    wrongCharacters.add(character.code)
                }
            }

            if (!wrongCharacters.isEmpty) {
                printWrongCharacters(categoryTestData, wrongCharacters)
                Assertions.fail<Any>("There are multiple characters that do not belong to a category")
            }
        }
    }

    @Nested
    inner class GenerateInCategoryTest : UnicodeCategoryGenerateTestBase() {
        @ParameterizedTest(name = "{index}: {0}")
        @MethodSource("getCategoryTestData")
        fun generateInCategoryTest(categoryTestData: CategoryTestData) {
            val inCategoryPattern = categoryTestData.inCategoryPattern
            val pattern = inCategoryPattern.pattern()
            val rgxGen = parse(pattern)
            val random = newRandom(pattern.hashCode())
            registerTestedCategory(categoryTestData.category)
            val rgxGenTestPattern = RgxGenTestPattern(pattern, inCategoryPattern, categoryTestData.category, true)
            val validationResult = ValidationResult()
            for (i in 0 until GENERATE_ITERATIONS) {
                validateGeneratedText(rgxGenTestPattern, { rgxGen.generate(random) }, validationResult)
            }
            validationResult.assertPassed()
        }
    }

    @Nested
    inner class GenerateInCategoryNotMatchingTest : UnicodeCategoryGenerateTestBase() {
        @ParameterizedTest(name = "{index}: {0}")
        @MethodSource("getCategoryTestData")
        fun generateInCategoryNotMatchingTest(categoryTestData: CategoryTestData) {
            val inCategoryPattern = categoryTestData.inCategoryPattern
            val pattern = inCategoryPattern.pattern()
            val rgxGen = parse(pattern)
            val random = newRandom(pattern.hashCode())
            registerTestedCategory(categoryTestData.category)
            val rgxGenTestPattern = RgxGenTestPattern(pattern, inCategoryPattern, categoryTestData.category, false)
            val validationResult = ValidationResult()
            for (i in 0 until GENERATE_ITERATIONS) {
                validateGeneratedText(rgxGenTestPattern, { rgxGen.generateNotMatching(random) }, validationResult)
            }
            validationResult.assertPassed()
        }
    }

    @Nested
    inner class GenerateUniqueTest : UnicodeCategoryGenerateTestBase() {
        @ParameterizedTest(name = "{index}: {0}")
        @MethodSource("getCategoryTestData")
        fun generateUniqueTest(categoryTestData: CategoryTestData) {
            val inCategoryPattern = categoryTestData.inCategoryPattern
            val pattern = inCategoryPattern.pattern()
            val rgxGen = parse(pattern)
            val stringIterator = rgxGen.iterateUnique()
            registerTestedCategory(categoryTestData.category)
            val rgxGenTestPattern = RgxGenTestPattern(pattern, inCategoryPattern, categoryTestData.category, true)
            val validationResult = ValidationResult()
            var i = 0
            while (i < GENERATE_ITERATIONS && stringIterator!!.hasNext()) {
                validateGeneratedText(rgxGenTestPattern, { stringIterator.next() }, validationResult)
                i++
            }
            validationResult.assertPassed()
        }
    }

    @Nested
    inner class GenerateNotInCategoryTest : UnicodeCategoryGenerateTestBase() {
        @ParameterizedTest(name = "{index}: {0}")
        @MethodSource("getCategoryTestData")
        fun generateNotInCategoryTest(categoryTestData: CategoryTestData) {
            val notInCategoryPattern = categoryTestData.notInCategoryPattern
            val pattern = notInCategoryPattern.pattern()
            val rgxGen = parse(pattern)
            registerTestedCategory(categoryTestData.category)
            val random = newRandom(pattern.hashCode())
            val rgxGenTestPattern = RgxGenTestPattern(pattern, notInCategoryPattern, categoryTestData.category, true)
            val validationResult = ValidationResult()
            for (i in 0 until GENERATE_ITERATIONS) {
                validateGeneratedText(rgxGenTestPattern, { rgxGen.generate(random) }, validationResult)
            }
            validationResult.assertPassed()
        }
    }

    companion object {
        const val GENERATE_ITERATIONS: Int = 1000

        private fun printWrongCharacters(categoryTestData: CategoryTestData, wrongCharacters: CharList) {
            val compactedRanges: MutableList<SymbolRange> = ArrayList()
            val compactedCharacters = empty()
            compactOverlappingRangesAndSymbols(ArrayList(), wrongCharacters, compactedRanges, compactedCharacters)
            var sb = StringBuilder()
            for (i in 0 until compactedCharacters.size()) {
                val compactedCharacter = compactedCharacters[i]
                sb.append('\'').append(compactedCharacter).append('\'').append(',')
            }
            if (sb.length != 0) {
                println(categoryTestData.category.toString() + ": " + sb)
                sb = StringBuilder()
            }

            for (compactedRange in compactedRanges) {
                sb.append("range(").append(compactedRange.from).append(", ").append(compactedRange.to).append("), ")
            }

            if (sb.length != 0) {
                println(categoryTestData.category.toString() + ": " + sb)
            }
        }
    }
}