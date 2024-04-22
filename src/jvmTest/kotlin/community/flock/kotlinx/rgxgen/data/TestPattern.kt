package community.flock.kotlinx.rgxgen.data

import community.flock.kotlinx.rgxgen.model.MatchType
import community.flock.kotlinx.rgxgen.model.RgxGenCharsDefinition.Companion.of
import community.flock.kotlinx.rgxgen.model.SymbolRange
import community.flock.kotlinx.rgxgen.model.SymbolRange.Companion.range
import community.flock.kotlinx.rgxgen.model.UnicodeCategory
import community.flock.kotlinx.rgxgen.model.UnicodeCategoryConstants.BASIC_LATIN_LOWERCASE_LATIN_ALPHABET
import community.flock.kotlinx.rgxgen.nodes.Choice
import community.flock.kotlinx.rgxgen.nodes.FinalSymbol
import community.flock.kotlinx.rgxgen.nodes.Group
import community.flock.kotlinx.rgxgen.nodes.GroupRef
import community.flock.kotlinx.rgxgen.nodes.Node
import community.flock.kotlinx.rgxgen.nodes.NotSymbol
import community.flock.kotlinx.rgxgen.nodes.Repeat
import community.flock.kotlinx.rgxgen.nodes.Repeat.Companion.minimum
import community.flock.kotlinx.rgxgen.nodes.SymbolSet.Companion.ofAscii
import community.flock.kotlinx.rgxgen.nodes.SymbolSet.Companion.ofAsciiCharacters
import community.flock.kotlinx.rgxgen.nodes.SymbolSet.Companion.ofAsciiRanges
import community.flock.kotlinx.rgxgen.nodes.SymbolSet.Companion.ofDotPattern
import community.flock.kotlinx.rgxgen.nodes.SymbolSet.Companion.ofUnicode
import community.flock.kotlinx.rgxgen.nodes.SymbolSet.Companion.ofUnicodeCharacterClass
import community.flock.kotlinx.rgxgen.parsing.dflt.ConstantsProvider.CAPITAL_LATIN_LETTERS
import community.flock.kotlinx.rgxgen.parsing.dflt.ConstantsProvider.DIGITS
import community.flock.kotlinx.rgxgen.parsing.dflt.ConstantsProvider.SMALL_LATIN_LETTERS
import community.flock.kotlinx.rgxgen.parsing.dflt.ConstantsProvider.makeAsciiCharacterArray
import community.flock.kotlinx.rgxgen.testutil.TestingUtilities
import community.flock.kotlinx.rgxgen.util.chars.CharList.Companion.charList
import community.flock.kotlinx.rgxgen.util.chars.CharList.Companion.emptyUnmodifiable
import java.util.Arrays
import java.util.stream.Collectors
import java.util.stream.IntStream
import java.util.stream.Stream


private fun getSymbolStream(category: UnicodeCategory): Stream<String> {
    return Stream.concat(
        stream(category.symbols).map { obj: Char? -> java.lang.String.valueOf(obj) },
        Stream.of(*category.symbolRanges.toTypedArray())
            .flatMap { range: SymbolRange -> getRangeSymbolStream(range) }
    )
}

private fun getRangeSymbolStream(range: SymbolRange): Stream<String> {
    return IntStream.range(range.from, range.to + 1).mapToObj { i: Int -> i.toChar().toString() }
}

fun stream(chars: CharArray?): Stream<Char> {
    return String(chars!!).chars().mapToObj { i: Int -> i.toChar() }
}

enum class TestPattern(val aPattern: String, val aResultNode: Node) : DataInterface {
    SIMPLE_A(
        "a",
        FinalSymbol("a")
    ) {
        init {
            setAllUniqueValues("a")
        }
    },

    //-----------------------------------------------------------------------------------------------------------------------------------------
    SIMPLE_A_WITH_START_END(
        "^a$",
        FinalSymbol("a")
    ) {
        init {
            setAllUniqueValues("a")
        }
    },

    //-----------------------------------------------------------------------------------------------------------------------------------------
    ANY_DIGIT(
        "\\d",
        ofAsciiRanges(
            "\\d",
            listOf<SymbolRange>(range('0', '9')), MatchType.POSITIVE
        )
    ) {
        init {
            setAllUniqueValues("0", "1", "2", "3", "4", "5", "6", "7", "8", "9")
        }
    },

    //-----------------------------------------------------------------------------------------------------------------------------------------
    NOT_A_DIGIT(
        "\\D",  // Any non-digit
        ofAsciiRanges(
            "\\D",
            listOf<SymbolRange>(range('0', '9')), MatchType.NEGATIVE
        )
    ),

    //-----------------------------------------------------------------------------------------------------------------------------------------
    ANY_DIGIT_RANGE(
        "[0-9]",
        ofAsciiRanges(
            "[0-9]",
            listOf<SymbolRange>(range('0', '9')), MatchType.POSITIVE
        )
    ) {
        init {
            setAllUniqueValues("0", "1", "2", "3", "4", "5", "6", "7", "8", "9")
        }
    },
    LETTER_RANGE(
        "[a-cA-C]",
        ofAsciiRanges(
            "[a-cA-C]",
            Arrays.asList<SymbolRange>(range('a', 'c'), range('A', 'C')), MatchType.POSITIVE
        )
    ),

    //-----------------------------------------------------------------------------------------------------------------------------------------
    ANY_WORD_CHARACTER(
        "\\w",  // Any word character  [a-zA-Z0-9_]
        ofAscii(
            "\\w",
            Arrays.asList<SymbolRange>(SMALL_LATIN_LETTERS, CAPITAL_LATIN_LETTERS, DIGITS),
            charList('_'),
            MatchType.POSITIVE
        )
    ),

    //-----------------------------------------------------------------------------------------------------------------------------------------
    ANY_NON_WORD_CHARACTER(
        "\\W",  // Any non-word symbol  [a-zA-Z0-9_]
        ofAscii(
            "\\W",
            Arrays.asList<SymbolRange>(SMALL_LATIN_LETTERS, CAPITAL_LATIN_LETTERS, DIGITS),
            charList('_'),
            MatchType.NEGATIVE
        )
    ),

    //-----------------------------------------------------------------------------------------------------------------------------------------
    HEX_SPACE(
        "\\x20",  // Space
        FinalSymbol(" ")
    ),

    //-----------------------------------------------------------------------------------------------------------------------------------------
    HEX_SYMBOL(
        "\\x{26F8}",
        FinalSymbol("⛸")
    ),

    //-----------------------------------------------------------------------------------------------------------------------------------------
    HEX_SPACE_THEN_A(
        "\\x20a",  // Space
        FinalSymbol(" a")
    ),

    //-----------------------------------------------------------------------------------------------------------------------------------------
    HEX_SYMBOL_THEN_A(
        "\\x{26F8}a",
        FinalSymbol("⛸a")
    ),

    //-----------------------------------------------------------------------------------------------------------------------------------------
    A_OR_B(
        "[ab]",
        ofAsciiCharacters(
            "[ab]",
            charArrayOf(
                'a', 'b'
            ), MatchType.POSITIVE
        )
    ) {
        init {
            setAllUniqueValues("a", "b")
        }
    },

    //-----------------------------------------------------------------------------------------------------------------------------------------
    A_OR_B_THEN_C(
        "[ab]c",
        community.flock.kotlinx.rgxgen.nodes.Sequence(
            "[ab]c",
            ofAsciiCharacters(
                "[ab]", charArrayOf(
                    'a', 'b'
                ), MatchType.POSITIVE
            ), FinalSymbol("c")
        )
    ) {
        init {
            setAllUniqueValues("ac", "bc")
        }
    },

    //-----------------------------------------------------------------------------------------------------------------------------------------
    D_THEN_A_OR_B_THEN_C(
        "d[ab]c",
        community.flock.kotlinx.rgxgen.nodes.Sequence(
            "d[ab]c",
            FinalSymbol("d"),
            ofAsciiCharacters(
                "[ab]", charArrayOf(
                    'a', 'b'
                ), MatchType.POSITIVE
            ), FinalSymbol("c")
        )
    ) {
        init {
            setAllUniqueValues("dac", "dbc")
        }
    },

    //-----------------------------------------------------------------------------------------------------------------------------------------
    A_REPEAT_RANGE(
        "a{2,5}",
        Repeat("a{2,5}", FinalSymbol("a"), 2, 5)
    ) {
        init {
            setAllUniqueValues("aa", "aaa", "aaaa", "aaaaa")
        }
    },

    //-----------------------------------------------------------------------------------------------------------------------------------------
    A_REPEAT_CONST(
        "a{2}",
        Repeat("a{2}", FinalSymbol("a"), 2)
    ) {
        init {
            setAllUniqueValues("aa")
        }
    },

    //-----------------------------------------------------------------------------------------------------------------------------------------
    A_REPEAT_60K(
        "a{60000}",
        Repeat("a{60000}", FinalSymbol("a"), 60000)
    ) {
        init {
            setAllUniqueValues(Stream.generate { "a" }
                .limit(60000)
                .reduce("") { obj: String, str: String -> obj + str })
        }
    },

    //-----------------------------------------------------------------------------------------------------------------------------------------
    A_OR_B_REPEAT_CONST(
        "(a|b){2}",
        Repeat(
            "(a|b){2}", Group(
                "(a|b)", 1,
                Choice("(a|b)", FinalSymbol("a"), FinalSymbol("b"))
            ), 2
        )
    ) {
        init {
            setAllUniqueValues("aa", "ab", "ba", "bb")
        }
    },

    //-----------------------------------------------------------------------------------------------------------------------------------------
    A_OR_B_REPEAT_RANGE(
        "(a|b){0,2}",
        Repeat(
            "(a|b){0,2}",
            Group(
                "(a|b)", 1,
                Choice("(a|b)", FinalSymbol("a"), FinalSymbol("b"))
            ), 0, 2
        )
    ) {
        init {
            setAllUniqueValues("", "a", "b", "aa", "ab", "ba", "bb")
        }
    },

    //-----------------------------------------------------------------------------------------------------------------------------------------
    A_REPEAT_OR_B_REPEAT(
        "(a{0,2}|b{0,2})",
        Group(
            "(a{0,2}|b{0,2})", 1,
            Choice(
                "(a{0,2}|b{0,2})",
                Repeat("a{0,2}", FinalSymbol("a"), 0, 2),
                Repeat("b{0,2}", FinalSymbol("b"), 0, 2)
            )
        )
    ) {
        init {
            setAllUniqueValues("", "a", "aa", "", "b", "bb")
        }
    },

    //-----------------------------------------------------------------------------------------------------------------------------------------
    NOTHING_OR_A_REPEAT_OR_B_REPEAT(
        "(|(a{1,2}|b{1,2}))",
        Group(
            "(|(a{1,2}|b{1,2}))", 1,
            Choice(
                "(|(a{1,2}|b{1,2}))",
                FinalSymbol(""),
                Group(
                    "(a{1,2}|b{1,2})", 2,
                    Choice(
                        "(a{1,2}|b{1,2})",
                        Repeat("a{1,2}", FinalSymbol("a"), 1, 2),
                        Repeat("b{1,2}", FinalSymbol("b"), 1, 2)
                    )
                )
            )
        )
    ) {
        init {
            setAllUniqueValues("", "a", "aa", "b", "bb")
        }
    },

    //-----------------------------------------------------------------------------------------------------------------------------------------
    A_THEN_ANY(
        "a.",
        community.flock.kotlinx.rgxgen.nodes.Sequence("a.", FinalSymbol("a"), ofDotPattern(null))
    ) {
        init {
            setAllUniqueValues(
                stream(makeAsciiCharacterArray())
                    .map { s: Char -> "a$s" }
                    .collect(Collectors.toList()))
        }
    },

    //-----------------------------------------------------------------------------------------------------------------------------------------
    ANY_THEN_ANY(
        "..",
        community.flock.kotlinx.rgxgen.nodes.Sequence("..", ofDotPattern(null), ofDotPattern(null))
    ) {
        init {
            setAllUniqueValues(
                stream(makeAsciiCharacterArray())
                    .flatMap { s: Char ->
                        stream(makeAsciiCharacterArray())
                            .map { v: Char -> s.toString() + v }
                    }
                    .collect(Collectors.toList()))
        }
    },

    //-----------------------------------------------------------------------------------------------------------------------------------------
    A_REPEAT_ZERO_OR_MORE(
        "a*",
        minimum("a*", FinalSymbol("a"), 0)
    ) {
        init {
            setInfinite()
        }
    },

    //-----------------------------------------------------------------------------------------------------------------------------------------
    A_REPEAT_MIN_4(
        "a{4,}",
        minimum("a{4,}", FinalSymbol("a"), 4)
    ),

    //-----------------------------------------------------------------------------------------------------------------------------------------
    NOT_A(
        "[^a]",
        ofAsciiCharacters("[^a]", charArrayOf('a'), MatchType.NEGATIVE)
    ),

    //-----------------------------------------------------------------------------------------------------------------------------------------
    NOT_LETTER_RANGE(
        "[^a-dE-F]",
        ofAsciiRanges("[^a-dE-F]", Arrays.asList<SymbolRange>(range('a', 'd'), range('E', 'F')), MatchType.NEGATIVE)
    ),  //-----------------------------------------------------------------------------------------------------------------------------------------

    ANY_WHITESPACE(
        "\\s",  // Any White Space
        ofAscii(
            "\\s",
            of(' ', '\t'),
            of('\t', '\n', '\u000B', '\u000C', '\r', ' '),
            MatchType.POSITIVE
        )
    ),

    //-----------------------------------------------------------------------------------------------------------------------------------------
    NOT_A_WHITESPACE(
        "\\S",  // Any Non White Space
        ofAscii(
            "\\S",
            of(' ', '\t'),
            of('\t', '\n', '\u000B', '\u000C', '\r', ' '),
            MatchType.NEGATIVE
        )
    ),

    //-----------------------------------------------------------------------------------------------------------------------------------------
    A_THEN_A_OR_NOT(
        "aa?",
        community.flock.kotlinx.rgxgen.nodes.Sequence(
            "aa?",
            FinalSymbol("a"),
            Repeat("a?", FinalSymbol("a"), 0, 1)
        )
    ) {
        init {
            setAllUniqueValues("a", "aa")
        }
    },

    //-----------------------------------------------------------------------------------------------------------------------------------------
    A_THEN_A_ONE_OR_MORE(
        "aa+",
        community.flock.kotlinx.rgxgen.nodes.Sequence(
            "aa+",
            FinalSymbol("a"),
            minimum("a+", FinalSymbol("a"), 1)
        )
    ) {
        init {
            setInfinite()
        }
    },

    //-----------------------------------------------------------------------------------------------------------------------------------------
    A_THEN_ANY_REPEAT_INFINITE(
        "a.*",  // If use unlimited repetition that will cause an error when trying to save all data in memory, thus we limit repetition times
        community.flock.kotlinx.rgxgen.nodes.Sequence(
            "a.*",
            FinalSymbol("a"),
            minimum(".*", ofDotPattern(null), 0)
        )
    ) {
        init {
            setInfinite()
        }
    },

    //-----------------------------------------------------------------------------------------------------------------------------------------
    POSITIVE_LOOKAHEAD(
        "foo(?=bar)",
        community.flock.kotlinx.rgxgen.nodes.Sequence(
            "foo(?=bar)",
            FinalSymbol("foo"), FinalSymbol("bar")
        )
    ),

    //-----------------------------------------------------------------------------------------------------------------------------------------
    NEGATIVE_LOOKAHEAD(
        "foo(?!bar)",
        community.flock.kotlinx.rgxgen.nodes.Sequence(
            "foo(?!bar)",
            FinalSymbol("foo"), NotSymbol("bar", FinalSymbol("bar"))
        )
    ) {
        init {
            setInfinite()
        }
    },

    //-----------------------------------------------------------------------------------------------------------------------------------------
    POSITIVE_LOOKBEHIND(
        "(?<=foo)bar",
        community.flock.kotlinx.rgxgen.nodes.Sequence(
            "(?<=foo)bar",
            FinalSymbol("foo"), FinalSymbol("bar")
        )
    ),

    //-----------------------------------------------------------------------------------------------------------------------------------------
    NEGATIVE_LOOKBEHIND(
        "(?<!not)foo",
        community.flock.kotlinx.rgxgen.nodes.Sequence(
            "(?<!not)foo",
            NotSymbol("not", FinalSymbol("not")), FinalSymbol("foo")
        )
    ) {
        init {
            setInfinite()
        }
    },

    //-----------------------------------------------------------------------------------------------------------------------------------------
    CHOICE_CAPTURED(
        "(a|b)\\1",
        community.flock.kotlinx.rgxgen.nodes.Sequence(
            "(a|b)\\1",
            Group(
                "(a|b)", 1,
                Choice(
                    "(a|b)",
                    FinalSymbol("a"),
                    FinalSymbol("b")
                )
            ),
            GroupRef("\\1", 1)
        )
    ) {
        init {
            setAllUniqueValues("aa", "bb")
        }
    },

    //-----------------------------------------------------------------------------------------------------------------------------------------
    CAPTURE_REPEAT(
        "(a|b){2,3}\\1",
        community.flock.kotlinx.rgxgen.nodes.Sequence(
            "(a|b){2,3}\\1",
            Repeat(
                "(a|b){2,3}",
                Group(
                    "(a|b)", 1,
                    Choice(
                        "(a|b)",
                        FinalSymbol("a"),
                        FinalSymbol("b")
                    )
                ), 2, 3
            ),
            GroupRef("\\1", 1)
        )
    ) {
        init {
            setAllUniqueValues(
                "aaa",
                "abb",
                "baa",
                "bbb",
                "aaaa",
                "aabb",
                "abaa",
                "abbb",
                "baaa",
                "babb",
                "bbaa",
                "bbbb"
            )
        }
    },

    //-----------------------------------------------------------------------------------------------------------------------------------------
    CAPTURE_REPEAT_0(
        "(a|b){3}\\1",
        community.flock.kotlinx.rgxgen.nodes.Sequence(
            "(a|b){3}\\1",
            Repeat(
                "(a|b){3}",
                Group(
                    "(a|b){3}", 1,
                    Choice(
                        "(a|b)",
                        FinalSymbol("a"),
                        FinalSymbol("b")
                    )
                ), 3, 3
            ),
            GroupRef("\\1", 1)
        )
    ) {
        init {
            setAllUniqueValues("aaaa", "aabb", "abaa", "abbb", "baaa", "babb", "bbaa", "bbbb")
        }
    },

    //-----------------------------------------------------------------------------------------------------------------------------------------
    CAPTURE_REPEAT_1(
        "(a|b){2}\\1",
        community.flock.kotlinx.rgxgen.nodes.Sequence(
            "(a|b){2}\\1",
            Repeat(
                "(a|b){2}",
                Group(
                    "(a|b)", 1,
                    Choice(
                        "(a|b)",
                        FinalSymbol("a"),
                        FinalSymbol("b")
                    )
                ), 2, 2
            ),
            GroupRef("\\1", 1)
        )
    ) {
        init {
            setAllUniqueValues("aaa", "abb", "baa", "bbb")
        }
    },

    //-----------------------------------------------------------------------------------------------------------------------------------------
    CAPTURE_REF_REPEAT(
        "(a|b)\\1{2,3}",
        community.flock.kotlinx.rgxgen.nodes.Sequence(
            "(a|b)\\1{2,3}",
            Group(
                "(a|b)", 1,
                Choice(
                    "(a|b)",
                    FinalSymbol("a"),
                    FinalSymbol("b")
                )
            ),
            Repeat(
                "\\1{2,3}",
                GroupRef("\\1", 1), 2, 3
            )
        )
    ) {
        init {
            setAllUniqueValues("aaa", "aaaa", "bbb", "bbbb")
        }
    },

    //-----------------------------------------------------------------------------------------------------------------------------------------
    CAPTURE_REPEAT_AND_REF_REPEAT(
        "(a|b){2,3}\\1{2,3}",
        community.flock.kotlinx.rgxgen.nodes.Sequence(
            "(a|b){2,3}\\1{2,3}",
            Repeat(
                "(a|b){2,3}",
                Group(
                    "(a|b)", 1,
                    Choice(
                        "(a|b)",
                        FinalSymbol("a"),
                        FinalSymbol("b")
                    )
                ), 2, 3
            ),
            Repeat(
                "\\1{2,3}",
                GroupRef("\\1", 1), 2, 3
            )
        )
    ) {
        init {
            setAllUniqueValues(
                "aaaa",
                "aaaaa",
                "abbb",
                "abbbb",
                "baaa",
                "baaaa",
                "bbbb",
                "bbbbb",
                "aaaaa",
                "aaaaaa",
                "aabbb",
                "aabbbb",
                "abaaa",
                "abaaaa",
                "abbbb",
                "abbbbb",
                "baaaa",
                "baaaaa",
                "babbb",
                "babbbb",
                "bbaaa",
                "bbaaaa",
                "bbbbb",
                "bbbbbb"
            )
        }
    },

    //-----------------------------------------------------------------------------------------------------------------------------------------
    XML_NODE(
        "<([abc])>d<\\/\\1>",
        community.flock.kotlinx.rgxgen.nodes.Sequence(
            "<([abc])>d<\\/\\1>", FinalSymbol("<"),
            Group(
                "([abc])", 1, ofAsciiCharacters(
                    "[abc]", charArrayOf(
                        'a', 'b', 'c'
                    ), MatchType.POSITIVE
                )
            ),
            FinalSymbol(">d</"),
            GroupRef("\\1", 1),
            FinalSymbol(">")
        )
    ) {
        init {
            setAllUniqueValues("<a>d</a>", "<b>d</b>", "<c>d</c>")
        }
    },

    //-----------------------------------------------------------------------------------------------------------------------------------------
    METASEQUENCE_IN_SQUARE_BRACKETS(
        "['\\-/\\.\\s]",
        ofAscii(
            "['\\-/\\.\\s]",
            of('\'', '-', '/', '.', '\t', ' '),
            of('\'', '-', '/', '.', '\t', '\n', '\u000B', '\u000C', '\r', ' '),
            MatchType.POSITIVE
        )
    ) {
        init {
            setAllUniqueValues("\t", " ", "'", "-", ".", "/")
        }
    },

    //-----------------------------------------------------------------------------------------------------------------------------------------
    TOP_LEVEL_CHOICE_WITHOUT_PARENTHESIS(
        "a|b",
        Choice("a|b", FinalSymbol("a"), FinalSymbol("b"))
    ) {
        init {
            setAllUniqueValues("a", "b")
        }
    },

    //-----------------------------------------------------------------------------------------------------------------------------------------
    EMPTY_CHOICE_AT_THE_START_OF_CHOICES(
        "(|A)",
        Group("(|A)", 1, Choice("(|A)", FinalSymbol(""), FinalSymbol("A")))
    ) {
        init {
            setAllUniqueValues("", "A")
        }
    },

    //-----------------------------------------------------------------------------------------------------------------------------------------
    EMPTY_CHOICE_IN_THE_MIDDLE_OF_CHOICES(
        "(B||A)",
        Group("(B||A)", 1, Choice("(B||A)", FinalSymbol("B"), FinalSymbol(""), FinalSymbol("A")))
    ) {
        init {
            setAllUniqueValues("B", "", "A")
        }
    },

    //-----------------------------------------------------------------------------------------------------------------------------------------
    EMPTY_CHOICE_AT_THE_END_OF_CHOICES(
        "(A|)",
        Group("(A|)", 1, Choice("(A|)", FinalSymbol("A"), FinalSymbol("")))
    ) {
        init {
            setAllUniqueValues("A", "")
        }
    },

    //-----------------------------------------------------------------------------------------------------------------------------------------
    GROUP_RESULT_USED_IN_CHOICES(
        "(a)(\\1|b)",
        community.flock.kotlinx.rgxgen.nodes.Sequence(
            "(a)(\\1|b)",
            Group("(a)", 1, FinalSymbol("a")),
            Group(
                "(\\1|b)", 2,
                Choice(
                    "(\\1|b)",
                    GroupRef("\\1", 1),
                    FinalSymbol("b")
                )
            )
        )
    ) {
        init {
            setAllUniqueValues("aa", "ab")
        }
    },
    SLASH_Q_AND_SLASH_E_BASIC(
        "\\Qm\\E",
        FinalSymbol("m")
    ) {
        init {
            setAllUniqueValues("m")
        }
    },
    SLASH_Q_WITHOUT_SLASH_E_BASIC(
        "\\Qmas",
        FinalSymbol("mas")
    ) {
        init {
            setAllUniqueValues("mas")
        }
    },
    SLASH_E_WITHOUT_SLASH_Q_BASIC(
        "mas\\E",
        FinalSymbol("mas")
    ) {
        init {
            setAllUniqueValues("mas")
            setCannotCompilePattern()
        }
    },
    SLASH_Q_AND_SLASH_E_IGNORE_SPECIALS(
        "\\Q[a]\\1(a|c).*\\W\\E",
        FinalSymbol("[a]\\1(a|c).*\\W")
    ) {
        init {
            setAllUniqueValues("[a]\\1(a|c).*\\W")
        }
    },
    SLASH_Q_AND_SLASH_E_WITH_PREFIX_SUFFIX(
        "123\\Qm\\Ezxc",
        FinalSymbol("123mzxc")
    ) {
        init {
            setAllUniqueValues("123mzxc")
        }
    },
    SLASH_Q_AND_SLASH_E_WITH_REPEAT(
        "123\\Qmass[]\\E{1,2}zxc",
        community.flock.kotlinx.rgxgen.nodes.Sequence(
            "123\\Qmass[]\\E{1,2}zxc",
            FinalSymbol("123mass["),
            Repeat(
                "]",
                FinalSymbol("]"), 1, 2
            ),
            FinalSymbol("zxc")
        )
    ) {
        init {
            setAllUniqueValues("123mass[]zxc", "123mass[]]zxc")
        }
    },
    UNICODE("\\u0041", FinalSymbol("A")) {
        init {
            setAllUniqueValues("A")
        }
    },
    IN_CYRILLIC_CATEGORY(
        "\\p{InCyrillic}{2}", Repeat(
            "\\p{InCyrillic}{2}",
            ofUnicodeCharacterClass("\\p{InRunic}", UnicodeCategory.IN_CYRILLIC, MatchType.POSITIVE),
            2
        )
    ) {
        init {
            setAllUniqueValues(
                getSymbolStream(UnicodeCategory.IN_CYRILLIC)
                    .flatMap { c: String -> getSymbolStream(UnicodeCategory.IN_CYRILLIC).map { cc: String -> c + cc } }
                    .collect(Collectors.toList()))
        }
    },
    CATEGORY_WITHIN_SQUART_BRACKETS(
        "[a-z\\p{Nd}]{2}", Repeat(
            "[a-z\\p{Nd}]{2}",
            ofUnicode(
                "\\p{Decimal_Digit_Number}",
                Stream.concat<SymbolRange>(
                    Stream.of<SymbolRange>(BASIC_LATIN_LOWERCASE_LATIN_ALPHABET),
                    Stream.of<SymbolRange>(*UnicodeCategory.DECIMAL_DIGIT_NUMBER.symbolRanges.toTypedArray())
                )
                    .collect(Collectors.toList<SymbolRange>()),
                emptyUnmodifiable(), MatchType.POSITIVE
            ), 2
        )
    ) {
        init {
            setAllUniqueValues(
                Stream.concat<String>(
                    getRangeSymbolStream(BASIC_LATIN_LOWERCASE_LATIN_ALPHABET),
                    getSymbolStream(UnicodeCategory.DECIMAL_DIGIT_NUMBER)
                )
                    .sorted(Comparator.naturalOrder<String>())
                    .flatMap<String> { c: String ->
                        Stream
                            .concat<String>(
                                getRangeSymbolStream(BASIC_LATIN_LOWERCASE_LATIN_ALPHABET),
                                getSymbolStream(UnicodeCategory.DECIMAL_DIGIT_NUMBER)
                            )
                            .sorted(Comparator.naturalOrder<String>())
                            .map<String> { cc: String -> c + cc }
                    }
                    .collect(Collectors.toList<String>()))
        }
    };

    var aEstimatedCount: Long?
    var aAllUniqueValues: List<String?>? = null
    var aIsUsableWithJavaPattern: Boolean = true

    init {
        aEstimatedCount = TestingUtilities.BIG_INTEGER_MINUS_ONE
    }

    override fun getPattern(): String? {
        return aPattern
    }

    override fun getResultNode(): Node? {
        return aResultNode
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

    protected fun setCannotCompilePattern() {
        aIsUsableWithJavaPattern = false
    }

    override fun hasEstimatedCount(): Boolean {
        return TestingUtilities.BIG_INTEGER_MINUS_ONE != aEstimatedCount
    }

    override fun hasAllUniqueValues(): Boolean {
        return aAllUniqueValues != null
    }

    override fun useFindForMatching(): Boolean {
        return this === POSITIVE_LOOKAHEAD || (this === NEGATIVE_LOOKAHEAD
                ) || (this === POSITIVE_LOOKBEHIND
                ) || (this === NEGATIVE_LOOKBEHIND)
    }

    override fun isUsableWithJavaPattern(): Boolean {
        return aIsUsableWithJavaPattern
    }

    override fun toString(): String {
        return "$name : $aPattern"
    }
}
