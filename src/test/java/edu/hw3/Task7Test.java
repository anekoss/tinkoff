package edu.hw3;

import edu.hw3.Task7.Task7;
import org.junit.jupiter.api.Test;
import java.util.TreeMap;
import static org.assertj.core.api.Assertions.assertThat;

class Task7Test {

    @Test
    void integerComparatorTest() {
        TreeMap<Integer, String> tree = new TreeMap<>(new Task7<Integer>().nullComparator);
        tree.put(null, "test");
        tree.put(1, "test1");
        assertThat(tree).containsEntry(null, "test");
        assertThat(tree.get(null)).isEqualTo("test");
    }

    @Test
    void stringComparatorTest() {
        TreeMap<String, String> tree = new TreeMap<>(new Task7<String>().nullComparator);
        tree.put(null, "test");
        tree.put("1", "test1");
        assertThat(tree).containsEntry(null, "test");
        assertThat(tree.get(null)).isEqualTo("test");
    }
}
