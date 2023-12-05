package edu.project3.metrics;

import edu.project3.reader.LogRecord;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.httpclient.HttpStatus;

public class CountMaxStatusCodeObserver implements LogObserver {

    private final Map<Integer, Integer> countStatusCodeMap = new HashMap<>();

    @Override
    public void update(LogRecord log) {
        Integer key = log.statusCode();
        if (countStatusCodeMap.containsKey(key)) {
            countStatusCodeMap.put(key, countStatusCodeMap.get(key) + 1);
        } else {
            countStatusCodeMap.put(key, 1);
        }
    }

    public List<List<String>> toListStringMetric() {
        return
            countStatusCodeMap.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(entry -> List.of(
                    entry.getKey().toString(),
                    HttpStatus.getStatusText(entry.getKey()),
                    entry.getValue().toString()
                )).toList();
    }
}
