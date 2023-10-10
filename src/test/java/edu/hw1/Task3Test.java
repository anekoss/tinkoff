package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw1.Task3.isNestable;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {

    @Test
    @DisplayName("max(a1) меньше, чем max(a2)")
    void test1() {
        int[] a1 = {1, 2, 3, 4};
        int[] a2 = {0, 6};
        boolean res = isNestable(a1, a2);
        assertThat(res).isEqualTo(true);
    }

    @Test
    @DisplayName("max(a1) меньше, чем max(a2) && min(a1) больше чем min(a2)")
    void test2() {
        int[] a1 = {3, 1};
        int[] a2 = {4, 0};
        boolean res = isNestable(a1, a2);
        assertThat(res).isEqualTo(true);
    }



    @Test
    @DisplayName("!max(a1) меньше, чем max(a2) && !min(a1) больше чем min(a2)")
    void test3() {
        int[] arr1 = {1, 2, 3, 4};
        int[] arr2 = {2, 3};
        boolean res = isNestable(arr1, arr2);
        assertThat(res).isEqualTo(false);
    }

    @Test
    @DisplayName("min(a1) больше чем min(a2)")
    void test4() {
        int[] a1 = {3, 1};
        int[] a2 = {2, 0};
        boolean res = isNestable(a1, a2);
        assertThat(res).isEqualTo(true);
    }

    @Test
    @DisplayName("!max(a1) меньше, чем max(a2) && !min(a1) больше чем min(a2)")
    void test5() {
        int[] arr1 = {9, 9, 8};
        int[] arr2 = {8, 9};
        boolean res = isNestable(arr1, arr2);
        assertThat(res).isEqualTo(false);
    }

    @Test
    @DisplayName("!max(a1) меньше, чем max(a2) && !min(a1) больше чем min(a2)")
    void test6() {
        int[] arr1 = {8};
        int[] arr2 = {8};
        boolean res = isNestable(arr1, arr2);
        assertThat(res).isEqualTo(false);
    }

    @Test
    @DisplayName("!max(a1) меньше, чем max(a2) && !min(a1) больше чем min(a2)")
    void test7() {
        int[] arr1 = {8, 4};
        int[] arr2 = {8, 5};
        boolean res = isNestable(arr1, arr2);
        assertThat(res).isEqualTo(false);
    }

    @Test
    @DisplayName("!max(a1) меньше, чем max(a2) && !min(a1) больше чем min(a2)")
    void test8() {
        int[] arr1 = {9, 4};
        int[] arr2 = {8, 5};
        boolean res = isNestable(arr1, arr2);
        assertThat(res).isEqualTo(false);
    }
}
