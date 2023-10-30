package edu.hw4;

import java.util.List;
import static edu.hw4.AnimalListValidator.validateAnimalList;

public class Task14 {

    public static boolean hasDogWithHeightMoreK(List<Animal> animals, int k)
        throws NullAnimalListException, NullAnimalException {
        validateAnimalList(animals);
        return animals.stream().filter(animal -> animal.type() == Animal.Type.DOG)
            .anyMatch(animal -> animal.height() > k);
    }
}
