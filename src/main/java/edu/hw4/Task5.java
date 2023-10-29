package edu.hw4;

import java.util.List;
import java.util.Objects;

public class Task5 {
    public static String getMaxSexAnimal(List<Animal> animals) {
        long countM = animals.stream().filter(Objects::nonNull).filter(animal -> animal.sex() != null)
            .filter(animal -> animal.sex() == Animal.Sex.M).count();
        long countF = animals.stream().filter(Objects::nonNull).filter(animal -> animal.sex() != null)
            .filter(animal -> animal.sex() == Animal.Sex.F).count();
        if (countM > countF) {
            return Animal.Sex.M.name();
        }
        if (countF > countM) {
            return Animal.Sex.F.name();
        }
        return "equals";
    }
}
