package edu.project2.solver;

import edu.project2.BadCoordinateException;
import edu.project2.game.Cell;
import edu.project2.game.Coordinate;
import edu.project2.game.Maze;
import edu.project2.game.MazeManager;
import java.util.ArrayDeque;
import java.util.Deque;

public class SolverManager {
    private final Maze maze;
    private final MazeManager mazeManager;

    public SolverManager(Maze maze) {
        this.maze = maze;
        this.mazeManager = new MazeManager(maze);
    }

    public void validateCoordinate(Coordinate coordinate) {
        if (coordinate.col() < 0 || coordinate.col() > maze.getHeight() || coordinate.row() < 0 ||
            coordinate.row() > maze.getWidth()) {
            throw new IllegalArgumentException();
        }
    }

    public Cell getCellForCoordinate(Coordinate coordinate) {
        validateCoordinate(coordinate);
        return mazeManager.getGrid()[coordinate.row()][coordinate.col()];
    }
}
