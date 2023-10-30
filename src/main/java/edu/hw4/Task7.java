package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import static edu.hw4.AnimalListValidator.validateAnimalList;

public class Task7 {
    public static final Comparator<Animal> compareByAge = Comparator.comparing(Animal::age);

    public static Animal getKOldestAnimal(List<Animal> animals, int k)
        throws NullAnimalListException, NullAnimalException, NotFoundAnimalException {
        validateAnimalList(animals);
        if (k <= 0) {
            throw new NotFoundAnimalException();
        }
        if (k > animals.size()) {
            throw new NotFoundAnimalException();
        }
        return animals.stream().sorted(compareByAge.reversed()).toList().get(k - 1);
    }
}
