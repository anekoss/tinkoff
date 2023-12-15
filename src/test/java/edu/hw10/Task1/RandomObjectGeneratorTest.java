package edu.hw10.Task1;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RandomObjectGeneratorTest {
    private static AnnotationHandler annotationHandler;
    private static RandomObjectGenerator randomObjectGenerator;

    @BeforeAll
    static void init() {
        Map<Class<?>, Supplier<?>> classSupplierMap = new HashMap<>();
        annotationHandler = new AnnotationHandler();
        classSupplierMap.put(Integer.class, (Supplier<Integer>) () -> 1);
        classSupplierMap.put(String.class, (Supplier<String>) () -> "not null string");
        randomObjectGenerator = new RandomObjectGenerator(classSupplierMap, annotationHandler);
    }

    @Test
    void nextObjectClassTest()
        throws ClassNotFoundException, InvocationTargetException, InstantiationException, NoSuchMethodException,
        IllegalAccessException {
        GeneratorTestClass excepted = new GeneratorTestClass(1, 1, 1, 1, "not null string", "not null string");
        assertThat(randomObjectGenerator.nextObject(GeneratorTestClass.class)).isEqualTo(excepted);
        assertThat(randomObjectGenerator.nextObject(GeneratorTestClass.class, "create")).isEqualTo(excepted);
    }

    @Test
    void nextObjectRecordTest()
        throws ClassNotFoundException, InvocationTargetException, InstantiationException, NoSuchMethodException,
        IllegalAccessException {
        GeneratorTestRecord excepted = new GeneratorTestRecord("not null string", 100, 10, "not null string");
        assertThat(randomObjectGenerator.nextObject(GeneratorTestRecord.class)).isEqualTo(excepted);
    }

    @Test
    void nextObjectExceptionTest() {
        String exceptedMessage = "type interface java.util.Map not support by generator";
        Exception exception =
            assertThrows(
                IllegalArgumentException.class,
                () -> randomObjectGenerator.nextObject(RandomObjectGenerator.class)
            );
        assertThat(exception.getMessage()).isEqualTo(exceptedMessage);
    }

    @Test
    void nextObjectMethodNotFoundExceptionTest() {
        String exceptedMessage = "method notMethod not found";
        Exception exception =
            assertThrows(
                IllegalArgumentException.class,
                () -> randomObjectGenerator.nextObject(GeneratorTestClass.class, "notMethod")
            );
        assertThat(exception.getMessage()).isEqualTo(exceptedMessage);
    }

    @Test
    void nextObjectExceptionNotNullTest() {
        String exceptedMessage = "object not be null";
        Map<Class<?>, Supplier<?>> map = new HashMap<>();
        map.put(String.class, (Supplier<String>) () -> null);
        map.put(Integer.class, (Supplier<Integer>) () -> 1);
        RandomObjectGenerator objectGenerator = new RandomObjectGenerator(map, annotationHandler);
        Exception exception =
            assertThrows(
                IllegalArgumentException.class,
                () -> objectGenerator.nextObject(GeneratorTestClass.class)
            );
        assertThat(exception.getMessage()).isEqualTo(exceptedMessage);
        exception =
            assertThrows(
                IllegalArgumentException.class,
                () -> objectGenerator.nextObject(GeneratorTestClass.class, "create")
            );
        assertThat(exception.getMessage()).isEqualTo(exceptedMessage);
    }
}
