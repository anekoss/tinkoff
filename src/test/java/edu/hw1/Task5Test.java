package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static edu.hw1.Task5.isPalindromeDescendant;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {

    @ParameterizedTest
    @DisplayName("OK the result should be true")
    @CsvSource({
        "11211230, true",
        "13001120, true",
        "23336014, true",
        "11, true",
        "123312, true",
        "121,true",
        "112, true"
    })
    void correctInputTest1(int n, boolean res) {
        assertThat(isPalindromeDescendant(n)).isEqualTo(res);
    }

    @ParameterizedTest
    @DisplayName("OK the result should be false")
    @CsvSource({
        "1124, false",
        "37, false",
    })
    void correctInputTest2(int n, boolean res) {
        assertThat(isPalindromeDescendant(n)).isEqualTo(res);
    }

}
