package edu.project3.metrics;

import edu.project3.reader.LogRecord;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountResourcesObserver implements LogObserver {
    private final Map<String, Integer> countResources = new HashMap<>();

    @Override
    public void update(LogRecord log) {
        String key = log.request();
        if (countResources.containsKey(key)) {
            countResources.put(key, countResources.get(key) + 1);
        } else {
            countResources.put(key, 1);
        }
    }

    public List<List<String>> toStringMetric() {
        return
            countResources.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(entry -> List.of(entry.getKey(), entry.getValue().toString())).toList();
    }
}
