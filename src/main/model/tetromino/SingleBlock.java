package model.tetromino;

import model.game.Vector2D;

import java.awt.*;

public class SingleBlock extends NewTetromino {

    SingleBlock() {
        super("null", Color.DARK_GRAY,
                new Vector2D(0, 0));
    }
}
