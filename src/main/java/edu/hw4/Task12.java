package edu.hw4;

import java.util.List;
import static edu.hw4.AnimalListValidator.validateAnimalList;

public class Task12 {

    public static Integer getCntWeightMoreHeightAnimal(List<Animal> animals)
        throws NullAnimalListException, NullAnimalException {
        validateAnimalList(animals);
        return animals.stream().filter(animal -> animal.weight() > animal.height()).toList().size();
    }
}
