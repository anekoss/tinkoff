package edu.project3.metrics;

public interface LogMetric {

    void registerObserver(LogObserver observer);

    void notifyObservers();
}
