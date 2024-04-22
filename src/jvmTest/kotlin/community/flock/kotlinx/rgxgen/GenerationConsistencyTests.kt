package community.flock.kotlinx.rgxgen

import community.flock.kotlinx.rgxgen.RgxGen.Companion.parse
import community.flock.kotlinx.rgxgen.config.RgxGenOption
import community.flock.kotlinx.rgxgen.config.RgxGenProperties
import community.flock.kotlinx.rgxgen.data.TestPattern
import community.flock.kotlinx.rgxgen.data.TestPatternCaseInsensitive
import community.flock.kotlinx.rgxgen.util.Util.newRandom
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.io.File
import java.io.IOException
import java.nio.charset.MalformedInputException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardOpenOption
import java.util.Arrays
import java.util.function.Function
import java.util.stream.Collectors
import java.util.stream.Stream
import kotlin.io.path.absolutePathString

/**
 * The aim of these test is to show that the result of generation was changed by writing values generated with same seed into a text file.
 * When a developer makes a change to a generation algorithm - the generated values will change and will pop up during commit.
 * This is an indication that the generated values changed - there are 2 possible ways to act:
 * 1. Change generation algorithm so that the values stay the same
 * 2. When the change goes to release - make a note, that the generation algorithm changed and the expected values may change as well.
 */
class GenerationConsistencyTests {
    @ParameterizedTest
    @MethodSource("getCaseInsensitivePatterns")
    @Throws(IOException::class)
    fun verifyThatAllCaseInsensitivePatternsStaysTheSameTest(data: TestPatternCaseInsensitive) {
        val name = data.name
        val fileName = caseInsensitivePath.resolve("matching").resolve(createFileName(name)).toAbsolutePath()
        val properties: RgxGenProperties<Any> = RgxGenProperties<Any>()
        RgxGenOption.CASE_INSENSITIVE.setInProperties(properties, true)
        val rgxGen = parse(properties, data.getPattern())
        val random = newRandom(17)
        for (i in 0 until NUM_ITERATIONS) {
            val generated = rgxGen.generate(random)
            Files.write(fileName, listOf(generated), StandardOpenOption.CREATE, StandardOpenOption.APPEND)
        }
    }

    @ParameterizedTest
    @MethodSource("getCaseInsensitivePatterns")
    @Throws(IOException::class)
    fun verifyThatAllCaseInsensitivePatternsStaysTheSameNotMatchingTest(data: TestPatternCaseInsensitive) {
        val name = data.name
        val fileName = caseInsensitivePath.resolve("notmatching").resolve(createFileName(name)).toAbsolutePath()
        val properties: RgxGenProperties<Any> = RgxGenProperties<Any>()
        RgxGenOption.CASE_INSENSITIVE.setInProperties(properties, true)
        val rgxGen = parse(properties, data.getPattern())
        val random = newRandom(17)
        for (i in 0 until NUM_ITERATIONS) {
            val generated = rgxGen.generateNotMatching(random)
            Files.write(fileName, listOf(generated), StandardOpenOption.CREATE, StandardOpenOption.APPEND)
        }
    }

    @ParameterizedTest
    @MethodSource("getPatterns")
    @Throws(IOException::class)
    fun verifyThatAllCaseSensitivePatternsStaysTheSameTest(data: TestPattern) {
        val name = data.name
        val fileName = caseSensitivePath.resolve("matching").resolve(createFileName(name)).toAbsolutePath()
        val rgxGen = parse(data.getPattern())
        val random = newRandom(17)
        for (i in 0 until NUM_ITERATIONS) {
            val generated = rgxGen.generate(random)
            Files.write(fileName, listOf(generated), StandardOpenOption.CREATE, StandardOpenOption.APPEND)
        }
    }


    @ParameterizedTest
    @MethodSource("getPatterns")
    @Throws(IOException::class)
    fun verifyThatAllCaseSensitivePatternsStaysTheSameNotMatchingTest(data: TestPattern) {
        val name = data.name
        val fileName = caseSensitivePath.resolve("notmatching").resolve(createFileName(name)).toAbsolutePath()
        val rgxGen = parse(data.getPattern())
        val random = newRandom(17)
        for (i in 0 until NUM_ITERATIONS) {
            val generated = rgxGen.generateNotMatching(random)
            try {
                Files.write(fileName, listOf(generated), StandardOpenOption.CREATE, StandardOpenOption.APPEND)
            } catch (e: MalformedInputException) {
                println("Failed to write a text " + e.message)
                println(generated)
                Assertions.fail<Any>(e)
            }
        }
    }

    @Test
    fun verifyCompletePatternsHaveDifferentNames() {
        Assertions.assertDoesNotThrow {
            completeTestsPatterns
                .filter { arguments: Arguments -> arguments.get()[3] as Int == 0 } // Seed is 0
                .map { arguments: Arguments -> arguments.get()[0] }
                .map { obj: Any -> obj.toString() }
                .collect(Collectors.toMap(Function.identity(), Function.identity()))
        }
    }

    @ParameterizedTest(name = "{index}: {0}")
    @MethodSource("getCompleteTestsPatterns")
    @Throws(
        IOException::class
    )
    fun generateTest(name: String, aUseFind: Boolean, aRegex: String?, aSeed: Int) {
        val fileName = caseSensitivePath.resolve("matching").resolve(createFileName(name)).toAbsolutePath()
        val rgxGen = parse(aRegex)
        val generated = rgxGen.generate(newRandom(aSeed))
        try {
            Files.write(fileName, listOf(generated), StandardOpenOption.CREATE, StandardOpenOption.APPEND)
        } catch (e: MalformedInputException) {
            println("Failed to write a text " + e.message)
            println(generated)
            Assertions.fail<Any>(e)
        }
    }

    @ParameterizedTest(name = "{index}: {0}")
    @MethodSource("getCompleteTestsPatterns")
    @Throws(
        IOException::class
    )
    fun generateNotMatchingTest(name: String, aUseFind: Boolean, aRegex: String?, aSeed: Int) {
        val fileName = caseSensitivePath.resolve("notmatching").resolve(createFileName(name)).toAbsolutePath()
        val rgxGen = parse(aRegex)
        val generated = rgxGen.generateNotMatching(newRandom(aSeed))
        try {
            Files.write(fileName, listOf(generated), StandardOpenOption.CREATE, StandardOpenOption.APPEND)
        } catch (e: MalformedInputException) {
            println("Failed to write a text " + e.message)
            println(generated)
            Assertions.fail<Any>(e)
        }
    }

    companion object {
        private const val NUM_ITERATIONS = 100
        private val caseInsensitivePath: Path = Paths.get("testdata/caseinsensitive/")
        private val caseSensitivePath: Path = Paths.get("testdata/casesensitive/")

        @JvmStatic
        val caseInsensitivePatterns: Stream<TestPatternCaseInsensitive>
            get() = Arrays.stream(TestPatternCaseInsensitive.entries.toTypedArray())

        @JvmStatic
        val patterns: Stream<TestPattern>
            get() = Arrays.stream(TestPattern.entries.toTypedArray())

        @JvmStatic
        val completeTestsPatterns: Stream<Arguments>
            get() = CompleteTests.data

        @BeforeAll
        @JvmStatic
        @Throws(IOException::class)
        fun cleanupFiles() {
            Files.walk(caseInsensitivePath).use { pathStream ->
                pathStream.filter { path: Path? -> Files.isRegularFile(path) }
                    .forEach { path: Path? ->
                        try {
                            Files.delete(path)
                        } catch (e: IOException) {
                            throw RuntimeException(e)
                        }
                    }
            }
            Files.walk(caseSensitivePath).use { pathStream ->
                pathStream.filter { path: Path? -> Files.isRegularFile(path) }
                    .forEach { path: Path? ->
                        try {
                            Files.delete(path)
                        } catch (e: IOException) {
                            throw RuntimeException(e)
                        }
                    }
            }
        }

        private fun createFileName(name: String): String {
            return name.replace('/', '_').replace('\\', '_') + ".txt"
        }
    }
}
