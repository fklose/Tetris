package model;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class BlockTest {

    private Block b1 = new Block(1, 0, Color.CYAN);
    private Block b2 = new Block(10, 90, Color.BLACK);

    @Test
    void getPosition() {
        assertEquals(new Vector2D(1, 0), b1.getPosition());
        assertEquals(new Vector2D(10, 90), b2.getPosition());
    }

    @Test
    void getColours() {
        assertEquals(Color.CYAN, b1.getColor());
        assertEquals(Color.BLACK, b2.getColor());
    }
}