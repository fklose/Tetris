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

        vectorEquals(result1, matrix.matrixVectorProduct(vec1));
        vectorEquals(result2, matrix.matrixVectorProduct(vec2));
        vectorEquals(result3, matrix.matrixVectorProduct(vec3));
    }

    private void vectorEquals(Vector2D vecA, Vector2D vecB) {
        assertEquals(vecA.getComponentX(), vecB.getComponentX(), "X component not equal");
        assertEquals(vecA.getComponentY(), vecB.getComponentY(), "Y component not equal");
    }
}