package edu.project3;

import edu.project3.args.FormatReport;
import edu.project3.metrics.LogAnalyze;
import edu.project3.report.LogReport;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class LogReportTest {
    static LogAnalyze logAnalyze = Mockito.mock(LogAnalyze.class);
    LogReport logReportMd = new LogReport(logAnalyze, FormatReport.MARKDOWN);
    LogReport logReportAdoc = new LogReport(logAnalyze, FormatReport.ADOC);

    @BeforeAll static void initLogReport() {
        when(logAnalyze.getCountResourcesMetrics()).thenReturn(List.of(
            List.of("GET /downloads/product_1 HTTP/1.1", "14"),
            List.of("GET /downloads/product_2 HTTP/1.1", "7")
        ));
        when(logAnalyze.getCommonMetrics()).thenReturn(List.of(
            List.of("Файл(ы)", "testLog\\logs\\logFile.txt"),
            List.of("Начальная дата", "2015-05-17"),
            List.of("Конечная дата", "2015-05-17"),
            List.of("Средний размер ответа", "101b"),
            List.of("Количество запросов", "21")
        ));
        when(logAnalyze.getStatusCodeMetrcis()).thenReturn(List.of(
            List.of("200", "OK", "1")
        ));
    }

    @AfterEach
    @BeforeEach
    void removeTestReport() throws IOException {
        Files.deleteIfExists(Path.of("REPORT.MD"));
        Files.deleteIfExists(Path.of("report.adoc"));
        Files.deleteIfExists(Path.of("report — копия.adoc"));
        Files.deleteIfExists(Path.of("REPORT — копия.adoc"));
    }

    @Test
    void createFileReportTest() {
        assertThat(logReportMd.createFileReport(FormatReport.MARKDOWN)).isEqualTo(Path.of("REPORT.MD"));
        assertThat(logReportAdoc.createFileReport(FormatReport.ADOC)).isEqualTo(Path.of("report.adoc"));
    }

    @ParameterizedTest
    @MethodSource("provideDataForTest")
    void writeMetricsToFileTest(List<String> mdReport, List<String> adocReport) throws IOException {

        logReportMd.createFileReport(FormatReport.MARKDOWN);
        logReportAdoc.createFileReport(FormatReport.ADOC);
        Path mdPath = logReportMd.writeMetricsToFile();
        assertThat(mdPath).isRegularFile().isNotEmptyFile();
        Path adocPath = logReportAdoc.writeMetricsToFile();
        assertThat(adocPath).isRegularFile().isNotEmptyFile();
        assertThat(Files.readAllLines(mdPath)).isEqualTo(mdReport);
        assertThat(Files.readAllLines(adocPath)).isEqualTo(adocReport);
    }

    static Stream<Arguments> provideDataForTest() {
        return Stream.of(
            Arguments.of(
                List.of(
                    "#### Общая информация",
                    "",
                    "",
                    "|Метрика|Значение|",
                    "|:-:|:-:|",
                    "|Файл(ы)|testLog\\logs\\logFile.txt|",
                    "|Начальная дата|2015-05-17|",
                    "|Конечная дата|2015-05-17|",
                    "|Средний размер ответа|101b|",
                    "|Количество запросов|21|",
                    "",
                    "#### Запрашиваемые ресурсы",
                    "",
                    "",
                    "|Ресурсы|Количество|",
                    "|:-:|:-:|",
                    "|GET /downloads/product_1 HTTP/1.1|14|",
                    "|GET /downloads/product_2 HTTP/1.1|7|",
                    "",
                    "#### Коды ответа",
                    "",
                    "",
                    "|Код|Имя|Количество|",
                    "|:-:|:-:|:-:|",
                    "|200|OK|1|",
                    ""
                ),
                List.of(
                    "==== Общая информация",
                    "",
                    "",
                    "[cols=2]",
                    "|====",
                    "|Метрика",
                    "|Значение",
                    "|Файл(ы)",
                    "|testLog\\logs\\logFile.txt",
                    "|Начальная дата",
                    "|2015-05-17",
                    "|Конечная дата",
                    "|2015-05-17",
                    "|Средний размер ответа",
                    "|101b",
                    "|Количество запросов",
                    "|21",
                    "|====",
                    "==== Запрашиваемые ресурсы",
                    "",
                    "",
                    "[cols=2]",
                    "|====",
                    "|Ресурсы",
                    "|Количество",
                    "|GET /downloads/product_1 HTTP/1.1",
                    "|14",
                    "|GET /downloads/product_2 HTTP/1.1",
                    "|7",
                    "|====",
                    "==== Коды ответа",
                    "",
                    "",
                    "[cols=3]",
                    "|====",
                    "|Код",
                    "|Имя",
                    "|Количество",
                    "|200",
                    "|OK",
                    "|1",
                    "|===="
                )
            ));
    }

}
