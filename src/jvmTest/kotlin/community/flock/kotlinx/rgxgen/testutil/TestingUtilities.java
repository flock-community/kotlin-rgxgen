package community.flock.kotlinx.rgxgen.testutil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;

import static community.flock.kotlinx.rgxgen.parsing.dflt.ConstantsProvider.MAX_UNICODE_CHARACTER;
import static community.flock.kotlinx.rgxgen.parsing.dflt.ConstantsProvider.SPACE_ASCII_CODE;

public final class TestingUtilities {
    public static final Long BIG_INTEGER_MINUS_ONE = Long.valueOf(-1);

    public static <T> List<T> iteratorToList(Iterator<T> it) {
        List<T> lst = new ArrayList<>(100);

        while (it.hasNext()) {
            T next = it.next();
            lst.add(next);
        }

        return lst;
    }

    public static Character[] getAllDigits() {
        return IntStream.rangeClosed('0', '9')
                        .mapToObj(i -> (char) i)
                        .toArray(Character[]::new);
    }

    public static Character[] makeUnicodeCharacterArray() {
        Character[] characters = new Character[MAX_UNICODE_CHARACTER - SPACE_ASCII_CODE];
        for (int i = SPACE_ASCII_CODE; i < MAX_UNICODE_CHARACTER; ++i) {
            characters[i - SPACE_ASCII_CODE] = (char) i;
        }
        return characters;
    }
}
