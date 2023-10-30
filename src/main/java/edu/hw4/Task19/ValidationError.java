package edu.hw4.Task19;

public class ValidationError extends Exception {
    private final String fieldName;
    private final TypeError typeError;

    public ValidationError(String fieldName, TypeError typeError) {
        super("field " + fieldName + " : " + typeError);
        this.fieldName = fieldName;
        this.typeError = typeError;
    }

}
