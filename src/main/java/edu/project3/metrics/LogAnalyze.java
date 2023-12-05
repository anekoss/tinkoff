package edu.project3.metrics;

import edu.project3.reader.LogParser;
import edu.project3.reader.LogReader;
import edu.project3.reader.LogRecord;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LogAnalyze {
    private final Iterator<LogRecord> logIterator;
    private final LogAnalyzeMetric filter;
    private final List<List<String>> commonMetrics;
    private List<List<String>> countStatusCodeMetrics = new ArrayList<>();
    private List<List<String>> countResourcesMetrics = new ArrayList<>();

    public LogAnalyze(LogReader logReader) {
        this.commonMetrics = logReader.getArgsRecord().toStringMetrics();
        this.logIterator = new LogParser().parseLogs(logReader.getLogsStream(), logReader.getArgsRecord()).iterator();
        this.filter = new LogAnalyzeMetric(
            new CountRequestObserver(),
            new AvgResponseSizeObserver(),
            new CountResourcesObserver(),
            new CountMaxStatusCodeObserver()
        );
    }

    public void getLogAnalyzeMetrics() {
        analyzeLogs();
        commonMetrics.addAll(filter.getCommonMetrics());
        countResourcesMetrics = filter.getCountResourcesMetrics();
        countStatusCodeMetrics = filter.getStatusCodeMetrics();
    }

    public List<List<String>> getCommonMetrics() {
        return commonMetrics;
    }

    public List<List<String>> getCountResourcesMetrics() {
        return this.countResourcesMetrics;
    }

    public List<List<String>> getStatusCodeMetrcis() {
        return countStatusCodeMetrics;
    }

    private void analyzeLogs() {
        while (logIterator.hasNext()) {
            filter.onNewData(logIterator.next());
        }
    }
}
