package model.game;

import model.exceptions.GameOverException;
import model.tetromino.SingleBlock;
import model.tetromino.Square;
import model.tetromino.Tetromino;
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
    void getHeightAndWidth() {
        assertEquals(20, board0.getHeight());
        assertEquals(10, board0.getWidth());

        assertEquals(20, board1.getHeight());
        assertEquals(5, board1.getWidth());
    }

    @Test
    void placeTetrominoOnBoardNoGameOver() {
        Tetromino square = new Square();
        square.setCentre(new Vector2D(0, 18));
        try {
            board0.placeTetrominoOnBoard(square);
            assertTrue(board0.getBoard().containsAll(square.getBlocks()));
        } catch (GameOverException goe) {
            fail();
        }
    }

    @Test
    void placeTetrominoOnBoardGameOver() {
        Tetromino square = new Square();
        square.setCentre(new Vector2D(4, -1));
        try {
            board0.placeTetrominoOnBoard(square);
            fail();
        } catch (GameOverException goe) {
            assertFalse(board0.getBoard().containsAll(square.getBlocks()));
        }
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
        placeNullShape(board0, 0, 0);
        placeNullShape(board0, 5, 9);

        assertEquals(12, board0.getBoard().size());
        ArrayList<Integer> lineNumbers = new ArrayList<>();
        lineNumbers.add(19);
        board0.clearLines(lineNumbers);
        assertEquals(2, board0.getBoard().size());

        assertTrue(board0.getBlockPositions().contains(new Vector2D(0, 1)));
        assertTrue(board0.getBlockPositions().contains(new Vector2D(5, 10)));
    }

    @Test
    void clearLinesWithShifts() {
        makeLine(board1, 15);
        makeLine(board1, 10);

        placeNullShape(board1, 1, 5);
        placeNullShape(board1, 3, 12);
        placeNullShape(board1, 4, 19);

        assertEquals(13, board1.getBoard().size());
        ArrayList<Integer> lineNumbers = new ArrayList<>();
        lineNumbers.add(15);
        lineNumbers.add(10);
        board1.clearLines(lineNumbers);
        assertEquals(3, board1.getBoard().size());

        assertTrue(board1.getBlockPositions().contains(new Vector2D(1, 7)));
        assertTrue(board1.getBlockPositions().contains(new Vector2D(3, 13)));
        assertTrue(board1.getBlockPositions().contains(new Vector2D(4, 19)));
    }

    private void makeLine(Board board, int lineNumber) {
        for (int x = 0; x < board.getWidth(); x++) {
            Tetromino nullShape = new SingleBlock();
            nullShape.setCentre(new Vector2D(x, lineNumber));
            try {
                board.placeTetrominoOnBoard(nullShape);
            } catch (GameOverException goe) {
                fail();
            }
        }
    }

    private Tetromino placeNullShape(Board board, int x, int y) {
        Tetromino nullShape = new SingleBlock();
        nullShape.setCentre(new Vector2D(x, y));
        try {
            board.placeTetrominoOnBoard(nullShape);
        } catch (GameOverException goe) {
            fail();
        }
        return nullShape;
    }
}