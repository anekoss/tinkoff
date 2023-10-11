package edu.hw1;

public class Task8 {

    private static final int SIZE_ARR = 8;

    private static final int IS_HORSE = 1;

    private static final int BOUND_ARRAY = 0;

    private Task8() {

    }

    public static boolean knightBoardCapture(int[][] arr) {
        for (int i = 0; i < SIZE_ARR; i++) {
            for (int j = 0; j < SIZE_ARR; j++) {
                if (arr[i][j] == IS_HORSE) {
                    if (i - 2 >= BOUND_ARRAY && j + 1 < SIZE_ARR && arr[i - 2][j + 1] == IS_HORSE
                        || i - 1 >= BOUND_ARRAY && j + 2 < SIZE_ARR && arr[i - 1][j + 2] == IS_HORSE
                        || i - 2 >= BOUND_ARRAY && j - 1 >= 0 && arr[i - 2][j - 1] == IS_HORSE
                        || i - 1 >= BOUND_ARRAY && j - 2 >= 0 && arr[i - 1][j - 2] == IS_HORSE) {
                        return false;
                    }
                }

            }
        }
        return true;
    }
}
