package model;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;

// TODO: WRITE METHOD TO SHIFT LINES AFTER CLEARING THEM
// TODO: MAKE SIDES NOT "STICKY"
// TODO: TEST
// TODO: TEST
// TODO: TEST

// Stores information and methods needed to simulate the game board
// NOTE: +Y is down, -X is left and +X is right. Top left corner is (0,0)
public class TetrisGame {

    private static final int WIDTH = 10;
    private static final int HEIGHT = 20;
    private static final int SPAWN_X = 5;
    private static final int SPAWN_Y = 0;

    private TetrominoQueue tetroQueue;
    private Tetromino currentTetro;
    private int score;
    private final HashSet<Block> board;
    private boolean gameStatus; // true if game is running, false if game is over

    // REQUIRES :
    // MODIFIES : this
    // EFFECTS  : Constructs a new game with score 0, a queue of Tetrominos and a single Tetromino ready to drop
    public TetrisGame() {
        this.score = 0;
        this.gameStatus = true;
        this.board = new HashSet<>(WIDTH * HEIGHT);
        this.tetroQueue = new TetrominoQueue();
        spawnNextTetromino();
    }

    // REQUIRES :
    // MODIFIES : this
    // EFFECTS  : Updates the game, moves current tetromino down by one, checks if game is over,
    //          clears lines once the current tetromino has stopped moving
    //          and spawns a new tetromino at the top of the board
    public void update() {
        if (canMove(Direction.DOWN)) {
            System.out.println("Moving Down");
            move(Direction.DOWN);
        } else if (!canMove(Direction.DOWN) && canSpawn()) {
            placeTetrominoOnBoard();
            clearLines();
            spawnNextTetromino();
        } else if (!canMove(Direction.DOWN) && !canSpawn()) {
            System.out.println("GAME OVER");
            gameOver();
        }
    }

    // REQUIRES :
    // MODIFIES : this
    // EFFECTS  : Responds to user key inputs
    public void keyPressed(int keyCode) {
        if (keyCode == KeyEvent.VK_UP) {
            if (canRotate()) {
                System.out.println("Spinning!");
                rotate();
            }
        } else if ((keyCode == KeyEvent.VK_LEFT) || (keyCode == KeyEvent.VK_KP_LEFT)) {
            if (canMove(Direction.LEFT)) {
                System.out.println("Move left!");
                move(Direction.LEFT);
            }
        } else if ((keyCode == KeyEvent.VK_RIGHT) || (keyCode == KeyEvent.VK_KP_RIGHT)) {
            if (canMove(Direction.RIGHT)) {
                System.out.println("Move right!");
                move(Direction.RIGHT);
            }
        } else if (keyCode == KeyEvent.VK_ESCAPE) {
            System.out.println("Exit!");
            System.exit(0);
        } else if (keyCode == KeyEvent.VK_SPACE) {
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
        this.currentTetro.rotateCCW();
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
        ArrayList<Block> toBeCleared = new ArrayList<>();
        for (int y = 0; y < HEIGHT; y++) {
            ArrayList<Block> line = new ArrayList<>();
            for (Block b : board) {
                if (b.getY() == y) {
                    line.add(b);
                }
                if (line.size() == WIDTH) {
                    toBeCleared.addAll(line);
                    score++;
                }
            }
        }
        board.removeAll(toBeCleared);
    }

    // MODIFIES : this
    // EFFECTS  : Spawn the next Tetromino from the queue at the top of the board
    private void spawnNextTetromino() {
        this.currentTetro = tetroQueue.getNextTetromino();
        this.currentTetro.setCentre(new Vector2D(SPAWN_X, SPAWN_Y));
    }

    // MODIFIES : this
    // EFFECTS  : Sets game status to false, indicating that the game is over
    private void gameOver() {
        this.gameStatus = false;
        System.out.println(score);
        System.exit(0);
    }

    // EFFECTS  : returns true if the current tetromino can move down, false if not
    private boolean canMove(Direction d) {
        return isThereSpaceToMove(d) && isInsideBoard();
    }

    private boolean canRotate() {
        return isThereSpaceToRotate() && isInsideBoard();
    }

    // EFFECTS  : returns true if there is space for all blocks of the current Tetromino to rotate 90 degrees ccw,
    //          else false
    private boolean isThereSpaceToRotate() {
        this.currentTetro.rotateCCW();
        if (areGivenPositionsOccupied(this.currentTetro.getPositions())) {
            this.currentTetro.rotateCW();
            return false;
        }
        this.currentTetro.rotateCW();
        return true;
    }

    // EFFECTS  : returns true if there is space for all blocks of the current Tetromino to move one step
    //          in the given direction, else false
    private boolean isThereSpaceToMove(Direction d) {
        for (Vector2D pos: this.currentTetro.getPositions()) {
            if (isPositionOccupied(pos.addVectorGetNewVector(d.getVector()))) {
                return false;
            }
        }
        return true;
    }

    // EFFECTS  : Returns true if current Tetromino is inside the bounds of the board
    //          Only checks Tetromino does not fall through the bottom or the sides, since
    //          upwards movement is not possible anyways and blocks may spawn in above the
    //          board, i.e. at (4, -1), etc.
    private boolean isInsideBoard() {
        ArrayList<Vector2D> positions = currentTetro.getPositions();

        for (Vector2D pos : positions) {
            if (!((0 <= pos.getX()) && (pos.getX() < WIDTH) && (pos.getY() < HEIGHT))) {
                return false;
            }
        }
        return true;
    }

    // EFFECTS  : Returns true if the position of the given block is already on the board, else false
    private boolean isPositionOccupied(Vector2D position) {
        return getAllPositions().contains(position);
    }

    // EFFECTS  : Returns true if one of the positions in a given list of positions is already on the board, else false
    private boolean areGivenPositionsOccupied(ArrayList<Vector2D> positions) {
        for (Vector2D pos : positions) {
            if (isPositionOccupied(pos)) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS  : returns true if the next tetromino can be spawned at the top of the board, else false
    private boolean canSpawn() {
        ArrayList<Vector2D> nextTemplate = tetroQueue.viewFirstTetromino().getTemplate();
        ArrayList<Vector2D> nextTetrominoPositions = new ArrayList<>();

        for (Vector2D vec : nextTemplate) {
            nextTetrominoPositions.add(vec.addVectorGetNewVector(new Vector2D(SPAWN_X, SPAWN_Y)));
        }

        return !areGivenPositionsOccupied(nextTetrominoPositions);
    }

    // EFFECTS  : Returns the positions of all blocks in board
    private ArrayList<Vector2D> getAllPositions() {
        ArrayList<Vector2D> blockPositions = new ArrayList<>();
        for (Block b: board) {
            blockPositions.add(b.getPosition());
        }
        return blockPositions;
    }

    // MODIFIES : this
    // EFFECTS  : Places all blocks belonging to the current tetromino on the board and spawns next tetromino
    private void placeTetrominoOnBoard() {
        board.addAll(this.currentTetro.getBlocks());
    }

    public int getScore() {
        return score;
    }

    public HashSet<Block> getBoard() {
        return board;
    }

    public boolean getGameStatus() {
        return gameStatus;
    }

    public Tetromino getCurrentTetro() {
        return currentTetro;
    }
}
