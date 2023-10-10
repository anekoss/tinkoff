package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw1.Task6.countK;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task6Test {

    @Test
    void test1() {
        int n = 3524;
        int res = countK(n);
        assertThat(res).isEqualTo(3);
    }

    @Test
    void test2() {
        int n = 6621;
        int res = countK(n);
        assertThat(res).isEqualTo(5);
    }

    @Test
    void test3() {
        int n = 6554;
        int res = countK(n);
        assertThat(res).isEqualTo(4);
    }

    @Test
    void test4() {
        int n = 1234;
        int res = countK(n);
        assertThat(res).isEqualTo(3);
    }

    @Test
    @DisplayName("число равно постоянной Капрекара")
    void test5() {
        int n = 6174;
        int res = countK(n);
        assertThat(res).isEqualTo(0);
    }

    @Test
    @DisplayName("не четырехзначное число")
    void test6() {
        int n = 999;
        int res = countK(n);
        assertThat(res).isEqualTo(-1);
    }

    @Test
    @DisplayName("число равно 1000")
    void test7() {
        int n = 1000;
        int res = countK(n);
        assertThat(res).isEqualTo(-1);
    }

    @Test
    @DisplayName("все цифры одинаковы")
    void test8() {
        int n = 9999;
        int res = countK(n);
        assertThat(res).isEqualTo(-1);
    }
}
