package edu.hw5;

import edu.hw5.Task3.DayAgoDateFormatter;
import edu.hw5.Task3.EuropeanDateFormatter;
import edu.hw5.Task3.EuropeanShortDateFormatter;
import edu.hw5.Task3.Formatter;
import edu.hw5.Task3.USADateFormatter;
import edu.hw5.Task3.WordDateFormatter;
import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task3Test {

    static Stream<Arguments> provideDataForTest() {
        return Stream.of(
            Arguments.of(
                "2020-10-10", LocalDate.of(2020, 10, 10)),
            Arguments.of(
                "2020-12-2", LocalDate.of(2020, 12, 2)),
            Arguments.of(
                "1/3/1976", LocalDate.of(1976, 3, 1)),
            Arguments.of(
                "1/3/20", LocalDate.of(2020, 3, 1)),
            Arguments.of(
                "today", LocalDate.now()),
            Arguments.of(
                "tomorrow", LocalDate.now().plusDays(1)),
            Arguments.of(
                "yesterday", LocalDate.now().minusDays(1)),
            Arguments.of(
                "1 day ago", LocalDate.now().minusDays(1)),
            Arguments.of(
                "2234 days ago", LocalDate.now().minusDays(2234))
        );
    }

    @ParameterizedTest
    @MethodSource("provideDataForTest")
    void parseDateTest(String date, LocalDate excepted) throws InputErrorException {
        Formatter formatter =
            new Formatter(new USADateFormatter(new EuropeanDateFormatter(new EuropeanShortDateFormatter(new WordDateFormatter(
                new DayAgoDateFormatter())))));
        assertThat(formatter.parseDate(date)).isEqualTo(Optional.of(excepted));
    }

    @ParameterizedTest
    @MethodSource("provideDataForTest")
    void parseDateBadTest(String date) throws InputErrorException {
        Formatter formatter =
            new Formatter(new USADateFormatter(new EuropeanDateFormatter(new EuropeanShortDateFormatter(new WordDateFormatter(
                new DayAgoDateFormatter())))));
        assertThat(formatter.parseDate(date + "sdmcjd")).isEqualTo(Optional.empty());
    }

    @ParameterizedTest
    @NullSource
    void parseDateNullTest(String date) {
        Formatter formatter =
            new Formatter(new USADateFormatter(new EuropeanDateFormatter(new EuropeanShortDateFormatter(new WordDateFormatter(
                new DayAgoDateFormatter())))));
        assertThrows(InputErrorException.class, () -> formatter.parseDate(date));
    }
}
