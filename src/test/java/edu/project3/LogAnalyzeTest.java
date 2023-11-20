package edu.project3;

import edu.project3.args.ArgsRecord;
import edu.project3.args.FormatReport;
import edu.project3.metrics.LogAnalyze;
import edu.project3.reader.LogReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LogAnalyzeTest {
    static Stream<Arguments> provideDataForTest() {
        return Stream.of(
            Arguments.of(
                Path.of("testLog/logs/logFile.txt"),
                new ArgsRecord(
                    Set.of(
                        Path.of("testLog/logs/logFile.txt")),
                    Set.of(),
                    LocalDate.parse("2015-05-17"),
                    LocalDate.parse("2015-05-17"),
                    FormatReport.MARKDOWN
                ),
                List.of(
                    List.of("Файл(ы)", "testLog\\logs\\logFile.txt"),
                    List.of("Начальная дата", "2015-05-17"),
                    List.of("Конечная дата", "2015-05-17"),
                    List.of("Средний размер ответа", "101b"),
                    List.of("Количество запросов", "21")
                ),
                List.of(
                    List.of("GET /downloads/product_1 HTTP/1.1", "14"),
                    List.of("GET /downloads/product_2 HTTP/1.1", "7")
                ),
                List.of(
                    List.of("304", "Not Modified", "15"),
                    List.of("404", "Not Found", "5"),
                    List.of("200", "OK", "1")
                )
            ));
    }

    @ParameterizedTest
    @MethodSource("provideDataForTest")
    void logAnalyzeTest(
        Path path,
        ArgsRecord argsRecord,
        List<List<String>> commonMetrics,
        List<List<String>> countResourcesMetrics,
        List<List<String>> countStatusCodeMetrics
    ) throws IOException {
        LogReader logReader = mock(LogReader.class);
        when(logReader.getLogsStream()).thenReturn(Files.readAllLines(path).stream());
        when(logReader.getArgsRecord()).thenReturn(argsRecord);
        LogAnalyze logAnalyze = new LogAnalyze(logReader);
        logAnalyze.getLogAnalyzeMetrics();
        assertThat(logAnalyze.getCommonMetrics()).isEqualTo(commonMetrics);
        assertThat(logAnalyze.getCountResourcesMetrics()).isEqualTo(countResourcesMetrics);
        assertThat(logAnalyze.getStatusCodeMetrcis()).isEqualTo(countStatusCodeMetrics);
    }
}
