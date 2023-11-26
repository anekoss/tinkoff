package edu.project3.metrics;

import edu.project3.reader.LogRecord;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LogAnalyzeMetric implements LogMetric {
    private LogRecord logRecord;
    private final Set<LogObserver> observers;
    private final CountRequestObserver countRequestObserver;
    private final AvgResponseSizeObserver avgResponseSizeObserver;
    private final CountResourcesObserver countResourcesObserver;
    private final CountMaxStatusCodeObserver countMaxStatusCodeObserver;

    public LogAnalyzeMetric(
        CountRequestObserver countRequestObserver,
        AvgResponseSizeObserver avgResponseSizeObserver,
        CountResourcesObserver countResourcesObserver,
        CountMaxStatusCodeObserver countMaxStatusCodeObserver
    ) {
        this.countRequestObserver = countRequestObserver;
        this.avgResponseSizeObserver = avgResponseSizeObserver;
        this.countResourcesObserver = countResourcesObserver;
        this.countMaxStatusCodeObserver = countMaxStatusCodeObserver;
        observers = new HashSet<>();
        registerObserver(countRequestObserver);
        registerObserver(avgResponseSizeObserver);
        registerObserver(countResourcesObserver);
        registerObserver(countMaxStatusCodeObserver);
    }

    @Override
    public void registerObserver(LogObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers() {
        if (logRecord != null) {
            for (LogObserver observer : observers) {
                observer.update(logRecord);
            }
        }
    }

    public void onNewData(LogRecord newData) {
        this.logRecord = newData;
        notifyObservers();
    }

    public List<List<String>> getCommonMetrics() {
        return List.of(avgResponseSizeObserver.toStringMetric(), countRequestObserver.toStringMetric());
    }

    public List<List<String>> getStatusCodeMetrics() {
        return countMaxStatusCodeObserver.toListStringMetric();
    }

    public List<List<String>> getCountResourcesMetrics() {
        return countResourcesObserver.toListStringMetric();
    }
}
