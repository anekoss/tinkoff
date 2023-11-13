package edu.hw5;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task2Test {

    static Stream<Arguments> provideDataForAllFridayTest() {
        return Stream.of(
            Arguments.of(
                1925, List.of(LocalDate.of(1925, 2, 13), LocalDate.of(1925, 3, 13), LocalDate.of(1925, 11, 13))
            ),
            Arguments.of(
                2024, List.of(LocalDate.of(2024, 9, 13), LocalDate.of(2024, 12, 13))
            ),
            Arguments.of(
                1973, List.of(LocalDate.of(1973, 4, 13), LocalDate.of(1973, 7, 13))
            )
        );
    }

    @ParameterizedTest
    @MethodSource("provideDataForAllFridayTest")
    void getAllFridayForYearTest(int year, List<LocalDate> dates) {
        assertThat(new Task2().getAllFridayForYear(year)).isEqualTo(dates);

    }

    static Stream<Arguments> provideDataForNextFridayTest() {
        return Stream.of(
            Arguments.of(
                LocalDate.of(1925, 2, 13), LocalDate.of(1925, 3, 13)),
            Arguments.of(
                LocalDate.of(2024, 9, 13), LocalDate.of(2024, 12, 13))
            ,
            Arguments.of(
                LocalDate.of(1973, 4, 13), LocalDate.of(1973, 7, 13))
        );
    }

    @ParameterizedTest
    @MethodSource("provideDataForNextFridayTest")
    void getNextFridayThirteenTest(LocalDate date, LocalDate exceptedDate) throws InputErrorException {
        assertThat(new Task2().getNextFridayThirteen(date)).isEqualTo(exceptedDate);
    }

    @ParameterizedTest
    @NullSource
    void getNextFridayThirteenNullTest(LocalDate date) {
        assertThrows(InputErrorException.class, () -> new Task2().getNextFridayThirteen(date));
    }
}
