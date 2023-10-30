package edu.hw4.Task19;

import java.util.Objects;

public class ValidationError extends Exception {
    private final String fieldName;
    private final TypeError typeError;

    public ValidationError(String fieldName, TypeError typeError) {
        super("field " + fieldName + " : " + typeError);
        this.fieldName = fieldName;
        this.typeError = typeError;
    }

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ValidationError that = (ValidationError) o;
        return fieldName.equals(that.fieldName) && typeError == that.typeError;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fieldName, typeError);
    }

    public String getFieldName() {
        return fieldName;
    }
}
