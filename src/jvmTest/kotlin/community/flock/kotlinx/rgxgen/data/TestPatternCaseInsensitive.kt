package community.flock.kotlinx.rgxgen.data

import community.flock.kotlinx.rgxgen.nodes.Node
import community.flock.kotlinx.rgxgen.testutil.TestingUtilities
import java.util.Arrays

// CAUTION! Double braced initialization is used.
enum class TestPatternCaseInsensitive(val aPattern: String) : DataInterface {
    FINAL_SYMBOL_A("a") {
        init {
            setAllUniqueValues("a", "A")
        }
    },

    //-----------------------------------------------------------------------------------------------------------------------------------------
    FINAL_SYMBOL_ABC("abc") {
        init {
            setAllUniqueValues("abc", "Abc", "aBc", "ABc", "abC", "AbC", "aBC", "ABC")
        }
    },

    //-----------------------------------------------------------------------------------------------------------------------------------------
    A_OR_B("[ab]") {
        init {
            setAllUniqueValues("A", "B", "a", "b")
        }
    },

    //-----------------------------------------------------------------------------------------------------------------------------------------
    A_REPEAT_RANGE("a{2,3}") {
        init {
            setAllUniqueValues("aa", "aA", "Aa", "AA", "aaa", "aaA", "aAa", "aAA", "Aaa", "AaA", "AAa", "AAA")
        }
    },

    //-----------------------------------------------------------------------------------------------------------------------------------------
    A_OR_B_REPEAT_CONST("(a|b){2}") {
        init {
            setAllUniqueValues(
                "aa",
                "aA",
                "ab",
                "aB",
                "Aa",
                "AA",
                "Ab",
                "AB",
                "ba",
                "bA",
                "bb",
                "bB",
                "Ba",
                "BA",
                "Bb",
                "BB"
            )
        }
    },

    //-----------------------------------------------------------------------------------------------------------------------------------------
    CHOICE_CAPTURED("(a|b)\\1") {
        init {
            setAllUniqueValues("aa", "AA", "bb", "BB")
        }
    },

    //-----------------------------------------------------------------------------------------------------------------------------------------
    CAPTURE_REF_REPEAT("(a|b)\\1{2,3}") {
        init {
            setAllUniqueValues("aaa", "aaaa", "AAA", "AAAA", "bbb", "bbbb", "BBB", "BBBB")
        }
    },

    //-----------------------------------------------------------------------------------------------------------------------------------------
    GROUP_RESULT_USED_IN_CHOICES("(a)(\\1|b)") {
        init {
            setAllUniqueValues("aa", "ab", "aB", "AA", "Ab", "AB")
        }
    };

    var aEstimatedCount: Long?
    var aAllUniqueValues: List<String?>? = null

    init {
        aEstimatedCount = TestingUtilities.BIG_INTEGER_MINUS_ONE
    }

    override fun getPattern(): String? {
        return aPattern
    }

    override fun getResultNode(): Node? {
        return null
    }

    override fun getEstimatedCount(): Long? {
        return aEstimatedCount
    }

    override fun getAllUniqueValues(): List<String?>? {
        return aAllUniqueValues
    }

    protected fun setInfinite() {
        aEstimatedCount = null
    }

    protected fun setAllUniqueValues(vararg values: String?) {
        setAllUniqueValues(Arrays.asList(*values))
    }

    protected fun setAllUniqueValues(values: List<String?>) {
        aAllUniqueValues = values
        aEstimatedCount = values.size.toLong()
    }

    override fun hasEstimatedCount(): Boolean {
        return TestingUtilities.BIG_INTEGER_MINUS_ONE != aEstimatedCount
    }

    override fun hasAllUniqueValues(): Boolean {
        return aAllUniqueValues != null
    }

    override fun toString(): String {
        return "$name : $aPattern"
    }

    override fun useFindForMatching(): Boolean {
        return false
    }

    override fun isUsableWithJavaPattern(): Boolean {
        return true
    }
}
