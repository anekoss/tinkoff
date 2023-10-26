package edu.hw3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Task3<T> {
    public Map<T, Long> freqDict(List<T> list) {
        Map<T, Long> map =
            list.stream().collect(groupingBy(Function.identity(), counting()));
        return map;
    }
}
