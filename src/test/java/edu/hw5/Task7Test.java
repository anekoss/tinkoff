package edu.hw5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task7Test {

    @ParameterizedTest
    @CsvSource({
        "1,false",
        "11, false",
        "2102, false",
        "111, false",
        "110, true",
        "0000000,true",
        "0010000, false",
        "  0, false"
    })
    void checkThirdCharZeroLengthMoreTwoTest(String string, boolean excepted) throws InputErrorException {
        assertThat(new Task7().checkThirdCharZeroLengthMoreTwo(string)).isEqualTo(excepted);
    }

    @ParameterizedTest
    @CsvSource({
        "1,true",
        "11, true",
        "2102, false",
        "111, true",
        "110, false",
        "0000000,true",
        "0010000, true",
        "  0, true"
    })
    void checkSameCharStartAndEndTest(String string, boolean excepted) throws InputErrorException {
        assertThat(new Task7().checkSameCharStartAndEnd(string)).isEqualTo(excepted);
    }

    @ParameterizedTest
    @CsvSource({
        "1,true",
        "11, true",
        "210, false",
        "1110, false",
        "111, true",
        "110, true",
        "0000000,false",
        "0010000, false",
        "  0, true"
    })
    void checkLengthMoreZeroLessFourTest(String string, boolean excepted) throws InputErrorException {
        assertThat(new Task7().checkLengthMoreZeroLessFour(string)).isEqualTo(excepted);
    }

    @ParameterizedTest
    @NullSource
    void checkNullWordForAlphabet(String string) {
        assertThrows(InputErrorException.class, () -> new Task7().checkLengthMoreZeroLessFour(string));
        assertThrows(InputErrorException.class, () -> new Task7().checkThirdCharZeroLengthMoreTwo(string));
        assertThrows(InputErrorException.class, () -> new Task7().checkSameCharStartAndEnd(string));
    }

    @ParameterizedTest
    @EmptySource
    void checkEmptyWordForAlphabet(String string) throws InputErrorException {
        assertThat(new Task7().checkLengthMoreZeroLessFour(string)).isFalse();
        assertThat(new Task7().checkThirdCharZeroLengthMoreTwo(string)).isFalse();
        assertThat(new Task7().checkSameCharStartAndEnd(string)).isFalse();
    }
}
