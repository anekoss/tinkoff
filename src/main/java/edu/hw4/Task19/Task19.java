package edu.hw4.Task19;

import edu.hw4.Animal.Animal;
import edu.hw4.Animal.NullAnimalException;
import edu.hw4.Animal.NullAnimalListException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static edu.hw4.Animal.AnimalListValidator.validateAnimalList;

public class Task19 {
    private Set<ValidationError> validationErrors;

    public Map<String, Set<ValidationError>> getFieldErrorAnimals(List<Animal> animals)
        throws NullAnimalListException, NullAnimalException {
        validateAnimalList(animals);
        Map<String, Set<ValidationError>> fieldErrorMap = new HashMap<>();
        for (Animal animal : animals) {
            if (hasAnimalError(animal, animals)) {
                fieldErrorMap.put(animal.name(), validationErrors);
            }
        }
        return fieldErrorMap;
    }

    private boolean hasAnimalError(Animal animal, List<Animal> animals) {
        validationErrors = new HashSet<>();
        if (isNull(animal.name())) {
            validationErrors.add(new ValidationError(animal.getNameText(), TypeError.NULL));
        } else if (isNoUnique(animal.name(), animals)) {
            validationErrors.add(new ValidationError(animal.getNameText(), TypeError.NO_UNIQUE));
        }
        if (isNull(animal.type())) {
            validationErrors.add(new ValidationError(animal.getTypeText(), TypeError.NULL));
        }
        if (isNull(animal.sex())) {
            validationErrors.add(new ValidationError(animal.getSexText(), TypeError.NULL));
        }
        if (isNegativeOrZero(animal.age())) {
            validationErrors.add(new ValidationError(animal.getAgeText(), TypeError.NEGATIVE_OR_ZERO));
        }
        if (isNegativeOrZero(animal.weight())) {
            validationErrors.add(new ValidationError(animal.getWeightText(), TypeError.NEGATIVE_OR_ZERO));
        }
        if (isNegativeOrZero(animal.height())) {
            validationErrors.add(new ValidationError(animal.getHeightText(), TypeError.NEGATIVE_OR_ZERO));
        }
        return !validationErrors.isEmpty();
    }

    private boolean isNegativeOrZero(int value) {
        return value <= 0;
    }

    private boolean isNull(Object object) {
        return object == null;
    }

    private boolean isNoUnique(String value, List<Animal> animals) {
        return animals.stream().filter(animal -> value.equals(animal.name())).count() > 1;
    }
}



