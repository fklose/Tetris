package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class TetrisGameTest {

    private static final int WIDTH = 10;
    private static final int HEIGHT = 20;

    TetrisGame game0;

    RotationMatrix2D CCW = RotationMatrix2D.COUNTERCLOCKWISE;

    @BeforeEach
    void setup() {
        game0 = new TetrisGame();
    }

    @Test
    void testInitialization() {
        HashSet<Block> emptyBoard = new HashSet<>(20 * 10);

        assertEquals(0, game0.getScore());
        assertTrue(game0.getGameActive());
        assertEquals(emptyBoard, game0.getBoard());
        assertNotNull(game0.getCurrentTetro());
    }

    @Test
    void update() {
        HashSet<Block> emptyBoard = new HashSet<>(WIDTH * HEIGHT);

        // continually ticking with no user input ends the game, as blocks stack up too high
        while (game0.getGameActive()) {
            game0.update();
            assertNotNull(game0.getCurrentTetro());
        }
        assertFalse(game0.getGameActive());
        assertNotEquals(emptyBoard, game0.getBoard());
    }

    @Test
    void keyPressedUP() {
        int oldX = game0.getCurrentTetro().getCentre().getX();
        int oldY = game0.getCurrentTetro().getCentre().getY();
        Vector2D oldCenter = new Vector2D(oldX, oldY);

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

        game0.keyPressed(KeyEvent.VK_KP_LEFT);
        assertEquals(newCenter, game0.getCurrentTetro().getCentre());
        assertEquals(oldTemplate, game0.getCurrentTetro().getTemplate());
    }

    @Test
    void holdKeyKPLEFT() {
        ArrayList<Vector2D> oldTemplate = new ArrayList<>();
        for (Vector2D pos : game0.getCurrentTetro().getTemplate()) {
            oldTemplate.add(pos.addVectorGetNewVector(Direction.NULL.getVector()));
        }

        for (int i = 0; i < 40; i++) {
            game0.keyPressed(KeyEvent.VK_KP_LEFT);
        }

        for (Vector2D v : game0.getCurrentTetro().getPositions()) {
            assertTrue(v.getX() < WIDTH);
            assertTrue(v.getX() >= 0);
            assertTrue(v.getY() < HEIGHT);
        }
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

        game0.keyPressed(KeyEvent.VK_KP_RIGHT);
        assertEquals(newCenter, game0.getCurrentTetro().getCentre());
        assertEquals(oldTemplate, game0.getCurrentTetro().getTemplate());
    }

    @Test
    void holdKeyKPRIGHT() {
        ArrayList<Vector2D> oldTemplate = new ArrayList<>();
        for (Vector2D pos : game0.getCurrentTetro().getTemplate()) {
            oldTemplate.add(pos.addVectorGetNewVector(Direction.NULL.getVector()));
        }

        for (int i = 0; i < 40; i++) {
            game0.keyPressed(KeyEvent.VK_KP_RIGHT);
        }

        for (Vector2D v : game0.getCurrentTetro().getPositions()) {
            assertTrue(v.getX() < WIDTH);
            assertTrue(v.getX() >= 0);
            assertTrue(v.getY() < HEIGHT);
        }
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

        game0.keyPressed(KeyEvent.VK_KP_DOWN);
        assertEquals(newCenter, game0.getCurrentTetro().getCentre());
        assertEquals(oldTemplate, game0.getCurrentTetro().getTemplate());
    }

    @Test
    void holdKeyKPDOWN() {
        ArrayList<Vector2D> oldTemplate = new ArrayList<>();
        for (Vector2D pos : game0.getCurrentTetro().getTemplate()) {
            oldTemplate.add(pos.addVectorGetNewVector(Direction.NULL.getVector()));
        }

        for (int i = 0; i < 40; i++) {
            game0.keyPressed(KeyEvent.VK_KP_DOWN);
        }

        for (Vector2D v : game0.getCurrentTetro().getPositions()) {
            assertTrue(v.getX() < WIDTH);
            assertTrue(v.getX() >= 0);
            assertTrue(v.getY() < HEIGHT);
        }
        assertEquals(oldTemplate, game0.getCurrentTetro().getTemplate());
    }

    @Test
    void keyPressedSPACE() {
        game0.keyPressed(KeyEvent.VK_SPACE);
        assertEquals(4, game0.getBoard().size());
        assertEquals(0, game0.getScore());
        assertTrue(game0.getGameActive());
    }

    @Test
    void willItBeInsideBoard() {
        for (int i = 0; i < 10; i++) {
            game0.keyPressed(KeyEvent.VK_RIGHT);
        }
        for (Vector2D pos : game0.getCurrentTetro().getPositions()) {
            assertTrue(pos.getX() < WIDTH, "Position our of bounds on right side!");
        }
        game0.keyPressed(KeyEvent.VK_SPACE);

        for (int i = 0; i < 10; i++) {
            game0.keyPressed(KeyEvent.VK_LEFT);
        }
        for (Vector2D pos : game0.getCurrentTetro().getPositions()) {
            assertTrue(pos.getX() >= 0, "Position our of bounds on left side!");
        }
        game0.keyPressed(KeyEvent.VK_SPACE);

        for (Block b : game0.getBoard()) {
            assertTrue(b.getX() < WIDTH, "Block out of bounds on right side!");
            assertTrue(b.getX() >= 0, "Block out of bounds on left side!");
            assertTrue(b.getY() < HEIGHT, "Block out of bounds at the bottom!");
        }
    }

    @Test
    void willItBeInsideBoardAfterMovingRightAndRotating() {
        for (int i = 0; i < 10; i++) {
            game0.keyPressed(KeyEvent.VK_RIGHT);
        }
        game0.keyPressed(KeyEvent.VK_UP);
        for (Vector2D pos : game0.getCurrentTetro().getPositions()) {
            assertTrue(pos.getX() < WIDTH, "Position our of bounds on right side!");
        }
        game0.keyPressed(KeyEvent.VK_SPACE);

        for (Block b : game0.getBoard()) {
            assertTrue(b.getX() < WIDTH, "Block out of bounds on right side!");
            assertTrue(b.getX() >= 0, "Block out of bounds on left side!");
            assertTrue(b.getY() < HEIGHT, "Block out of bounds at the bottom!");
        }
    }

    @Test
    void willItBeInsideBoardAfterMovingLeftAndRotating() {
        for (int i = 0; i < 10; i++) {
            game0.keyPressed(KeyEvent.VK_LEFT);
        }
        game0.keyPressed(KeyEvent.VK_UP);
        for (Vector2D pos : game0.getCurrentTetro().getPositions()) {
            assertTrue(pos.getX() >= 0, "Position our of bounds on left side!");
        }
        game0.keyPressed(KeyEvent.VK_SPACE);

        for (Block b : game0.getBoard()) {
            assertTrue(b.getX() < WIDTH, "Block out of bounds on right side!");
            assertTrue(b.getX() >= 0, "Block out of bounds on left side!");
            assertTrue(b.getY() < HEIGHT, "Block out of bounds at the bottom!");
        }
    }

    @Test
    void willItBeInsideBoardAfterMovingDownAndRotating() {
        for (int i = 0; i < 20; i++) {
            game0.keyPressed(KeyEvent.VK_DOWN);
        }
        game0.keyPressed(KeyEvent.VK_UP);
        for (Vector2D pos : game0.getCurrentTetro().getPositions()) {
            assertTrue(pos.getX() >= 0, "Position our of bounds on left side!");
        }
        game0.keyPressed(KeyEvent.VK_SPACE);

        for (Block b : game0.getBoard()) {
            assertTrue(b.getX() < WIDTH, "Block out of bounds on right side!");
            assertTrue(b.getX() >= 0, "Block out of bounds on left side!");
            assertTrue(b.getY() < HEIGHT, "Block out of bounds at the bottom!");
        }
    }

    @Test
    void resetGame() {
        while (game0.getGameActive()) {
            game0.update();
        }
        assertFalse(game0.getGameActive());
        assertNotEquals(0, game0.getScore());
        assertNotEquals(0, game0.getBoard().size());

        game0.resetGame();
        assertTrue(game0.getGameActive());
        assertEquals(0, game0.getScore());
        assertEquals(0, game0.getBoard().size());
    }

    @Test
    void getQueue() {
        assertEquals(TetrominoQueue.class, game0.getQueue().getClass());
    }
}