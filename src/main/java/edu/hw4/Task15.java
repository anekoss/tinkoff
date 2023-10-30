package edu.hw4;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import static edu.hw4.AnimalListValidator.validateAnimalList;

public class Task15 {

    public static Map<Animal.Type, Integer> getSumWeightTypeAnimal(List<Animal> animals, int k, int l)
        throws NullAnimalListException, NullAnimalException {
        validateAnimalList(animals);
        Map<Animal.Type, Integer> sumTypeMap =
            animals.stream().filter(animal -> animal.age() >= k && animal.age() <= l && animal.type() != null).collect(
                Collectors.groupingBy(Animal::type, Collectors.summingInt(Animal::weight)));
        for (Animal.Type type : Animal.Type.values()) {
            sumTypeMap.putIfAbsent(type, 0);
        }
        return sumTypeMap;
    }
}
