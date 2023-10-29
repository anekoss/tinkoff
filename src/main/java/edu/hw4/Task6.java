package edu.hw4;

import java.util.List;
import static edu.hw4.Task2.sortAnimalsByWeight;

public class Task6 {

    public static Animal.Type getTypeMaxWeightAnimal(List<Animal> animals) {
        List<Animal> sortedAnimal = sortAnimalsByWeight(animals, 1);
        if (!sortedAnimal.isEmpty()) {
            if (sortedAnimal.get(0).type() != null) {
                return sortedAnimal.get(0).type();
            }
        }
    }
}
