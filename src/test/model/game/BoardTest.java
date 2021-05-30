package model.game;

import model.exceptions.GameOverException;
import model.tetromino.NewTetromino;
import model.tetromino.SingleBlock;
import model.tetromino.Square;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    Board board0;
    Board board1;

    @BeforeEach
    void setup() {
        board0 = new Board(10, 20);
        board1 = new Board(5, 20);
    }

    @Test
    void placeTetrominoOnBoardNoGameOver() {
        NewTetromino square = new Square();
        square.setCentre(new Vector2D(0, 18));
        try {
            board0.placeTetrominoOnBoard(square);
            assertTrue(board0.getBoard().containsAll(square.getBlocks()));
        } catch (GameOverException goe) {
            fail();
        }

        /*
        Tetromino square = Tetromino.shapeSquare;
        square.setCentre(new Vector2D(0, 18));
        try {
            board0.placeTetrominoOnBoard(square);
            assertTrue(board0.getBoard().containsAll(square.getBlocks()));
        } catch (GameOverException goe) {
            fail();
        }
         */
    }

    @Test
    void placeTetrominoOnBoardGameOver() {
        NewTetromino square = new Square();
        square.setCentre(new Vector2D(4, -1));
        try {
            board0.placeTetrominoOnBoard(square);
            fail();
        } catch (GameOverException goe) {
            assertFalse(board0.getBoard().containsAll(square.getBlocks()));
        }
        /*
        Tetromino square = Tetromino.shapeSquare;
        square.setCentre(new Vector2D(4, -1));
        try {
            board0.placeTetrominoOnBoard(square);
            fail();
        } catch (GameOverException goe) {
            assertFalse(board0.getBoard().containsAll(square.getBlocks()));
        }
         */
    }

    @Test
    void clearLineNoShift() {
        makeLine(board0, 19);
        assertEquals(10, board0.getBoard().size());
        ArrayList<Integer> lineNumbers = new ArrayList<>();
        lineNumbers.add(19);
        board0.clearLines(lineNumbers);
        assertEquals(0, board0.getBoard().size());
    }

    @Test
    void clearLinesNoShift() {
        for (int y = 18; y < 20; y++) {
            makeLine(board0, y);
        }
        assertEquals(20, board0.getBoard().size());
        ArrayList<Integer> lineNumbers = new ArrayList<>();
        lineNumbers.add(19);
        lineNumbers.add(18);
        board0.clearLines(lineNumbers);
        assertEquals(0, board0.getBoard().size());
    }

    @Test
    void clearLineWithShift() {
        makeLine(board0, 19);

        // Adding some nullShapes above the bottom row to check if lines are shifted properly
        ArrayList<Vector2D> result = new ArrayList<>();
        result.add(new Vector2D(0, 0));
        result.add(new Vector2D(3, 3));

        for (Vector2D v : result) {
            v.addVectorInPlace(new Vector2D(0, 1));
        }

        assertEquals(12, board0.getBoard().size());
        ArrayList<Integer> lineNumbers = new ArrayList<>();
        lineNumbers.add(19);
        board0.clearLines(lineNumbers);
        assertEquals(2, board0.getBoard().size());

        for (Vector2D pos : result) {
            assertTrue(board0.getBlockPositions().contains(pos));
        }
    }

    private void makeLine(Board board, int lineNumber) {
        for (int x = 0; x < board.getWidth(); x++) {
            NewTetromino nullShape = new SingleBlock();
            nullShape.setCentre(new Vector2D(x, lineNumber));
            try {
                board.placeTetrominoOnBoard(nullShape);
            } catch (GameOverException goe) {
                fail();
            }
        }
        /*
        for (int x = 0; x < board.getWidth(); x++) {
            Tetromino nullShape = Tetromino.nullShape;
            nullShape.setCentre(new Vector2D(x, lineNumber));
            try {
                board.placeTetrominoOnBoard(nullShape);
            } catch (GameOverException goe) {
                fail();
            }
        }
         */
    }

    private NewTetromino placeNullShape(Board board, int x, int y) {
        NewTetromino nullShape = new SingleBlock();
        nullShape.setCentre(new Vector2D(x, y));
        try {
            board.placeTetrominoOnBoard(nullShape);
        } catch (GameOverException goe) {
            fail();
        }
        return nullShape;
    }
    /*
    private Tetromino placeNullShape(Board board, int x, int y) {
        Tetromino nullShape = Tetromino.nullShape;
        nullShape.setCentre(new Vector2D(x, y));
        try {
            board.placeTetrominoOnBoard(nullShape);
        } catch (GameOverException goe) {
            fail();
        }
        return nullShape;
    }
     */

    private void moveDown(Tetromino t) {
        t.move(Direction.DOWN);
    }
}