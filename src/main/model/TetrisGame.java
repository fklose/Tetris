package model;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

// TODO: TEST
// TODO: IMPLEMENT CLEARING OF LINES
// TODO: OPTIONAL BUT MAYBE MAKE IT SO THAT WHEN A ROTATION COULD BE OUT OF BOUNDS THE GAME JUST ROTATES
//  AND MOVES THE TETROMINO SO THAT IT WILL NOT BE OUT OF BOUNDS AFTER ROTATING.
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
    private HashSet<Block> board;
    private boolean isGameActive; // true if game is running, false if game is over

    private int tick = 0;
    private static final int TICK_RATE = 50;

    // REQUIRES :
    // MODIFIES : this
    // EFFECTS  : Constructs a new game with score 0, a queue of Tetrominos and a single Tetromino ready to drop
    public TetrisGame() {
        this.score = 0;
        this.isGameActive = true;
        this.board = new HashSet<>(WIDTH * HEIGHT);
        this.tetroQueue = new TetrominoQueue();
        spawnNextTetromino();
    }

    // TODO: NEEDS TESTING
    // REQUIRES :
    // MODIFIES : this
    // EFFECTS  : Updates the game, moves current tetromino down by one, checks if game is over,
    //          clears lines once the current tetromino has stopped moving
    //          and spawns a new tetromino at the top of the board
    public void update() {
        tick++;
        if (tick % TICK_RATE == 0) {
            if (!canMove(Direction.DOWN) && canSpawn()) {
                placeTetrominoOnBoard();
                spawnNextTetromino();
                this.score += 1;
            } else if (!canMove(Direction.DOWN) && !canSpawn()) {
                gameOver();
            } else {
                move(Direction.DOWN);
            }
        }
    }

    // TODO: NEEDS TESTING
    // REQUIRES :
    // MODIFIES : this
    // EFFECTS  : Responds to user key inputs
    public void keyPressed(int keyCode) {
        if (keyCode == KeyEvent.VK_UP) {
            if (canRotate()) {
                rotate();
            }
        } else if ((keyCode == KeyEvent.VK_LEFT) || (keyCode == KeyEvent.VK_KP_LEFT)) {
            if (canMove(Direction.LEFT)) {
                move(Direction.LEFT);
            }
        } else if ((keyCode == KeyEvent.VK_RIGHT) || (keyCode == KeyEvent.VK_KP_RIGHT)) {
            if (canMove(Direction.RIGHT)) {
                move(Direction.RIGHT);
            }
        } else if (keyCode == KeyEvent.VK_SPACE) {
            drop();
        } else if ((keyCode == KeyEvent.VK_DOWN) || (keyCode == KeyEvent.VK_KP_DOWN)) {
            if (canMove(Direction.DOWN)) {
                move(Direction.DOWN);
            }
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
        while (canMove(Direction.DOWN)) {
            this.currentTetro.move(Direction.DOWN);
        }
        placeTetrominoOnBoard();
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
        this.isGameActive = false;
    }

    // EFFECTS  : returns true if the current tetromino can move down, false if not
    private boolean canMove(Direction d) {
        return isThereSpaceToMove(d) && willItBeInsideBoard(d);
    }

    private boolean canRotate() {
        return isThereSpaceToRotate() && willItBeInsideBoardAfterRotatingCCW();
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

    // EFFECTS  : Returns true if current Tetromino will be inside the bounds of the board
    //          after moving one unit in the given direction.
    //          Only checks Tetromino does not fall through the bottom or the sides, since
    //          upwards movement is not possible anyways and blocks may spawn in above the
    //          board, i.e. at (4, -1), etc.
    private boolean willItBeInsideBoard(Direction d) {
        ArrayList<Vector2D> positions = currentTetro.getPositions();
        for (Vector2D pos : positions) {
            Vector2D newPos = pos.addVectorGetNewVector(d.getVector());
            if (!((0 <= newPos.getX()) && (newPos.getX() < WIDTH) && (newPos.getY() < HEIGHT))) {
                return false;
            }
        }
        return true;
    }

    // TODO : INCREASE COVERAGE
    // EFFECTS  : Returns true if current Tetromino will be inside the bounds of the board
    //          after performing one counterclockwise rotation.
    //          Only checks Tetromino does not fall through the bottom or the sides, since
    //          upwards movement is not possible anyways and blocks may spawn in above the
    //          board, i.e. at (4, -1), etc.
    private boolean willItBeInsideBoardAfterRotatingCCW() {
        currentTetro.rotateCCW();
        ArrayList<Vector2D> positions = currentTetro.getPositions();
        for (Vector2D pos : positions) {
            if (!((0 <= pos.getX()) && (pos.getX() < WIDTH) && (pos.getY() < HEIGHT))) {
                currentTetro.rotateCW();
                return false;
            }
        }
        currentTetro.rotateCW();
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

    public boolean getGameActive() {
        return isGameActive;
    }

    public Tetromino getCurrentTetro() {
        return currentTetro;
    }

    public void resetGame() {
        this.score = 0;
        this.isGameActive = true;
        this.board = new HashSet<>(WIDTH * HEIGHT);
        this.tetroQueue = new TetrominoQueue();
        spawnNextTetromino();
    }

    public TetrominoQueue getQueue() {
        return tetroQueue;
    }
}
