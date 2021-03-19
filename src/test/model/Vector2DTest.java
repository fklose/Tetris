package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void equals() {
        //TODO: IMPLEMENT TESTS
    }

    @Test
    void testHashCode() {
        // TODO: IMPLEMENT TESTS
    }
}