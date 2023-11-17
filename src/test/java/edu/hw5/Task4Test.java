package edu.hw5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task4Test {

    @ParameterizedTest
    @CsvSource({"password, false",
        "P@ssw0rd, true",
        "hello233, false",
        "hello..., false",
        "Test!ng, true",
        "P@$$w0rd, false",
        "P@$$w0rd, false",
        "P@w0r||, false",
        "Pffggw0r|, true",
        " , false"
    })
    void passwdMatchPatternTest(String passwd, boolean excepted) {
        assertThat(new Task4().checkPasswordSecurity(passwd)).isEqualTo(excepted);
    }

    @ParameterizedTest
    @EmptySource
    @NullSource
    @NullAndEmptySource
    void passwdMatchNullOrEmptyTest(String passwd) {
        assertThat(new Task4().checkPasswordSecurity(passwd)).isFalse();
    }
}
