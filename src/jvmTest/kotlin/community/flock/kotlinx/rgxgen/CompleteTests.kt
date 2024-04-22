package community.flock.kotlinx.rgxgen

import community.flock.kotlinx.rgxgen.RgxGen.Companion.parse
import community.flock.kotlinx.rgxgen.util.Util.newRandom
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.Arrays
import java.util.regex.Pattern
import java.util.stream.IntStream
import java.util.stream.Stream

class CompleteTests {
    @ParameterizedTest(name = "{index}: {0}")
    @MethodSource("getData")
    fun generateTest(aName: String?, aUseFind: Boolean, aRegex: String, aSeed: Int) {
        val rgxGen = parse(aRegex)
        val s = rgxGen.generate(newRandom(aSeed))
        Assertions.assertTrue(matches(aRegex, s, aUseFind), "Text: '$s'does not match pattern $aRegex")
    }

    @ParameterizedTest(name = "{index}: {0}")
    @MethodSource("getData")
    fun generateNotMatchingTest(aName: String?, aUseFind: Boolean, aRegex: String, aSeed: Int) {
        val rgxGen = parse(aRegex)
        val s = rgxGen.generateNotMatching(newRandom(aSeed))
        Assertions.assertFalse(matches(aRegex, s, aUseFind), "Text: '$s'does not match pattern $aRegex")
    }

    companion object {
        @JvmStatic
        val data: Stream<Arguments>
            get() = Arrays.stream(
                arrayOf(
                    arrayOf<Any>(
                        "Card number",
                        java.lang.Boolean.FALSE,
                        "[a-zA-Z]{2}[0-9]{2}[a-zA-Z0-9]{4}[0-9]{7}([a-zA-Z0-9]?){0,16}"
                    ),
                    arrayOf<Any>(
                        "IP v4",
                        java.lang.Boolean.FALSE,
                        "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9])\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9])\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9])\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9])"
                    ),
                    arrayOf<Any>(
                        "IP v6",
                        java.lang.Boolean.FALSE,
                        "(([0-9a-f]{1,4}:){1,1}(:[0-9a-f]{1,4}){1,6})|(([0-9a-f]{1,4}:){1,2}(:[0-9a-f]{1,4}){1,5})|" +
                                "(([0-9a-f]{1,4}:){1,3}(:[0-9a-f]{1,4}){1,4})|(([0-9a-f]{1,4}:){1,4}(:[0-9a-f]{1,4}){1,3})|(([0-9a-f]{1,4}:){1,5}(:[0-9a-f]{1,4}){1,2})|" +
                                "(([0-9a-f]{1,4}:){1,6}(:[0-9a-f]{1,4}){1,1})|((([0-9a-f]{1,4}:){1,7}|:):)|(:(:[0-9a-f]{1,4}){1,7})|(((([0-9a-f]{1,4}:){6})(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)" +
                                "(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}))|((([0-9a-f]{1,4}:){5}[0-9a-f]{1,4}:(25[0-5]|2,[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}))|" +
                                "(([0-9a-f]{1,4}:){5}:[0-9a-f]{1,4}:(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3})|(([0-9a-f]{1,4}:){1,1}(:[0-9a-f]{1,4}){1,4}:" +
                                "(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3})|(([0-9a-f]{1,4}:){1,2}(:[0-9a-f]{1,4}){1,3}:(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\." +
                                "(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3})|(([0-9a-f]{1,4}:){1,3}(:[0-9a-f]{1,4}){1,2}:(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3})|" +
                                "(([0-9a-f]{1,4}:){1,4}(:[0-9a-f]{1,4}){1,1}:(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3})|((([0-9a-f]{1,4}:){1,5}|:):(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\." +
                                "(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3})|(:(:[0-9a-f]{1,4}){1,5}:(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3})"
                    ),
                    arrayOf<Any>("Simple XML tag", java.lang.Boolean.FALSE, "<([a-z])>asdf<\\/\\1>"),
                    arrayOf<Any>("XML tag", java.lang.Boolean.FALSE, "<([a-z]+)[^<]{5,10}<\\/\\1>"),
                    arrayOf<Any>("JustRandom", java.lang.Boolean.FALSE, "\\w\\W\\d\\D\\s\\S"),
                    arrayOf<Any>("JustRandom2", java.lang.Boolean.FALSE, "[a-zA-Z0-9_]{5}"),
                    arrayOf<Any>("JustRandom3", java.lang.Boolean.FALSE, "[^a-zA-Z0-9_]{5}"),
                    arrayOf<Any>(
                        "Email name-surname",
                        java.lang.Boolean.FALSE,
                        "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*"
                    ),
                    arrayOf<Any>(
                        "Email quoted name left",
                        java.lang.Boolean.FALSE,
                        "[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]"
                    ),
                    arrayOf<Any>(
                        "Email quoted name right",
                        java.lang.Boolean.FALSE,
                        "\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f]"
                    ),
                    arrayOf<Any>(
                        "Email quoted name",
                        java.lang.Boolean.FALSE,
                        "\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\""
                    ),
                    arrayOf<Any>(
                        "Email before @",
                        java.lang.Boolean.FALSE,
                        "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")"
                    ),
                    arrayOf<Any>(
                        "Email after @ - dns name",
                        java.lang.Boolean.FALSE,
                        "[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\."
                    ),
                    arrayOf<Any>(
                        "Email after @",
                        java.lang.Boolean.FALSE,
                        "(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"
                    ),
                    arrayOf<Any>(
                        "Email pattern",
                        java.lang.Boolean.FALSE,
                        "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"
                    ),
                    arrayOf<Any>(
                        "Name or Surname",
                        java.lang.Boolean.FALSE,
                        "(-?([A-Z].\\s)?([A-Z][a-z]+)\\s?)+([A-Z]'([A-Z][a-z]+))?"
                    ),
                    arrayOf<Any>("File Extension", java.lang.Boolean.FALSE, "(\\.)[a-z]{1,4}$"),
                    arrayOf<Any>(
                        "24 Hour Time",
                        java.lang.Boolean.FALSE,
                        "([01]?[0-9]|2[0-3]):[0-5][0-9](:[0-5][0-9])?"
                    ),
                    arrayOf<Any>(
                        "ddXmmXyyyy Date",
                        java.lang.Boolean.FALSE,
                        "^(0?[1-9]|[12][0-9]|3[01])([ \\/\\-])(0?[1-9]|1[012])\\2([0-9][0-9][0-9][0-9])(([ -])([0-1]?[0-9]|2[0-3]):[0-5]?[0-9]:[0-5]?[0-9])?$"
                    ),
                    arrayOf<Any>(
                        "mm/dd/yyyy Date",
                        java.lang.Boolean.FALSE,
                        "^(0?[1-9]|1[0-2])[/](0?[1-9]|[12]\\d|3[01])[/](19|20)\\d{2}$"
                    ),
                    arrayOf<Any>("24 or 32 bit colors", java.lang.Boolean.FALSE, "(?:#|0x)?(?:[0-9A-Fa-f]{2}){3,4}"),
                    arrayOf<Any>(
                        "rgb Value",
                        java.lang.Boolean.FALSE,
                        "rgb\\((?:([0-9]{1,2}|1[0-9]{1,2}|2[0-4][0-9]|25[0-5]), ?)(?:([0-9]{1,2}|1[0-9]{1,2}|2[0-4][0-9]|25[0-5]), ?)(?:([0-9]{1,2}|1[0-9]{1,2}|2[0-4][0-9]|25[0-5]))\\)"
                    ),
                    arrayOf<Any>(
                        "src of image tag",
                        java.lang.Boolean.FALSE,
                        "^<\\s*img[^>]+src\\s*=\\s*([\"'])(.*?)\\1[^>]*>$"
                    ),
                    arrayOf<Any>("Float value", java.lang.Boolean.FALSE, "^[0-9]*.[0-9]*[1-9]+$"),
                    arrayOf<Any>(
                        "Windows path",
                        java.lang.Boolean.FALSE,
                        "^(([a-zA-Z]:)|(\\\\{2}\\w+)\\$?)(\\\\(\\w[\\w ]*.*))+$"
                    ),
                    arrayOf<Any>("Dollar amounts", java.lang.Boolean.FALSE, "\\$[0-9]+(.[0-9][0-9])?"),
                    arrayOf<Any>(
                        "Youtube URL",
                        java.lang.Boolean.FALSE,
                        "(?:https?://)?(?:(?:(?:www\\.?)?youtube\\.com(?:/(?:(?:watch\\?.*?(v=[^&\\s]+).*)|(?:v(/.*))|(channel/.+)|(?:user/(.+))|(?:results\\?(search_query=.+))))?)|(?:youtu\\.be(/.*)?))"
                    ),
                    arrayOf<Any>("CSS comment", java.lang.Boolean.FALSE, "(/\\*)(.|\r|\n)*?(\\*/)"),
                    arrayOf<Any>(
                        "URL with optional protocol",
                        java.lang.Boolean.FALSE,
                        "^((https?|ftp|file):\\/\\/)?([\\da-z\\.-]+)\\.([a-z\\.]{2,6})([\\/\\w \\.-]*)*\\/?$"
                    ),
                    arrayOf<Any>(
                        "Password strength",
                        java.lang.Boolean.FALSE,
                        "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$"
                    ),
                    arrayOf<Any>(
                        "UK Postal code",
                        java.lang.Boolean.FALSE,
                        "^(([gG][iI][rR] {0,}0[aA]{2})|(([aA][sS][cC][nN]|[sS][tT][hH][lL]|[tT][dD][cC][uU]|[bB][bB][nN][dD]|[bB][iI][qQ][qQ]|[fF][iI][qQ][qQ]|[pP][cC][rR][nN]|[sS][iI][qQ][qQ]|[iT][kK][cC][aA]) {0,}1[zZ]{2})|((([a-pr-uwyzA-PR-UWYZ][a-hk-yxA-HK-XY]?[0-9][0-9]?)|(([a-pr-uwyzA-PR-UWYZ][0-9][a-hjkstuwA-HJKSTUW])|([a-pr-uwyzA-PR-UWYZ][a-hk-yA-HK-Y][0-9][abehmnprv-yABEHMNPRV-Y]))) {0,}[0-9][abd-hjlnp-uw-zABD-HJLNP-UW-Z]{2}))$"
                    ),
                    arrayOf<Any>(
                        "Semver",
                        java.lang.Boolean.FALSE,
                        "^(0|[1-9][0-9]*)\\.(0|[1-9][0-9]*)\\.(0|[1-9][0-9]*)(?:-((?:0|[1-9][0-9]*|[0-9]*[a-zA-Z-][0-9a-zA-Z-]*)(?:\\.(?:0|[1-9][0-9]*|[0-9]*[a-zA-Z-][0-9a-zA-Z-]*))*))?(?:\\+([0-9a-zA-Z-]+(?:\\.[0-9a-zA-Z-]+)*))?$"
                    ),
                    arrayOf<Any>(
                        "Periodic Table Elements",
                        java.lang.Boolean.FALSE,
                        "\\b(?:A[cglmr-u]|B[aehikr]?|C[adefl-orsu]?|D[bsy]|E[rsu]|F[elmr]?|G[ade]|H[efgos]?|I[nr]?|Kr?|L[airuv]|M[dgont]|N[abdeiop]?|Os?|P[abdmortu]?|R[abe-hnu]|S[bcegimnr]?|T[abcehilm]|U(?:u[opst])?|V|W|Xe|Yb?|Z[nr])\\b"
                    ),
                    arrayOf<Any>(
                        "Russia Phone Number",
                        java.lang.Boolean.FALSE,
                        "^((\\+7|7|8)+([0-9]){10})$|\\b\\d{3}[-.]?\\d{3}[-.]?\\d{4}\\b"
                    ),
                    arrayOf<Any>("Brainfuck code", java.lang.Boolean.FALSE, "^[+-<>.,\\[\\] \t\n\r]+$"),
                    arrayOf<Any>(
                        "USA and Canada Zip codes",
                        java.lang.Boolean.FALSE,
                        "(^\\d{5}(-\\d{4})?$)|(^[A-Z]{1}\\d{1}[A-Z]{1} *\\d{1}[A-Z]{1}\\d{1}$)"
                    ),
                    arrayOf<Any>("JS comments", java.lang.Boolean.TRUE, "//(?![\\S]{2,}\\.[\\w]).*|/\\*(.|\n)+\\*/"),
                    arrayOf<Any>("2-5 letter palindromes", java.lang.Boolean.FALSE, "\\b(\\w?)(\\w)\\w?\\2\\1"),
                    arrayOf<Any>(
                        "Morse code",
                        java.lang.Boolean.TRUE,
                        "^[.-]{1,5}(?: +[.-]{1,5})*(?: +[.-]{1,5}(?: +[.-]{1,5})*)$"
                    ),
                    arrayOf<Any>(
                        "JWT",
                        java.lang.Boolean.TRUE,
                        "^[A-Za-z0-9-_=]+\\.[A-Za-z0-9-_=]+\\.?[A-Za-z0-9-_.+/=]*$"
                    ),
                )
            )
                .flatMap { arr: Array<Any> ->
                    IntStream.range(0, 100)
                        .mapToObj { index: Int ->
                            Arguments.of(
                                arr[0], arr[1], arr[2], index
                            )
                        }
                }


        private val PATTERN_CACHE: MutableMap<String, Pattern> = HashMap()

        private fun matches(pattern: String, text: String, aUseFind: Boolean): Boolean {
            val compiledPattern = PATTERN_CACHE.computeIfAbsent(pattern) { k: String? -> Pattern.compile(pattern) }
            val matcher = compiledPattern.matcher(text)
            return if (aUseFind) matcher.find() else matcher.matches()
        }
    }
}
