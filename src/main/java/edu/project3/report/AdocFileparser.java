package edu.project3.report;

import edu.project3.metrics.LogAnalyze;
import java.util.List;
import java.util.stream.Stream;

public class AdocFileparser implements FileParser {
    private final List<List<String>> countRecourcesMetrics;
    private final List<List<String>> commonMetrics;
    private final List<List<String>> countStatusCodeMetrics;

    public AdocFileparser(LogAnalyze logAnalyze) {
        commonMetrics = logAnalyze.getCommonMetrics();
        countStatusCodeMetrics = logAnalyze.getStatusCodeMetrcis();
        countRecourcesMetrics = logAnalyze.getCountResourcesMetrics();
    }

    public String parseDelimiter(List<String> headerColumn) {
        return "|====";
    }

    public List<String> parseHeaderColumn(List<String> headerColumn) {
        String columnCount = "[cols=" + headerColumn.size() + "]";
        return Stream.of(
                List.of(columnCount, parseDelimiter(headerColumn)),
                headerColumn.stream().map(value -> "|" + value).toList()
            )
            .flatMap(List<String>::stream).toList();
    }

    public List<String> parseMetrics(List<List<String>> metrics) {
        return metrics.stream().flatMap(List<String>::stream).map(value -> "|" + value).toList();
    }

    public String parseHeader(String header) {
        return "==== " + header;
    }

    public String parseEmptyLine() {
        return "\n";
    }

    public List<String> parseMetricInfo(String header, List<String> headerColumn, List<List<String>> metrics) {
        return Stream.of(
            List.of(
                parseHeader(header),
                parseEmptyLine()
            ),
            parseHeaderColumn(headerColumn),
            parseMetrics(metrics),
            List.of(parseDelimiter(headerColumn))
        ).flatMap(List<String>::stream).toList();

    }

    public List<String> parseMetricLogsInfo() {
        return parseMetricLogsInfo(commonMetrics, countRecourcesMetrics, countStatusCodeMetrics);
    }
}
