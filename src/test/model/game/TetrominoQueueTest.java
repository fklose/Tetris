package model.game;

import model.tetromino.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class TetrominoQueueTest {

    private TetrominoQueue queue1;
    private TetrominoQueue queue2;
    private static final int SEED_ONE = 12345;
    private static final int SEED_TWO = 98765;

    private Tetromino shape0;
    private Tetromino shape1;
    private Tetromino shape2;
    private Tetromino shape3;
    private Tetromino shape4;
    private Tetromino shape5;
    private Tetromino shape6;
    private Tetromino shape7;

    private Random rng1;
    private Random rng2;


    @BeforeEach
    void setUp() {
        queue1 = new TetrominoQueue();
        queue2 = new TetrominoQueue();
        queue1.setSeed(SEED_ONE);
        queue2.setSeed(SEED_TWO);

        shape0 = new Line();
        shape1 = new Square();
        shape2 = new TShape();
        shape3 = new LeftSkew();
        shape4 = new RightSkew();
        shape5 = new LeftL();
        shape6 = new RightL();
        shape7 = new SingleBlock();

        rng1 = new Random();
        rng2 = new Random();
        rng1.setSeed(SEED_ONE);
        rng2.setSeed(SEED_TWO);
    }

    @Test
    void getNextTetromino() {
        TetrominoQueue queue3 = new TetrominoQueue();

        queue1.initializeQueue();
        queue2.initializeQueue();
        queue3.initializeQueue();

        /*
         * Tried getting a second random number generator with the same seed so I can generate the same blocks
         * independently. I could not get it to work so I am simply checking that the nullShape is not returned
         * over 20 trials.
         */

        for (int i = 0; i < 20; i++) {
            assertNotEquals(shape7, queue1.getNextTetromino());
            assertNotEquals(shape7, queue3.getNextTetromino());
            assertEquals(5, queue1.getQueue().size());
            assertEquals(5, queue3.getQueue().size());
            assertNotEquals(4, queue1.getQueue().size());
            assertNotEquals(6, queue2.getQueue().size());
        }
    }

    @Test
    void getTetromino() {
        assertEquals(shape0, queue1.getTetromino(0));
        assertEquals(shape1, queue1.getTetromino(1));
        assertEquals(shape2, queue1.getTetromino(2));
        assertEquals(shape3, queue1.getTetromino(3));
        assertEquals(shape4, queue2.getTetromino(4));
        assertEquals(shape5, queue2.getTetromino(5));
        assertEquals(shape6, queue2.getTetromino(6));

        assertEquals(shape7, queue1.getTetromino(7));
        assertEquals(shape7, queue2.getTetromino(80));
    }

    @Test
    void getRandomInteger() {
        for (int i = 0; i < 10; i++) {
            assertEquals(rng1.nextInt(7), queue1.getRandomInteger());
            assertEquals(rng2.nextInt(7), queue2.getRandomInteger());
        }
    }

    @Test
    void viewFirstTetromino() {
        Tetromino t1 = queue1.viewFirstTetromino();
        assertEquals(t1, queue1.getNextTetromino());
        Tetromino t2 = queue1.viewFirstTetromino();
        assertEquals(t2, queue1.getNextTetromino());
        Tetromino t3 = queue1.viewFirstTetromino();
        assertEquals(t3, queue1.getNextTetromino());

        Tetromino t4 = queue2.viewFirstTetromino();
        assertEquals(t4, queue2.getNextTetromino());
        Tetromino t5 = queue2.viewFirstTetromino();
        assertEquals(t5, queue2.getNextTetromino());
    }

}