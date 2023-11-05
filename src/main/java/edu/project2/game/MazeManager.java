package edu.project2.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import static edu.project2.game.Cell.Dictionary.CELL;
import static edu.project2.game.Cell.Dictionary.WALL;

public class MazeManager {
    private static final Random RANDOM = new Random();
    private final Map<Cell, Boolean> noVisitedCell;

    private final Map<Cell, List<Cell>> noVisitedNeighbours;
    private final Cell[][] grid;

    public MazeManager(int height, int width) {

        this.grid = initGrid(height, width);
        this.noVisitedCell =
            initNoVisitedCell(grid);
        this.noVisitedNeighbours = initNeighbours(grid, height, width, 2);
    }

    public MazeManager(Maze maze) {
        this.grid = maze.getGrid();
        this.noVisitedCell =
            initNoVisitedCell(grid);
        this.noVisitedNeighbours = initNeighbours(grid, maze.getHeight(), maze.getWidth(), 1);
    }

    public Cell[][] initGrid(int height, int width) {
        Cell[][] grid = new Cell[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if ((i % 2 != 0 && j % 2 != 0) &&
                    (i < height - 1 && j < width - 1)) {
                    grid[i][j] = new Cell(i, j, CELL);
                } else {
                    grid[i][j] = new Cell(i, j, WALL);
                }
            }
        }
        return grid;
    }

    private Map<Cell, Boolean> initNoVisitedCell(Cell[][] grid) {
        Map<Cell, Boolean> visitedMap = new HashMap<>();
        Arrays.stream(grid).flatMap(Arrays::stream).filter(cell -> cell.type() == CELL)
            .forEach(cell -> visitedMap.put(cell, false));
        return visitedMap;
    }

    private Map<Cell, List<Cell>> initNeighbours(Cell[][] grid, int height, int width, int diff) {
        Map<Cell, List<Cell>> neighboursMap = new HashMap<>();
        Arrays.stream(grid).flatMap(Arrays::stream).filter(cell -> cell.type() == CELL)
            .forEach(cell -> neighboursMap.put(cell, getNeighboursCell(cell, height, width, diff)));
        return neighboursMap;
    }

    private List<Cell> getNeighboursCell(Cell cell, int height, int width, int diff) {
        ArrayList<Cell> neighbours = new ArrayList<>();
        if (cell.x() - diff > 0) {
            neighbours.add(grid[cell.x() - diff][cell.y()]);
        }
        if (cell.x() + diff < height - 1) {
            neighbours.add(grid[cell.x() + diff][cell.y()]);
        }
        if (cell.y() - diff > 0) {
            neighbours.add(grid[cell.x()][cell.y() - diff]);
        }
        if (cell.y() + diff < width - 1) {
            neighbours.add(grid[cell.x()][cell.y() + diff]);
        }
        return neighbours;
    }

    public boolean isCellVisited(Cell cell) {
        return noVisitedCell.containsKey(cell);
    }

    public void removeWall(Cell current, Cell neighbour) {
        int x = current.x() - neighbour.x();
        int y = current.y() - neighbour.y();
        int wallX = current.x();
        int wallY = current.y();
        if (x > 0) {
            wallX = wallX - 1;
        }
        if (x < 0) {
            wallX = wallX + 1;
        }
        if (y > 0) {
            wallY = wallY - 1;
        }
        if (y < 0) {
            wallY = wallY + 1;
        }
        this.grid[wallX][wallY] = new Cell(wallX, wallY, CELL);
    }

    public Cell getRandomNoVisitedCell() {
        return
            noVisitedCell.keySet().stream().toList().get(RANDOM.nextInt(noVisitedCell.size()));
    }

    public List<Cell> getNoVisitedCell() {
        return noVisitedCell.keySet().stream().toList();
    }

    public boolean noVisitedIsEmpty() {
        return noVisitedCell.isEmpty();
    }

    public boolean isNoVisitedNeighboursIsEmpty(Cell cell) {
        return noVisitedNeighbours.containsKey(cell) && noVisitedNeighbours.get(cell) != null &&
            !noVisitedNeighbours.get(cell).isEmpty();
    }

    public void removeVisited(Cell cell) {

        noVisitedCell.remove(cell);
        noVisitedNeighbours.forEach((key, value) -> value.remove(cell));

    }

    public List<Cell> getNeighbours(Cell cell) {
        return noVisitedNeighbours.get(cell);
    }

    public void removeNeighbour(Cell cell, Cell neighbour) {
        List<Cell> neighbours = new ArrayList<>(noVisitedNeighbours.get(cell));
        if (!neighbours.isEmpty()) {
            neighbours.remove(neighbour);
        }
        this.noVisitedNeighbours.put(cell, neighbours);
    }

    public Cell getRandomNeighbour(Cell cell) {
        List<Cell> neighbour = noVisitedNeighbours.get(cell);
        return neighbour.get(RANDOM.nextInt(neighbour.size()));
    }

    public Cell[][] getGrid() {
        return grid;
    }
}
