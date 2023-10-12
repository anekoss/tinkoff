package edu.hw1;

import static edu.hw1.Task4.fixString;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {

    @ParameterizedTest
    @DisplayName("OK should return correct result")
    @CsvSource({
        "123456, 214365",
        "hTsii  s aimex dpus rtni.g, This is a mixed up string.",
        "badce, abcde",
        "оПомигети псаривьтс ртко!и, Помогите исправить строки!"
    })
    void correctInputTest(String s, String res) {
        assertThat(fixString(s)).isEqualTo(res);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Incorrect input data format: null or empty parameter")
    void inCorrectInputTest(String s) {
        assertThat(fixString(s)).isEqualTo(null);
    }
}
