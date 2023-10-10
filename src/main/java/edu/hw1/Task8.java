package edu.hw1;

public class Task8 {
    private Task8() {

    }

    private static final int SIZEARR = 8;

    public static boolean knightBoardCapture(int[][] arr) {
        for (int i = 0; i < SIZEARR; i++) {
            for (int j = 0; j < SIZEARR; j++) {
                if (arr[i][j] == 1) {
                    if (i - 2 >= 0 && j + 1 < SIZEARR && arr[i - 2][j + 1] == 1
                        || i - 1 >= 0 && j + 2 < SIZEARR && arr[i - 1][j + 2] == 1
                        || i - 2 >= 0 && j - 1 >= 0 && arr[i - 2][j - 1] == 1
                        || i - 1 >= 0 && j - 2 >= 0 && arr[i - 1][j - 2] == 1) {
                        return false;
                    }
                }

            }
        }
        return true;
    }
}
