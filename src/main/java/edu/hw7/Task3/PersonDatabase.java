package edu.hw7.Task3;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

interface PersonDatabase {
    void add(Person person);

    void delete(int id);

    List<Person> findByName(String name);

    List<Person> findByAddress(String address);

    List<Person> findByPhone(String phone);

    default void addToIndex(Map<String, Set<Integer>> indexMap, String personField, Integer id) {
        Set<Integer> ids = indexMap.getOrDefault(personField, new HashSet<>());
        ids.add(id);
        indexMap.put(personField, ids);
    }

    default void removeFromIndex(Map<String, Set<Integer>> indexMap, String personField, Integer id) {
        Set<Integer> ids = indexMap.getOrDefault(personField, Set.of());
        if (ids.contains(id)) {
            ids.remove(id);
            if (ids.isEmpty()) {
                indexMap.remove(personField);
            } else {
                indexMap.put(personField, ids);
            }
        }
    }

    default List<Person> getPersonFromDatabase(Set<Integer> ids, Map<Integer, Person> personDatabase) {
        return personDatabase.entrySet().stream()
            .filter(integerPersonEntry -> ids.contains(integerPersonEntry.getKey()))
            .map(Map.Entry::getValue).toList();
    }

}
