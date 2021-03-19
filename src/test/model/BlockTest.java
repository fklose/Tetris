package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class BlockTest {

    private Vector2D p1;
    private Vector2D p2;
    private Vector2D p3;

    private Block b1;
    private Block b2;
    private Block b3;

    @BeforeEach
    void setup() {
        p1 = new Vector2D(1,0);
        p2 = new Vector2D(10,90);
        p3 = new Vector2D(1,0);

        b1 = new Block(p1, Color.CYAN);
        b2 = new Block(p2, Color.BLACK);
        b3 = new Block(p3, Color.CYAN);
    }

    @Test
    void getPosition() {
        assertEquals(p1, b1.getPosition());
        assertEquals(1, b1.getX());
        assertEquals(0, b1.getY());
        assertEquals(p2, b2.getPosition());
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
    void equalsIfSame() {
        assertEquals(b1, b1);
        assertEquals(b2, b2);
    }

    @Test
    void equalsIfNull() {
        assertFalse(b1.equals(null) || b2.equals(null));
    }

    @Test
    void equalsIfClassIsDifferent() {
        assertFalse(b1.equals(p1) || p1.equals(b1));
    }

    @Test
    void equalsIfPositionIsDifferent() {
        Block b1CopyDiffPos = new Block(p2, Color.CYAN);
        Block b1CopySamePos = new Block(p1, Color.CYAN);

        assertNotEquals(b1, b1CopyDiffPos);
        assertEquals(b1, b1CopySamePos);
    }

    @Test
    void equalsIfColourIsDifferent() {
        Block b2CopyDiffColor = new Block(p2, Color.BLUE);
        Block b2CopySameColor = new Block(p2, Color.BLACK);

        assertEquals(b2, b2CopyDiffColor);
        assertEquals(b2, b2CopySameColor);
    }

    @Test
    void testHashCode() {
        Block b1CopySamePos = new Block(p1, Color.CYAN);
        Block b2CopySameColor = new Block(p2, Color.BLACK);
        Block b1CopyDiffPos = new Block(p2, Color.CYAN);
        Block b2CopyDiffColor = new Block(p2, Color.BLUE);

        assertEquals(b1.hashCode(), b1CopySamePos.hashCode());
        assertEquals(b2.hashCode(), b2CopySameColor.hashCode());
        assertNotEquals(b1.hashCode(), b1CopyDiffPos.hashCode());
        assertEquals(b2.hashCode(), b2CopyDiffColor.hashCode());
    }
}