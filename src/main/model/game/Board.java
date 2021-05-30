package model.game;

import model.exceptions.GameOverException;
import model.tetromino.NewTetromino;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

// Manages the Blocks that are placed on the board
public class Board {

    private final int width;
    private final int height;
    private final HashSet<Block> board;
    private HashMap<Integer, HashSet<Block>> lineMap;

    public Board(int width, int height) {
        initializeLineMap(width, height);
        this.board = new HashSet<>(width * height);
        this.width = width;
        this.height = height;
    }

    /**
     * Initializes a HashMap with the Key being the y-coordinate of some line and the value being a Collection,
     * in this case a HashSet of Blocks that are located in that line.
     * @param width the width of the board
     * @param height the height of the board
     */
    private void initializeLineMap(int width, int height) {
        lineMap = new HashMap<>(width * height);
        for (int lineNumber = 0; lineNumber < height; lineNumber++) {
            lineMap.put(lineNumber, new HashSet<>(width));
        }
    }

    /**
     * Places all blocks belonging to the current tetromino on the board also updates the lineMap
     * by placing each block into the correct line.
     * @param t the Tetromino to be placed on the board
     */
    public void placeTetrominoOnBoard(NewTetromino t) throws GameOverException {
        for (Block b : t.getBlocks()) {
            if (b.getY() < 0) {
                throw new GameOverException();
            }
            lineMap.get(b.getY()).add(b);
            board.add(b);
        }
    }
    /*
    public void placeTetrominoOnBoard(Tetromino t) throws GameOverException {
        for (Block b : t.getBlocks()) {
            if (b.getY() < 0) {
                throw new GameOverException();
            }
            lineMap.get(b.getY()).add(b);
            board.add(b);
        }
    }
     */

    /**
     * Clears all lines that are fully filled, i.e. contain a number of blocks greater than or equal to the width of the
     * board.
     * @param lineNumbers the y-coordinate of the lines to be cleared
     */
    public void clearLines(Collection<Integer> lineNumbers) {
        for (int lineNumber : lineNumbers) {
            lineMap.replace(lineNumber, new HashSet<>());
        }
        boardFromLineMap(lineMap);
        shiftLines(lineNumbers);
        lineNumbers.clear();
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
        for (int y = 0; y < height; y++) {
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
        initializeLineMap(width, height);
        for (Block b : board) {
            lineMap.get(b.getY()).add(b);
        }
    }

    public HashSet<Vector2D> getBlockPositions() {
        HashSet<Vector2D> positions = new HashSet<>();
        for (Block b : board) {
            positions.add(b.getPosition());
        }
        return positions;
    }

    public HashMap<Integer, HashSet<Block>> getLineMap() {
        return lineMap;
    }

    public HashSet<Block> getBoard() {
        return board;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
