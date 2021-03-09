package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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
        Direction down = Direction.DOWN;

        ArrayList<Vector2D> result1 = new ArrayList<>();
        for (Vector2D vec : tetromino1.getPositions()) {
            result1.add(vec.addVector(down.getVector()));
        }
        tetromino1.move(down);
        checkListEquals(result1, tetromino1.getPositions());

        ArrayList<Vector2D> result2 = new ArrayList<>();
        for (Vector2D vec : tetromino2.getPositions()) {
            result1.add(vec.addVector(down.getVector()));
        }
        tetromino2.move(down);
        checkListEquals(result2, tetromino2.getPositions());
    }

    @Test
    void moveRight() {
        Direction right = Direction.RIGHT;

        ArrayList<Vector2D> result1 = new ArrayList<>();
        for (Vector2D vec : tetromino3.getPositions()) {
            result1.add(vec.addVector(right.getVector()));
        }
        tetromino3.move(right);
        checkListEquals(result1, tetromino3.getPositions());

        ArrayList<Vector2D> result2 = new ArrayList<>();
        for (Vector2D vec : tetromino4.getPositions()) {
            result1.add(vec.addVector(right.getVector()));
        }
        tetromino4.move(right);
        checkListEquals(result2, tetromino4.getPositions());
    }

    @Test
    void moveLeft() {
        Direction left = Direction.LEFT;

        ArrayList<Vector2D> result1 = new ArrayList<>();
        for (Vector2D vec : tetromino5.getPositions()) {
            result1.add(vec.addVector(left.getVector()));
        }
        tetromino5.move(left);
        checkListEquals(result1, tetromino5.getPositions());

        ArrayList<Vector2D> result2 = new ArrayList<>();
        for (Vector2D vec : tetromino6.getPositions()) {
            result1.add(vec.addVector(left.getVector()));
        }
        tetromino6.move(left);
        checkListEquals(result2, tetromino6.getPositions());
    }

    @Test
    void rotate() {
        RotationMatrix2x2 matrix = new RotationMatrix2x2();

        ArrayList<Vector2D> result1 = new ArrayList<>();
        for (Vector2D vec : tetromino1.getPositions()) {
            result1.add(matrix.matrixVectorProduct(vec));
        }
        tetromino1.rotate();
        checkListEquals(result1, tetromino1.getPositions());

        ArrayList<Vector2D> result2 = new ArrayList<>();
        for (Vector2D vec : tetromino7.getPositions()) {
            result1.add(matrix.matrixVectorProduct(vec));
        }
        tetromino7.rotate();
        checkListEquals(result2, tetromino7.getPositions());
    }

    @Test
    void getBlocks() {
        Block lb1 = new Block(0,1, Color.CYAN);
        Block lb2 = new Block(0,0, Color.CYAN);
        Block lb3 = new Block(0,-1, Color.CYAN);
        Block lb4 = new Block(0,-2, Color.CYAN);

        ArrayList<Block> lineBlocks = new ArrayList<>();
        lineBlocks.add(lb1);
        lineBlocks.add(lb2);
        lineBlocks.add(lb3);
        lineBlocks.add(lb4);

        checkListEquals(lineBlocks, Tetromino.shapeLine.getBlocks());

        Block rsb1 = new Block(0,0, Color.GREEN);
        Block rsb2 = new Block(0,1, Color.GREEN);
        Block rsb3 = new Block(-1,0, Color.GREEN);
        Block rsb4 = new Block(-1,-1, Color.GREEN);

        ArrayList<Block> rightSkewBlocks = new ArrayList<>();
        rightSkewBlocks.add(rsb1);
        rightSkewBlocks.add(rsb2);
        rightSkewBlocks.add(rsb3);
        rightSkewBlocks.add(rsb4);

        checkListEquals(rightSkewBlocks, Tetromino.shapeRightSkew.getBlocks());
    }

    private void checkListEquals(List listA, List listB) {
        for (int i = 0; i < listA.size(); i++) {
            assertEquals(listA.get(i), listB.get(i));
        }
    }
}