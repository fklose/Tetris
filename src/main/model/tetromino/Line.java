package model.tetromino;

import model.game.Vector2D;

import java.awt.*;

public class Line extends NewTetromino {

    public Line() {
        super("Line", Color.CYAN,
                new Vector2D(0, 1),
                new Vector2D(0, 0),
                new Vector2D(0, -1),
                new Vector2D(0, -2));
    }
}
