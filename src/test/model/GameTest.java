package model;


import model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Game emptyGame;

    @BeforeEach
    void setup() {
        emptyGame = new Game();
    }

    @Test
    void constructor() {
        assertEquals(0, emptyGame.getScore());
        assertTrue(emptyGame.getBoard().isEmpty());
        assertTrue(emptyGame.getGameStatus());
    }

    @Test
    void update() {
        // In theory I should just be able to run update a bunch and by default the game would end at some point

        while (emptyGame.getGameStatus()) {
            emptyGame.update();
        }
        assertFalse(emptyGame.getGameStatus());
    }

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