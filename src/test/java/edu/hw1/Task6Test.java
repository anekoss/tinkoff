package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static edu.hw1.Task6.countK;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task6Test {

    @ParameterizedTest
    @DisplayName("OK should return correct result")
    @CsvSource({
        "3524, 3",
        "6621, 5",
        "6554, 4",
        "1234, 3",
        "6174, 0"
    })
    void correctInputTest(int n, int res) {
        assertThat(countK(n)).isEqualTo(res);
    }

    @ParameterizedTest
    @DisplayName("Incorrect input data format")
    @CsvSource({
        "999, -1",
        "1000, -1",
        "8888, -1"
    })
    void inCorrectInputTest2(int n, int res) {
        assertThat(countK(n)).isEqualTo(res);
    }

}
