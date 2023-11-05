package edu.project2.solver.BSF;

import edu.project2.BadCoordinateException;
import edu.project2.game.Cell;
import edu.project2.game.Coordinate;
import edu.project2.game.Maze;
import edu.project2.game.MazeManager;
import edu.project2.solver.SolverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BSFUtil {

    private final MazeManager grid;

    private final SolverManager solverManager;

    public BSFUtil(Maze maze) {
        this.grid = new MazeManager(maze);
        this.solverManager = new SolverManager(maze);
    }

    private final Map<Cell, Cell> parent = new HashMap<>();
    List<Cell> visited = new ArrayList<>();
    LinkedList<Cell> path = new LinkedList<>();

    public List<Cell> solve(Coordinate in, Coordinate out) throws BadCoordinateException {
        path = new LinkedList<>();
        Cell cell = solverManager.getCellForCoordinate(in);
        Cell outCell = solverManager.getCellForCoordinate(out);
        bfs(cell, outCell);
        return getPath(cell, outCell);
    }

    private void bfs(Cell inCell, Cell outCell) {
        Cell currentCell = inCell;
        path.push(currentCell);
        while (!path.isEmpty()) {
            currentCell = path.remove();
            if (visited.contains(currentCell)) {
                continue;
            }
            if (currentCell.equals(outCell)) {
                return;
            }
            if (grid.getNeighbours(currentCell) != null) {
                for (Cell cell1 : grid.getNeighbours(currentCell)) {
                    path.add(cell1);
                    parent.put(cell1, currentCell);
                }
            }
            grid.removeVisited(currentCell);
            visited.add(currentCell);
        }
    }

    public List<Cell> getPath(Cell inCell, Cell outCell) {
        Cell currentCell = outCell;
        path = new LinkedList<>();
        path.add(currentCell);
        while (!currentCell.equals(inCell)) {
            currentCell = parent.get(currentCell);
            path.add(currentCell);
        }
        return path;
    }

}
