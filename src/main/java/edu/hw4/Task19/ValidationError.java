package edu.hw4.Task19;

public class ValidationError {
    private final String fieldName;
    private final TypeError typeError;

    public ValidationError(String fieldName, TypeError typeError) {
        this.fieldName = fieldName;
        this.typeError = typeError;
    }

    public String getMessage() {
        return "field " + fieldName + " : " + typeError;
    }

    public String getFieldName() {
        return fieldName;
    }
}
