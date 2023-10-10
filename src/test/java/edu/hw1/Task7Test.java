package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw1.Task7.rotateLeft;
import static edu.hw1.Task7.rotateRight;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task7Test {

    @Test
    @DisplayName("циклический сдвиг вправо на 1")
    void test1() {
        int n = 8;
        int res = rotateRight(n, 1);
        assertThat(res).isEqualTo(4);
    }

    @Test
    @DisplayName("циклический сдвиг вправо числа 0")
    void test2() {
        int n = 0;
        int res = rotateRight(n, 5);
        assertThat(res).isEqualTo(0);
    }

    @Test
    @DisplayName("циклический сдвиг влево на 1")
    void test3() {
        int n = 16;
        int res = rotateLeft(n, 1);
        assertThat(res).isEqualTo(1);
    }

    @Test
    @DisplayName("циклический сдвиг влево на 2")
    void test4() {
        int n = 17;
        int res = rotateLeft(n, 2);
        assertThat(res).isEqualTo(6);
    }

    @Test
    @DisplayName("циклический сдвиг влево числа 0")
    void test5() {
        int n = 0;
        int res = rotateLeft(n, 5);
        assertThat(res).isEqualTo(0);
    }

    @Test
    @DisplayName("циклический сдвиг вправо на размер двоичного числа")
    void test6() {
        int n = 16;
        int res = rotateRight(n, 5);
        assertThat(res).isEqualTo(16);
    }

    @Test
    @DisplayName("циклический сдвиг влево на размер двоичного числа")
    void test7() {
        int n = 128;
        int res = rotateLeft(n, 8);
        assertThat(res).isEqualTo(128);
    }
}
