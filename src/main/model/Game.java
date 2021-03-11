package model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

// Stores information and methods needed to simulate the game board
// NOTE: +Y is down, -X is left and +X is right. Top left corner is (0,0)
public class Game {

    private static final int WIDTH = 10;
    private static final int HEIGHT = 20;
    private static final Vector2D SPAWN = new Vector2D(4,0);

    private TetrominoQueue tetroQueue;
    private Tetromino currentTetro;
    private int score;
    private ArrayList<Block> board;
    private boolean gameStatus; // true if game is running, false if game is over

    // REQUIRES :
    // MODIFIES : this
    // EFFECTS  : Constructs a new game with score 0, a queue of Tetrominos and a single Tetromino ready to drop
    public Game() {
        this.score = 0;
        this.gameStatus = true;
        this.board = new ArrayList<>();
        this.tetroQueue = new TetrominoQueue();
        this.currentTetro = tetroQueue.getNextTetromino();
    }

    // REQUIRES :
    // MODIFIES : this
    // EFFECTS  : Updates the game, moves current tetromino down by one, checks if game is over,
    //          clears lines once the current tetromino has stopped moving
    //          and spawns a new tetromino at the top of the board
    public void update() {
        if (canMoveDown()) {
            moveDown();
        } else if (!canMoveDown() && canSpawn()) {
            clearLines();
            spawnNextTetromino();
        } else if (!canMoveDown() && !canSpawn()) {
            gameOver();
        }
    }

    // MODIFIES : this
    // EFFECTS  : Moves the current Tetromino down by one
    private void moveDown() {
        this.currentTetro.move(Direction.DOWN);
    }

    // MODIFIES : this
    // EFFECTS  : Remove any blocks from this.blocks that form an uninterrupted line across the board and increments
    //          score for each line that was cleared
    private void clearLines() {
        for (int y = 0; y < HEIGHT; y++) {
            int blocksInLine = 0;
            for (Block b : board) {
                if (b.getY() == y) {
                    blocksInLine++;
                }
                if (blocksInLine == WIDTH) {
                    score++;
                }
            }
        }
    }

    // MODIFIES : this
    // EFFECTS  : Spawn the next Tetromino from the queue at the top of the board
    private void spawnNextTetromino() {
        currentTetro = tetroQueue.getNextTetromino();
        currentTetro.setPosition(SPAWN);
    }

    // MODIFIES : this
    // EFFECTS  : Sets game status to false, indicating that the game is over
    private void gameOver() {
        this.gameStatus = false;
    }

    // EFFECTS  : returns true if the current tetromino can move down, false if not
    private boolean canMoveDown() {
        return isThereSpace(Direction.DOWN) && isInsideBoard();
    }

    // EFFECTS  : returns true if there is space for all blocks of the current Tetromino to move one step
    //          in the given direction, else false
    private boolean isThereSpace(Direction d) {
        currentTetro.setPosition(d.getVector());
        ArrayList<Vector2D> newTetroPositions = currentTetro.getPositions();
        ArrayList<Vector2D> blockPositions = new ArrayList<>();

        for (Block b: board) {
            blockPositions.add(b.getPosition());
        }
        return !blockPositions.contains(newTetroPositions);
    }

    // EFFECTS  : Returns true if current Tetromino is inside the bounds of the board
    //          Only checks Tetromino does not fall through the bottom or the sides, since
    //          upwards movement is not possible anyways and blocks may spawn in above the
    //          board, i.e. at (4, -1), etc.
    private boolean isInsideBoard() {
        for (Vector2D p : currentTetro.getPositions()) {
            if ((p.getX() >= WIDTH) && (p.getX() < 0) || (p.getY() >= HEIGHT)) {
                return false;
            }
        }
        return true;
    }

    // EFFECTS  : returns true if the next tetromino can be spawned at the top of the board, else false
    private boolean canSpawn() {
        return false;
    }

    public int getScore() {
        return score;
    }

    public ArrayList<Block> getBoard() {
        return board;
    }

    public boolean getGameStatus() {
        return gameStatus;
    }

}
