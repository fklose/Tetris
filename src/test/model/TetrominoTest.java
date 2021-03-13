package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TetrominoTest {

    Tetromino tetromino1;
    Tetromino tetromino2;
    Tetromino tetromino3;
    Tetromino tetromino4;
    Tetromino tetromino5;
    Tetromino tetromino6;
    Tetromino tetromino7;

    RotationMatrix2x2 CCW = RotationMatrix2x2.COUNTERCLOCKWISE;
    RotationMatrix2x2 CW = RotationMatrix2x2.CLOCKWISE;

    @BeforeEach
    void setUp() {
        tetromino1 = Tetromino.shapeLeftL;
        tetromino2 = Tetromino.shapeLeftSkew;
        tetromino3 = Tetromino.shapeLine;
        tetromino4 = Tetromino.shapeRightL;
        tetromino5 = Tetromino.shapeRightSkew;
        tetromino6 = Tetromino.shapeSquare;
        tetromino7 = Tetromino.shapeT;

        tetromino1.setCentre(new Vector2D(0,0));
        tetromino2.setCentre(new Vector2D(0,0));
        tetromino3.setCentre(new Vector2D(0,0));
        tetromino4.setCentre(new Vector2D(0,0));
        tetromino5.setCentre(new Vector2D(0,0));
        tetromino6.setCentre(new Vector2D(0,0));
        tetromino7.setCentre(new Vector2D(0,0));

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

        Vector2D zero = new Vector2D(0,0);

        tetromino1.move(down);
        zero.addVectorInPlace(down.getVector());
        assertEquals(zero, tetromino1.getCentre());

        tetromino1.move(down);
        zero.addVectorInPlace(down.getVector());
        assertEquals(zero, tetromino1.getCentre());

        Vector2D centre = new Vector2D(10,10);
        tetromino2.setCentre(centre);
        assertEquals(centre, tetromino2.getCentre());

        tetromino2.move(down);
        centre.addVectorInPlace(down.getVector());
        assertEquals(centre, tetromino2.getCentre());

        tetromino2.move(down);
        centre.addVectorInPlace(down.getVector());
        assertEquals(centre, tetromino2.getCentre());
    }

    @Test
    void moveRight() {
        Direction right = Direction.RIGHT;

        Vector2D zero = new Vector2D(0,0);
        tetromino3.setCentre(zero);
        tetromino3.move(right);
        zero.addVectorInPlace(right.getVector());
        assertEquals(zero, tetromino3.getCentre());

        tetromino3.move(right);
        zero.addVectorInPlace(right.getVector());
        assertEquals(zero, tetromino3.getCentre());

        Vector2D centre = new Vector2D(-10,10);
        tetromino4.setCentre(centre);
        assertEquals(centre, tetromino4.getCentre());

        tetromino4.move(right);
        centre.addVectorInPlace(right.getVector());
        assertEquals(centre, tetromino4.getCentre());

        tetromino4.move(right);
        centre.addVectorInPlace(right.getVector());
        assertEquals(centre, tetromino4.getCentre());
    }

    @Test
    void moveLeft() {
        Direction left = Direction.LEFT;

        Vector2D zero = new Vector2D(0,0);

        tetromino5.move(left);
        zero.addVectorInPlace(left.getVector());
        assertEquals(zero, tetromino5.getCentre());

        tetromino5.move(left);
        zero.addVectorInPlace(left.getVector());
        assertEquals(zero, tetromino5.getCentre());

        Vector2D centre = new Vector2D(10,10);
        tetromino6.setCentre(centre);
        assertEquals(centre, tetromino6.getCentre());

        tetromino6.move(left);
        centre.addVectorInPlace(left.getVector());
        assertEquals(centre, tetromino6.getCentre());

        tetromino6.move(left);
        centre.addVectorInPlace(left.getVector());
        assertEquals(centre, tetromino6.getCentre());
    }

    @Test
    void rotateCCW() {
        checkCCWRotation(tetromino3);

        checkCCWRotation(tetromino2);
    }

    @Test
    void rotateCW() {
        checkCWRotation(tetromino5);

        checkCWRotation(tetromino1);
    }

    @Test
    void getBlocks() {
        Color resultColor3 = Color.CYAN;
        ArrayList<Block> resultBlocks3 = new ArrayList<>();
        Vector2D newCentre3 = new Vector2D(1,8);

        for (Vector2D v : tetromino3.getTemplate()) {
            resultBlocks3.add(new Block(v.addVectorGetNewVector(newCentre3), resultColor3));
        }

        tetromino3.setCentre(newCentre3);
        assertTrue(checkBlockListEquals(resultBlocks3, tetromino3.getBlocks()));

        Color resultColor1 = Color.ORANGE;
        ArrayList<Block> resultBlocks1 = new ArrayList<>();
        Vector2D newCentre1 = new Vector2D(16,-100);

        for (Vector2D v : tetromino1.getTemplate()) {
            resultBlocks1.add(new Block(v.addVectorGetNewVector(newCentre1), resultColor1));
        }

        tetromino1.setCentre(newCentre1);
        assertTrue(checkBlockListEquals(resultBlocks1, tetromino1.getBlocks()));
    }

    @Test
    void getBlocksAfterMoving() {
        Color resultColor3 = Color.CYAN;
        ArrayList<Block> resultBlocks3 = new ArrayList<>();
        Vector2D newCentre3 = new Vector2D(1,8);

        for (Vector2D v : tetromino3.getTemplate()) {
            resultBlocks3.add(new Block(v.addVectorGetNewVector(newCentre3), resultColor3));
        }

        tetromino3.setCentre(newCentre3);
        assertTrue(checkBlockListEquals(resultBlocks3, tetromino3.getBlocks()));

        Color resultColor1 = Color.ORANGE;
        ArrayList<Block> resultBlocks1 = new ArrayList<>();
        Vector2D newCentre1 = new Vector2D(16,-100);

        for (Vector2D v : tetromino1.getTemplate()) {
            resultBlocks1.add(new Block(v.addVectorGetNewVector(newCentre1), resultColor1));
        }

        tetromino1.setCentre(newCentre1);
        assertTrue(checkBlockListEquals(resultBlocks1, tetromino1.getBlocks()));
    }

    @Test
    void getBlocksAfterRotation() {
        Color resultColor3 = Color.CYAN;
        ArrayList<Block> resultBlocks3 = new ArrayList<>();
        Vector2D newCentre3 = new Vector2D(1,8);

        for (Vector2D v : tetromino3.getTemplate()) {
            Vector2D v1 = CCW.matrixVectorProductGetNewVec(v);
            CCW.matrixVectorProductInPlace(v1);
            resultBlocks3.add(new Block(v1.addVectorGetNewVector(newCentre3), resultColor3));
        }

        tetromino3.setCentre(newCentre3);
        tetromino3.rotateCCW();
        tetromino3.rotateCCW();
        assertTrue(checkBlockListEquals(resultBlocks3, tetromino3.getBlocks()));

        Color resultColor1 = Color.ORANGE;
        ArrayList<Block> resultBlocks1 = new ArrayList<>();
        Vector2D newCentre1 = new Vector2D(16,-100);

        for (Vector2D v : tetromino1.getTemplate()) {
            resultBlocks1.add(new Block(v.addVectorGetNewVector(newCentre1), resultColor1));
        }

        tetromino1.setCentre(newCentre1);
        tetromino1.rotateCCW();
        tetromino1.rotateCW();
        assertTrue(checkBlockListEquals(resultBlocks1, tetromino1.getBlocks()));
    }

    @Test
    void testComplexChainOfMovesAndRotations() {
        Direction right = Direction.RIGHT;
        Direction left = Direction.LEFT;
        Direction down = Direction.DOWN;
        Vector2D centre = new Vector2D(10, -9);

        tetromino1.setCentre(centre);
        tetromino1.move(down);
        centre.addVectorInPlace(down.getVector());
        assertEquals(tetromino1.getCentre(), centre);

        tetromino1.move(down);
        centre.addVectorInPlace(down.getVector());
        assertEquals(tetromino1.getCentre(), centre);

        checkCCWRotation(tetromino1);
        assertEquals(tetromino1.getCentre(), centre);

        tetromino1.move(right);
        centre.addVectorInPlace(right.getVector());
        assertEquals(tetromino1.getCentre(), centre);

        tetromino1.move(right);
        centre.addVectorInPlace(right.getVector());
        assertEquals(tetromino1.getCentre(), centre);

        checkCWRotation(tetromino1);
        tetromino1.move(left);
        centre.addVectorInPlace(left.getVector());
        assertEquals(tetromino1.getCentre(), centre);

        checkCCWRotation(tetromino1);
    }

    private void checkVectorListEquals(ArrayList<Vector2D> listA, ArrayList<Vector2D> listB) {
        for (int i = 0; i < listA.size(); i++) {
            assertEquals(listA.get(i), listB.get(i));
        }
    }

    private boolean checkBlockListEquals(ArrayList<Block> listA, ArrayList<Block> listB) {
        for (Block b : listA) {
            if (!listB.contains(b)) {
                return false;
            }
        } return true;
    }

    // EFFECTS  : Rotates the given Tetromino CW and checks if the rotation was complete
    private void checkCWRotation(Tetromino t) {
        ArrayList<Vector2D> result = new ArrayList<>();
        for (Vector2D vec : t.getTemplate()) {
            result.add(CW.matrixVectorProductGetNewVec(vec));
        }
        t.rotateCW();
        checkVectorListEquals(result, t.getTemplate());
    }

    // EFFECTS  : Rotates the given Tetromino CCW and checks if the rotation was complete
    private void checkCCWRotation(Tetromino t) {
        ArrayList<Vector2D> result = new ArrayList<>();
        for (Vector2D vec : t.getTemplate()) {
            result.add(CCW.matrixVectorProductGetNewVec(vec));
        }
        t.rotateCCW();
        checkVectorListEquals(result, t.getTemplate());
    }
}