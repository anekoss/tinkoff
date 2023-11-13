package edu.project2.solver;

import edu.project2.game.Cell;
import edu.project2.game.Coordinate;
import edu.project2.game.Maze;
import edu.project2.game.MazeManager;

public class SolverManager {
    private final MazeManager mazeManager;

    public SolverManager(Maze maze) {
        this.mazeManager = new MazeManager(maze);
    }

    public Cell getCellForCoordinate(Coordinate coordinate) {
        return mazeManager.getGrid()[coordinate.row()][coordinate.col()];
    }
}
