package edu.hw4;

import java.util.Comparator;
import java.util.List;

public class Task1 {
    private Task1() {
    }

    private static final Comparator<Animal> compareByHeight = Comparator.comparing(Animal::height);

    static List<Animal> sortAnimalsByHeight(List<Animal> animals) {
        return animals.stream().sorted(Comparator.nullsFirst(compareByHeight)).toList();
    }
}
