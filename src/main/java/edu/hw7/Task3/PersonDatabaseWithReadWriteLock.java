package edu.hw7.Task3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.jetbrains.annotations.NotNull;

public class PersonDatabaseWithReadWriteLock implements PersonDatabase {
    private final Map<Integer, Person> personDatabase;
    private final Map<String, Set<Integer>> nameIndex;
    private final Map<String, Set<Integer>> addressIndex;
    private final Map<String, Set<Integer>> phoneIndex;
    private final Lock writeLock;
    private final Lock readLock;

    public PersonDatabaseWithReadWriteLock() {
        this.personDatabase = new HashMap<>();
        this.phoneIndex = new HashMap<>();
        this.addressIndex = new HashMap<>();
        this.nameIndex = new HashMap<>();
        ReadWriteLock lock = new ReentrantReadWriteLock();
        this.writeLock = lock.writeLock();
        this.readLock = lock.readLock();
    }

    @Override
    public void add(@NotNull Person person) {
        try {
            writeLock.lock();
            personDatabase.put(person.id(), person);
            addToIndex(nameIndex, person.name(), person.id());
            addToIndex(addressIndex, person.address(), person.id());
            addToIndex(phoneIndex, person.phoneNumber(), person.id());
        } finally {
            writeLock.unlock();
        }

    }

    @Override
    public void delete(int id) {
        try {
            writeLock.lock();
            Person person = personDatabase.getOrDefault(id, null);
            if (person != null) {
                removeFromIndex(nameIndex, person.name(), person.id());
                removeFromIndex(addressIndex, person.address(), person.id());
                removeFromIndex(phoneIndex, person.phoneNumber(), person.id());
                personDatabase.remove(id);
            }
        } finally {
            writeLock.unlock();
        }

    }

    @Override
    public List<Person> findByName(String name) {
        try {
            readLock.lock();
            if (!nameIndex.containsKey(name)) {
                return List.of();
            }
            return getPersonFromDatabase(nameIndex.get(name));
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public List<Person> findByAddress(String address) {
        try {
            readLock.lock();
            if (!addressIndex.containsKey(address)) {
                return List.of();
            }
            return getPersonFromDatabase(addressIndex.get(address));
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public List<Person> findByPhone(String phone) {
        try {
            readLock.lock();
            if (!phoneIndex.containsKey(phone)) {
                return List.of();
            }
            return getPersonFromDatabase(phoneIndex.get(phone));
        } finally {
            readLock.unlock();
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
