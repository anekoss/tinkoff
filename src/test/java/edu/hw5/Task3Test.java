package edu.hw5;

import edu.hw5.Task3.DayAgoDateFormatter;
import edu.hw5.Task3.EuropeanDateFormatter;
import edu.hw5.Task3.EuropeanShortDateFormatter;
import edu.hw5.Task3.Formatter;
import edu.hw5.Task3.USADateFormatter;
import edu.hw5.Task3.WordDateFormatter;
import org.junit.jupiter.params.provider.Arguments;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

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
                "1/3/20", LocalDate.of(1976, 3, 1))

            );
    }

    void parseDateTest(String date, LocalDate excepted) throws InputErrorException {
        Formatter formatter =
            new Formatter(new USADateFormatter(new EuropeanDateFormatter(new EuropeanShortDateFormatter(new WordDateFormatter(
                new DayAgoDateFormatter())))));
        assertThat(formatter.parseDate(date)).isEqualTo(excepted);
    }
}
