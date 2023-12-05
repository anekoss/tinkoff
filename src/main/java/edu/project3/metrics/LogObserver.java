package edu.project3.metrics;

import edu.project3.reader.LogRecord;
import java.util.List;

public interface LogObserver {

    void update(LogRecord log);

    default List<String> toStringMetric() {
        return List.of();
    }

    default List<List<String>> toListStringMetric() {
        return List.of();
    }

}
