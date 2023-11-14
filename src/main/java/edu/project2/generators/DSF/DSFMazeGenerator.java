package edu.project2.generators.DSF;

import edu.project2.BadFieldMazeException;
import edu.project2.game.Cell;
import edu.project2.game.Maze;
import edu.project2.generators.Generator;

public class DSFMazeGenerator implements Generator {

    public Maze generateMaze(int height, int width) throws BadFieldMazeException {
        int heightMaze = validateSizeMaze(height);
        int widthMaze = validateSizeMaze(width);
        Cell[][] grid = new DSFGenerator(heightMaze, widthMaze).generateDSFGrid();
        return new Maze(heightMaze, widthMaze, grid);
    }

}
