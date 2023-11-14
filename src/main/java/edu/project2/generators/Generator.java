package edu.project2.generators;

import edu.project2.BadFieldMazeException;
import edu.project2.game.Maze;

public interface Generator {

    Maze generateMaze(int height, int width) throws BadFieldMazeException;

    default int validateSizeMaze(int side) throws BadFieldMazeException {
        if (side <= 0) {
            throw new BadFieldMazeException();
        }
        if (side % 2 == 0) {
            return side + 1;
        }
        return side + 2;
    }
}
