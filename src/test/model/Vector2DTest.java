package model;

import model.game.Tetromino;
import model.game.Vector2D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2DTest {

    private Vector2D vec1;
    private Vector2D vec2;
    private Vector2D vec3;
    private Vector2D vec4;

    @BeforeEach
    void setUp() {
        vec1 = new Vector2D(0, 0);
        vec2 = new Vector2D(1, 1);
        vec3 = new Vector2D(1, -1);
        vec4 = new Vector2D(-6, 9);
    }

    @Test
    void addVectorGetNewVector() {
        Vector2D result1 = new Vector2D(0, 0);
        Vector2D result2 = new Vector2D(2, 0);
        Vector2D result3 = new Vector2D(-6, 9);
        Vector2D result4 = new Vector2D(-5, 10);

        assertEquals(result1, vec1.addVectorGetNewVector(vec1));
        assertEquals(result2, vec2.addVectorGetNewVector(vec3));
        assertEquals(result2, vec3.addVectorGetNewVector(vec2));
        assertEquals(result3, vec4.addVectorGetNewVector(vec1));
        assertEquals(result4, vec2.addVectorGetNewVector(vec4));
    }

    @Test
    void addVectorInPlace() {
        vec1.addVectorInPlace(vec1);
        assertEquals(new Vector2D(0,0), vec1);

        vec2.addVectorInPlace(vec1);
        assertEquals(vec2, new Vector2D(1,1));

        vec3.addVectorInPlace(vec3);
        assertEquals(vec3, new Vector2D(2, -2));
        vec3.addVectorInPlace(vec3);
        assertEquals(vec3, new Vector2D(4, -4));

        vec1.addVectorInPlace(vec3);
        assertEquals(vec1, new Vector2D(4, -4));
        vec4.addVectorInPlace(vec1);
        assertEquals(vec4, new Vector2D(-2, 5));
    }

    @Test
    void setX() {
        vec1.setX(18);
        assertEquals(18, vec1.getX());
        assertEquals(0, vec1.getY());

        vec2.setX(-10);
        assertEquals(-10, vec2.getX());
        assertEquals(1, vec2.getY());
    }

    @Test
    void setY() {
        vec3.setY(88);
        assertEquals(1, vec3.getX());
        assertEquals(88, vec3.getY());

        vec4.setY(-20);
        assertEquals(-6, vec4.getX());
        assertEquals(-20, vec4.getY());
    }

    @Test
    void equals() {
        // tests first conditional
        assertEquals(vec1, vec1);
        assertEquals(vec3, vec3);

        // tests second conditional
        assertNotEquals(null, vec1);
        assertNotEquals(Tetromino.nullShape, vec2);
        assertFalse(vec1.equals(null) || vec2.equals(Tetromino.nullShape));

        // tests return clause
        Vector2D vec1Copy = new Vector2D(0, 0);
        Vector2D vec1CopyDiffX = new Vector2D(2, 0);
        Vector2D vec1CopyDiffY = new Vector2D(0, -10);

        assertEquals(vec1, vec1Copy);
        assertNotEquals(vec1, vec1CopyDiffX);
        assertNotEquals(vec1, vec1CopyDiffY);
        assertNotEquals(vec3, vec4);
        assertNotEquals(vec1, vec2);
    }

    @Test
    void testHashCode() {
        Vector2D vec1Copy = new Vector2D(0, 0);
        Vector2D vec1CopyDiffX = new Vector2D(2, 0);
        Vector2D vec1CopyDiffY = new Vector2D(0, -10);

        assertEquals(vec1.hashCode(), vec1Copy.hashCode());
        assertNotEquals(vec1.hashCode(), vec1CopyDiffX.hashCode());
        assertNotEquals(vec1.hashCode(), vec1CopyDiffY.hashCode());
        assertNotEquals(vec2.hashCode(), vec3.hashCode());
    }
}