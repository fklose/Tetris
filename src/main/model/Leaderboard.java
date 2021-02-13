package model;

import java.util.ArrayList;

// Stores information about many players
public class Leaderboard {

    private ArrayList<Player> leaderboard;

    // REQUIRES :
    // MODIFIES :
    // EFFECTS  : Creates an empty Leaderboard
    public Leaderboard() {
        // STUB
    }

    // REQUIRES :
    // MODIFIES : this.leaderboard
    // EFFECTS  : Adds a player in the correct place on the leaderboard
    public void addPlayer(Player player) {
        // STUB
    }

    // REQUIRES :
    // MODIFIES :
    // EFFECTS  : Returns the player at the specified positions
    public Player getPlayer(int position) {
        return new Player("player0", 0); // STUB
    }

}
