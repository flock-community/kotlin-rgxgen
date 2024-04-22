package community.flock.kotlinx.rgxgen.model

import community.flock.kotlinx.rgxgen.model.data.CategoryTestData
import community.flock.kotlinx.rgxgen.util.chars.CharList.Companion.charList
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestInstance
import java.util.Arrays
import java.util.EnumMap
import java.util.EnumSet
import java.util.regex.Pattern
import java.util.stream.Collectors
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
open class UnicodeCategoryGenerateTestBase {
    var testedCategories: MutableSet<UnicodeCategory>? = null
    var generatedCharacters: MutableMap<UnicodeCategory, MutableSet<Char>>? = null

    @BeforeAll
    fun beforeAll() {
        testedCategories = EnumSet.noneOf(UnicodeCategory::class.java)
    }

    @BeforeEach
    fun beforeEach() {
        generatedCharacters = EnumMap(
            UnicodeCategory::class.java
        )
    }

    fun registerTestedCategory(category: UnicodeCategory) {
        testedCategories!!.add(category)
    }

    fun validateGeneratedText(
        testPattern: RgxGenTestPattern,
        generateFunction: () -> String?,
        validationResult: ValidationResult
    ): ValidationResult {
        val generatedText = Assertions.assertDoesNotThrow<String> { generateFunction() }
        val generatedCharactersForCategory =
            generatedCharacters!!.computeIfAbsent(testPattern.unicodeCategory) { k: UnicodeCategory? -> HashSet() }
        val generatedTextCharArray = generatedText.toCharArray()
        for (c in generatedTextCharArray) {
            generatedCharactersForCategory.add(c)
        }

        if (testPattern.compiled.matcher(generatedText).matches() == testPattern.isExpectToMatch) {
            return validationResult.addMatched()
        }

        val singleLetterPattern = Pattern.compile(testPattern.patternWithoutLength + '*')
        val matches = BooleanArray(generatedText.length)
        for (i in generatedTextCharArray.indices) {
            matches[i] = singleLetterPattern.matcher(generatedTextCharArray[i].toString()).matches()
        }
        println("Failed for text '$generatedText'")
        println("Match debug: " + testPattern.unicodeCategory)
        println('\t'.toString() + generatedText + "\t length = " + generatedText.length)
        val lettersBuilder = StringBuilder("\t")
        val matchesBuilder = StringBuilder("\t")
        val unmatchedCodes = StringBuilder("\t")
        for (i in 0 until generatedText.length) {
            lettersBuilder.append('\'').append(generatedText[i]).append("' ")
            val isOk = matches[i] == testPattern.isExpectToMatch
            matchesBuilder.append(' ').append(if (isOk) "." else '!').append("  ")
            unmatchedCodes.append(' ').append(if (isOk) " " else (generatedText[i].code)).append("  ")
        }
        println(lettersBuilder)
        println(matchesBuilder)
        println(unmatchedCodes)
        return validationResult.addNotMatched()
    }

    @AfterEach
    fun printGenerationStatistics() {
        for ((category, charactersPresentInGroup) in generatedCharacters!!) {
            val intSummaryStatistics =
                charactersPresentInGroup.stream().mapToInt { i: Char -> i.code }.summaryStatistics()
            println("Category " + category + " with keys " + category!!.keys + " character stats: ")
            println("\tMin: " + intSummaryStatistics.min + "; '" + intSummaryStatistics.min.toChar() + '\'')
            println("\tMax: " + intSummaryStatistics.max + "; '" + intSummaryStatistics.max.toChar() + '\'')
            val totalValueCount = (category.symbols.size + category.symbolRanges
                .sumOf { range: SymbolRange -> range.to - range.from + 1 }).toDouble()
            val coveredRate = charactersPresentInGroup.size / totalValueCount
            println("\tCovered: $coveredRate")
        }
    }

    @AfterAll
    fun verifyAllCategoriesTestedWithPatternCompile() {
        val notTestedCategories = Arrays.stream(UnicodeCategory.entries.toTypedArray())
            .filter { category: UnicodeCategory -> !testedCategories!!.contains(category) }
            .collect(Collectors.toList())
        if (!notTestedCategories.isEmpty()) {
            Assertions.fail<Any>("Pattern.compile() failed for - $notTestedCategories")
        }
    }

    object CategoryFinder {
        @JvmStatic
        fun main(args: Array<String>) {
            val minChar = 4341
            val maxChar = 4341
            val characters = charList(*UnicodeCategory.OTHER_LETTER.symbols)
            for (i in minChar..maxChar) {
                if (characters.contains(i.toChar())) {
                    println("$i found in individual characters")
                }

                for (symbolRange in UnicodeCategory.OTHER_LETTER.symbolRanges) {
                    if (symbolRange.from <= i && symbolRange.to >= i) {
                        println("$i found in a range: $symbolRange")
                    }
                }
            }
        }
    }

    companion object {
        @JvmStatic
        val categoryTestData: Stream<CategoryTestData>
            get() = Arrays.stream(UnicodeCategory.entries.toTypedArray())
                .map { CategoryTestData.create(it) }

        fun wrapInCurvy(s: String): String {
            return "{$s}"
        }
    }
}
