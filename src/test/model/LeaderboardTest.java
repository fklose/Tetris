package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LeaderboardTest {

    private Player player1;
    private Player player2;
    private Player player3;
    private Player player4;

    private Leaderboard leaderboard1;

    @BeforeEach
    void setUp() {
        player1 = new Player("player1", 1);
        player2 = new Player("player2", 2);
        player3 = new Player("player3", 3);
        player4 = new Player("player4", 4);

        leaderboard1 = new Leaderboard();
    }

    @Test
    void addPlayer() {
        leaderboard1.addPlayer(player1);
        checkCorrectPlayerCorrectPosition(leaderboard1, 0, player1);

        leaderboard1.addPlayer(player4);
        checkCorrectPlayerCorrectPosition(leaderboard1, 1, player1);
        checkCorrectPlayerCorrectPosition(leaderboard1, 0, player4);

        leaderboard1.addPlayer(player2);
        checkCorrectPlayerCorrectPosition(leaderboard1, 2, player1);
        checkCorrectPlayerCorrectPosition(leaderboard1, 1, player2);
        checkCorrectPlayerCorrectPosition(leaderboard1, 0, player4);

        leaderboard1.addPlayer(player3);
        checkCorrectPlayerCorrectPosition(leaderboard1, 3, player1);
        checkCorrectPlayerCorrectPosition(leaderboard1, 2, player2);
        checkCorrectPlayerCorrectPosition(leaderboard1, 1, player3);
        checkCorrectPlayerCorrectPosition(leaderboard1, 0, player4);
    }


    private void checkCorrectPlayerCorrectPosition(Leaderboard leaderboard, int position, Player player) {
        assertEquals(player, leaderboard.getPlayer(position), "Player is not in correct position!");
    }
}