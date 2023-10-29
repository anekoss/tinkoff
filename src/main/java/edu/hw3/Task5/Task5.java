package edu.hw3.Task5;

import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Task5 {
    private Task5() {
    }

    public static Contact[] parseContacts(String[] contactNames, String order) throws EmptyContactArrayException {
        Contact[] contacts = sortContact(contactNames);
        if (contacts.length == 0) {
            throw new EmptyContactArrayException();
        }
        if (order.equalsIgnoreCase("ASC")) {
            log.info("ASC sorting execution");
            Arrays.sort(contacts, new ContactComparator());
            log.info("ASC sorting completed");
        }
        if (order.equalsIgnoreCase("DESC")) {
            log.info("DESC sorting execution");
            Arrays.sort(contacts, new ContactComparator().reversed());
            log.info("DESC sorting completed");
        }
        return contacts;
    }

    static Contact[] sortContact(String[] contactNames) {
        if (contactNames.length == 0) {
            return new Contact[0];
        }
        Contact[] contacts = new Contact[contactNames.length];
        for (int i = 0; i < contactNames.length; i++) {
            if (contactNames[i] != null) {
                String[] names = contactNames[i].split(" ");
                if (names.length == 2) {
                    contacts[i] = new Contact(names[0], names[1]);
                }
                if (names.length == 1) {
                    contacts[i] = new Contact(names[0]);
                }
            }
        }
        return contacts;
    }

}
