package edu.project2.solver.DSF;

import edu.project2.game.Cell;
import edu.project2.game.Coordinate;
import edu.project2.game.Maze;
import edu.project2.game.MazeManager;
import edu.project2.solver.SolverManager;
import java.util.ArrayDeque;
import java.util.Deque;

public class DSFSolver {
    private final MazeManager mazeManager;
    private final Deque<Cell> path;
    private final SolverManager solverManager;

    public DSFSolver(Maze maze) {
        this.mazeManager = new MazeManager(maze);
        this.solverManager = new SolverManager(maze);
        this.path = new ArrayDeque<>();
    }

    public Deque<Cell> solve(Coordinate in, Coordinate out) {
        Cell currentCell = solverManager.getCellForCoordinate(in);
        path.push(currentCell);
        Cell outCell = solverManager.getCellForCoordinate(out);
        searchOutPoint(currentCell, outCell);
        return path;
    }

    private void searchOutPoint(Cell inCell, Cell out) {
        Cell currentCell = inCell;
        mazeManager.removeVisited(currentCell);
        while (!currentCell.equals(out)) {
            if (mazeManager.isNoVisitedNeighboursIsEmpty(currentCell)) {
                currentCell = visitRandomNeighbour(currentCell);
                path.push(currentCell);
            } else if (!path.isEmpty()) {
                path.pop();
                if (!path.isEmpty()) {
                    currentCell = path.peek();
                }
            }
        }
    }

    private Cell visitRandomNeighbour(Cell cell) {
        Cell neighbour = mazeManager.getRandomNeighbour(cell);
        mazeManager.removeVisited(neighbour);
        mazeManager.removeNeighbour(cell, neighbour);
        return neighbour;
    }

}
