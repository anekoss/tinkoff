package edu.hw5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task5Test {

    @ParameterizedTest
    @CsvSource({"password, false",
        "А123ВЕ777, true",
        "О777ОО177, true",
        "123АВЕ777, false",
        "А123ВГ77, false",
        "А123ВЕ7777, false",
        "А123ВЕ7777А123ВЕ7777, false",
        "A123BC177, false",
        "A123BC17, false",
        "ABCD1234, false",
        "hello, false"
    })
    void checkCorrectNumberTest(String number, boolean excepted) throws InputErrorException {
        assertThat(new Task5().checkCorrectNumber(number)).isEqualTo(excepted);
    }

    @ParameterizedTest
    @NullSource
    void checkCorrectNumberNullTest(String number) {
        assertThrows(InputErrorException.class, () -> new Task5().checkCorrectNumber(number));
    }

    @ParameterizedTest
    @EmptySource
    void checkCorrectNumberEmptyTest(String number) throws InputErrorException {
        assertThat(new Task5().checkCorrectNumber(number)).isFalse();

    }
}
