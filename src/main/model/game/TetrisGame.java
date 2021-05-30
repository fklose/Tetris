package model.game;

import model.exceptions.GameOverException;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;

// TODO: OPTIONAL BUT MAYBE MAKE IT SO THAT WHEN A ROTATION COULD BE OUT OF BOUNDS THE GAME JUST ROTATES
//  AND MOVES THE TETROMINO SO THAT IT WILL NOT BE OUT OF BOUNDS AFTER ROTATING.
// Stores information and methods needed to simulate the game board
// NOTE: +Y is down, -X is left and +X is right. Top left corner is (0,0)
public class TetrisGame {

    public static final int WIDTH = 10;
    public static final int HEIGHT = 20;
    private static final int SPAWN_X = 4;
    private static final int SPAWN_Y = 0;

    private TetrominoQueue tetroQueue;
    private Tetromino currentTetro;
    private int score;
    private boolean isGameActive; // true if game is running, false if game is over
    private Tetromino savedTetromino;

    private Board board;

    private int tick = 0;
    private int tickRate = 50;

    /**
     * Constructs a new game with score 0, a queue of Tetrominos and a single Tetromino ready to drop
     */
    public TetrisGame() {
        board = new Board(WIDTH, HEIGHT);
        this.score = 0;
        this.isGameActive = true;
        this.tetroQueue = new TetrominoQueue();
        // Not so nice having the try catch here, since there is no way this will ever be triggered
        try {
            spawnNextTetromino();
        } catch (Exception e) {
            resetGame();
        }
        savedTetromino = Tetromino.nullShape;
    }

    /**
     * Updates the game, moves current tetromino down by one, checks if game is over, clears lines once the current
     * tetromino has stopped moving and spawns a new tetromino at the top of the board
     */
    public void update() {
        tick++;
        if (tick % tickRate == 0) {
            if (!canMove(Direction.DOWN)) {
                try {
                    board.placeTetrominoOnBoard(currentTetro);
                    clearLines();
                    spawnNextTetromino();
                } catch (GameOverException goe) {
                    gameOver();
                }
            } else {
                move(Direction.DOWN);
            }
        }
    }

    /**
     * Finds lines that need to be cleared, adjusts the score and clears the lines.
     */
    private void clearLines() {
        ArrayList<Integer> linesToBeCleared = linesToBeCleared();
        score += linesToBeCleared.size();
        board.clearLines(linesToBeCleared);
    }

    /**
     * Determines what lines need to be cleared. A line can be cleared if it contains 10 blocks.
     * @return Array of line numbers to be cleared
     */
    private ArrayList<Integer> linesToBeCleared() {
        ArrayList<Integer> toBeCleared = new ArrayList<>();
        for (int i = 0; i < HEIGHT; i++) {
            if (board.getLineMap().get(i).size() >= WIDTH) {
                toBeCleared.add(i);
            }
        }
        return toBeCleared;
    }

    /**
     * Defines the actions taken upon pressing any key associated to the given keyCode.
     * @param keyCode Code assigned to a keyEvent
     */
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
        } else if (keyCode == KeyEvent.VK_C) {
            if (canTetrominosBeSwapped()) {
                swapTetrominos();
            }
        }
    }

    /**
     * Checks if the current Tetromino can be swapped with the stored one.
     * @return true if the saved Tetromino can be swapped with the current tetromino, false otherwise
     */
    private boolean canTetrominosBeSwapped() {
        savedTetromino.setCentre(new Vector2D(SPAWN_X, SPAWN_Y));
        if (!areGivenPositionsOccupied(savedTetromino.getPositions())) {
            savedTetromino.setCentre(new Vector2D(0, 0));
            return true;
        } else {
            // TODO: Test this branch
            savedTetromino.setCentre(new Vector2D(0, 0));
            return false;
        }
    }

    /**
     * Swaps the current Tetromino with the saved one, spawning the saved one at the top of the board.
     */
    private void swapTetrominos() {
        if (savedTetromino == Tetromino.nullShape) {
            savedTetromino = currentTetro.copy();
            try {
                spawnNextTetromino();
            } catch (Exception ignored) { }
        } else {
            // TODO: Test this branch
            Tetromino currentCopy = currentTetro.copy();
            currentTetro = savedTetromino.copy();
            savedTetromino = currentCopy;
            savedTetromino.setCentre(new Vector2D(0,0));
            spawnTetromino(currentTetro);
        }
    }

    /**
     * Moves the current Tetromino in the given direction by one
     * @param d the direction in which the Tetromino should move
     */
    private void move(Direction d) {
        this.currentTetro.move(d);
    }

    /**
     * Rotates the Tetromino 90 degrees counterclockwise
     */
    private void rotate() {
        this.currentTetro.rotateCCW();
    }

    /**
     * Drops the Tetromino on the lowest reachable layer
     */
    private void drop() {
        while (canMove(Direction.DOWN)) {
            this.currentTetro.move(Direction.DOWN);
        }
    }

    /**
     * Spawn the next Tetromino from the queue at the top of the board. Modifies the TetroMinoQueue in the process
     */
    private void spawnNextTetromino() throws GameOverException {
        if (!canSpawn()) {
            throw new GameOverException();
        }
        spawnTetromino(tetroQueue.getNextTetromino());
    }

    /**
     * Spawns the given Tetromino at the top of the board
     * @param t the Tetromino to be spawned in
     */
    private void spawnTetromino(Tetromino t) {
        this.currentTetro = t;
        this.currentTetro.setCentre(new Vector2D(SPAWN_X, SPAWN_Y));
    }

    /**
     * Sets the game status to false, indicating that the game is over.
     */
    private void gameOver() {
        this.isGameActive = false;
    }

    /**
     * Checks if the current Tetromino can be moved in the given direction
     * @param d the direction to check
     * @return True if tetromino can move in given direction, false if not
     */
    private boolean canMove(Direction d) {
        return isThereSpaceToMove(d) && willItBeInsideBoard(d);
    }

    /**
     * Checks if Tetromino can rotate counterclockwise.
     * @return True if rotation is possible, false if not.
     */
    private boolean canRotate() {
        return isThereSpaceToRotate() && willItBeInsideBoardAfterRotatingCCW();
    }

    /**
     * Checks if there is space to rotate, specifically that no other Blocks are blocking the rotation.
     * @return True if rotation is not blocked, false if not.
     */
    private boolean isThereSpaceToRotate() {
        this.currentTetro.rotateCCW();
        if (areGivenPositionsOccupied(this.currentTetro.getPositions())) {
            // TODO: Increase coverage
            this.currentTetro.rotateCW();
            return false;
        }
        this.currentTetro.rotateCW();
        return true;
    }

    /**
     * Checks if the direction of movement is blocked by other Blocks.
     * @param d The direction of movement to check.
     * @return True if movement is not blocked, false if not.
     */
    private boolean isThereSpaceToMove(Direction d) {
        for (Vector2D pos: this.currentTetro.getPositions()) {
            if (isPositionOccupied(pos.addVectorGetNewVector(d.getVector()))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if Tetromino will be inside the board after moving in the given direction. Only checks bottom and sides.
     * @param d The direction to be checked.
     * @return True if the current Tetromino will be inside the board, false if not.
     */
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

    /**
     * Checks if the current Tetromino will be inside the board after rotating counterclockwise.
     * @return True if current Tetromino will be inside board, false if not.
     */
    private boolean willItBeInsideBoardAfterRotatingCCW() {
        currentTetro.rotateCCW();
        if (willItBeInsideBoard(Direction.NULL)) {
            currentTetro.rotateCW();
            return true;
        } else {
            currentTetro.rotateCW();
            return false;
        }
    }

    /**
     * Checks if the given position is occupied.
     * @param position the position to be checked.
     * @return True if position is occupied, false if not.
     */
    private boolean isPositionOccupied(Vector2D position) {
        return board.getBlockPositions().contains(position);
    }

    /**
     * Checks if the given positions are occupied.
     * @param positions the positions to be checked.
     * @return True if positions are occupied, false if not.
     */
    private boolean areGivenPositionsOccupied(ArrayList<Vector2D> positions) {
        for (Vector2D pos : positions) {
            if (isPositionOccupied(pos)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if there is space to spawn the next Tetromino in the queue.
     * @return True if next Tetromino can be spawned, false if not.
     */
    private boolean canSpawn() {
        ArrayList<Vector2D> nextTemplate = tetroQueue.viewFirstTetromino().getTemplate();
        ArrayList<Vector2D> nextTetrominoPositions = new ArrayList<>();

        for (Vector2D vec : nextTemplate) {
            nextTetrominoPositions.add(vec.addVectorGetNewVector(new Vector2D(SPAWN_X, SPAWN_Y)));
        }

        return !areGivenPositionsOccupied(nextTetrominoPositions);
    }

    public int getScore() {
        return score;
    }

    public HashSet<Block> getBoard() {
        return board.getBoard();
    }

    public boolean getGameActive() {
        return isGameActive;
    }

    public Tetromino getCurrentTetro() {
        return currentTetro;
    }

    /**
     * Resets the game. This sets the score to 0, makes a new empty board, a new queue and sets the isGameActiveField to
     * true.
     */
    public void resetGame() {
        this.score = 0;
        this.isGameActive = true;
        this.tetroQueue = new TetrominoQueue();
        board = new Board(WIDTH, HEIGHT);
        try {
            spawnNextTetromino();
        } catch (Exception ignored) { }
        this.savedTetromino = Tetromino.nullShape;

    }

    public TetrominoQueue getQueue() {
        return tetroQueue;
    }

    public Tetromino getSavedTetromino() {
        return savedTetromino;
    }

    public int getTickRate() {
        return tickRate;
    }

    public void setTickRate(int newRate) {
        tickRate = newRate;
    }
}
