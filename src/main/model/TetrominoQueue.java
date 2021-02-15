package model;

import java.util.LinkedList;
import java.util.Random;

// Used to store Tetrominos and generate a new random Tetromino when one is taken out.
public class TetrominoQueue {

    private static final int MAX_SIZE = 5;
    private LinkedList<Tetromino> queue;
    private Random rnd;

    // REQUIRES :
    // MODIFIES :
    // EFFECTS  : Makes a new queue containing a set number of random Tetrominos with a given seed
    public TetrominoQueue() {
        rnd = new Random();

        queue = new LinkedList<>();
        for (int i = 0; i < MAX_SIZE; i++) {
            queue.addLast(randomTetromino());
        }
    }

    // REQUIRES :
    // MODIFIES : this
    // EFFECTS  : Sets the seed for the random number generator. For testing purposes
    public void setSeed(long seed) {
        rnd.setSeed(seed);
    }

    // REQUIRES :
    // MODIFIES : this
    // EFFECTS  : Returns the first Tetromino in queue and adds a new one at the end
    public Tetromino getNextTetromino() {
        addRandomTetromino();
        return queue.pollFirst();
    }

    // REQUIRES :
    // MODIFIES :
    // EFFECTS  : Returns the queue
    public LinkedList<Tetromino> getQueue() {
        return queue;
    }

    // REQUIRES :
    // MODIFIES : this
    // EFFECTS  : Adds a random Tetromino to the end of the list
    public void addRandomTetromino() {
        Tetromino tetromino = randomTetromino();
        addTetromino(tetromino);
    }

    // REQUIRES :
    // MODIFIES : this
    // EFFECTS  : Adds a Tetromino to the end of the queue
    public void addTetromino(Tetromino tetromino) {
        queue.addLast(tetromino);
    }

    // REQUIRES :
    // MODIFIES :
    // EFFECTS  : Generates a random Tetromino
    public Tetromino randomTetromino() {
        switch (getRandomInteger()) {
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

    // REQUIRES :
    // MODIFIES :
    // EFFECTS  : Generates a random integer in the interval [0, 6]
    public int getRandomInteger() {
        return rnd.nextInt(7);
    }
}
