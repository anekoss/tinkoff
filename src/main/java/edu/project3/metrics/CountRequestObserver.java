package edu.project3.metrics;

import edu.project3.reader.LogRecord;
import java.util.List;

public class CountRequestObserver implements LogObserver {

    private final String nameMetrics = "Количество запросов";
    private long count = 0;

    @Override
    public void update(LogRecord log) {
        count++;
    }

    public List<String> toStringMetric() {
        return List.of(nameMetrics, String.valueOf(count));
    }
}
