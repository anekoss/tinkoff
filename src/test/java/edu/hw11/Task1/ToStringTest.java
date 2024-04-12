package edu.hw11.Task1;

import java.lang.reflect.InvocationTargetException;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import org.junit.jupiter.api.Test;
import static net.bytebuddy.matcher.ElementMatchers.named;
import static org.assertj.core.api.Assertions.assertThat;

public class ToStringTest {

    @Test
    void test() throws InstantiationException, IllegalAccessException, NoSuchMethodException,
        InvocationTargetException {
        String helloString = "Hello, ByteBuddy!";
        Class<?> hello =
            new ByteBuddy()
                .subclass(Object.class)
                .name("Hello")
                .method(named("toString"))
                .intercept(FixedValue.value("Hello, ByteBuddy!"))
                .make()
                .load(getClass().getClassLoader())
                .getLoaded();
        assertThat(hello.getSimpleName()).isEqualTo("Hello");
        assertThat(hello.getDeclaredConstructor().newInstance().toString()).isEqualTo(helloString);

    }
}
