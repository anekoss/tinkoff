package edu.hw6.Task1;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DiskMap implements Map<String, String>, Serializable {
    private final List<Entry<String, String>> map;

    public DiskMap() {
        map = new ArrayList<>();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        if (key instanceof String) {
            return map.stream().map(Entry::getKey).anyMatch(key::equals);
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        if (value instanceof String) {
            return map.stream().map(Entry::getValue)
                .anyMatch(value::equals);
        }
        return false;
    }

    @Override
    public String get(Object key) {
        Entry<String, String> node =
            map.stream().filter(entry -> key.equals(entry.getKey())).findAny().orElse(null);
        if (node != null) {
            return node.getValue();
        }
        return null;
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        Entry<String, String> newNode = new AbstractMap.SimpleEntry<>(key, value);
        int indexNode = map.indexOf(newNode);
        if (indexNode == -1) {
            map.add(newNode);
        } else {
            map.add(indexNode, newNode);
            return map.remove(indexNode + 1).getValue();
        }
        return null;
    }

    @Override
    public String remove(Object key) {
        if (key instanceof String) {
            int indexNode = map.stream().map(Entry::getKey).toList().indexOf(key);
            if (indexNode != -1) {
                return map.remove(indexNode).getValue();
            }
        }
        return null;
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        m.forEach(this::put);
    }

    @Override
    public void clear() {
        if (size() > 0) {
            map.clear();
        }
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        return map.stream().map(Entry::getKey).collect(Collectors.toSet());
    }

    @NotNull
    @Override
    public Collection<String> values() {
        return map.stream().map(Entry::getValue).collect(Collectors.toList());
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        return new HashSet<>(map);
    }

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DiskMap diskMap = (DiskMap) o;
        return Objects.equals(map, diskMap.map);
    }

    @Override
    public int hashCode() {
        return Objects.hash(map);
    }
}
