package edu.project3.metrics;

import edu.project3.reader.LogRecord;

public interface LogObserver {

    void update(LogRecord log);

}
