package model.tetromino;

import model.game.Vector2D;

import java.awt.*;

public class LeftSkew extends NewTetromino {

    LeftSkew() {
        super("LeftSkew", Color.PINK,
                new Vector2D(0, 0),
                new Vector2D(0, 1),
                new Vector2D(1, 0),
                new Vector2D(1, -1));
    }
}
