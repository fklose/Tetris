package model.tetromino;

import model.game.Vector2D;

import java.awt.*;

public class RightSkew extends NewTetromino {

    RightSkew() {
        super("RightSkew", Color.GREEN,
                new Vector2D(0, 0),
                new Vector2D(0, 1),
                new Vector2D(-1, 0),
                new Vector2D(-1, -1));
    }
}
