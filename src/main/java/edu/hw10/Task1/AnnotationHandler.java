package edu.hw10.Task1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.Random;

public class AnnotationHandler {
    private final static String METHOD_NAME = "value";
    private final String castExceptionMessage = " not converted to int";
    private final static Random RANDOM = new Random();

    public Object annotationsHandler(Parameter parameter, Object generated)
        throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        Min min = parameter.getAnnotation(Min.class);
        Max max = parameter.getAnnotation(Max.class);
        NotNull notNull = parameter.getAnnotation(NotNull.class);
        if (min != null || max != null) {
            if (min != null && max != null) {
                return minMaxHandler(generated, min, max);
            }
            if (max != null) {
                return maxHandler(generated, max);
            }
            return minHandler(generated, min);
        }
        return notNull != null ? notNullHandler(generated) : generated;
    }

    public Object notNullHandler(Object value) {
        if (value == null) {
            throw new IllegalArgumentException("object not be null");
        }
        return value;
    }

    public Object minMaxHandler(Object value, Min minAnnotation, Max maxAnnotation)
        throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        int min =
            (int) minAnnotation.annotationType().getDeclaredMethod(METHOD_NAME, null)
                .invoke(minAnnotation);
        int max =
            (int) maxAnnotation.annotationType().getDeclaredMethod(METHOD_NAME, null)
                .invoke(maxAnnotation);
        if (min >= max) {
            throw new IllegalArgumentException("max value must be greater than min value");
        }
        try {
            if (min <= (int) value && (int) value <= max) {
                return value;
            }
        } catch (ClassCastException e) {
            throw new IllegalArgumentException(value.getClass() + castExceptionMessage);
        }
        return RANDOM.nextInt(min, max);
    }

    public Object minHandler(Object value, Min minAnnotation)
        throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        int min =
            (int) minAnnotation.annotationType().getDeclaredMethod(METHOD_NAME, null)
                .invoke(minAnnotation);
        try {
            if (min > (int) value) {
                return min;
            }
        } catch (ClassCastException e) {
            throw new IllegalArgumentException(value.getClass() + castExceptionMessage);
        }
        return value;
    }

    public Object maxHandler(Object value, Max maxAnnotation)
        throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        int max =
            (int) maxAnnotation.annotationType().getDeclaredMethod(METHOD_NAME, null)
                .invoke(maxAnnotation);
        try {
            if (max < (int) value) {
                return max;
            }
        } catch (ClassCastException e) {
            throw new IllegalArgumentException(value.getClass() + castExceptionMessage);
        }
        return value;
    }

}
