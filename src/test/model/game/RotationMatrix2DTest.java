package model.game;

import model.game.RotationMatrix2D;
import model.game.Vector2D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RotationMatrix2DTest {

    private static final RotationMatrix2D CCW = RotationMatrix2D.COUNTERCLOCKWISE;
    private static final RotationMatrix2D CW = RotationMatrix2D.CLOCKWISE;
    private Vector2D vec1;
    private Vector2D vec2;
    private Vector2D vec3;


    @BeforeEach
    void setUp() {
        vec1 = new Vector2D(0, 0);
        vec2 = new Vector2D(1, 0);
        vec3 = new Vector2D(1, -1);
    }

    @Test
    void matrixVectorProductGetNewVec() {
        assertEquals(new Vector2D(0, 0), CCW.matrixVectorProductGetNewVec(vec1));
        assertEquals(new Vector2D(0, 0), vec1);

        assertEquals(new Vector2D(0, 1), CCW.matrixVectorProductGetNewVec(vec2));
        assertEquals(new Vector2D(1, 0), vec2);

        assertEquals(new Vector2D(1, 1), CCW.matrixVectorProductGetNewVec(vec3));
        assertEquals(new Vector2D(1, -1), vec3);
    }

    @Test
    void matrixVectorProductInPlace() {
        CW.matrixVectorProductInPlace(vec1);
        assertEquals(new Vector2D(0, 0), vec1);

        CW.matrixVectorProductInPlace(vec2);
        assertEquals(new Vector2D(0, -1), vec2);

        CW.matrixVectorProductInPlace(vec3);
        assertEquals(new Vector2D(-1, -1), vec3);
    }
}