package model.game;

import model.tetromino.*;

import java.util.LinkedList;
import java.util.Random;

// Used to store Tetrominos and generate random Tetrominos
public class TetrominoQueue {

    private static final int SIZE = 5;
//    private LinkedList<Tetromino> queue;
    private LinkedList<Tetromino> queue;
    private final Random rnd;

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
    // EFFECTS  : Returns and removes the first Tetromino in queue and adds a random new one at the end
    public Tetromino getNextTetromino() {
        addRandomTetromino();
        return queue.pollFirst();
    }

    // EFFECTS  : Returns a copy of the first tetromino in queue without removing it
    public Tetromino viewFirstTetromino() {
        return queue.peekFirst();
    }

    public LinkedList<Tetromino> getQueue() {
        return queue;
    }

    // MODIFIES : this
    // EFFECTS  : Adds a random Tetromino to the end of the list
    public void addRandomTetromino() {
        Tetromino tetromino = getTetromino(getRandomInteger());
        this.queue.addLast(tetromino);
    }

    // EFFECTS  : Returns a Tetromino given a number
    public Tetromino getTetromino(int n) {
        switch (n) {
            case 0:
                return new Line();
            case 1:
                return new Square();
            case 2:
                return new TShape();
            case 3:
                return new LeftSkew();
            case 4:
                return new RightSkew();
            case 5:
                return new LeftL();
            case 6:
                return new RightL();
        }
        return new SingleBlock();
    }

    // EFFECTS  : Generates a random integer in the interval [0, 6]
    public int getRandomInteger() {
        return rnd.nextInt(7);
    }
}
