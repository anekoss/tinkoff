package edu.hw4;

import java.util.List;
import static edu.hw4.AnimalListValidator.validateAnimalList;
import static edu.hw4.Task9.getSumPawsAnimals;

public class Task10 {

    public static List<Animal> getAgeNoEqualsCntPawsAnimals(List<Animal> animals)
        throws NullAnimalListException, NullAnimalException {
        validateAnimalList(animals);
        return animals.stream().filter(animal -> animal.type() != null && animal.age() != animal.paws()).toList();
    }
}
