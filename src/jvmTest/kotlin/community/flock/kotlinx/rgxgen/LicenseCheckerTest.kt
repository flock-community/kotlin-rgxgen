package community.flock.kotlinx.rgxgen

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assumptions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.stream.Collectors
import java.util.stream.Stream

class LicenseCheckerTest {
    @ParameterizedTest
    @MethodSource("getAllSourceFiles")
    @Throws(IOException::class)
    fun checkLicence(path: Path) {
        Assumptions.assumeTrue(path.toString().endsWith(".java"))
        val contents = java.lang.String.join("\n", Files.readAllLines(path))
        Assertions.assertTrue(contents.contains(LICENCE))
    }

    companion object {
        private val LICENCE = readLicenceFile()

        private fun readLicenceFile(): String {
            try {
                val allLines = Files.readAllLines(Paths.get("LICENSE.txt"))
                val licenseText: List<String> = allLines.subList(189, 202)
                return java.lang.String.join("\n", licenseText)
            } catch (e: IOException) {
                throw RuntimeException(e)
            }
        }

        @JvmStatic
        val allSourceFiles: Stream<Path>
            get() {
                try {
                    Files.walk(Paths.get("src/commonMain")).use { files ->
                        return files
                            .filter { path: Path? -> Files.isRegularFile(path) }
                            .collect(Collectors.toList())
                            .stream()
                    }
                } catch (e: IOException) {
                    throw RuntimeException(e)
                }
            }
    }
}
