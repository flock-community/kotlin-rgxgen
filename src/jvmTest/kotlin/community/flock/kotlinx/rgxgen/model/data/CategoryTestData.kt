package community.flock.kotlinx.rgxgen.model.data

import community.flock.kotlinx.rgxgen.model.UnicodeCategory
import community.flock.kotlinx.rgxgen.model.UnicodeCategoryGenerateTestBase
import community.flock.kotlinx.rgxgen.util.chars.CharList
import community.flock.kotlinx.rgxgen.util.chars.CharList.Companion.charList
import java.util.regex.Pattern
import java.util.stream.Collectors
import java.util.stream.Stream

class CategoryTestData(
    @JvmField val key: String,
    @JvmField val category: UnicodeCategory,
    @JvmField val inCategoryPattern: Pattern,
    @JvmField val notInCategoryPattern: Pattern
) {
    val categoryCharacters: CharList
        get() = charList(
            category.symbolRanges,
            *category.symbols
        )

    override fun toString(): String {
        return key
    }

    companion object {
        @JvmStatic
        fun create(category: UnicodeCategory): CategoryTestData {
            val keys = category.keys
                .stream()
                .flatMap { k: String ->
                    if (k.length == 1) Stream.of(
                        k,
                        UnicodeCategoryGenerateTestBase.wrapInCurvy(k)
                    ) else Stream.of(UnicodeCategoryGenerateTestBase.wrapInCurvy(k))
                }
                .collect(Collectors.toList())
            for (key in keys) {
                try {
                    return CategoryTestData(
                        key,
                        category,
                        Pattern.compile("\\p$key{100}"),
                        Pattern.compile("\\P$key{100}")
                    )
                } catch (ignored: Exception) {
                }
            }
            throw IllegalArgumentException("Couldn't compile pattern for category: $category")
        }
    }
}
