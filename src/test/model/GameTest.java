package model;


import model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    }
}