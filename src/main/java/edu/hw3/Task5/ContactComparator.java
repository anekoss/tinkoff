package edu.hw3.Task5;

import java.util.Comparator;

public class ContactComparator implements Comparator<Contact> {
    @Override
    public int compare(Contact o1, Contact o2) {
        if (o1.getLastName() == null && o2.getLastName() == null) {
            return o1.getFirstName().compareTo(o2.getFirstName());
        }
        if (o1.getLastName() == null) {
            return -1;
        }
        if (o2.getLastName() == null) {
            return 1;
        }
        return o1.getLastName().compareTo(o2.getLastName());
    }

    @Override
    public Comparator<Contact> reversed() {
        return Comparator.super.reversed();
    }
}
