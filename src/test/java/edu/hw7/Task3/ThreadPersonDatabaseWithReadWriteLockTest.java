package edu.hw7.Task3;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class ThreadPersonDatabaseWithReadWriteLockTest {

    private final CashedPersonDatabase database = new CashedPersonDatabase();
    private final AtomicReference<List<Person>> phoneIndex = new AtomicReference<>();
    private final AtomicReference<List<Person>> addressIndex = new AtomicReference<>();
    private final AtomicReference<List<Person>> nameIndex = new AtomicReference<>();

    static Stream<Arguments> provideDataForTest() {
        return Stream.of(
            Arguments.of(
                new Person(1, "n1", "ff1", "111"),
                new Person(2, "n2", "ff2", "222"),
                new Person(3, "n3", "ff3", "333"),
                new Person(4, "n4", "ff4", "444"),
                new Person(5, "n5", "ff5", "555"),
                new Person(6, "n6", "ff6", "666")
            ));
    }

    @ParameterizedTest
    @MethodSource("provideDataForTest")
    void addPersonToDatabaseTest(Person person) throws InterruptedException {
        Thread threadAddPerson = new Thread(() -> database.add(person));
        Thread threadFindAddress = new Thread(() -> phoneIndex.set(database.findByAddress(person.address())));
        Thread threadFindName = new Thread(() -> addressIndex.set(database.findByName(person.name())));
        Thread threadFindPhone = new Thread(() -> nameIndex.set(database.findByPhone(person.phoneNumber())));
        threadAddPerson.start();
        threadFindName.start();
        threadFindPhone.start();
        threadFindAddress.start();
        threadAddPerson.join();
        threadFindName.join();
        threadFindPhone.join();
        threadFindAddress.join();
        assertThat(phoneIndex.get()).isEqualTo(addressIndex.get()).isEqualTo(nameIndex.get());
    }

    @ParameterizedTest
    @MethodSource("provideDataForTest")
    void deletePersonFromDatabaseTest(Person person) throws InterruptedException {
        Thread threadAddPerson = new Thread(() -> database.delete(person.id()));
        Thread threadFindAddress = new Thread(() -> phoneIndex.set(database.findByAddress(person.address())));
        Thread threadFindName = new Thread(() -> addressIndex.set(database.findByName(person.name())));
        Thread threadFindPhone = new Thread(() -> nameIndex.set(database.findByPhone(person.phoneNumber())));
        threadAddPerson.start();
        threadFindName.start();
        threadFindPhone.start();
        threadFindAddress.start();
        threadAddPerson.join();
        threadFindName.join();
        threadFindPhone.join();
        threadFindAddress.join();
        assertThat(phoneIndex.get()).isEqualTo(addressIndex.get()).isEqualTo(nameIndex.get());
    }

}
