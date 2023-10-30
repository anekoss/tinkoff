package edu.hw4;

import java.util.Comparator;
import java.util.List;
import static edu.hw4.AnimalListValidator.validateAnimalList;

public class Task16 {

    public static final Comparator<Animal> compareByType = Comparator.comparing(Animal::type);
    public static final Comparator<Animal> compareByName = Comparator.comparing(Animal::name);
    public static final Comparator<Animal> compareBySex = Comparator.comparing(Animal::sex);

    public static List<Animal> sortByTypeSexNameAnimals(List<Animal> animals)
        throws NullAnimalListException, NullAnimalException {
        validateAnimalList(animals);
        return animals.stream().filter(animal -> animal.name() != null && animal.sex() != null && animal.type() != null)
            .sorted(compareByType.thenComparing(compareBySex).thenComparing(compareByName)
                .thenComparing(Animal::sex)
                .thenComparing(Animal::name))
            .toList();
    }
}
