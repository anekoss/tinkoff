package edu.hw3.Task3;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import static java.util.stream.Collectors.groupingBy;

@Slf4j
public class Task3<T> {
    public Map<T, Integer> freqDict(List<T> list) {
        log.info("dictionary data counting execution");
        Map<T, Integer> freqDictMap = list.stream().filter(Objects::nonNull)
            .collect(groupingBy(Function.identity(), Collectors.summingInt(e -> 1)));
        int countNull = (int) list.stream().filter(Objects::isNull).count();
        if (countNull > 0) {
            freqDictMap.put(null, countNull);
        }
        log.info("dictionary data counting completed");
        return freqDictMap;
    }
}
