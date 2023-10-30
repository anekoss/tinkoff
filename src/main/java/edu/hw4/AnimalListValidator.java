package edu.hw4;

import java.util.List;
import java.util.Objects;

public class AnimalListValidator {
    public static void validateAnimalList(List<Animal> animals)
        throws NullAnimalListException, NullAnimalException {
        if (animals == null) {
            throw new NullAnimalListException();
        }
        if (animals.stream().filter(Objects::nonNull).count() != animals.size()) {
            throw new NullAnimalException();
        }
    }

    public static void validateMoreOneAnimalLists(List<List<Animal>> animalLists)
        throws NullAnimalListException, NullAnimalException {
        if (animalLists == null) {
            throw new NullAnimalListException();
        }
        if (animalLists.stream().anyMatch(Objects::isNull)) {
            throw new NullAnimalListException();
        }
        for (List<Animal> animals : animalLists) {
            if (animals.stream().anyMatch(Objects::isNull)) {
                throw new NullAnimalException();
            }
        }

    }
}
