package edu.hw6;

import edu.hw6.Task1.DiskMap;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task1Test {

    @AfterAll
    static void deleteTestFile() throws IOException {
        Files.deleteIfExists(Path.of("Task1Test"));
    }

    static Stream<Arguments> provideDataForTest() {
        DiskMap diskMap = new DiskMap();
        DiskMap diskMap1 = new DiskMap();
        return Stream.of(
            Arguments.of(diskMap),
            Arguments.of(diskMap1)
        );
    }

    static Stream<Arguments> provideDataForMapMethodTest() {
        return Stream.of(
            Arguments.of(new DiskMap(), 0, Map.of("1", "2", "2", "3", "5", "5"))
        );
    }

    @ParameterizedTest
    @MethodSource("provideDataForMapMethodTest")
    void mapSizeTest(DiskMap diskMap, int size) {
        assertThat(diskMap.size()).isEqualTo(size);
        assertThat(diskMap.isEmpty()).isEqualTo(size == 0);
        diskMap.put("null", "null");
        assertThat(diskMap.size()).isEqualTo(size + 1);
        assertThat(diskMap.isEmpty()).isEqualTo(size + 1 == 0);
    }

    @ParameterizedTest
    @MethodSource("provideDataForMapMethodTest")
    void mapPutAllTest(DiskMap diskMap, int ignoredSize, Map<String, String> elements) {
        diskMap.putAll(elements);
        assertThat(diskMap.size()).isEqualTo(elements.size());
        for (Map.Entry<String, String> entry : elements.entrySet()) {
            assertThat(diskMap.get(entry.getKey())).isEqualTo(entry.getValue());
        }
        assertThat(diskMap.size()).isEqualTo(elements.size());
    }

    @ParameterizedTest
    @MethodSource("provideDataForMapMethodTest")
    void mapGetAndRemoveTest(DiskMap diskMap, int ignoredSize, Map<String, String> elements) {
        diskMap.putAll(elements);
        int sizeElements = elements.size();
        for (Map.Entry<String, String> entry : elements.entrySet()) {
            assertThat(diskMap.get(entry.getKey())).isEqualTo(entry.getValue());
            diskMap.remove(entry.getKey());
            assertThat(diskMap.size()).isEqualTo(--sizeElements);
            assertThat(diskMap.get(entry.getKey())).isNull();
        }
    }

    @ParameterizedTest
    @MethodSource("provideDataForMapMethodTest")
    void containsKeyAndValueTest(DiskMap diskMap, int ignoredSize, Map<String, String> elements) {
        for (Map.Entry<String, String> entry : elements.entrySet()) {
            assertThat(diskMap.containsKey(entry.getKey())).isEqualTo(false);
            assertThat(diskMap.containsValue(entry.getValue())).isEqualTo(false);
            diskMap.put(entry.getKey(), entry.getValue());
            assertThat(diskMap.containsKey(entry.getKey())).isEqualTo(true);
            assertThat(diskMap.containsValue(entry.getValue())).isEqualTo(true);
        }
    }

    @ParameterizedTest
    @MethodSource("provideDataForTest")
    void WriteAndReadMapToFileTest(DiskMap map) {
        try (ObjectOutputStream inputStream = new ObjectOutputStream(new FileOutputStream("Task1Test"))) {
            inputStream.writeObject(map);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("Task1Test"))) {
            assertThat(map).isEqualTo(objectInputStream.readObject());
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @ParameterizedTest
    @MethodSource("provideDataForTest")
    void readMapFromFile() {

    }

    @ParameterizedTest
    @MethodSource("provideDataForMapMethodTest")
    void clearTest(DiskMap diskMap, int ignoredSize, Map<String, String> elements) {
        diskMap.clear();
        assertThat(diskMap.size()).isEqualTo(0);
        diskMap.putAll(elements);
        assertThat(diskMap.size()).isEqualTo(elements.size());
        diskMap.clear();
        assertThat(diskMap.size()).isEqualTo(0);
    }

    @ParameterizedTest
    @MethodSource("provideDataForMapMethodTest")
    void keySetAndValuesTest(DiskMap diskMap, int ignoredSize, Map<String, String> elements) {
        assertThat(diskMap.keySet()).isEqualTo(Set.of());
        assertThat(diskMap.entrySet()).isEqualTo(Set.of());
        assertThat(diskMap.values()).isEmpty();
        diskMap.putAll(elements);
        assertThat(diskMap.keySet()).isEqualTo(elements.keySet());
        assertThat(diskMap.entrySet()).isEqualTo(elements.entrySet());
        assertThat(diskMap.values().stream().sorted().toList()).isEqualTo(elements.values().stream().sorted().toList());
    }

    @ParameterizedTest
    @MethodSource("provideDataForMapMethodTest")
    void entrySetTest(DiskMap diskMap, int ignoredSize, Map<String, String> elements) {
        assertThat(diskMap.entrySet()).isEqualTo(Set.of());
        assertThat(diskMap.values()).isEmpty();
        diskMap.putAll(elements);
        assertThat(diskMap.keySet()).isEqualTo(elements.keySet());
        assertThat(diskMap.values().stream().sorted().toList()).isEqualTo(elements.values().stream().sorted().toList());
    }

}
