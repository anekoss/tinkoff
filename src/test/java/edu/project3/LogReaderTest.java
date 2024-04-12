package edu.project3;

import edu.project3.args.ArgsRecord;
import edu.project3.args.CommandLineArgsParser;
import edu.project3.args.FormatReport;
import edu.project3.reader.LogReader;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class LogReaderTest {

    public static Stream<Arguments> provideDataForTest() {
        return Stream.of(
            Arguments.of(
                new ArgsRecord(
                    Set.of(),
                    Set.of(),
                    LocalDate.parse("2023-08-31"),
                    null,
                    FormatReport.ADOC
                ), 0
            ),
            Arguments.of(
                new ArgsRecord(
                    Set.of(Path.of("src/main/resources/project3/testLog/logs.txt")), Set.of(),
                    LocalDate.parse("2023-08-31"), null, FormatReport.ADOC
                ), 2634
            ),
            Arguments.of(
                new ArgsRecord(
                    Set.of(
                        Path.of("src/main/resources/project3/testLog/logs.txt"),
                        Path.of("src/main/resources/project3/testLog/logs(1).txt"),
                        Path.of("src/main/resources/project3/testLog/logFile.txt")
                    ),
                    Set.of(),
                    LocalDate.parse("2023-08-31"),
                    null,
                    FormatReport.ADOC
                ), 5278
            ),
            Arguments.of(
                new ArgsRecord(Set.of(
                    Path.of("src/main/resources/project3/testLog/logs.txt"),
                    Path.of("src/main/resources/project3/testLog/logs(1).txt"),
                    Path.of("src/main/resources/project3/testLog/logFile.txt")

                ), Set.of(),
                    LocalDate.parse("2023-08-31"), null, FormatReport.ADOC
                ), 5278
            ),
            Arguments.of(
                new ArgsRecord(Set.of(
                    Path.of("src/main/resources/project3/testLog/logs/logFile.txt")
                ), Set.of(),
                    LocalDate.parse("2023-08-31"), null, FormatReport.ADOC
                ), 28
            ),
            Arguments.of(
                new ArgsRecord(Set.of(
                    Path.of("src/main/resources/project3/testLog/logs/logFile.txt")
                ), Set.of(),
                    LocalDate.parse("2023-08-31"), null, FormatReport.ADOC
                ), 28
            ),
            Arguments.of(
                new ArgsRecord(Set.of(
                    Path.of("src/main/resources/project3/testLog/logs.txt"),
                    Path.of("src/main/resources/project3/testLog/logFile.txt")

                ), Set.of(),
                    LocalDate.parse("2023-08-31"), LocalDate.parse("2023-09-25"), FormatReport.ADOC
                ), 2644
            ),
            Arguments.of(
                new ArgsRecord(Set.of(
                    Path.of("src/main/resources/project3/testLog/logs(1).txt")

                ), Set.of(),
                    null, LocalDate.parse("2023-09-25"), FormatReport.ADOC
                ), 2634
            )
        );
    }

    @ParameterizedTest
    @MethodSource("provideDataForTest")
    void getLogsStreamTest(ArgsRecord record, Integer countLine) {
        CommandLineArgsParser commandLineArgsParser = Mockito.mock(CommandLineArgsParser.class);
        when(commandLineArgsParser.getArgs()).thenReturn(record);
        assertThat(new LogReader(commandLineArgsParser).getLogsStream().toList().size()).isEqualTo(countLine);
    }

    @ParameterizedTest
    @MethodSource("provideDataForTest")
    void parseLogsTest(ArgsRecord record, Integer countLine) {
        CommandLineArgsParser commandLineArgsParser = Mockito.mock(CommandLineArgsParser.class);
        when(commandLineArgsParser.getArgs()).thenReturn(record);
        assertThat(new LogReader(commandLineArgsParser).getLogsStream().toList().size()).isEqualTo(countLine);
    }
}
