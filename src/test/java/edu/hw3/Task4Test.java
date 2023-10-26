package edu.hw3;

import org.junit.jupiter.api.Test;
import static edu.hw3.Task4.convertToRoman;
import static org.assertj.core.api.Assertions.assertThat;

public class Task4Test {

    @Test
    void convertToRomanTest() {
        assertThat(convertToRoman(2)).isEqualTo("II");
        assertThat(convertToRoman(12)).isEqualTo("XII");
        assertThat(convertToRoman(16)).isEqualTo("XVI");
    }
}
