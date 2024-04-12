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

    public CashedPersonDatabase() {
        this.personDatabase = new HashMap<>();
        this.phoneIndex = new HashMap<>();
        this.addressIndex = new HashMap<>();
        this.nameIndex = new HashMap<>();
    }

    @Override
    public synchronized void add(@NotNull Person person) {
        personDatabase.put(person.id(), person);
        addToIndex(nameIndex, person.name(), person.id());
        addToIndex(addressIndex, person.address(), person.id());
        addToIndex(phoneIndex, person.phoneNumber(), person.id());
    }

    @Override
    public synchronized void delete(int id) {
        Person person = personDatabase.getOrDefault(id, null);
        if (person != null) {
            removeFromIndex(nameIndex, person.name(), person.id());
            removeFromIndex(addressIndex, person.address(), person.id());
            removeFromIndex(phoneIndex, person.phoneNumber(), person.id());
            personDatabase.remove(id);
        }
    }

    @Override
    public synchronized List<Person> findByName(String name) {
        if (!nameIndex.containsKey(name)) {
            return List.of();
        }
        return getPersonFromDatabase(nameIndex.get(name), personDatabase);

    }

    @Override
    public synchronized List<Person> findByAddress(String address) {
        if (!addressIndex.containsKey(address)) {
            return List.of();
        }
        return getPersonFromDatabase(addressIndex.get(address), personDatabase);
    }

    @Override
    public synchronized List<Person> findByPhone(String phone) {
        if (!phoneIndex.containsKey(phone)) {
            return List.of();
        }
        return getPersonFromDatabase(phoneIndex.get(phone), personDatabase);

    }

    public Map<Integer, Person> getPersonDatabase() {
        return personDatabase;
    }

    public Map<String, Set<Integer>> getNameIndex() {
        return nameIndex;
    }

    public Map<String, Set<Integer>> getPhoneIndex() {
        return phoneIndex;
    }

    public Map<String, Set<Integer>> getAddressIndex() {
        return addressIndex;
    }
}
