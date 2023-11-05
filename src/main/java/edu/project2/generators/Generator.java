package edu.project2.generators;

import edu.project2.BadFieldMazeException;
import edu.project2.game.Maze;

public interface Generator {

    Maze generateMaze(int height, int width) throws BadFieldMazeException;

    default int validateHeightMaze(int height) throws BadFieldMazeException {
        if (height <= 0) {
            throw new BadFieldMazeException();
        }
        if (height % 2 == 0) {
            height++;
        }
        return height;
    }

    default int validateWidthMaze(int width) throws BadFieldMazeException {
        if (width <= 0) {
            throw new BadFieldMazeException();
        }
        if (width % 2 == 0) {
            width++;
        }
        return width;
    }
}
