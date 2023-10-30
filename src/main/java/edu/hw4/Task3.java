package edu.hw4;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import static edu.hw4.AnimalListValidator.validateAnimalList;

public class Task3 {

    public static Map<Animal.Type, Integer> getCountAnimalsType(List<Animal> animals)
        throws NullAnimalListException, NullAnimalException {
        validateAnimalList(animals);
        Map<Animal.Type, Integer> typeMap =
            animals.stream().filter(animal -> animal.type() != null)
                .collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(animal -> 1)));
        for (Animal.Type type : Animal.Type.values()) {
            typeMap.putIfAbsent(type, 0);
        }
        return typeMap;
    }
}
