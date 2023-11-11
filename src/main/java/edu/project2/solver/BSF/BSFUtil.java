package edu.project2.solver.BSF;

import edu.project2.BadCoordinateException;
import edu.project2.game.Cell;
import edu.project2.game.Coordinate;
import edu.project2.game.Maze;
import edu.project2.game.MazeManager;
import edu.project2.solver.SolverManager;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BSFUtil {

    private final MazeManager grid;

    private final SolverManager solverManager;
    private final Map<Cell, Cell> parent = new HashMap<>();
    List<Cell> visited = new ArrayList<>();
    Deque<Cell> path = new ArrayDeque<>();

    public BSFUtil(Maze maze) {
        this.grid = new MazeManager(maze);
        this.solverManager = new SolverManager(maze);
    }

    public Deque<Cell> solve(Coordinate in, Coordinate out) throws BadCoordinateException {
        path = new LinkedList<>();
        Cell cell = solverManager.getCellForCoordinate(in);
        Cell outCell = solverManager.getCellForCoordinate(out);
        bfs(cell, outCell);
        return getPath(cell, outCell);
    }

    private void bfs(Cell inCell, Cell outCell) {
        Cell currentCell = inCell;
        path.push(currentCell);
        while (!currentCell.equals(outCell)) {
            currentCell = path.remove();
            if (!visited.contains(currentCell)) {
                if (grid.getNeighbours(currentCell) != null) {
                    Cell finalCurrentCell = currentCell;
                    grid.getNeighbours(currentCell).stream().forEach(cell -> {
                        path.add(cell);
                        parent.put(cell, finalCurrentCell);
                    });
                }
                grid.removeVisited(currentCell);
                visited.add(currentCell);
            }
        }
    }

    private Deque<Cell> getPath(Cell inCell, Cell outCell) {
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
