package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class TetrominoTest {

    Tetromino tetromino1;
    Tetromino tetromino2;
    Tetromino tetromino3;
    Tetromino tetromino4;
    Tetromino tetromino5;
    Tetromino tetromino6;
    Tetromino tetromino7;

    @BeforeEach
    void setUp() {
        tetromino1 = Tetromino.shapeLeftL;
        tetromino2 = Tetromino.shapeLeftSkew;
        tetromino3 = Tetromino.shapeLine;
        tetromino4 = Tetromino.shapeRightL;
        tetromino5 = Tetromino.shapeRightSkew;
        tetromino6 = Tetromino.shapeSquare;
        tetromino7 = Tetromino.shapeT;
    }

    @Test
    void getName() {
        assertEquals("Left L", tetromino1.getName());
        assertEquals("Right Skew", tetromino5.getName());
        assertEquals("T Shape", tetromino7.getName());
    }

    @Test
    void moveDown() {
        Vector2D down = new Vector2D(0, -1);

        ArrayList<Vector2D> result1 = new ArrayList<Vector2D>();
        for (Vector2D vec: tetromino1.getPositions()) {
            result1.add(vec.addVector(down));
        }
        tetromino1.move(down);
        checkVectorListEquals(result1, tetromino1.getPositions());

        ArrayList<Vector2D> result2 = new ArrayList<Vector2D>();
        for (Vector2D vec: tetromino2.getPositions()) {
            result1.add(vec.addVector(down));
        }
        tetromino2.move(down);
        checkVectorListEquals(result2, tetromino2.getPositions());
    }

    @Test
    void moveRight() {
        Vector2D right = new Vector2D(1, 0);

        ArrayList<Vector2D> result1 = new ArrayList<Vector2D>();
        for (Vector2D vec: tetromino3.getPositions()) {
            result1.add(vec.addVector(right));
        }
        tetromino3.move(right);
        checkVectorListEquals(result1, tetromino3.getPositions());

        ArrayList<Vector2D> result2 = new ArrayList<Vector2D>();
        for (Vector2D vec: tetromino4.getPositions()) {
            result1.add(vec.addVector(right));
        }
        tetromino4.move(right);
        checkVectorListEquals(result2, tetromino4.getPositions());
    }

    @Test
    void moveLeft() {
        Vector2D left = new Vector2D(-1, 0);

        ArrayList<Vector2D> result1 = new ArrayList<Vector2D>();
        for (Vector2D vec: tetromino5.getPositions()) {
            result1.add(vec.addVector(left));
        }
        tetromino5.move(left);
        checkVectorListEquals(result1, tetromino5.getPositions());

        ArrayList<Vector2D> result2 = new ArrayList<Vector2D>();
        for (Vector2D vec: tetromino6.getPositions()) {
            result1.add(vec.addVector(left));
        }
        tetromino6.move(left);
        checkVectorListEquals(result2, tetromino6.getPositions());
    }

    @Test
    void rotate() {
        RotationMatrix2x2 matrix = new RotationMatrix2x2();

        ArrayList<Vector2D> result1 = new ArrayList<Vector2D>();
        for (Vector2D vec: tetromino1.getPositions()) {
            result1.add(matrix.matrixVectorProduct(vec));
        }
        tetromino1.rotate();
        checkVectorListEquals(result1, tetromino1.getPositions());

        ArrayList<Vector2D> result2 = new ArrayList<Vector2D>();
        for (Vector2D vec: tetromino7.getPositions()) {
            result1.add(matrix.matrixVectorProduct(vec));
        }
        tetromino7.rotate();
        checkVectorListEquals(result2, tetromino7.getPositions());
    }

    private void checkVectorListEquals(ArrayList<Vector2D> listA, ArrayList<Vector2D> listB) {
        for (int i = 0 ; i < listA.size() ; i++) {
            vectorEquals(listA.get(i), listB.get(i));
        }
    }

    private void vectorEquals(Vector2D vecA, Vector2D vecB) {
        assertEquals(vecA.getComponentX(), vecB.getComponentX(), "X component not equal");
        assertEquals(vecA.getComponentY(), vecB.getComponentY(), "Y component not equal");
    }

}