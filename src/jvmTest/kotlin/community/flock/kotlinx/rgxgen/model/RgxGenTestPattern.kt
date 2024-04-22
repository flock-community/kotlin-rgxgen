package community.flock.kotlinx.rgxgen.model

import java.util.regex.Pattern


class RgxGenTestPattern(
    val pattern: String,
    val compiled: Pattern,
    val unicodeCategory: UnicodeCategory,
    val isExpectToMatch: Boolean
) {
    val patternWithoutLength: String
        get() = COMPILE.split(pattern)[0]

    companion object {
        private val COMPILE: Pattern = Pattern.compile("\\{\\d+}")
    }
}
