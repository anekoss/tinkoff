package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    @Test
    @DisplayName("счетчик количества цифр в числе 0")
    void test1() {
        Integer num = 0;
        Integer cnt = Task2.countDigits(num);
        assertThat(cnt).isEqualTo(1);
    }

    @Test
    @DisplayName("счетчик количества цифр в отрицательном числе")
    void test2() {
        Integer num = -123;
        Integer cnt = Task2.countDigits(num);
        assertThat(cnt).isEqualTo(3);
    }

    @Test
    @DisplayName("счетчик количества цифр в числе OK")
    void test3() {
        Integer num = 123406;
        Integer cnt = Task2.countDigits(num);
        assertThat(cnt).isEqualTo(6);
    }

    @Test
    @DisplayName("счетчик количества цифр в числе OK")
    void test4() {
        Integer num = 2;
        Integer cnt = Task2.countDigits(num);
        assertThat(cnt).isEqualTo(1);
    }
}
