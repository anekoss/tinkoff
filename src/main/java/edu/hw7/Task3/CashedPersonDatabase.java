package edu.hw7.Task3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jetbrains.annotations.NotNull;

public class CashedPersonDatabase implements PersonDatabase {
    private final Map<Integer, Person> personDatabase;
    private final Map<String, Set<Integer>> nameIndex;
    private final Map<String, Set<Integer>> addressIndex;
    private final Map<String, Set<Integer>> phoneIndex;
    private final Object nameLock = new Object();
    private final Object addressLock = new Object();
    private final Object phoneLock = new Object();

    public CashedPersonDatabase() {
        this.personDatabase = new HashMap<>();
        this.phoneIndex = new HashMap<>();
        this.addressIndex = new HashMap<>();
        this.nameIndex = new HashMap<>();
    }

    @Override
    public void add(@NotNull Person person) {
        synchronized (nameLock) {
            synchronized (addressLock) {
                synchronized (phoneLock) {
                    personDatabase.put(person.id(), person);
                    addToIndex(nameIndex, person.name(), person.id());
                    addToIndex(addressIndex, person.address(), person.id());
                    addToIndex(phoneIndex, person.phoneNumber(), person.id());
                }
            }
        }
    }

    @Override
    public void delete(int id) {
        Person person = personDatabase.getOrDefault(id, null);
        if (person != null) {
            synchronized (nameLock) {
                synchronized (addressLock) {
                    synchronized (phoneLock) {
                        removeFromIndex(nameIndex, person.name(), person.id());
                        removeFromIndex(addressIndex, person.address(), person.id());
                        removeFromIndex(phoneIndex, person.phoneNumber(), person.id());
                        personDatabase.remove(id);
                    }
                }
            }
        }
    }

    @Override
    public List<Person> findByName(String name) {
        synchronized (nameLock) {
            if (!nameIndex.containsKey(name)) {
                return List.of();
            }
        }
        synchronized (nameIndex.get(name)) {
            return getPersonFromDatabase(nameIndex.get(name));
        }
    }

    @Override
    public List<Person> findByAddress(String address) {
        synchronized (addressLock) {
            if (!addressIndex.containsKey(address)) {
                return List.of();
            }
        }
        synchronized (addressIndex.get(address)) {
            return getPersonFromDatabase(addressIndex.get(address));
        }
    }

    @Override
    public List<Person> findByPhone(String phone) {
        synchronized (phoneLock) {
            if (!phoneIndex.containsKey(phone)) {
                return List.of();
            }
        }
        synchronized (phoneIndex.get(phone)) {
            return getPersonFromDatabase(phoneIndex.get(phone));
        }
    }

    private void addToIndex(Map<String, Set<Integer>> indexMap, String personField, Integer id) {
        Set<Integer> ids = indexMap.getOrDefault(personField, Set.of());
        ids.add(id);
        indexMap.put(personField, ids);
    }

    private void removeFromIndex(Map<String, Set<Integer>> indexMap, String personField, Integer id) {
        Set<Integer> ids = indexMap.getOrDefault(personField, Set.of());
        if (ids.contains(id)) {
            ids.remove(id);
            indexMap.put(personField, ids);
        }
    }

    private List<Person> getPersonFromDatabase(Set<Integer> ids) {
        return personDatabase.entrySet().stream()
            .filter(integerPersonEntry -> ids.contains(integerPersonEntry.getKey()))
            .map(Map.Entry::getValue).toList();
    }
}
