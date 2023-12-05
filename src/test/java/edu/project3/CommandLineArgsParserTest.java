package edu.project3;

import edu.project3.args.ArgsRecord;
import edu.project3.args.CommandLineArgsParser;
import edu.project3.args.FormatReport;
import java.net.URI;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CommandLineArgsParserTest {
    public static Stream<Arguments> provideDataForTest() {
        return Stream.of(
            Arguments.of(
                new String[] {
                    "--path", "src/main/resources/project3/testLog/logs.txt", "--from", "2023-08-31", "--format", "adoc"},
                new ArgsRecord(Set.of(Path.of("src/main/resources/project3/testLog/logs.txt")), Set.of(),
                    LocalDate.parse("2023-08-31"), null, FormatReport.ADOC
                )
            ),
            Arguments.of(
                new String[] {
                    "--path", "src/main/resources/project3/testLog/log*", "--from", "2023-08-31", "--format", "adoc"},
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
                )
            ),
            Arguments.of(
                new String[] {
                    "--path", "src/main/resources/project3/testLog/log*", "src/main/resources/project3/testLog/logs.txt", "--from", "2023-08-31", "--format", "adoc"},
                new ArgsRecord(Set.of(
                    Path.of("src/main/resources/project3/testLog/logs.txt"),
                    Path.of("src/main/resources/project3/testLog/logs(1).txt"),
                    Path.of("src/main/resources/project3/testLog/logFile.txt")

                ), Set.of(),
                    LocalDate.parse("2023-08-31"), null, FormatReport.ADOC
                )
            ),
            Arguments.of(
                new String[] {
                    "--path", "src/main/resources/project3/*/*/logFile.txt", "--from", "2023-08-31", "--format", "adoc"},
                new ArgsRecord(Set.of(
                    Path.of("src/main/resources/project3/testLog/logs/logFile.txt")
                ), Set.of(),
                    LocalDate.parse("2023-08-31"), null, FormatReport.ADOC
                )
            ),
            Arguments.of(
                new String[] {
                    "--path", "src/main/resources/project3/testLog/**/logFile.txt", "--from", "2023-08-31", "--format", "adoc"},
                new ArgsRecord(Set.of(
                    Path.of("src/main/resources/project3/testLog/logs/logFile.txt")
                ), Set.of(),
                    LocalDate.parse("2023-08-31"), null, FormatReport.ADOC
                )
            ),
            Arguments.of(
                new String[] {
                    "--path", "src/main/resources/project3/testLog/log*", "src/main/resources/project3/testLog/logs.txt", "--from", "2023-08-31", "--to", "2023-09-25",
                    "--format", "adoc"},
                new ArgsRecord(Set.of(
                    Path.of("src/main/resources/project3/testLog/logs.txt"),
                    Path.of("src/main/resources/project3/testLog/logs(1).txt"),
                    Path.of("src/main/resources/project3/testLog/logFile.txt")

                ), Set.of(),
                    LocalDate.parse("2023-08-31"), LocalDate.parse("2023-09-25"), FormatReport.ADOC
                )
            ),
            Arguments.of(
                new String[] {
                    "--path", "src/main/resources/project3/testLog/log*", "src/main/resources/project3/testLog/logs.txt", "--to", "2023-09-25", "--format", "adoc"},
                new ArgsRecord(Set.of(
                    Path.of("src/main/resources/project3/testLog/logs.txt"),
                    Path.of("src/main/resources/project3/testLog/logs(1).txt"),
                    Path.of("src/main/resources/project3/testLog/logFile.txt")

                ), Set.of(),
                    null, LocalDate.parse("2023-09-25"), FormatReport.ADOC
                )
            ),
            Arguments.of(
                new String[] {
                    "--path", "src/main/resources/project3/testLog/log*",
                    "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs",
                    "src/main/resources/project3/testLog/logs.txt", "--from", "2023-08-31", "--format", "adoc"},
                new ArgsRecord(
                    Set.of(
                        Path.of("src/main/resources/project3/testLog/logs.txt"),
                        Path.of("src/main/resources/project3/testLog/logs(1).txt"),
                        Path.of("src/main/resources/project3/testLog/logFile.txt")
                    ),
                    Set.of(URI.create(
                        "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs")),
                    LocalDate.parse("2023-08-31"),
                    null,
                    FormatReport.ADOC
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource("provideDataForTest")
    void getArgsTest(String[] args, ArgsRecord excepted) {
        assertThat(new CommandLineArgsParser(args).getArgs()).isEqualTo(excepted);
    }

    static Stream<Arguments> provideDataForExceptionTest() {
        return Stream.of(
            Arguments.of("jdjdjdjdjd", "Неверный формат аргументов командной строки"),
            Arguments.of(
                "--path hi --from 2023-08-31 --format markdown",
                "Неверный формат пути к log файлу. Ожидается локальный шаблон или URL."
            ),
            Arguments.of(
                "--path src/main/resources/project3/testLog/logs.txt --from 2023-08-f31 --format markdown",
                "Неверный формат аргументов командной строки"
            ),
            Arguments.of(
                "--path src/main/resources/project3/testLog/logs.txt 2023-08-31 --format markdown",
                "Неверный формат пути к log файлу. Ожидается локальный шаблон или URL."
            ),
            Arguments.of(
                "--path src/main/resources/project3/testLog/logs.txt --from 2023-08-31 --to fjfj --format markdown",
                "Неверный формат аргументов командной строки"
            ),
            Arguments.of(
                "--path src/main/resources/project3/testLog/logs.txt --from 2023-08-31 --to --format markdown",
                "Неверный формат времени. Ожидается ISO8601."
            ),
            Arguments.of(
                "--path src/main/resources/project3/testLog/logs.txt --from 2023-08-31 --to 2023-08-31 --format hi",
                "Неверный формат аргументов командной строки"
            ),
            Arguments.of("src/main/resources/project3/testLog/logs.txt --from 2023-08-31 --to --format hi", "Неверный формат аргументов командной строки")
        );

    }

    @ParameterizedTest
    @MethodSource("provideDataForExceptionTest")
    void getArgsExceptionTest(String args, String exceptedMessage) {
        IllegalArgumentException exception =
            assertThrows(IllegalArgumentException.class, () -> new CommandLineArgsParser(args.split(" ")).getArgs());
        assertThat(exception.getMessage()).isEqualTo(exceptedMessage);
    }
}
