package edu.hw7.Task3;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class CashedPersonDatabaseTest {

    static Stream<Arguments> provideDataForTest() {
        return Stream.of(
            Arguments.of(
                List.of(
                    new Person(1, "n1", "ff1", "111"),
                    new Person(2, "n2", "ff2", "222"),
                    new Person(3, "n2", "ff2", "222"),
                    new Person(4, "n3", "ff3", "333"),
                    new Person(5, "n4", "ff3", "444"),
                    new Person(6, "n4", "ff5", "111")
                ),
                Map.of(1, new Person(1, "n1", "ff1", "111"),
                    2, new Person(2, "n2", "ff2", "222"),
                    3, new Person(3, "n2", "ff2", "222"),
                    4, new Person(4, "n3", "ff3", "333"),
                    5, new Person(5, "n4", "ff3", "444"),
                    6, new Person(6, "n4", "ff5", "111")
                ),
                Map.of("n1", Set.of(1), "n2", Set.of(2, 3), "n3", Set.of(4), "n4", Set.of(5, 6)),
                Map.of("ff1", Set.of(1), "ff2", Set.of(2, 3), "ff3", Set.of(4, 5), "ff5", Set.of(6)),
                Map.of("111", Set.of(1, 6), "222", Set.of(2, 3), "333", Set.of(4), "444", Set.of(5))
            ));
    }

    @ParameterizedTest
    @MethodSource("provideDataForTest")
    void addPersonToDatabaseTest(
        List<Person> personList,
        Map<Integer, Person> personDatabase,
        Map<String, Set<Integer>> nameIndex,
        Map<String, Set<Integer>> addressIndex,
        Map<String, Set<Integer>> phoneIndex
    ) {
        CashedPersonDatabase cashedPersonDatabase = new CashedPersonDatabase();
        for (Person person : personList) {
            cashedPersonDatabase.add(person);
        }
        assertThat(cashedPersonDatabase.getPersonDatabase()).isEqualTo(personDatabase);
        assertThat(cashedPersonDatabase.getAddressIndex()).isEqualTo(addressIndex);
        assertThat(cashedPersonDatabase.getNameIndex()).isEqualTo(nameIndex);
        assertThat(cashedPersonDatabase.getPhoneIndex()).isEqualTo(phoneIndex);
    }

    @ParameterizedTest
    @MethodSource("provideDataForTest")
    void deletePersonToDatabaseTest(
        List<Person> personList
    ) {
        CashedPersonDatabase cashedPersonDatabase = new CashedPersonDatabase();
        for (Person person : personList) {
            cashedPersonDatabase.add(person);
        }
        for (Person person : personList) {
            cashedPersonDatabase.delete(person.id());
        }
        assertThat(cashedPersonDatabase.getPersonDatabase()).isEmpty();
        assertThat(cashedPersonDatabase.getAddressIndex()).isEmpty();
        assertThat(cashedPersonDatabase.getNameIndex()).isEmpty();
        assertThat(cashedPersonDatabase.getPhoneIndex()).isEmpty();
    }

    static Stream<Arguments> provideDataForFindTest() {
        return Stream.of(
            Arguments.of(
                List.of(
                    new Person(1, "n1", "ff1", "111"),
                    new Person(2, "n2", "ff2", "222"),
                    new Person(3, "n2", "ff2", "222"),
                    new Person(4, "n3", "ff3", "333"),
                    new Person(5, "n4", "ff3", "444"),
                    new Person(6, "n4", "ff5", "111")
                ),
                Map.of(
                    "n1",
                    List.of(new Person(1, "n1", "ff1", "111")),
                    "n2",
                    List.of(new Person(2, "n2", "ff2", "222"), new Person(3, "n2", "ff2", "222")),
                    "n3",
                    List.of(new Person(4, "n3", "ff3", "333")),
                    "n4",
                    List.of(new Person(5, "n4", "ff3", "444"), new Person(6, "n4", "ff5", "111"))
                ),
                Map.of(
                    "ff1",
                    List.of(new Person(1, "n1", "ff1", "111")),
                    "ff2",
                    List.of(new Person(2, "n2", "ff2", "222"), new Person(3, "n2", "ff2", "222")),
                    "ff3",
                    List.of(new Person(4, "n3", "ff3", "333"), new Person(5, "n4", "ff3", "444")),
                    "ff5",
                    List.of(new Person(6, "n4", "ff5", "111"))
                ),
                Map.of(
                    "111",
                    List.of(new Person(1, "n1", "ff1", "111"), new Person(6, "n4", "ff5", "111")),
                    "222",
                    List.of(new Person(2, "n2", "ff2", "222"), new Person(3, "n2", "ff2", "222")),
                    "333",
                    List.of(new Person(4, "n3", "ff3", "333")),
                    "444",
                    List.of(new Person(5, "n4", "ff3", "444"))
                )
            ));
    }

    @ParameterizedTest
    @MethodSource("provideDataForFindTest")
    void findPersonTest(
        List<Person> personList,
        Map<String, List<Person>> nameIndex,
        Map<String, List<Person>> addressIndex,
        Map<String, List<Person>> phoneIndex
    ) {
        CashedPersonDatabase cashedPersonDatabase = new CashedPersonDatabase();
        for (Person person : personList) {
            cashedPersonDatabase.add(person);
        }
        for (Person person : personList) {
            assertThat(cashedPersonDatabase.findByName(person.name())).isEqualTo(nameIndex.get(person.name()));
            assertThat(cashedPersonDatabase.findByAddress(person.address())).isEqualTo(addressIndex.get(person.address()));
            assertThat(cashedPersonDatabase.findByPhone(person.phoneNumber())).isEqualTo(phoneIndex.get(person.phoneNumber()));
        }
    }

}
