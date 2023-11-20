package edu.project3;

import edu.project3.args.ArgsRecord;
import edu.project3.reader.LogParser;
import edu.project3.reader.LogRecord;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class LogParserTest {

    static Stream<Arguments> provideDataForTest() {
        return Stream.of(
            Arguments.of(
                List.of(
                    "93.180.71.3 - - [17/May/2015:08:05:27 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"",
                    "93.180.71.3 - - [17/May/2015:08:05:23 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\""
                ),
                List.of(
                    new LogRecord(
                        "93.180.71.3",
                        OffsetDateTime.parse("2015-05-17T08:05:27Z"),
                        "GET /downloads/product_1 HTTP/1.1",
                        304,
                        0,
                        "-",
                        "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)"
                    ),
                    new LogRecord(
                        "93.180.71.3",
                        OffsetDateTime.parse("2015-05-17T08:05:23Z"),
                        "GET /downloads/product_1 HTTP/1.1",
                        304,
                        0,
                        "-",
                        "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)"
                    )
                )
            ),
            Arguments.of(
                List.of(
                    "217.168.17.5 - - [17/May/2015:08:05:09 +0000] \"GET /downloads/product_2 HTTP/1.1\" 200 490 \"-\" \"Debian APT-HTTP/1.3 (0.8.10.3)\""
                ),
                List.of(new LogRecord(
                    "217.168.17.5",
                    OffsetDateTime.parse("2015-05-17T08:05:09Z"),
                    "GET /downloads/product_2 HTTP/1.1",
                    200,
                    490,
                    "-",
                    "Debian APT-HTTP/1.3 (0.8.10.3)"
                ))

            )
        );
    }

    @ParameterizedTest
    @MethodSource("provideDataForTest")
    void parseLogsTest(List<String> linesStream, List<LogRecord> excepted) {
        ArgsRecord argsRecord = Mockito.mock(ArgsRecord.class);
        when(argsRecord.to()).thenReturn(excepted.get(0).timestamp().toLocalDate());
        when(argsRecord.from()).thenReturn(excepted.get(0).timestamp().toLocalDate());
        assertThat(new LogParser().parseLogs(linesStream.stream(), argsRecord).toList()).isEqualTo(excepted);
    }
}
