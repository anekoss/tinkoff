package edu.hw5;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task1Test {

    static Stream<Arguments> provideDataForTest() {
        return Stream.of(
            Arguments.of(
                List.of(
                    "2022-03-12, 20:20 - 2022-03-12, 23:50"
                ), Duration.ofMinutes(230)),
            Arguments.of(
                List.of(
                    "2022-03-12, 20:20 - 2022-03-12, 23:50",
                    "2022-04-01, 21:30 - 2022-04-02, 01:20"
                ), Duration.ofMinutes(220)
            ),
            Arguments.of(List.of(
                "2022-11-25, 19:30 - 2022-11-25, 23:00",
                "2022-11-30, 20:45 - 2022-11-30, 23:30",
                "2022-03-12, 20:20 - 2022-03-12, 23:50",
                "2022-04-01, 21:30 - 2022-04-02, 01:20"
            ), Duration.ofMinutes(204)),
            Arguments.of(
                List.of(
                    "2022-03-12, 23:59 - 2022-03-13, 00:01",
                    "2022-03-12, 23:59 - 2022-03-13, 00:01"
                ), Duration.ofMinutes(2))
        );
    }

    @ParameterizedTest
    @MethodSource("provideDataForTest")
    void getSessionTimeTest(List<String> sessions, Duration excepted) throws InputErrorException {
        assertThat(new Task1().getSessionTime(sessions)).isEqualTo(excepted);
    }

    static Stream<Arguments> provideDataForExceptionTest() {
        return Stream.of(
            Arguments.of(
                List.of()
            ),
            Arguments.of(
                List.of(
                    ""
                )
            ),
            Arguments.of(
                Arrays.asList(
                    null, null
                )
            ),
            Arguments.of(Arrays.asList(
                "2022-11-25, 19:30 - 2022-11-25, 23:00",
                null,
                "2022-03-12, 20:20 - 2022-03-12, 23:50",
                "2022-04-01, 21:30 - 2022-04-02, 01:20"
            )),
            Arguments.of(
                List.of(
                    "2022-03-12, 23:59 hiii 2022-03-13, 00:01",
                    "2022-03-12, 23:59 - 2022-03-13, 00:01"
                ))
        );
    }

    @ParameterizedTest
    @MethodSource("provideDataForExceptionTest")
    void getSessionTimeExceptionTest(List<String> sessions) throws InputErrorException {
        assertThrows(InputErrorException.class, () -> new Task1().getSessionTime(sessions));
    }
}
