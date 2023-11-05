package edu.project2.generators.DSF;

import edu.project2.game.Cell;
import edu.project2.game.MazeManager;
import edu.project2.generators.GeneratorUtil;
import java.util.LinkedList;

public class DFSUtil {
    private final MazeManager mazeManager;
    private final LinkedList<Cell> visitedCell;
    private final GeneratorUtil generatorUtil;

    public DFSUtil(int height, int width) {
        this.mazeManager = new MazeManager(height, width);
        this.visitedCell = new LinkedList<>();
        this.generatorUtil = new GeneratorUtil(mazeManager);
    }

    public Cell[][] generateDFSGrid() {
        Cell currentCell = generatorUtil.visitRandomCell();
        visitedCell.push(currentCell);
        while (!mazeManager.noVisitedIsEmpty()) {
            if (mazeManager.isNoVisitedNeighboursIsEmpty(currentCell)) {
                currentCell = generatorUtil.removeNeighbourAndWall(currentCell);
                visitedCell.push(currentCell);
            } else if (!visitedCell.isEmpty()) {
                currentCell = visitedCell.pop();
            } else {
                currentCell = generatorUtil.visitRandomCell();
            }
        }
        return mazeManager.getGrid();
    }

}
