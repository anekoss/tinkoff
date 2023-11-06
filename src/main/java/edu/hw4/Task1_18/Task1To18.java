package edu.hw4.Task1_18;

import edu.hw4.Animal.Animal;
import edu.hw4.Animal.NotFoundAnimalException;
import edu.hw4.Animal.NullAnimalException;
import edu.hw4.Animal.NullAnimalListException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import static edu.hw4.Animal.AnimalListValidator.validateAnimalList;
import static edu.hw4.Animal.AnimalListValidator.validateMoreOneAnimalLists;

public class Task1To18 {
    private static final int MAX_HEIGHT_ANIMAL = 100;
    private static final Comparator<Animal> COMPARATOR_BY_HEIGHT = Comparator.comparing(Animal::height);
    public static final Comparator<Animal> COMPARATOR_BY_WEIGHT = Comparator.comparing(Animal::weight);
    public static final Comparator<Animal> COMPARATOR_BY_TYPE = Comparator.comparing(Animal::type);
    public static final Comparator<Animal> COMPARATOR_BY_NAME = Comparator.comparing(Animal::name);
    public static final Comparator<Animal> COMPARATOR_BY_SEX = Comparator.comparing(Animal::sex);
    public static final Comparator<Animal> COMPARATOR_BY_AGE = Comparator.comparing(Animal::age);

    private Task1To18() {
    }

    public static List<Animal> sortAnimalsByHeight(List<Animal> animals)
        throws NullAnimalListException, NullAnimalException {
        validateAnimalList(animals);
        return animals.stream().sorted(COMPARATOR_BY_HEIGHT).toList();
    }

    public static List<Animal> sortAnimalsByWeight(List<Animal> animals, int k)
        throws NullAnimalListException, NullAnimalException {
        validateAnimalList(animals);
        int countAnimal = k;
        if (countAnimal < 0) {
            return new ArrayList<>();
        }
        if (countAnimal > animals.size()) {
            countAnimal = animals.size();
        }
        return animals.stream().sorted(COMPARATOR_BY_WEIGHT.reversed()).toList().subList(0, countAnimal);
    }

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

    public static Animal getMaxNameAnimal(List<Animal> animals)
        throws NullAnimalListException, NullAnimalException, NotFoundAnimalException {
        validateAnimalList(animals);
        Optional<Animal> maxNameAnimal =
            animals.stream().filter(animal -> animal.name() != null)
                .max(Comparator.comparing(animal -> animal.name().length()));
        if (maxNameAnimal.isEmpty()) {
            throw new NotFoundAnimalException();
        }
        return maxNameAnimal.get();
    }

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

    public static Map<Animal.Type, Animal> getTypeMaxWeightAnimal(List<Animal> animals)
        throws NullAnimalListException, NullAnimalException {
        validateAnimalList(animals);
        return animals.stream()
            .filter(animal -> animal.type() != null).collect(Collectors.toMap(
                Animal::type,
                Function.identity(),
                BinaryOperator.maxBy(COMPARATOR_BY_WEIGHT)
            ));
    }

    public static Animal getKOldestAnimal(List<Animal> animals, int k)
        throws NullAnimalListException, NullAnimalException, NotFoundAnimalException {
        validateAnimalList(animals);
        if (k <= 0) {
            throw new NotFoundAnimalException();
        }
        if (k > animals.size()) {
            throw new NotFoundAnimalException();
        }
        return animals.stream().sorted(COMPARATOR_BY_AGE.reversed()).toList().get(k - 1);
    }

    public static Optional<Animal> getMaxWeightHeightKAnimal(List<Animal> animals, int k)
        throws NullAnimalListException, NullAnimalException {
        validateAnimalList(animals);
        return animals.stream().filter(animal -> animal.height() < k).max(COMPARATOR_BY_WEIGHT);
    }

    public static Integer getSumPawsAnimals(List<Animal> animals)
        throws NullAnimalListException, NullAnimalException {
        validateAnimalList(animals);
        return animals.stream().filter(animal -> animal.type() != null).mapToInt(animal -> animal.type().getPaws())
            .sum();
    }

    public static List<Animal> getAgeNoEqualsCntPawsAnimals(List<Animal> animals)
        throws NullAnimalListException, NullAnimalException {
        validateAnimalList(animals);
        return animals.stream().filter(animal -> animal.type() != null && animal.age() != animal.type().getPaws())
            .toList();
    }

    public static List<Animal> getBiteAnimals(List<Animal> animals)
        throws NullAnimalListException, NullAnimalException {
        validateAnimalList(animals);
        return animals.stream().filter(Animal::bites).filter(animal -> animal.height() > MAX_HEIGHT_ANIMAL)
            .toList();
    }

    public static Integer getCntWeightMoreHeightAnimal(List<Animal> animals)
        throws NullAnimalListException, NullAnimalException {
        validateAnimalList(animals);
        return animals.stream().filter(animal -> animal.weight() > animal.height()).toList().size();
    }

    public static List<Animal> getNameMoreTwoWordsAnimals(List<Animal> animals)
        throws NullAnimalListException, NullAnimalException {
        validateAnimalList(animals);
        return animals.stream().filter(animal -> animal.name() != null && animal.name().split(" ").length > 2).toList();
    }

    public static boolean hasDogWithHeightMoreK(List<Animal> animals, int k)
        throws NullAnimalListException, NullAnimalException {
        validateAnimalList(animals);
        return animals.stream().filter(animal -> animal.type() == Animal.Type.DOG)
            .anyMatch(animal -> animal.height() > k);
    }

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

    public static List<Animal> sortByTypeSexNameAnimals(List<Animal> animals)
        throws NullAnimalListException, NullAnimalException {
        validateAnimalList(animals);
        return animals.stream().filter(animal -> animal.name() != null && animal.sex() != null && animal.type() != null)
            .sorted(COMPARATOR_BY_TYPE.thenComparing(COMPARATOR_BY_SEX).thenComparing(COMPARATOR_BY_NAME)
                .thenComparing(Animal::sex)
                .thenComparing(Animal::name))
            .toList();
    }

    public static boolean hasSpidersBitesMoreDogs(List<Animal> animals)
        throws NullAnimalListException, NullAnimalException {
        validateAnimalList(animals);
        Map<Animal.Type, Integer> bitesMap =
            animals.stream().filter(animal -> (animal.type() == Animal.Type.SPIDER && animal.bites())
                || (animal.type() == Animal.Type.DOG && animal.bites())).collect(Collectors.groupingBy(
                Animal::type,
                Collectors.summingInt(value -> 1)
            ));
        return bitesMap.containsKey(Animal.Type.DOG) && bitesMap.containsKey(Animal.Type.SPIDER)
            && bitesMap.get(Animal.Type.DOG)
            < bitesMap.get(Animal.Type.SPIDER);
    }

    public static Animal getMaxWeightFish(List<List<Animal>> animalLists)
        throws NotFoundAnimalException, NullAnimalListException, NullAnimalException {
        validateMoreOneAnimalLists(animalLists);
        Optional<Animal> maxWeightFish =
            animalLists.stream().flatMap(List::stream).filter(animal -> animal.type() == Animal.Type.FISH)
                .max(COMPARATOR_BY_WEIGHT);
        if (maxWeightFish.isPresent()) {
            return maxWeightFish.get();
        }
        throw new NotFoundAnimalException();
    }
}
