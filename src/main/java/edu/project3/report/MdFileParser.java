package edu.project3.report;

import edu.project3.metrics.LogAnalyze;
import java.util.List;

public class MdFileParser implements FileParser {

    private final List<List<String>> countRecourcesMetrics;
    private final List<List<String>> commonMetrics;
    private final List<List<String>> countStatusCodeMetrics;

    public MdFileParser(LogAnalyze logAnalyze) {
        commonMetrics = logAnalyze.getCommonMetrics();
        countStatusCodeMetrics = logAnalyze.getStatusCodeMetrcis();
        countRecourcesMetrics = logAnalyze.getCountResourcesMetrics();
    }

    public String parseDelimiter(List<String> headerColumn) {
        return "|:-:|" + ":-:|".repeat(Math.max(0, headerColumn.size() - 1));
    }

    public String parseHeaderColumn(List<String> headerColumn) {
        return "|" + String.join("|", headerColumn) + "|";
    }

    public List<String> parseMetrics(List<List<String>> metrics) {
        return metrics.stream().map(value -> "|" + String.join("|", value) + "|" + "\n").toList();
    }

    public String parseHeader(String header) {
        return "#### " + header;
    }

    public String parseEmptyLine() {
        return "\n";
    }

    public List<String> parseMetricInfo(String header, List<String> headerColumn, List<List<String>> metrics) {
        return List.of(
            parseHeader(header),
            parseEmptyLine(),
            parseHeaderColumn(headerColumn),
            parseDelimiter(headerColumn),
            String.join("", parseMetrics(metrics))
        );

    }

    public List<String> parseMetricLogsInfo() {
        return parseMetricLogsInfo(commonMetrics, countRecourcesMetrics, countStatusCodeMetrics);
    }
}

