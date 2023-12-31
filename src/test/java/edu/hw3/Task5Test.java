package edu.hw3;

import edu.hw3.Task5.Contact;
import edu.hw3.Task5.EmptyContactArrayException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.Arrays;
import java.util.stream.Stream;
import static edu.hw3.Task5.Task5.parseContacts;
import static edu.hw3.Task5.Task5.sortContact;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Task5Test {

    static Stream<Arguments> provideDataForTest() {
        return Stream.of(
            Arguments.of(
                new String[] {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"},
                new Contact[] {new Contact("Thomas", "Aquinas"), new Contact("Rene", "Descartes"),
                    new Contact("David", "Hume"), new Contact("John", "Locke")},
                new Contact[] {new Contact("John", "Locke"), new Contact("David", "Hume"),
                    new Contact("Rene", "Descartes"), new Contact("Thomas", "Aquinas")}
            ),
            Arguments.of(
                new String[] {"John Locke", "Thomas", "David", "Rene Descartes"},
                new Contact[] {new Contact("David"), new Contact("Thomas"),
                    new Contact("Rene", "Descartes"), new Contact("John", "Locke")},
                new Contact[] {new Contact("John", "Locke"), new Contact("Rene", "Descartes"),
                    new Contact("Thomas"),
                    new Contact("David")
                }
            )
        );
    }

    @ParameterizedTest
    @MethodSource("provideDataForTest")
    void parseContactsTest(String[] contacts, Contact[] ascSortedContacts, Contact[] descSortedContacts)
        throws EmptyContactArrayException {
        assertThat(parseContacts(contacts, "ASC")).isEqualTo(ascSortedContacts);
        assertThat(parseContacts(contacts, "DESC")).isEqualTo(descSortedContacts);
        assertThat(parseContacts(contacts, null)).isEqualTo(sortContact(contacts));
        assertThat(parseContacts(contacts, "hi")).isEqualTo(sortContact(contacts));
    }

    @Test
    void parseNullContactsTest() {
        String[] contacts = new String[] {};
        assertThrows(EmptyContactArrayException.class, () -> parseContacts(contacts, "ASC"));
        assertThrows(EmptyContactArrayException.class, () -> parseContacts(contacts, "DESC"));
        assertThrows(EmptyContactArrayException.class, () -> parseContacts(contacts, null));
        assertThrows(EmptyContactArrayException.class, () -> parseContacts(contacts, "hi"));
    }
}
