package edu.hw3;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static edu.hw3.Task4.Task4.convertToRoman;
import static org.assertj.core.api.Assertions.assertThat;

class Task4Test {
    static Stream<Arguments> provideDataForTest() {
        return Stream.of(
            Arguments.of(2, "II"),
            Arguments.of(12, "XII"),
            Arguments.of(16, "XVI"),
            Arguments.of(138, "CXXXVIII"),
            Arguments.of(3008, "MMMVIII"),
            Arguments.of(3183, "MMMCLXXXIII"),
            Arguments.of(1000, "M")
        );
    }

    @ParameterizedTest
    @MethodSource("provideDataForTest")
    void convertToRomanTest(int number, String excepted) {
        assertThat(convertToRoman(number)).isEqualTo(excepted);
    }
}
