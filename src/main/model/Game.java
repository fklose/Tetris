package model;

import Exceptions.PositionOccupiedException;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

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
        // TODO: RIGHT NOW BLOCKS SEEM TO GET RANDOM POSITIONS AFTER DROPPING. ALSO SOME BLOCKS SEEM TO BE OUT OF BOUNDS
        if (canMove(Direction.DOWN)) {
            move(Direction.DOWN);
        } else if (!canMove(Direction.DOWN) && canSpawn()) {
            placeTetrominoOnBoard();
            clearLines();
            spawnNextTetromino();
        } else if (!canMove(Direction.DOWN) && !canSpawn()) {
            gameOver();
        }
    }

    // REQUIRES :
    // MODIFIES : this
    // EFFECTS  : Responds to user key inputs
    public void keyPressed(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_UP:
                if (canRotate()) {
                    rotate();
                }
            case KeyEvent.VK_LEFT:
                if (canMove(Direction.LEFT)) {
                    move(Direction.LEFT);
                }
            case KeyEvent.VK_RIGHT:
                if (canMove(Direction.RIGHT)) {
                    move(Direction.RIGHT);
                }
            case KeyEvent.VK_SPACE:
                drop();
        }
    }

    // MODIFIES : this
    // EFFECTS  : Moves the current Tetromino in the given direction by one
    private void move(Direction d) {
        this.currentTetro.move(d);
    }

    // MODIFIES : this
    // EFFECTS  : Rotates the Tetromino 90 degrees counterclockwise
    private void rotate() {
        this.currentTetro.rotate();
    }

    // MODIFIES : this
    // EFFECTS  : Drops the Tetromino on the lowest reachable layer
    private void drop() {
        for (int i = 0; i < HEIGHT; i++) {
            if (canMove(Direction.DOWN)) {
                this.currentTetro.move(Direction.DOWN);
            } else {
                break;
            }
        }
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
    private boolean canMove(Direction d) {
        return isThereSpace(d) && isInsideBoard();
    }

    private boolean canRotate() {
        return isThereSpace() && isInsideBoard();
    }

    // EFFECTS  : returns true if there is space for all blocks of the current Tetromino to rotate 90 degrees ccw,
    //          else false
    private boolean isThereSpace() {
        RotationMatrix2x2 ccw = new RotationMatrix2x2();
        ArrayList<Vector2D> newTetroPositions = new ArrayList<>();

        for (Vector2D pos: currentTetro.getPositionsCopy()) {
            newTetroPositions.add(ccw.matrixVectorProduct(pos));
        }

        return !isAnyPositionOccupied(newTetroPositions);
    }

    // EFFECTS  : returns true if there is space for all blocks of the current Tetromino to move one step
    //          in the given direction, else false
    private boolean isThereSpace(Direction d) {
        ArrayList<Vector2D> newTetroPositions = new ArrayList<>();

        for (Vector2D pos: currentTetro.getPositionsCopy()) {
            newTetroPositions.add(pos.addVector(d.getVector()));
        }
        return !isAnyPositionOccupied(newTetroPositions);
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

    // EFFECTS  : Returns true if the position of the given block is already occupied on the board, else false
    private void isPositionOccupied(Vector2D position) throws PositionOccupiedException {
        ArrayList<Vector2D> blockPositions = getAllPositions();
        for (Vector2D pos : blockPositions) {
            if (position == pos) {
                throw new PositionOccupiedException();
            }
        }
    }

    // EFFECTS  : Returns true if one of the positions in a given list of positions is already on the board, else false
    private boolean isAnyPositionOccupied(ArrayList<Vector2D> positions) {
        try {
            for (Vector2D pos : positions) {
                isPositionOccupied(pos);
            }
        } catch (PositionOccupiedException poe) {
            return true;
        }
        return false;
    }

    // EFFECTS  : returns true if the next tetromino can be spawned at the top of the board, else false
    private boolean canSpawn() {
        ArrayList<Vector2D> nextTetrominoPositions = tetroQueue.viewFirstTetromino().getPositionsCopy();

        for (Vector2D pos : nextTetrominoPositions) {
            pos.addVector(SPAWN);
        }

        return !isAnyPositionOccupied(nextTetrominoPositions);
    }

    // EFFECTS  : Returns the positions of all blocks in board
    private ArrayList<Vector2D> getAllPositions() {
        ArrayList<Vector2D> blockPositions = new ArrayList<>();
        for (Block b: board) {
            blockPositions.add(b.getPositionCopy());
        }
        return blockPositions;
    }

    // MODIFIES : this
    // EFFECTS  : Places all blocks belonging to the current tetromino on the board
    private void placeTetrominoOnBoard() {
        board.addAll(this.currentTetro.getBlocksCopy());
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
