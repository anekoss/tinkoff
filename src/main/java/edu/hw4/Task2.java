package edu.hw4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import static edu.hw4.AnimalListValidator.validateAnimalList;

public class Task2 {
    private Task2() {
    }

    public static final Comparator<Animal> compareByWeight = Comparator.comparing(Animal::weight);

    static List<Animal> sortAnimalsByWeight(List<Animal> animals, int k)
        throws NullAnimalListException, NullAnimalException {
        validateAnimalList(animals);
        if (k < 0) {
            return new ArrayList<>();
        }
        if (k > animals.size()) {
            k = animals.size();
        }
        return animals.stream().sorted(compareByWeight.reversed()).toList().subList(0, k);
    }

}
