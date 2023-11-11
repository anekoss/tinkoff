package edu.hw4.Task20;

import edu.hw4.Task19.ValidationError;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Task20 {

    public Map<String, String> getStringErrorAnimals(Map<String, Set<ValidationError>> fieldErrorMap) {
        if (fieldErrorMap == null || fieldErrorMap.isEmpty()) {
            return Map.of();
        }
        Map<String, String> errorMap = new HashMap<>();
        for (Map.Entry<String, Set<ValidationError>> errorAnimal : fieldErrorMap.entrySet()) {
            String errors = errorAnimal.getValue().stream().sorted(Comparator.comparing(ValidationError::getFieldName))
                .map(ValidationError::getMessage)
                .collect(Collectors.joining(","));
            errorMap.put(errorAnimal.getKey(), errors);
        }
        return errorMap;
    }
}
