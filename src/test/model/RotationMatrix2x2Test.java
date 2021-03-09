package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RotationMatrix2x2Test {

    private RotationMatrix2x2 matrix = new RotationMatrix2x2();
    private Vector2D vec1 = new Vector2D(0, 0);
    private Vector2D vec2 = new Vector2D(1, 0);
    private Vector2D vec3 = new Vector2D(1, -1);

    @Test
    void matrixVectorProduct() {
        Vector2D result1 = new Vector2D(0, 0);
        Vector2D result2 = new Vector2D(0, -1);
        Vector2D result3 = new Vector2D(-1, -1);

        assertEquals(result1, matrix.matrixVectorProduct(vec1));
        assertEquals(result2, matrix.matrixVectorProduct(vec2));
        assertEquals(result3, matrix.matrixVectorProduct(vec3));
    }
}