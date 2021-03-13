package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TetrisGameTest {

    private TetrisGame emptyTetrisGame;

    @BeforeEach
    void setup() {
        emptyTetrisGame = new TetrisGame();
    }

    @Test
    void constructor() {
        assertEquals(0, emptyTetrisGame.getScore());
        assertTrue(emptyTetrisGame.getBoard().isEmpty());
        assertTrue(emptyTetrisGame.getGameStatus());
    }

//    @Test
//    void update() {
//        // In theory I should just be able to run update a bunch and by default the game would end at some point
//
//        while (emptyTetrisGame.getGameStatus()) {
//            emptyTetrisGame.update();
//        }
//        assertFalse(emptyTetrisGame.getGameStatus());
//    }

    @Test
    void keyPressed() {
    }

    @Test
    void getScore() {
    }

    @Test
    void getBoard() {
    }
}