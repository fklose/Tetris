package model.tetromino;

import model.game.Vector2D;

import java.awt.*;

public class TShape extends NewTetromino {

    TShape() {
        super("TShape", Color.MAGENTA,
                new Vector2D(0, 0),
                new Vector2D(-1, 0),
                new Vector2D(1, 0),
                new Vector2D(0, -1));
    }
}
