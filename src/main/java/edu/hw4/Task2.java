package edu.hw4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Task2 {
    private Task2() {
    }

    private static final Comparator<Animal> compareByWeight = Comparator.comparing(Animal::weight).reversed();

    static List<Animal> sortAnimalsByWeight(List<Animal> animals, int k) {
        if (k < 0) {
            return new ArrayList<>();
        }
        if (k > animals.size()) {
            k = animals.size();
        }
        return animals.stream().sorted(Comparator.nullsFirst(compareByWeight)).toList().subList(0, k);
    }
}
