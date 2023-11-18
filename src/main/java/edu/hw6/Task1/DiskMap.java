package edu.hw6.Task1;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DiskMap implements Map<String, String>, Serializable {
    private final Map<String, String> map;

    public DiskMap() {
        map = new HashMap<>();
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
            return map.keySet().stream().anyMatch(key::equals);
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        if (value instanceof String) {
            return map.values().stream()
                .anyMatch(value::equals);
        }
        return false;
    }

    @Override
    public String get(Object key) {
        if (key instanceof String) {
            if (containsKey(key)) {
                return map.get(key);
            }
        }
        return null;
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        if (key != null && value != null) {
            return map.put(key, value);
        }
        return null;
    }

    @Override
    public String remove(Object key) {
        if (key instanceof String) {
            if (containsKey(key)) {
                map.remove(key);
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
        return map.keySet();
    }

    @NotNull
    @Override
    public Collection<String> values() {
        return map.values();
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        return map.entrySet();
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
