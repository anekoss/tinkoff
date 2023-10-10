package edu.hw1;

public class Task8 {

    private static final int size = 8;

    public Task8() {

    }

    public static boolean knightBoardCapture(int[][] arr) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (arr[i][j] == 1) {
                    if (i - 2 >= 0 && j + 1 < size && arr[i - 2][j + 1] == 1 ||
                        i - 1 >= 0 && j + 2 < size && arr[i - 1][j + 2] == 1 ||
                        i + 1 < size && j + 2 < size && arr[i + 1][j + 2] == 1 ||
                        i + 2 < size && j + 1 < size && arr[i + 2][j + 1] == 1 ||
                        i + 2 < size && j - 1 >= 0 && arr[i + 2][j - 1] == 1 ||
                        i + 1 < size && j - 2 >= 0 && arr[i + 1][j - 2] == 1 ||
                        i - 2 >= 0 && j - 1 >= 0 && arr[i - 2][j - 1] == 1 ||
                        i - 1 >= 0 && j - 2 >= 0 && arr[i - 1][j - 2] == 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
