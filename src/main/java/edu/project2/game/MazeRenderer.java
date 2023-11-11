package edu.project2.game;

import java.util.Deque;
import java.util.List;
import static edu.project2.game.Cell.Dictionary.WALL;

public class MazeRenderer {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN_BACKGROUND
        = "\u001B[47m";
    public static final String WHITESPACE = "  ";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String NEXT_LINE = "\n";

    private final Output output;

    public MazeRenderer(Output output) {
        this.output = output;
    }

    public void printMaze(Maze maze) {
        int height = maze.height();
        int width = maze.width();
        Cell[][] grid = maze.grid();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (grid[i][j].type() == WALL) {
                    output.print(ANSI_GREEN_BACKGROUND + WHITESPACE + ANSI_RESET);
                } else {
                    output.print(WHITESPACE);
                }

            }
            output.print(NEXT_LINE);
        }
    }

    public void printSolve(Maze maze, Deque<Cell> path) {
        int height = maze.height();
        int width = maze.width();
        Cell[][] grid = maze.grid();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (path.contains(grid[i][j])) {
                    output.print(ANSI_RED_BACKGROUND + WHITESPACE + ANSI_RESET);
                } else if (grid[i][j].type() == WALL) {
                    output.print(ANSI_GREEN_BACKGROUND + WHITESPACE + ANSI_RESET);
                } else {
                    output.print(WHITESPACE);
                }

            }
            output.print(NEXT_LINE);
        }
    }

}
