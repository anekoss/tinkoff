package edu.project2.generators.DSF;

import edu.project2.game.Cell;
import edu.project2.game.MazeManager;
import java.util.ArrayDeque;
import java.util.Deque;

public class DSFGenerator {
    private final MazeManager mazeManager;
    private final Deque<Cell> visitedCell;

    public DSFGenerator(int height, int width) {
        this.mazeManager = new MazeManager(height, width);
        this.visitedCell = new ArrayDeque<>();
    }

    public Cell[][] generateDSFGrid() {
        Cell currentCell = visitRandomCell();
        visitedCell.push(currentCell);
        while (!mazeManager.noVisitedIsEmpty()) {
            if (mazeManager.isNoVisitedNeighboursIsEmpty(currentCell)) {
                currentCell = removeNeighbourAndWall(currentCell);
                visitedCell.push(currentCell);
            } else if (!visitedCell.isEmpty()) {
                currentCell = visitedCell.pop();
            } else {
                currentCell = visitRandomCell();
            }
        }
        return mazeManager.getGrid();
    }

    private Cell visitRandomCell() {
        Cell cell = mazeManager.getRandomNoVisitedCell();
        mazeManager.removeVisited(cell);
        return cell;
    }

    private Cell removeNeighbourAndWall(Cell cell) {
        Cell neighbour = mazeManager.getRandomNeighbour(cell);
        removeVisitedWall(cell, neighbour);
        mazeManager.removeNeighbour(cell, neighbour);
        return neighbour;
    }

    private void removeVisitedWall(Cell cell, Cell neighbour) {
        mazeManager.removeVisited(neighbour);
        mazeManager.removeVisited(cell);
        mazeManager.removeWall(cell, neighbour);
    }

}
