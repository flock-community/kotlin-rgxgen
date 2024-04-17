package community.flock.kotlinx.rgxgen.nodes;

import community.flock.kotlinx.rgxgen.model.MatchType;
import community.flock.kotlinx.rgxgen.nodes.SymbolSet;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static community.flock.kotlinx.rgxgen.parsing.dflt.ConstantsProvider.makeAsciiCharacterArray;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class SymbolSetCaseInsensitiveTests {
    public static char[] excluding(String chars) {
        char[] allSymbols = makeAsciiCharacterArray();
        char[] result = new char[allSymbols.length - chars.length()];
        int target = 0;
        for (char sym : allSymbols) {
            if (chars.indexOf(sym) == -1) {
                result[target] = sym;
                ++target;
            }
        }
        return result;
    }


    public static Stream<Arguments> parameters() {
        return Stream.of(
                Arguments.of("a", "Aa"),
                Arguments.of("abc", "ABCabc"),
                Arguments.of("123", "123"),
                Arguments.of("123ab", "123ABab"),
                Arguments.of("A", "Aa")
        );
    }

    @ParameterizedTest
    @MethodSource("parameters")
    public void positiveSetTest(String aOriginalString, String aExpectedCaseInsensitive) {
        SymbolSet symbolSet = SymbolSet.ofAsciiCharacters(aOriginalString, aOriginalString.toCharArray(), MatchType.POSITIVE);
        char[] actual = symbolSet.getCaseInsensitiveSymbolSetIndexer().getAll();
        char[] expected = aExpectedCaseInsensitive.toCharArray();
        assertArrayEquals(expected, actual, "\n" + Arrays.asList(expected) + "\nexpected vs got\n" + Arrays.asList(actual) + '\n');
    }

    @ParameterizedTest
    @MethodSource("parameters")
    public void negativeSetTest(String aOriginalString, String aExpectedCaseInsensitive) {
        SymbolSet symbolSet = SymbolSet.ofAsciiCharacters(aOriginalString, aOriginalString.toCharArray(), MatchType.NEGATIVE);
        char[] actual = symbolSet.getCaseInsensitiveSymbolSetIndexer().getAll();
        char[] expected = excluding(aExpectedCaseInsensitive);
        assertArrayEquals(expected, actual, "\n" + Arrays.asList(expected) + "\nexpected vs got\n" + Arrays.asList(actual) + '\n');

    }
}
