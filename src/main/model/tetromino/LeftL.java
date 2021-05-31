package model.tetromino;

import model.game.Vector2D;

import java.awt.*;

public class LeftL extends Tetromino {

    public LeftL() {
        super("LeftL", Color.ORANGE,
                new Vector2D(0, 0),
                new Vector2D(0, 1),
                new Vector2D(-1, 1),
                new Vector2D(0, -1));
    }

}
