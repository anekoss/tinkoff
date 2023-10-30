package edu.hw4;

import java.util.List;

import static edu.hw4.AnimalListValidator.validateAnimalList;

public class Task5 {
    public static String getMaxSexAnimal(List<Animal> animals)
        throws NullAnimalListException, NullAnimalException {
        validateAnimalList(animals);
        long countM = animals.stream()
            .filter(animal -> animal.sex() == Animal.Sex.M).count();
        long countF = animals.stream()
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
