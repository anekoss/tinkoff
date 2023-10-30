package edu.hw4;

import java.util.Comparator;
import java.util.List;

import static edu.hw4.AnimalListValidator.validateAnimalList;

public class Task1 {
    private static final Comparator<Animal> compareByHeight = Comparator.comparing(Animal::height);
    public static final Comparator<Animal> compareByWeight = Comparator.comparing(Animal::weight);

    static List<Animal> sortAnimalsByHeight(List<Animal> animals) throws NullAnimalListException, NullAnimalException {
        validateAnimalList(animals);
        return animals.stream().sorted(compareByHeight).toList();
    }

}
