package edu.hw4;

import java.util.List;
import static edu.hw4.AnimalListValidator.validateAnimalList;

public class Task11 {

    public static List<Animal> getBiteAnimals(List<Animal> animals) throws NullAnimalListException, NullAnimalException {
        validateAnimalList(animals);
        return animals.stream().filter(Animal::bites).filter(animal -> animal.height() > 100)
            .toList();
    }
}
