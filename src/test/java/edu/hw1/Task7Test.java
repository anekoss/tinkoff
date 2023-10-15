package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static edu.hw1.Task7.rotateLeft;
import static edu.hw1.Task7.rotateRight;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task7Test {

    @ParameterizedTest
    @DisplayName("OK cyclic shift to the right")
    @CsvSource({
        "8, 1, 4",
        "0, 4, 0",
        "16, 5, 16"
    })
    void correctInputTest1(int n, int shift, int res) {
        assertThat(rotateRight(n, shift)).isEqualTo(res);
    }

    @ParameterizedTest
    @DisplayName("OK cyclic shift to the left")
    @CsvSource({
        "16, 1, 1",
        "17, 2, 6",
        "0, 5, 0",
        "128, 8, 128",
        "128, 0, 128"
    })
    void correctInputTest2(int n, int shift, int res) {
        assertThat(rotateLeft(n, shift)).isEqualTo(res);
    }

}
