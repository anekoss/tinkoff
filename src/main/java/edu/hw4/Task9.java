package edu.hw4;

import java.util.List;
import static edu.hw4.AnimalListValidator.validateAnimalList;

public class Task9 {

    public static Integer getSumPawsAnimals(List<Animal> animals)
        throws NullAnimalListException, NullAnimalException {
        validateAnimalList(animals);
        return animals.stream().filter(animal -> animal.type() != null).mapToInt(Animal::paws).sum();
    }
}
