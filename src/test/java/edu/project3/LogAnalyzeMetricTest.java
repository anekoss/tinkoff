package edu.project3;

import edu.project3.metrics.AvgResponseSizeObserver;
import edu.project3.metrics.CountMaxStatusCodeObserver;
import edu.project3.metrics.CountRequestObserver;
import edu.project3.metrics.CountResourcesObserver;
import edu.project3.metrics.LogAnalyzeMetric;
import edu.project3.reader.LogRecord;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Stream;
import org.apache.commons.httpclient.HttpStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import static org.assertj.core.api.Assertions.assertThat;

public class LogAnalyzeMetricTest {
    static LogAnalyzeMetric logAnalyzeMetric;
    static CountRequestObserver countRequestObserver;
    static CountResourcesObserver countResourcesObserver;
    static AvgResponseSizeObserver avgResponseSizeObserver;
    static CountMaxStatusCodeObserver countMaxStatusCodeObserver;
    CountResourcesObserver countResourcesObserver1 = new CountResourcesObserver();

    static Stream<Arguments> provideDataForTest() {
        return Stream.of(
            Arguments.of(
                new LogRecord(
                    "93.180.71.3",
                    OffsetDateTime.parse("2015-05-17T08:05:27Z"),
                    "GET /downloads/product_1 HTTP/1.1",
                    304,
                    0,
                    "-",
                    "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)"
                ),
                List.of("Количество запросов", "1"),
                List.of(List.of("304", HttpStatus.getStatusText(304), "1")),
                List.of(List.of("GET /downloads/product_1 HTTP/1.1", "1")), List.of("Средний размер ответа", "0b")
            ),
            Arguments.of(
                new LogRecord(
                    "93.180.71.3",
                    OffsetDateTime.parse("2015-05-17T08:05:23Z"),
                    "GET /downloads/product_1 HTTP/1.1",
                    304,
                    0,
                    "-",
                    "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)"
                ), List.of("Количество запросов", "2"),
                List.of(List.of("304", HttpStatus.getStatusText(304), "2")),
                List.of(List.of("GET /downloads/product_1 HTTP/1.1", "2")), List.of("Средний размер ответа", "0b")
            ),
            Arguments.of(
                new LogRecord(
                    "217.168.17.5",
                    OffsetDateTime.parse("2015-05-17T08:05:09Z"),
                    "GET /downloads/product_2 HTTP/1.1",
                    200,
                    490,
                    "-",
                    "Debian APT-HTTP/1.3 (0.8.10.3)"
                ),
                List.of("Количество запросов", "3"),
                List.of(
                    List.of("304", HttpStatus.getStatusText(304), "2"),
                    List.of("200", HttpStatus.getStatusText(200), "1")
                ),
                List.of(
                    List.of("GET /downloads/product_1 HTTP/1.1", "2"),
                    List.of("GET /downloads/product_2 HTTP/1.1", "1")
                ),
                List.of("Средний размер ответа", "163b")
            )
        );
    }

    @BeforeAll
    static void initLogAnalyzeMetric() {
        countRequestObserver = new CountRequestObserver();
        countResourcesObserver = new CountResourcesObserver();
        countMaxStatusCodeObserver = new CountMaxStatusCodeObserver();
        avgResponseSizeObserver = new AvgResponseSizeObserver();
        logAnalyzeMetric = new LogAnalyzeMetric(
            countRequestObserver, avgResponseSizeObserver, countResourcesObserver, countMaxStatusCodeObserver);
    }

    @ParameterizedTest
    @MethodSource("provideDataForTest")
    void onNewDataTest(
        LogRecord logRecord,
        List<String> countRequests,
        List<List<String>> statusCodeMap,
        List<List<String>> countResources,
        List<String> avgSize
    ) {
        logAnalyzeMetric.onNewData(logRecord);
        assertThat(countMaxStatusCodeObserver.toListStringMetric()).isEqualTo(statusCodeMap);
        assertThat(countResourcesObserver.toListStringMetric()).isEqualTo(countResources);
        assertThat(countRequestObserver.toStringMetric()).isEqualTo(countRequests);
        assertThat(avgResponseSizeObserver.toStringMetric()).isEqualTo(avgSize);
    }

    @Test
    @AfterEach
    void onNullDataTest() {
        List<String> countRequests = countRequestObserver.toStringMetric();
        List<List<String>> statusCodeMap = countMaxStatusCodeObserver.toListStringMetric();
        List<List<String>> countResources = countResourcesObserver.toListStringMetric();
        List<String> avgSize = avgResponseSizeObserver.toStringMetric();
        logAnalyzeMetric.onNewData(null);
        assertThat(countMaxStatusCodeObserver.toListStringMetric()).isEqualTo(statusCodeMap);
        assertThat(countResourcesObserver.toListStringMetric()).isEqualTo(countResources);
        assertThat(countRequestObserver.toStringMetric()).isEqualTo(countRequests);
        assertThat(avgResponseSizeObserver.toStringMetric()).isEqualTo(avgSize);
    }

    static Stream<Arguments> provideDataForRegisterTest() {
        return Stream.of(
            Arguments.of(
                new LogRecord(
                    "93.180.71.3",
                    OffsetDateTime.parse("2015-05-17T08:05:27Z"),
                    "GET /downloads/product_1 HTTP/1.1",
                    304,
                    0,
                    "-",
                    "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)"
                ),
                List.of(List.of("GET /downloads/product_1 HTTP/1.1", "1")), List.of("Средний размер ответа", "0b")
            ));
    }

    @ParameterizedTest
    @MethodSource("provideDataForRegisterTest")
    void registerObserverTest(
        LogRecord logRecord,
        List<List<String>> countResources
    ) {
        LogAnalyzeMetric logAnalyzeMetric1 = new LogAnalyzeMetric(
            Mockito.mock(CountRequestObserver.class),
            Mockito.mock(AvgResponseSizeObserver.class),
            new CountResourcesObserver(),
            Mockito.mock(CountMaxStatusCodeObserver.class)
        );
        logAnalyzeMetric1.registerObserver(countResourcesObserver1);
        logAnalyzeMetric1.onNewData(logRecord);
        assertThat(countResourcesObserver1.toListStringMetric()).isEqualTo(countResources);
        assertThat(countResourcesObserver1.toListStringMetric()).isEqualTo(logAnalyzeMetric1.getCountResourcesMetrics());
    }
}
