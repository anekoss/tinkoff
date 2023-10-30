package edu.hw4;

import java.util.List;
import static edu.hw4.AnimalListValidator.validateAnimalList;

public class Task13 {
    public static List<Animal> getNameMoreTwoWordsAnimals(List<Animal> animals)
        throws NullAnimalListException, NullAnimalException {
        validateAnimalList(animals);
        return animals.stream().filter(animal -> animal.name() != null && animal.name().split(" ").length > 2).toList();
    }
}
