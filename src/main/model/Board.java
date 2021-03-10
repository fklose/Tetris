package model;

import java.util.LinkedList;

// Stores information and methods needed to simulate the game board
// NOTE: +Y is down, -X is left and +X is right
public class Board {

    private static final int WIDTH = 10;
    private static final int HEIGHT = 20;

    private LinkedList<Block> blocks;
    private int score;
    private TetrominoQueue tetrominoQueue;
    private Tetromino currentTetromino;

    // REQUIRES :
    // MODIFIES : this
    // EFFECTS  : Creates an empty board with a filled queue and a Tetromino ready to drop and score set to 0.
    public Board() {
        this.score = 0;
        this.blocks = new LinkedList<>();
        this.tetrominoQueue = new TetrominoQueue();
        this.currentTetromino = this.tetrominoQueue.getNextTetromino();
    }

    // REQUIRES :
    // MODIFIES : this
    // EFFECTS  : Checks if any lines can be cleared and clears them,
    //          increments the score by the number of lines cleared
    public int clearLines() {
        return 0;
    }

    // REQUIRES :
    // MODIFIES : this
    // EFFECTS  : Checks if Tetromino can move in the desired direction.
    //          If it can move it it moves the tetromino in the desired direction by one  and returns True,
    //          else returns false and does nothing.
    public boolean moveTetromino(Direction d) {
        return false;
    }

    // REQUIRES :
    // MODIFIES :
    // EFFECTS  : Return the current queue
    public LinkedList<Tetromino> getQueue() {
        return tetrominoQueue.getQueue();
    }

    // REQUIRES :
    // MODIFIES :
    // EFFECTS  : returns the current Tetromino
    public Tetromino getCurrentTetromino() {
        return currentTetromino;
    }
}
