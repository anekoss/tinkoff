package edu.hw5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task8Test {

    @ParameterizedTest
    @CsvSource({"1, true",
        "23411, false",
        "101, true",
        "10, false",
        "2341, false"
    })
    void oddLengthTest(String string, boolean excepted) throws InputErrorException {
        assertThat(new Task8().checkOddLength(string)).isEqualTo(excepted);
    }

    @ParameterizedTest
    @CsvSource({"0101, false",
        "13411, false",
        "0411, false",
        "101, false",
        "10, true",
        "01010, true"
    })
    void oddLengthStart1OrEvenLengthStart0Test(String string, boolean excepted) throws InputErrorException {
        assertThat(new Task8().checkOddLengthStartOneOrEvenLengthStartZero(string)).isEqualTo(excepted);
    }

    @ParameterizedTest
    @CsvSource({"01010, true",
        "13411, false",
        "040011, false",
        "101, false",
        "000000, true",
        "011111010, true"
    })
    void count0DivBy3Test(String string, boolean excepted) throws InputErrorException {
        assertThat(new Task8().checkCountZeroDivByTree(
            string)).isEqualTo(excepted);
    }

    @ParameterizedTest
    @CsvSource({"01010, true",
        "13411, false",
        "040011, false",
        "101, true",
        "000000, true",
        "11, false",
        "111, false"
    })
    void allExcept11And111Test(String string, boolean excepted) throws InputErrorException {
        assertThat(new Task8().checkAllExcept11And111(
            string)).isEqualTo(excepted);
    }

    @ParameterizedTest
    @CsvSource({"01010, false",
        "13411, false",
        "040011, false",
        "101, true",
        "000000, false",
        "11, false",
        "111, true"
    })
    void anyOdd1Test(String string, boolean excepted) throws InputErrorException {
        assertThat(new Task8().checkAnyOdd1(
            string)).isEqualTo(excepted);
    }

    @ParameterizedTest
    @CsvSource({"01010, false",
        "13411, false",
        "040011, false",
        "10001, false",
        "000000, false",
        "1000, true",
        "0010, true"
    })
    void moreTwo0AndOne1Test(String string, boolean excepted) throws InputErrorException {
        assertThat(new Task8().checkMoreTwo0AndOne1(
            string)).isEqualTo(excepted);
    }

    @ParameterizedTest
    @CsvSource({"01010, true",
        "13411, false",
        "040011, false",
        "10001, true",
        "000000, true",
        "1100, false",
        "011010, false"
    })
    void noNeighbour1Test(String string, boolean excepted) throws InputErrorException {
        assertThat(new Task8().checkNoNeighbour1(
            string)).isEqualTo(excepted);
    }

    @ParameterizedTest
    @NullSource
    void nullAlphabetTest(String string) {
        assertThrows(InputErrorException.class, () -> new Task8().checkOddLength(string));
        assertThrows(InputErrorException.class, () -> new Task8().checkAllExcept11And111(string));
        assertThrows(InputErrorException.class, () -> new Task8().checkAnyOdd1(string));
        assertThrows(InputErrorException.class, () -> new Task8().checkMoreTwo0AndOne1(string));
        assertThrows(InputErrorException.class, () -> new Task8().checkCountZeroDivByTree(string));
        assertThrows(InputErrorException.class, () -> new Task8().checkOddLengthStartOneOrEvenLengthStartZero(string));
        assertThrows(InputErrorException.class, () -> new Task8().checkNoNeighbour1(string));
    }

    @ParameterizedTest
    @EmptySource
    void emptyAlphabetTest(String string) throws InputErrorException {
        assertThat(new Task8().checkOddLength(string)).isEqualTo(false);
        assertThat(new Task8().checkAllExcept11And111(string)).isEqualTo(true);
        assertThat(new Task8().checkCountZeroDivByTree(string)).isEqualTo(true);
        assertThat(new Task8().checkAnyOdd1(string)).isEqualTo(false);
        assertThat(new Task8().checkMoreTwo0AndOne1(string)).isEqualTo(false);
        assertThat(new Task8().checkOddLengthStartOneOrEvenLengthStartZero(string)).isEqualTo(false);
        assertThat(new Task8().checkNoNeighbour1(string)).isEqualTo(true);
    }
}
