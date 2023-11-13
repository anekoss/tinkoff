package edu.hw3.Task7;

import java.util.Comparator;

public class Task7<T extends Comparable<T>> {

    public Comparator<T> nullComparator = (o1, o2) -> {
        if (o1 == null && o2 == null) {
            return 0;
        }
        if (o1 == null) {
            return -1;
        }
        if (o2 == null) {
            return 1;
        }
        return o1.compareTo(o2);
    };

}
