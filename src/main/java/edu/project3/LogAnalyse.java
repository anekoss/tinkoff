package edu.project3;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogAnalyse {
    private final Set<LogRecord> logSet;

    public LogAnalyse(Stream<LogRecord> stream) {
        this.logSet = stream.collect(Collectors.toSet());
    }

    public Long getRequestCount() {
        return logSet.stream().filter(logRecord -> logRecord.request() != null).count();
    }

    public Long getRequestCount(OffsetDateTime timeDate, String format) {
        if (format.equals("to")) {
            return logSet.stream()
                .filter(logRecord -> logRecord.request() != null && logRecord.timestamp().isBefore(timeDate)).count();
        }
        if (format.equals("from")) {
            return logSet.stream()
                .filter(logRecord -> logRecord.request() != null && logRecord.timestamp().isAfter(timeDate)).count();
        }
        return getRequestCount();
    }

    public Long getRequestCount(OffsetDateTime from, OffsetDateTime to) {
        return logSet.stream().filter(logRecord -> logRecord.request() != null && logRecord.timestamp().isAfter(to) &&
            logRecord.timestamp().isBefore(from)).count();
    }

}
