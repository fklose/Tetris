package model;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class BlockTest {

    private final Block b1 = new Block(1, 0, Color.CYAN);
    private final Block b2 = new Block(10, 90, Color.BLACK);
    private final Block b3 = new Block(1, 0, Color.CYAN);

    @Test
    void getPosition() {
        assertEquals(new Vector2D(1, 0), b1.getPosition());
        assertEquals(1, b1.getX());
        assertEquals(0, b1.getY());
        assertEquals(new Vector2D(10, 90), b2.getPosition());
        assertEquals(10, b2.getX());
        assertEquals(90, b2.getY());
    }

    @Test
    void getColours() {
        assertEquals(Color.CYAN, b1.getColor());
        assertEquals(Color.BLACK, b2.getColor());
    }

    @Test
    void setPosition() {
        b1.setPosition(new Vector2D(10,10));
        assertEquals(b1.getPosition(), new Vector2D(10, 10));

        b2.setPosition(new Vector2D(1,-10));
        assertEquals(b2.getPosition(), new Vector2D(1, -10));
    }

    @Test
    void equals() {
        assertEquals(b1, b3);
        assertNotEquals(b1, b2);
    }
}