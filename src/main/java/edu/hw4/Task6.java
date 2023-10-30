package edu.hw4;

import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import static edu.hw4.AnimalListValidator.validateAnimalList;
import static edu.hw4.Task2.compareByWeight;

public class Task6 {
    private Task6() {
    }

    public static Map<Animal.Type, Animal> getTypeMaxWeightAnimal(List<Animal> animals)
        throws NullAnimalListException, NullAnimalException {
        validateAnimalList(animals);
        return animals.stream()
            .filter(animal -> animal.type() != null).collect(Collectors.toMap(
                Animal::type,
                Function.identity(),
                BinaryOperator.maxBy(compareByWeight)
            ));
    }

}
