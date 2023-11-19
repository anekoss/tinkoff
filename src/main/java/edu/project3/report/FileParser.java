package edu.project3.report;

import java.util.List;
import java.util.stream.Stream;

public interface FileParser {
    String GENERAL_INFO_HEADER = "Общая информация";
    String REQUESTED_RESOURCES_HEADER = "Запрашиваемые ресурсы";
    String RESPONSE_CODES_HEADER = "Коды ответа";
    String RESOURCE_COLUMN = "Ресурсы";
    String METRIC_COLUMN = "Метрика";
    String METRIC_VALUE_COLUMN = "Значение";
    String COUNT_COLUMN = "Количество";
    String CODE_COLUMN = "Код";
    String CODE_INFO_COLUMN = "Имя";

    String parseDelimiter(List<String> headerColumn);

    List<String> parseMetrics(List<List<String>> metrics);

    String parseHeader(String header);

    String parseEmptyLine();

    List<String> parseMetricInfo(String header, List<String> headerColumn, List<List<String>> metrics);

    List<String> parseMetricLogsInfo();

    default List<String> parseMetricLogsInfo(
        List<List<String>> commonMetrics,
        List<List<String>> countRecourcesMetrics,
        List<List<String>> countStatusCodeMetrics
    ) {
        List<String> commonMetricsInfo =
            parseMetricInfo(GENERAL_INFO_HEADER, List.of(METRIC_COLUMN, METRIC_VALUE_COLUMN), commonMetrics);
        List<String> countResourcesMetricsInfo =
            parseMetricInfo(REQUESTED_RESOURCES_HEADER, List.of(RESOURCE_COLUMN, COUNT_COLUMN), countRecourcesMetrics);
        List<String> countStatusCodeMetricsInfo =
            parseMetricInfo(
                RESPONSE_CODES_HEADER,
                List.of(CODE_COLUMN, CODE_INFO_COLUMN, COUNT_COLUMN),
                countStatusCodeMetrics
            );
        return Stream.of(commonMetricsInfo, countResourcesMetricsInfo, countStatusCodeMetricsInfo)
            .flatMap(List<String>::stream).toList();
    }
}
