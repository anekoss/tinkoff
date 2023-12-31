package edu.project3.metrics;

import edu.project3.reader.LogRecord;
import java.util.List;

public class AvgResponseSizeObserver implements LogObserver {
    private final String nameMetrics = "Средний размер ответа";
    private long size = 0;
    private int cntSize = 0;

    @Override
    public void update(LogRecord log) {
        size += log.responseSize();
        cntSize++;
    }

    public List<String> toStringMetric() {
        return List.of(nameMetrics, getAvgSize() + "b");
    }

    private long getAvgSize() {
        if (cntSize == 0) {
            return 0;
        }
        return size / cntSize;
    }

}
