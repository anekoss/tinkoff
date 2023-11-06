package edu.hw4.Task19;

import edu.hw4.Animal.Animal;
import edu.hw4.Animal.NullAnimalException;
import edu.hw4.Animal.NullAnimalListException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import static edu.hw4.Animal.AnimalListValidator.validateAnimalList;

public class Task19 {

    public Map<String, Set<ValidationError>> getFieldErrorsInAllAnimals(List<Animal> animals)
        throws NullAnimalListException, NullAnimalException {
        validateAnimalList(animals);
        Map<String, Set<ValidationError>> fieldErrorMap = new HashMap<>();
        for (Animal animal : animals) {
            Set<ValidationError> errorsInAnimal = getValidationErrorsInAnimal(animal, animals);
            if (!errorsInAnimal.isEmpty()) {
                fieldErrorMap.put(animal.name(), errorsInAnimal);
            }
        }
        return fieldErrorMap;
    }

    private Set<ValidationError> getValidationErrorsInAnimal(Animal animal, List<Animal> animals) {
        Set<ValidationError> validationErrors = new TreeSet<>(Comparator.comparing(ValidationError::getFieldName));
        if (isNull(animal.name())) {
            validationErrors.add(new ValidationError(Animal.NAME_FIELD_NAME, TypeError.NULL));
        } else if (isNoUnique(animal.name(), animals)) {
            validationErrors.add(new ValidationError(Animal.NAME_FIELD_NAME, TypeError.NO_UNIQUE));
        }
        if (isNull(animal.type())) {
            validationErrors.add(new ValidationError(Animal.TYPE_FIELD_NAME, TypeError.NULL));
        }
        if (isNull(animal.sex())) {
            validationErrors.add(new ValidationError(Animal.SEX_FIELD_NAME, TypeError.NULL));
        }
        if (isNegativeOrZero(animal.age())) {
            validationErrors.add(new ValidationError(Animal.AGE_FIELD_NAME, TypeError.NEGATIVE_OR_ZERO));
        }
        if (isNegativeOrZero(animal.weight())) {
            validationErrors.add(new ValidationError(Animal.WEIGHT_FIELD_NAME, TypeError.NEGATIVE_OR_ZERO));
        }
        if (isNegativeOrZero(animal.height())) {
            validationErrors.add(new ValidationError(Animal.HEIGHT_FIELD_NAME, TypeError.NEGATIVE_OR_ZERO));
        }
        return validationErrors;
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



