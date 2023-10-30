package edu.hw4;

import java.util.List;
import java.util.Optional;
import static edu.hw4.AnimalListValidator.validateAnimalList;
import static edu.hw4.AnimalListValidator.validateMoreOneAnimalLists;
import static edu.hw4.Task2.compareByWeight;

public class Task18 {

    public static Animal getMaxWeightFish(List<List<Animal>> animalLists)
        throws NotFoundAnimalException, NullAnimalListException, NullAnimalException {
        validateMoreOneAnimalLists(animalLists);
        Optional<Animal> maxWeightFish =
            animalLists.stream().flatMap(List::stream).filter(animal -> animal.type() == Animal.Type.FISH)
                .max(compareByWeight);
        if (maxWeightFish.isPresent()) {
            return maxWeightFish.get();
        }
        throw new NotFoundAnimalException();
    }
}
