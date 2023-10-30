package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import static edu.hw4.AnimalListValidator.validateAnimalList;

public class Task4 {

    public static Animal getMaxNameAnimal(List<Animal> animals)
        throws NullAnimalListException, NullAnimalException, NotFoundAnimalException {
        validateAnimalList(animals);
        Optional<Animal> maxNameAnimal =
            animals.stream().filter(animal -> animal.name() != null).
                max(Comparator.comparing(animal -> animal.name().length()));
        if (maxNameAnimal.isEmpty()) {
            throw new NotFoundAnimalException();
        }
        return maxNameAnimal.get();
    }

}
