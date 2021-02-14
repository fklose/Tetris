package model;

import java.util.LinkedList;
import java.util.Random;

// Used to store Tetrominos and generate a new random Tetromino when one is taken out.
public class TetrominoQueue {

    private static final int MAX_SIZE = 5;
    private static LinkedList<Tetromino> queue = new LinkedList<>();
    private static Random rnd = new Random();

    // REQUIRES :
    // MODIFIES :
    // EFFECTS  : Makes a new queue containing a set number of random Tetrominos with a given seed
    public TetrominoQueue(long seed) {
        rnd = new Random();
        rnd.setSeed(seed);

        queue = new LinkedList<>();
        for (int i = 0; i < MAX_SIZE; i++) {
            queue.addLast(randomTetromino());
        }
    }

    // REQUIRES :
    // MODIFIES : this
    // EFFECTS  : Returns the first Tetromino in queue and adds a new one at the end
    public static Tetromino getNextTetromino() {
        addRandomTetromino();
        return queue.pollFirst();
    }

    // REQUIRES :
    // MODIFIES :
    // EFFECTS  : Returns the queue
    public static LinkedList<Tetromino> getQueue() {
        return queue;
    }

    // REQUIRES :
    // MODIFIES : this
    // EFFECTS  : Adds a random Tetromino to the end of the list
    public static void addRandomTetromino() {
        Tetromino tetromino = randomTetromino();
        addTetromino(tetromino);
    }

    // REQUIRES :
    // MODIFIES : this
    // EFFECTS  : Adds a Tetromino to the end of the queue
    private static void addTetromino(Tetromino tetromino) {
        queue.addLast(tetromino);
    }

    // REQUIRES :
    // MODIFIES :
    // EFFECTS  : Generates a random Tetromino
    public static Tetromino randomTetromino() {
        int randInt = rnd.nextInt(7);

        switch (randInt) {
            case 0:
                return Tetromino.shapeLine;
            case 1:
                return Tetromino.shapeSquare;
            case 2:
                return Tetromino.shapeT;
            case 3:
                return Tetromino.shapeLeftSkew;
            case 4:
                return Tetromino.shapeRightSkew;
            case 5:
                return Tetromino.shapeLeftL;
            case 6:
                return Tetromino.shapeRightL;
        }
        return Tetromino.shapeLeftL;
    }
}
