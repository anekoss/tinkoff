package edu.hw3;

import edu.hw3.Task4.ConvertToRomanException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static edu.hw3.Task4.Task4.convertToRoman;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Task4Test {
    static Stream<Arguments> provideDataForTest() {
        return Stream.of(
            Arguments.of(2, "II"),
            Arguments.of(12, "XII"),
            Arguments.of(16, "XVI"),
            Arguments.of(138, "CXXXVIII"),
            Arguments.of(3008, "MMMVIII"),
            Arguments.of(3183, "MMMCLXXXIII"),
            Arguments.of(1000, "M"),
            Arguments.of(4345, "MMMMCCCXLV"),
            Arguments.of(5000, "MMMMM"),
            Arguments.of(4960, "MMMMCMLX")
        );
    }

    @ParameterizedTest
    @MethodSource("provideDataForTest")
    void convertToRomanTest(Integer number, String excepted) throws ConvertToRomanException {
        assertThat(convertToRoman(number)).isEqualTo(excepted);
    }

    @Test
    void nullConvertToRomanTest() {
        assertThrows(ConvertToRomanException.class, () -> convertToRoman(null));
        assertThrows(ConvertToRomanException.class, () -> convertToRoman(0));
    }
}
