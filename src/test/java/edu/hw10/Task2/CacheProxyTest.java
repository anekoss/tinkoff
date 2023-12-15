package edu.hw10.Task2;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public class CacheProxyTest {

    private static Calculator calculator;
    private static Map<String, Object> cache;

    @BeforeAll
    static void init() {
        cache = new TreeMap<>();
        calculator = CacheProxy.create(new CalculatorImlp(), Calculator.class, cache);
    }

    @ParameterizedTest
    @CsvSource({"-1,1", "2, 10", "30, -30"})
    void noneCacheMethodTest(int number1, int number2) {
        int startSize = cache.size();
        calculator.multiply(number1, number2);
        assertThat(calculator.multiply(number1, number2)).isEqualTo(number1 * number2);
        int endSize = cache.size();
        assertThat(startSize).isEqualTo(endSize);
    }

    @ParameterizedTest
    @CsvSource({"-1,1", "2, 10", "30, -30"})
    void persistCacheMethodTest(int number1, int number2) {
        int startSize = cache.size();
        assertThat(calculator.plus(number1, number2)).isEqualTo(number1 + number2);
        int endSize = cache.size();
        assertThat(startSize + 1).isEqualTo(endSize);
    }

    @ParameterizedTest
    @CsvSource({"-1,1", "2, 10", "30, -30"})
    void notPersistCacheMethodTest(int number1, int number2) {
        int startSize = cache.size();
        assertThat(calculator.minus(number1, number2)).isEqualTo(number1 - number2);
        int endSize = cache.size();
        assertThat(startSize).isEqualTo(endSize);
    }

    @ParameterizedTest
    @CsvSource({"-5,5", "-2, 10", "-30, -30"})
    void notDuplicateResultInMapTest(int number1, int number2) {
        int startSize = cache.size();
        calculator.plus(number1, number2);
        int endSize = cache.size();
        assertThat(startSize + 1).isEqualTo(endSize);
        calculator.plus(number1, number2);
        int duplicateSize = cache.size();
        assertThat(endSize).isEqualTo(duplicateSize);
    }

    @ParameterizedTest
    @CsvSource({"0,0", "-20, 10", "-300, -30"})
    void dataCacheTest(int number1, int number2) {
        int result = calculator.plus(number1, number2);
        String excepted = "plus:" + Arrays.hashCode(new Object[] {number1, number2});
        assertThat(cache).containsKey(excepted);
        assertThat(cache.get(excepted)).isEqualTo(result);
    }
}
