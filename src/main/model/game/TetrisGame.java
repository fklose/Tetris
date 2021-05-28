package model.game;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

// TODO: NEED TO FIX SOME TESTS
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
    private HashSet<Block> board;
    private final HashMap<Integer, HashSet<Block>> lineMap;
    private boolean isGameActive; // true if game is running, false if game is over
    private Tetromino savedTetromino;

    private int tick = 0;
    private static final int TICK_RATE = 50;

    /**
     * Constructs a new game with score 0, a queue of Tetrominos and a single Tetromino ready to drop
     */
    public TetrisGame() {
        this.score = 0;
        this.isGameActive = true;
        this.board = new HashSet<>(WIDTH * HEIGHT);
        this.lineMap = new HashMap<>();
        initializeLineMap(this.lineMap);
        this.tetroQueue = new TetrominoQueue();
        spawnNextTetromino();
        savedTetromino = Tetromino.nullShape;
    }

    /**
     * Initializes a HashMap with the Key being the y-coordinate of some line and the value being a Collection,
     * in this case a HashSet of Blocks that are located in that line.
     * @param lineMap An empty HashMap.
     */
    private void initializeLineMap(HashMap<Integer, HashSet<Block>> lineMap) {
        for (int i = 0; i < HEIGHT; i++) {
            lineMap.put(i, new HashSet<>());
        }
    }

    /**
     * Updates the game, moves current tetromino down by one, checks if game is over, clears lines once the current
     * tetromino has stopped moving and spawns a new tetromino at the top of the board
     */
    public void update() {
        tick++;
        if (tick % TICK_RATE == 0) {
            if (!canMove(Direction.DOWN)) {
                placeTetrominoOnBoard();
                clearLines();
                if (canSpawn()) {
                    spawnNextTetromino();
                } else {
                    gameOver();
                }
            } else {
                move(Direction.DOWN);
            }
        }
    }

    /**
     * Places all blocks belonging to the current tetromino on the board also updates the lineMap
     * by placing each block into the correct line
     */
    private void placeTetrominoOnBoard() {
        this.board.addAll(this.currentTetro.getBlocks());
        for (Block b : this.currentTetro.getBlocks()) {
            if (b.getY() < 0) {
                gameOver();
            } else {
                this.lineMap.get(b.getY()).add(b);
            }
        }
    }

    /**
     * Clears all lines that are fully filled with blocks along the width
     */
    private void clearLines() {
        ArrayList<Integer> lineNumbers = linesToBeCleared();
        for (int i : lineNumbers) {
            lineMap.replace(i, new HashSet<>());
            this.score++;
        }
        if (lineNumbers.size() != 0) {
            boardFromLineMap(lineMap);
            shiftLines(lineNumbers);
            lineNumbers.clear();
        }
    }

    /**
     * Determines what lines need to be cleared. A line can be cleared if it contains 10 blocks.
     * @return Array of line numbers to be cleared
     */
    private ArrayList<Integer> linesToBeCleared() {
        ArrayList<Integer> toBeCleared = new ArrayList<>();
        for (int i = 0; i < HEIGHT; i++) {
            if (lineMap.get(i).size() >= WIDTH) {
                toBeCleared.add(i);
            }
        }
        return toBeCleared;
    }

    /**
     * Shifts horizontal lines down to fill any lines that contain no blocks.
     * @param lineNumbers A collection of y-coordinates of lines that were cleared.
     */
    private void shiftLines(Collection<Integer> lineNumbers) {
        for (int i : lineNumbers) {
            for (Block b : board) {
                if (b.getY() < i) {
                    b.setPosition(new Vector2D(b.getX(), b.getY() + 1));
                }
            }
        }
        lineMapFromBoard();
    }

    /**
     * Translates a lineMap into a board and updates this board with the translation.
     * @param lineMap The LineMap to be translated.
     */
    private void boardFromLineMap(HashMap<Integer, HashSet<Block>> lineMap) {
        board.clear();
        for (int y = 0; y < HEIGHT; y++) {
            for (Block b : lineMap.get(y)) {
                b.setPosition(new Vector2D(b.getX(), y));
                board.add(b);
            }
        }
    }

    /**
     * Translates a board into a line map and updates the lineMap of this game.
     */
    private void lineMapFromBoard() {
        initializeLineMap(this.lineMap);
        for (Block b : board) {
            lineMap.get(b.getY()).add(b);
        }
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
            spawnNextTetromino();
        } else {
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
    private void spawnNextTetromino() {
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
        return getAllPositions().contains(position);
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

    private ArrayList<Vector2D> getAllPositions() {
        ArrayList<Vector2D> blockPositions = new ArrayList<>();
        for (Block b: board) {
            blockPositions.add(b.getPosition());
        }
        return blockPositions;
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

    /**
     * Resets the game. This sets the score to 0, makes a new empty board, a new queue and sets the isGameActiveField to
     * true.
     */
    public void resetGame() {
        this.score = 0;
        this.isGameActive = true;
        this.board = new HashSet<>(WIDTH * HEIGHT);
        this.tetroQueue = new TetrominoQueue();
        initializeLineMap(this.lineMap);
        spawnNextTetromino();
        this.savedTetromino = Tetromino.nullShape;
    }

    public TetrominoQueue getQueue() {
        return tetroQueue;
    }

    public Tetromino getSavedTetromino() {
        return savedTetromino;
    }
}
