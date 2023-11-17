package edu.hw5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task6Test {

    @ParameterizedTest
    @CsvSource({
        "abc ,achfdbaabgabcaabg, true",
        "xyz ,achfdbaabgabcaabg, false",
        "aab ,achfdbaabgabcaabg, true",
        "sjnx ,dgfhsjnxcbejdk, true",
        "ttttttttt, dgfhsjnxcbejdk, false"
    })
    void checkIsSubstringTest(String substring, String string, boolean excepted) throws InputErrorException {
        assertThat(new Task6().checkIsSubstring(substring, string)).isEqualTo(excepted);
        assertThrows(InputErrorException.class, () -> new Task6().checkIsSubstring(string, substring));
    }

    @ParameterizedTest
    @CsvSource({
        "abc",
    })
    void checkIsSubstringNullTest(String string) throws InputErrorException {
        assertThat(new Task6().checkIsSubstring(string, string)).isTrue();
        assertThat(new Task6().checkIsSubstring("", string)).isTrue();
        assertThrows(InputErrorException.class, () -> new Task6().checkIsSubstring(null, null));
        assertThrows(InputErrorException.class, () -> new Task6().checkIsSubstring(string, null));
        assertThrows(InputErrorException.class, () -> new Task6().checkIsSubstring(null, string));

    }

}
