package model;

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
    void addVector() {
        Vector2D result1 = new Vector2D(0, 0);
        Vector2D result2 = new Vector2D(2, 0);
        Vector2D result3 = new Vector2D(-6, 9);
        Vector2D result4 = new Vector2D(-5, 10);

        vectorEquals(result1, vec1.addVector(vec1));
        vectorEquals(result2, vec2.addVector(vec3));
        vectorEquals(result2, vec3.addVector(vec2));
        vectorEquals(result3, vec4.addVector(vec1));
        vectorEquals(result4, vec2.addVector(vec4));
    }

    private void vectorEquals(Vector2D vecA, Vector2D vecB) {
        assertEquals(vecA.getComponentX(), vecB.getComponentX(), "X component not equal");
        assertEquals(vecA.getComponentY(), vecB.getComponentY(), "Y component not equal");
    }
}