package edu.hw11.Task2;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.InvocationTargetException;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static net.bytebuddy.matcher.ElementMatchers.named;
import static org.assertj.core.api.Assertions.assertThat;

public class MethodOverrideTest {

    static class ArithmeticOverrideUtils {
        public int sum(int a, int b) {
            return a * b;
        }
    }

    <T> Class<? extends T> replaceMethod(Class<T> tClass, String methodName, Object objectForReplace)
        throws IllegalAccessException {
        return new ByteBuddy().subclass(tClass).method(named(methodName))
            .intercept(MethodDelegation.to(objectForReplace))
            .make().load(getClass().getClassLoader(), ClassLoadingStrategy.UsingLookup.of(MethodHandles
                .privateLookupIn(tClass, MethodHandles.lookup()))).getLoaded();
    }

    @ParameterizedTest
    @CsvSource({"3, 3, 9", "4, 4, 16", "0,-1, 0"})
    void replaceMethodTest(int number1, int number2, int excepted)
        throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        assertThat(replaceMethod(ArithmeticUtils.class, "sum", new ArithmeticOverrideUtils()).getDeclaredConstructor()
            .newInstance()
            .sum(number1, number2)).isEqualTo(excepted);
    }

}
