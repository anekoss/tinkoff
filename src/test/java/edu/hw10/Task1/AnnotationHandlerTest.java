package edu.hw10.Task1;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AnnotationHandlerTest {

    private static Min minAnnotation;
    private final static AnnotationHandler annotationHandler = new AnnotationHandler();
    private static Max maxAnnotation;
    private static Annotation[] minMaxAnnotations;
    private static Parameter notNullParameter;

    private static Parameter notNullMinMaxParameter;
    private static Parameter nullParameter;
    private static Parameter minParameter;
    private static Parameter maxParameter;

    @BeforeAll
    static void initParameters() {
        Parameter[] parameters = AnnotationTestClass.class.getConstructors()[0].getParameters();
        notNullMinMaxParameter = parameters[0];
        minParameter = parameters[1];
        maxParameter = parameters[2];
        notNullParameter = parameters[4];
        nullParameter = parameters[5];
    }

    @BeforeAll
    static void initAnnotations() {
        Parameter[] parameters = AnnotationTestClass.class.getConstructors()[0].getParameters();
        minAnnotation = (Min) parameters[1].getAnnotations()[0];
        maxAnnotation = (Max) parameters[2].getAnnotations()[0];
        minMaxAnnotations = parameters[3].getAnnotations();
    }

    static Stream<Arguments> provideDataForAnnotationsHandlerTest() {
        return Stream.of(
            Arguments.of(minParameter, -1, -1),
            Arguments.of(minParameter, 3, 3),
            Arguments.of(maxParameter, -3, -3),
            Arguments.of(maxParameter, 43, -1),
            Arguments.of(notNullMinMaxParameter, 10, 10),
            Arguments.of(notNullParameter, "hi", "hi"),
            Arguments.of(nullParameter, "hi", "hi"),
            Arguments.of(nullParameter, null, null)
        );
    }

    @ParameterizedTest
    @MethodSource("provideDataForAnnotationsHandlerTest")
    void annotationsHandlerTest(Parameter parameter, Object value, Object excepted)
        throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        assertThat(annotationHandler.annotationsHandler(parameter, value)).isEqualTo(excepted);
    }

    static Stream<Arguments> provideDataForMinMaxHandlerTest() {
        return Stream.of(
            Arguments.of(1, -1),
            Arguments.of(-5, -5),
            Arguments.of(43, -1),
            Arguments.of(-200, -200)
        );
    }

    @ParameterizedTest
    @MethodSource("provideDataForMinMaxHandlerTest")
    void maxHandlerTest(Object value, Object excepted)
        throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        assertThat(annotationHandler.maxHandler(value, maxAnnotation)).isEqualTo(excepted);
    }

    @ParameterizedTest
    @MethodSource("provideDataForMinMaxHandlerTest")
    void minMaxHandlerTest(Object ignoredExcepted, Object value)
        throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        assertThat((int) annotationHandler.minMaxHandler(
            value,
            (Min) minMaxAnnotations[0],
            (Max) minMaxAnnotations[1]
        )).isLessThan(100).isGreaterThan(1);
    }

    @Test
    void minMaxHandlerExceptionTest() {
        String exceptedMessage = "max value must be greater than min value";
        IllegalArgumentException exception =
            assertThrows(
                IllegalArgumentException.class,
                () -> annotationHandler.minMaxHandler(100, minAnnotation, maxAnnotation)
            );
        assertThat(exception.getMessage()).isEqualTo(exceptedMessage);
    }

    @Test
    void minMaxHandlerCastExceptionTest() {
        String value = "exception value";
        String exceptedMessage = "class java.lang.String not converted to int";
        IllegalArgumentException exception =
            assertThrows(IllegalArgumentException.class, () -> annotationHandler.minHandler(value, minAnnotation));
        assertThat(exception.getMessage()).isEqualTo(exceptedMessage);
        exception =
            assertThrows(IllegalArgumentException.class, () -> annotationHandler.maxHandler(value, maxAnnotation));
        assertThat(exception.getMessage()).isEqualTo(exceptedMessage);
        exception =
            assertThrows(IllegalArgumentException.class, () -> annotationHandler.minMaxHandler(value,
                (Min) minMaxAnnotations[0], (Max) minMaxAnnotations[1]
            ));
        assertThat(exception.getMessage()).isEqualTo(exceptedMessage);
    }

    @Test
    void notNullHandlerTest() {
        IllegalArgumentException exception =
            assertThrows(IllegalArgumentException.class, () -> annotationHandler.notNullHandler(null));
        assertThat(exception.getMessage()).isEqualTo("object not be null");
        assertThat(annotationHandler.notNullHandler("not null string")).isEqualTo("not null string");
    }

    static Stream<Arguments> provideDataForMinHandlerTest() {
        return Stream.of(
            Arguments.of(1, 1),
            Arguments.of(-5, -1),
            Arguments.of(43, 43),
            Arguments.of(-200, -1)
        );
    }

    @ParameterizedTest
    @MethodSource("provideDataForMinHandlerTest")
    void minHandlerTest(Object excepted, Object value)
        throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        assertThat(annotationHandler.minHandler(excepted, minAnnotation)).isEqualTo(value);
    }

}
