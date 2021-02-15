package model;

import java.util.ArrayList;

// Stores information about many players in an ArrayList ordered by score
public class Leaderboard {

    // Online says this should be CAMEL_CASE but checkstyle says that's an error
    private final ArrayList<Player> leaderboard;

    // MODIFIES : this
    // EFFECTS  : Creates an empty Leaderboard
    public Leaderboard() {
        this.leaderboard = new ArrayList<>();
    }

    // MODIFIES : this.leaderboard
    // EFFECTS  : Adds a player in the correct place on the leaderboard
    public void addPlayer(Player player) {
        int position = 0;

        for (Player p : leaderboard) {
            if (p.getScore() >= player.getScore()) {
                position++;
            }
        }
        leaderboard.add(position, player);
    }

    // REQUIRES : position >= 0
    // EFFECTS  : Returns the player at the specified positions
    public Player getPlayer(int position) {
        return leaderboard.get(position);
    }

    // EFFECTS  : Returns the leaderboard
    public ArrayList<Player> getLeaderboard() {
        return leaderboard;
    }

    // EFFECTS  : Returns the size of the leaderboard
    public int getSize() {
        return leaderboard.size();
    }
}
