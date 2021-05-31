package model.tetromino;

import model.game.Vector2D;

import java.awt.*;

public class Square extends Tetromino {

    public Square() {
        super("Square", Color.YELLOW,
                new Vector2D(1, 0),
                new Vector2D(0, 0),
                new Vector2D(1, -1),
                new Vector2D(0, -1));
    }

    @Override
    public void rotateCCW() {}

    public void rotateCW() {}
}
