package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {

    @ParameterizedTest
    @DisplayName("OK should return correct result")
    @CsvSource({
        "0, 1",
        "-123, 3",
        "123406, 6",
        "2, 1",
    })
    void correctInputTest(int n, int res) {
        assertThat(Task2.countDigits(n)).isEqualTo(res);
    }

}
