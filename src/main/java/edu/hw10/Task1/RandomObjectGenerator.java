package edu.hw10.Task1;

import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.Map;
import java.util.function.Supplier;

public class RandomObjectGenerator {

    private final AnnotationHandler annotationHandler;
    private final Map<Class<?>, Supplier<?>> generatorMap;

    public RandomObjectGenerator(Map<Class<?>, Supplier<?>> generatorMap, AnnotationHandler annotationHandler) {
        this.generatorMap = generatorMap;
        this.annotationHandler = annotationHandler;
    }

    public <T> T nextObject(Class<T> tClass)
        throws InvocationTargetException, InstantiationException, NoSuchMethodException, IllegalAccessException,
        ClassNotFoundException {
        Constructor<?> constructor = Class.forName(tClass.getName()).getDeclaredConstructors()[0];
        Object[] params = getParamsForExecutable(constructor);
        return (T) constructor.newInstance(params);
    }

    public <T> T nextObject(Class<T> tClass, String methodName)
        throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        Method method = findMethod(tClass, methodName);
        Object[] params = getParamsForExecutable(method);
        return (T) method.invoke(null, params);
    }

    private Method findMethod(Class<?> clazz, String methodName) {
        for (Method method : clazz.getDeclaredMethods()) {
            if (Modifier.isStatic(method.getModifiers()) && method.getReturnType() == clazz
                && method.getName().equals(methodName)) {
                return method;
            }
        }
        throw new IllegalArgumentException("method " + methodName + " not found");
    }

    private Object[] getParamsForExecutable(Executable executable)
        throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        Object[] objects = new Object[executable.getParameterCount()];
        Parameter[] parameters = executable.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            objects[i] = getParam(parameters[i]);
        }
        return objects;
    }

    private Object getParam(Parameter parameter)
        throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        if (!generatorMap.containsKey(parameter.getType())) {
            throw new IllegalArgumentException("type " + parameter.getType() + " not support by generator");
        }
        Object generated = generatorMap.get(parameter.getType()).get();
        return annotationHandler.annotationsHandler(parameter, generated);
    }
}
