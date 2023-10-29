package edu.hw4;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Task3 {

    public static Map<Animal.Type, Integer> getCountAnimalsType(List<Animal> animals) {
        Map<Animal.Type, Integer> typeMap =
            animals.stream().filter(Objects::nonNull).filter(animal -> animal.type() != null)
                .collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(animal -> 1)));
        for (Animal.Type type : Animal.Type.values()) {
            typeMap.putIfAbsent(type, 0);
        }
        return typeMap;
    }
}
