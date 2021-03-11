package model;

import java.util.LinkedList;
import java.util.Random;

// Used to store Tetrominos and generate random Tetrominos
public class TetrominoQueue {

    private static final int SIZE = 5;
    private LinkedList<Tetromino> queue;
    private Random rnd;

    // MODIFIES : this
    // EFFECTS  : Makes a new queue containing a set number of random Tetrominos with a random seed
    public TetrominoQueue() {
        rnd = new Random();
        initializeQueue();
    }

    // MODIFIES : this
    // EFFECTS  : Sets the seed for generating random numbers
    public void setSeed(long seed) {
        rnd.setSeed(seed);
    }

    // MODIFIES : this
    // EFFECTS  : Initializes the queue by filling it with random Tetrominos
    public void initializeQueue() {
        queue = new LinkedList<>();
        for (int i = 0; i < SIZE; i++) {
            queue.addLast(getTetromino(getRandomInteger()));
        }
    }

    // MODIFIES : this
    // EFFECTS  : Returns the first Tetromino in queue with removal and adds a new one at the end
    public Tetromino getNextTetromino() {
        addRandomTetromino();
        return queue.pollFirst();
    }

    // EFFECTS  : Returns the first tetromino in queue without removing it
    public Tetromino accessNextTetromino() {
        return queue.peekFirst();
    }

    // EFFECTS  : Returns the queue
    public LinkedList<Tetromino> getQueue() {
        return queue;
    }

    // MODIFIES : this
    // EFFECTS  : Adds a random Tetromino to the end of the list
    public void addRandomTetromino() {
        Tetromino tetromino = getTetromino(getRandomInteger());
        addTetromino(tetromino);
    }

    // MODIFIES : this
    // EFFECTS  : Adds a Tetromino to the end of the queue
    public void addTetromino(Tetromino tetromino) {
        queue.addLast(tetromino);
    }

    // EFFECTS  : Returns a Tetromino given a number
    public Tetromino getTetromino(int n) {
        switch (n) {
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
        return Tetromino.nullShape;
    }

    // EFFECTS  : Generates a random integer in the interval [0, 6]
    public int getRandomInteger() {
        return rnd.nextInt(7);
    }
}
