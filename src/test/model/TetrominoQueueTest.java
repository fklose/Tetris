package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

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


    @BeforeEach
    void setUp() {
        queue1 = new TetrominoQueue(SEED_ONE);
        queue2 = new TetrominoQueue(SEED_TWO);

        shape0 = Tetromino.shapeLine;
        shape1 = Tetromino.shapeSquare;
        shape2 = Tetromino.shapeT;
        shape3 = Tetromino.shapeLeftSkew;
        shape4 = Tetromino.shapeRightSkew;
        shape5 = Tetromino.shapeLeftL;
        shape6 = Tetromino.shapeRightL;
        shape7 = Tetromino.nullShape;
    }

    @Test
    void getNextTetromino() {
        Random rng1 = new Random();
        rng1.setSeed(SEED_ONE);
        Random rng2 = new Random();
        rng2.setSeed(SEED_TWO);

        for (int i = 0; i < 5; i++) {
            rng1.nextInt(7);
            rng2.nextInt(7);
        }

        queue1.initializeQueue();
        queue2.initializeQueue();

        assertNotEquals(shape7, queue1.getNextTetromino());
        assertEquals(5, queue1.getSize());

        assertNotEquals(shape7, queue1.getNextTetromino());
        assertNotEquals(shape7, queue1.getNextTetromino());
        assertEquals(5, queue1.getQueue().size());

        assertNotEquals(shape7, queue1.getNextTetromino());
        assertNotEquals(shape7, queue1.getNextTetromino());
        assertNotEquals(shape7, queue1.getNextTetromino());
        assertEquals(5, queue2.getQueue().size());
    }

    @Test
    void getQueue() {
    }

    @Test
    void addRandomTetromino() {
    }

    @Test
    void addTetromino() {
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
        Random rng1 = new Random();
        rng1.setSeed(SEED_ONE);
        Random rng2 = new Random();
        rng2.setSeed(SEED_TWO);

//        for (int i = 0; i < 5; i++) {
//            rng1.nextInt(7);
//            rng1.nextInt(7);
//        }

        for (int i = 0; i < 10; i++) {
            assertEquals(rng1.nextInt(7), queue1.getRandomInteger());
            assertEquals(rng2.nextInt(7), queue2.getRandomInteger());
        }
    }

}