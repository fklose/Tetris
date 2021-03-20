package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;
import java.security.Key;
import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

// TODO: TEST CASES WHERE TETROMINO CAN'T MOVE OR ROTATE
class TetrisGameTest {

    TetrisGame game0;
    TetrisGame game1;

    RotationMatrix2D CCW = RotationMatrix2D.COUNTERCLOCKWISE;
    RotationMatrix2D CW = RotationMatrix2D.CLOCKWISE;

    @BeforeEach
    void setup() {
        game0 = new TetrisGame();
        game1 = new TetrisGame();
    }

    @Test
    void testInitialization() {
        HashSet<Block> emptyBoard = new HashSet<>(20 * 10);

        assertEquals(0, game0.getScore());
        assertTrue(game0.getGameStatus());
        assertEquals(emptyBoard, game0.getBoard());
        assertNotNull(game0.getCurrentTetro());
    }

    @Test
    void update() {
        HashSet<Block> emptyBoard = new HashSet<>(20 * 10);

        // continually ticking with no user input ends the game, as blocks stack up too high
        while (game0.getGameStatus()) {
            game0.update();
            assertNotNull(game0.getCurrentTetro());
            assertEquals(0, game0.getScore());
        }
        assertFalse(game0.getGameStatus());
        assertNotEquals(emptyBoard, game0.getBoard());

        while (game1.getGameStatus()) {
            game1.update();
            assertNotNull(game1.getCurrentTetro());
            assertEquals(0, game1.getScore());
        }
        assertFalse(game1.getGameStatus());
        assertNotEquals(emptyBoard, game1.getBoard());
    }

    @Test
    void keyPressedUP() {
        int oldX = game0.getCurrentTetro().getCentre().getX();
        int oldY = game0.getCurrentTetro().getCentre().getY();
        Vector2D oldCenter = new Vector2D(oldX, oldY);

        Vector2D newCenter = oldCenter.addVectorGetNewVector(Direction.NULL.getVector());

        ArrayList<Vector2D> oldTemplate = new ArrayList<>();
        for (Vector2D pos : game0.getCurrentTetro().getTemplate()) {
            oldTemplate.add(pos.addVectorGetNewVector(Direction.NULL.getVector()));
        }

        ArrayList<Vector2D> newTemplate = new ArrayList<>();
        for (Vector2D pos : oldTemplate) {
            newTemplate.add(CCW.matrixVectorProductGetNewVec(pos));
        }

        game0.keyPressed(KeyEvent.VK_UP);
        assertEquals(oldCenter, game0.getCurrentTetro().getCentre());
        assertEquals(newTemplate, game0.getCurrentTetro().getTemplate());
    }

    @Test
    void keyPressedLEFT() {
        int oldX = game0.getCurrentTetro().getCentre().getX();
        int oldY = game0.getCurrentTetro().getCentre().getY();
        Vector2D oldCenter = new Vector2D(oldX, oldY);

        Vector2D newCenter = oldCenter.addVectorGetNewVector(Direction.LEFT.getVector());

        ArrayList<Vector2D> oldTemplate = new ArrayList<>();
        for (Vector2D pos : game0.getCurrentTetro().getTemplate()) {
            oldTemplate.add(pos.addVectorGetNewVector(Direction.NULL.getVector()));
        }

        ArrayList<Vector2D> newTemplate = new ArrayList<>();
        for (Vector2D pos : oldTemplate) {
            newTemplate.add(CCW.matrixVectorProductGetNewVec(pos));
        }

        game0.keyPressed(KeyEvent.VK_LEFT);
        assertEquals(newCenter, game0.getCurrentTetro().getCentre());
        assertEquals(oldTemplate, game0.getCurrentTetro().getTemplate());
    }

    @Test
    void keyPressedKPLEFT() {
        int oldX = game0.getCurrentTetro().getCentre().getX();
        int oldY = game0.getCurrentTetro().getCentre().getY();
        Vector2D oldCenter = new Vector2D(oldX, oldY);

        Vector2D newCenter = oldCenter.addVectorGetNewVector(Direction.LEFT.getVector());

        ArrayList<Vector2D> oldTemplate = new ArrayList<>();
        for (Vector2D pos : game0.getCurrentTetro().getTemplate()) {
            oldTemplate.add(pos.addVectorGetNewVector(Direction.NULL.getVector()));
        }

        ArrayList<Vector2D> newTemplate = new ArrayList<>();
        for (Vector2D pos : oldTemplate) {
            newTemplate.add(CCW.matrixVectorProductGetNewVec(pos));
        }

        game0.keyPressed(KeyEvent.VK_KP_LEFT);
        assertEquals(newCenter, game0.getCurrentTetro().getCentre());
        assertEquals(oldTemplate, game0.getCurrentTetro().getTemplate());
    }

    @Test
    void keyPressedRIGHT() {
        int oldX = game0.getCurrentTetro().getCentre().getX();
        int oldY = game0.getCurrentTetro().getCentre().getY();
        Vector2D oldCenter = new Vector2D(oldX, oldY);

        Vector2D newCenter = oldCenter.addVectorGetNewVector(Direction.RIGHT.getVector());

        ArrayList<Vector2D> oldTemplate = new ArrayList<>();
        for (Vector2D pos : game0.getCurrentTetro().getTemplate()) {
            oldTemplate.add(pos.addVectorGetNewVector(Direction.NULL.getVector()));
        }

        ArrayList<Vector2D> newTemplate = new ArrayList<>();
        for (Vector2D pos : oldTemplate) {
            newTemplate.add(CCW.matrixVectorProductGetNewVec(pos));
        }

        game0.keyPressed(KeyEvent.VK_RIGHT);
        assertEquals(newCenter, game0.getCurrentTetro().getCentre());
        assertEquals(oldTemplate, game0.getCurrentTetro().getTemplate());
    }

    @Test
    void keyPressedKPRIGHT() {
        int oldX = game0.getCurrentTetro().getCentre().getX();
        int oldY = game0.getCurrentTetro().getCentre().getY();
        Vector2D oldCenter = new Vector2D(oldX, oldY);

        Vector2D newCenter = oldCenter.addVectorGetNewVector(Direction.RIGHT.getVector());

        ArrayList<Vector2D> oldTemplate = new ArrayList<>();
        for (Vector2D pos : game0.getCurrentTetro().getTemplate()) {
            oldTemplate.add(pos.addVectorGetNewVector(Direction.NULL.getVector()));
        }

        ArrayList<Vector2D> newTemplate = new ArrayList<>();
        for (Vector2D pos : oldTemplate) {
            newTemplate.add(CCW.matrixVectorProductGetNewVec(pos));
        }

        game0.keyPressed(KeyEvent.VK_KP_RIGHT);
        assertEquals(newCenter, game0.getCurrentTetro().getCentre());
        assertEquals(oldTemplate, game0.getCurrentTetro().getTemplate());
    }

    @Test
    void keyPressedDOWN() {
        int oldX = game0.getCurrentTetro().getCentre().getX();
        int oldY = game0.getCurrentTetro().getCentre().getY();
        Vector2D oldCenter = new Vector2D(oldX, oldY);

        Vector2D newCenter = oldCenter.addVectorGetNewVector(Direction.DOWN.getVector());

        ArrayList<Vector2D> oldTemplate = new ArrayList<>();
        for (Vector2D pos : game0.getCurrentTetro().getTemplate()) {
            oldTemplate.add(pos.addVectorGetNewVector(Direction.NULL.getVector()));
        }

        ArrayList<Vector2D> newTemplate = new ArrayList<>();
        for (Vector2D pos : oldTemplate) {
            newTemplate.add(CCW.matrixVectorProductGetNewVec(pos));
        }

        game0.keyPressed(KeyEvent.VK_DOWN);
        assertEquals(newCenter, game0.getCurrentTetro().getCentre());
        assertEquals(oldTemplate, game0.getCurrentTetro().getTemplate());
    }

    @Test
    void keyPressedKPDOWN() {
        int oldX = game0.getCurrentTetro().getCentre().getX();
        int oldY = game0.getCurrentTetro().getCentre().getY();
        Vector2D oldCenter = new Vector2D(oldX, oldY);

        Vector2D newCenter = oldCenter.addVectorGetNewVector(Direction.DOWN.getVector());

        ArrayList<Vector2D> oldTemplate = new ArrayList<>();
        for (Vector2D pos : game0.getCurrentTetro().getTemplate()) {
            oldTemplate.add(pos.addVectorGetNewVector(Direction.NULL.getVector()));
        }

        ArrayList<Vector2D> newTemplate = new ArrayList<>();
        for (Vector2D pos : oldTemplate) {
            newTemplate.add(CCW.matrixVectorProductGetNewVec(pos));
        }

        game0.keyPressed(KeyEvent.VK_KP_DOWN);
        assertEquals(newCenter, game0.getCurrentTetro().getCentre());
        assertEquals(oldTemplate, game0.getCurrentTetro().getTemplate());
    }

    @Test
    void keyPressedSPACE() {
        game0.keyPressed(KeyEvent.VK_SPACE);
        assertEquals(4, game0.getBoard().size());
        assertEquals(0, game0.getScore());
        assertTrue(game0.getGameStatus());
    }
}