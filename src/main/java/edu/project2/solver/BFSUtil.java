package edu.project2.solver;

import edu.project2.game.Cell;
import edu.project2.game.Coordinate;
import edu.project2.game.Maze;
import edu.project2.game.MazeManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BFSUtil {

    private final MazeManager grid;
    private final Maze maze;
    private final SolverManager solverManager;

    public BFSUtil(Maze maze) {
        this.maze = maze;
        this.grid = new MazeManager(maze);
        this.solverManager = new SolverManager(maze);
    }

    private final Map<Cell, Cell> parent = new HashMap<>();
    List<Cell> visited = new ArrayList<>();
    LinkedList<Cell> path = new LinkedList<>();

    public List<Cell> solve(Coordinate in, Coordinate out) {
        path = new LinkedList<>();
        Cell cell = solverManager.getCellForCoordinate(in);
        Cell outCell = solverManager.getCellForCoordinate(out);
        bfs(cell, outCell);
        return getPath(cell, outCell);
    }

    private void bfs(Cell cell, Cell out) {
        path.push(cell);
        while (!path.isEmpty()) {
            cell = path.remove();
            if (visited.contains(cell)) {
                continue;
            }
            if (cell.equals(out)) {
                return;
            }
            if (grid.getNeighbours(cell) != null) {
                for (Cell cell1 : grid.getNeighbours(cell)) {
                    path.add(cell1);
                    parent.put(cell1, cell);
                }
            }
            grid.removeVisited(cell);
            visited.add(cell);
        }
    }

    public List<Cell> getPath(Cell inCell, Cell outCell) {
        path = new LinkedList<>();
        path.add(outCell);
        while (!outCell.equals(inCell)) {
            outCell = parent.get(outCell);
            path.add(outCell);
        }
        return path;
    }

}
