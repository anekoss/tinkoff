package edu.hw4;

import java.util.List;
import java.util.Optional;
import static edu.hw4.AnimalListValidator.validateAnimalList;

import static edu.hw4.Task2.compareByWeight;

public class Task8 {

    public static Optional<Animal> getMaxWeightHeightKAnimal(List<Animal> animals, int k)
        throws NullAnimalListException, NullAnimalException {
        validateAnimalList(animals);
        return animals.stream().filter(animal -> animal.height() < k).max(compareByWeight);
    }
}
