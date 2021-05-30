package model.tetromino;

import model.game.Vector2D;

import java.awt.*;

public class RightL extends NewTetromino {

    RightL() {
        super("RightL", Color.BLUE,
                new Vector2D(0, 0),
                new Vector2D(0, 1),
                new Vector2D(1, 1),
                new Vector2D(0, -1));
    }
}
