package edu.project3;

import edu.project3.metrics.LogAnalyze;
import edu.project3.report.AdocFileParser;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class AdocFileParserTest {

    static Stream<Arguments> provideDataForTest() {
        return Stream.of(
            Arguments.of(
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
                    List.of("200", "OK", "1")
                )
                ,
                List.of(
                    "==== Общая информация",
                    "\n",
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
                    "\n",
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
                    "\n",
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

    @ParameterizedTest
    @MethodSource("provideDataForTest")
    void parseTest(
        List<List<String>> commonMetrics,
        List<List<String>> countResourcesMetrics,
        List<List<String>> countStatusCodeMetrics, List<String> excepted
    ) {
        LogAnalyze logAnalyze = Mockito.mock(LogAnalyze.class);
        when(logAnalyze.getCommonMetrics()).thenReturn(commonMetrics);
        when(logAnalyze.getStatusCodeMetrcis()).thenReturn(countStatusCodeMetrics);
        when(logAnalyze.getCountResourcesMetrics()).thenReturn(countResourcesMetrics);
        AdocFileParser adocFileParser = new AdocFileParser(logAnalyze);
        assertThat(adocFileParser.parseMetricLogsInfo()).isEqualTo(excepted);
    }

    static Stream<Arguments> provideDataForColumnParseTest() {
        return Stream.of(
            Arguments.of(
                List.of("Файл(ы)", "testLog\\logs\\logFile.txt"),
                List.of("[cols=2]", "|====", "|Файл(ы)", "|testLog\\logs\\logFile.txt")
            ),
            Arguments.of(
                List.of("Начальная дата", "2015-05-17"),
                List.of("[cols=2]", "|====", "|Начальная дата", "|2015-05-17")
            ),
            Arguments.of(
                List.of("Конечная дата", "2015-05-17"),
                List.of("[cols=2]", "|====", "|Конечная дата", "|2015-05-17")
            )
        );
    }

    @ParameterizedTest
    @MethodSource("provideDataForColumnParseTest")
    void parseHeaderColumnTest(List<String> columns, List<String> excepted) {
        LogAnalyze logAnalyze = Mockito.mock(LogAnalyze.class);
        AdocFileParser adocFileParser = new AdocFileParser(logAnalyze);
        assertThat(adocFileParser.parseHeaderColumn(columns)).isEqualTo(excepted);
    }

    static Stream<Arguments> provideDataForParseMetricsTest() {
        return Stream.of(
            Arguments.of(
                List.of(
                    List.of("GET /downloads/product_1 HTTP/1.1", "14"),
                    List.of("GET /downloads/product_2 HTTP/1.1", "7")
                ),
                List.of(
                    "|GET /downloads/product_1 HTTP/1.1",
                    "|14",
                    "|GET /downloads/product_2 HTTP/1.1",
                    "|7"
                )
            ), Arguments.of(
                List.of(
                    List.of("304", "Not Modified", "15"),
                    List.of("404", "Not Found", "5"),
                    List.of("200", "OK", "1")
                ), List.of(
                    "|304",
                    "|Not Modified",
                    "|15",
                    "|404",
                    "|Not Found",
                    "|5",
                    "|200",
                    "|OK",
                    "|1"
                ))
        );
    }

    @ParameterizedTest
    @MethodSource("provideDataForParseMetricsTest")
    void parseMetricsTest(List<List<String>> metrics, List<String> excepted) {
        LogAnalyze logAnalyze = Mockito.mock(LogAnalyze.class);
        AdocFileParser adocFileParser = new AdocFileParser(logAnalyze);
        assertThat(adocFileParser.parseMetrics(metrics)).isEqualTo(excepted);
    }

}

