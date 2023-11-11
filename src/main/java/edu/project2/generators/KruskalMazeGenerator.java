package edu.project2.generators;

import edu.project2.BadFieldMazeException;
import edu.project2.game.Cell;
import edu.project2.game.Maze;
import edu.project2.generators.DSF.DFSUtil;

public class KruskalMazeGenerator implements Generator {
    public Maze generateMaze(int height, int width) throws BadFieldMazeException {
        int heightMaze = validateSizeMaze(height);
        int widthMaze = validateSizeMaze(width);
        Cell[][] grid = new Kruskal(heightMaze, widthMaze).solver();
        return new Maze(heightMaze, widthMaze, grid);
    }
}
