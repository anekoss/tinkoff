package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {

    @ParameterizedTest
    @DisplayName("Incorrect input data format")
    @CsvSource({
        "-1:24, -1",
        "sh:24, -1",
        "12:sh, -1",
        "12:-3, -1",
        "14:60, -1",
        "14:70, -1",
        "1415, -1",
    })
    void incorrectInputTest1(String str, int res) {
        assertThat(Task1.minutesToSeconds(str)).isEqualTo(res);
    }

    @ParameterizedTest
    @DisplayName("Incorrect input data format: not all parameters")
    @CsvSource({
        ":24, -1",
        ":, -1",
        "14:, -1"
    })
    void incorrectInputTest2(String str, int res) {
        assertThat(Task1.minutesToSeconds(str)).isEqualTo(res);
    }

    @ParameterizedTest
    @DisplayName("Incorrect input data format: null or empty parameter")
    @NullAndEmptySource
    void incorrectInputTest3(String str) {
        assertThat(Task1.minutesToSeconds(str)).isEqualTo(-1);
    }

    @ParameterizedTest
    @DisplayName("OK should return correct result")
    @CsvSource({
        "14:00, 840",
        "14:15, 855",
        "140:27, 8427",
    })
    void correctInputTest(String str, int res) {
        assertThat(Task1.minutesToSeconds(str)).isEqualTo(res);
    }

}
