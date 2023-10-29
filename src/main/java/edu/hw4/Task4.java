package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Task4 {

    public static Animal getMaxNameAnimal(List<Animal> animals) {
        Optional<Animal> maxNameAnimal =
            animals.stream().filter(Objects::nonNull).filter(animal -> animal.name() != null).
        max(Comparator.comparing(animal -> animal.name().length()));
        if (maxNameAnimal.isEmpty()) {
            return null;
        }
        return maxNameAnimal.get();
    }
}
