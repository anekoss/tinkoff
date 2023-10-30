package edu.hw4;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import static edu.hw4.AnimalListValidator.validateAnimalList;

public class Task17 {

    public static boolean hasSpidersBitesMoreDogs(List<Animal> animals)
        throws NullAnimalListException, NullAnimalException {
        validateAnimalList(animals);
        Map<Animal.Type, Integer> bitesMap =
            animals.stream().filter(animal -> (animal.type() == Animal.Type.SPIDER && animal.bites()) ||
                (animal.type() == Animal.Type.DOG && animal.bites())).collect(Collectors.groupingBy(
                Animal::type,
                Collectors.summingInt(value -> 1)
            ));
        if (bitesMap.containsKey(Animal.Type.DOG) && bitesMap.containsKey(Animal.Type.SPIDER) &&
            bitesMap.get(Animal.Type.DOG) <
                bitesMap.get(Animal.Type.SPIDER)) {
            return true;
        }
        return false;
    }
}
