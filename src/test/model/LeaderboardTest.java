package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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

    @Test
    void getLeaderboard() {
        leaderboard1.addPlayer(player1);
        leaderboard1.addPlayer(player4);
        leaderboard1.addPlayer(player2);
        leaderboard1.addPlayer(player3);

        ArrayList<Player> result = new ArrayList<>();
        result.add(0, player1);
        result.add(0, player2);
        result.add(0, player3);
        result.add(0, player4);
        ArrayList<Player> players = leaderboard1.getLeaderboard();

        checkPlayerListsEqual(leaderboard1, result);
    }

    private void checkCorrectPlayerCorrectPosition(Leaderboard leaderboard, int position, Player player) {
        assertEquals(player, leaderboard.getPlayer(position), "Player is not in correct position!");
    }

    private void checkPlayerListsEqual(Leaderboard leaderboard, ArrayList<Player> result) {
        int size = result.size();
        for (int i = 0 ; i < size ; i++) {
            Player player1 = result.get(i);
            checkCorrectPlayerCorrectPosition(leaderboard, i, player1);
        }
    }
}