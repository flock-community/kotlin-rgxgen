package community.flock.kotlinx.rgxgen

import community.flock.kotlinx.rgxgen.data.DataInterface
import org.junit.jupiter.api.Assumptions
import java.util.regex.Pattern

abstract class CombinedTestTemplate<T : DataInterface?> {
    protected fun isValidGenerated(testPattern: T, value: String?): Boolean {
        return isValidGenerated(testPattern, value, 0)
    }

    protected fun isValidGenerated(testPattern: T, value: String?, patternCompilationFlags: Int): Boolean {
        Assumptions.assumeTrue(testPattern!!.isUsableWithJavaPattern())
        val aCompiledPattern = Pattern.compile(testPattern.getPattern(), patternCompilationFlags)
        return if (testPattern.useFindForMatching()) {
            aCompiledPattern.matcher(value)
                .find()
        } else {
            aCompiledPattern.matcher(value)
                .matches()
        }
    }
}
