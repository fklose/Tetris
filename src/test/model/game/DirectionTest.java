package model.game;

import model.game.Direction;
import model.game.Vector2D;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DirectionTest {

    private final Direction down = Direction.DOWN;
    private final Direction left = Direction.LEFT;
    private final Direction right = Direction.RIGHT;

    @Test
    void getVector() {
        assertEquals(new Vector2D(0, 1), down.getVector());
        assertEquals(new Vector2D(-1, 0), left.getVector());
        assertEquals(new Vector2D(1, 0), right.getVector());
    }

    @Test
    void getOppositeVector() {
        assertEquals(left.getVector(), right.getOppositeVector());
        assertEquals(right.getVector(), left.getOppositeVector());
        assertEquals(new Vector2D(0,-1), down.getOppositeVector());
    }
}