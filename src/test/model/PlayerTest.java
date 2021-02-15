package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerTest {

    private Player player1;
    private Player player2;

    @BeforeEach
    void setUp() {
        player1 = new Player("player1", 0);
        player2 = new Player("player2", 32);
    }

    @Test
    void getName() {
        assertEquals("player1", player1.getName());
        assertEquals("player2", player2.getName());
    }

    @Test
    void getScore() {
        assertEquals(0, player1.getScore());
        assertEquals(32, player2.getScore());
    }
}