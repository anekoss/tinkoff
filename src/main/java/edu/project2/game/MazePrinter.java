package edu.project2.game;

import lombok.extern.slf4j.Slf4j;
import java.util.Deque;
import static edu.project2.game.Cell.Dictionary.WALL;

public class MazePrinter {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_WHITE_BACKGROUND
        = "\u001B[47m";
    public static final String ANSI_YELLOW_BACKGROUND
        = "\u001B[43m";

    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";

    public void printMaze(Maze maze) {
        int height = maze.getHeight();
        int width = maze.getWidth();
        Cell[][] grid = maze.getGrid();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (grid[i][j].type() == WALL) {
                    System.out.print(ANSI_WHITE_BACKGROUND + "  " + ANSI_RESET);
                } else {
                    System.out.print(ANSI_RESET + "  " + ANSI_RESET);
                }

            }
            System.out.println();
        }
    }

    public void printSolve(Maze maze, Deque<Cell> stack) {
        int height = maze.getHeight();
        int width = maze.getWidth();
        Cell[][] grid = maze.getGrid();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (grid[i][j].equals(new Cell(1, 1, Cell.Dictionary.CELL)) ||
                    grid[i][j].equals(new Cell(5, 5, Cell.Dictionary.CELL))) {
                    System.out.print(ANSI_YELLOW_BACKGROUND + "  " + ANSI_RESET);
                } else if (stack.contains(grid[i][j])) {
                    System.out.print(ANSI_RED_BACKGROUND + "  " + ANSI_RESET);

                } else if (grid[i][j].type() == WALL) {
                    System.out.print(ANSI_WHITE_BACKGROUND + "  " + ANSI_RESET);
                } else {
                    System.out.print(ANSI_RESET + "  " + ANSI_RESET);
                }

            }
            System.out.println();

        }
    }

}
