package edu.project2.generators;

import edu.project2.BadFieldMazeException;
import edu.project2.game.Cell;
import edu.project2.game.Maze;

public class DFSMazeGenerator implements Generator {

    public Maze generateMaze(int height, int width) throws BadFieldMazeException {
        height = validateHeightMaze(height);
        width = validateWidthMaze(width);
        Cell[][] grid = new DFSUtil(height, width).generateDFSGrid();
        return new Maze(height, width, grid);
    }

}
