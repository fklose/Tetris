package model;

import java.util.ArrayList;

// Stores information about many players in a list ordered by score
public class Leaderboard {

    private final ArrayList<Player> leaderboard;

    // REQUIRES :
    // MODIFIES :
    // EFFECTS  : Creates an empty Leaderboard
    public Leaderboard() {
        this.leaderboard = new ArrayList<>();
    }

    // REQUIRES :
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
    // MODIFIES :
    // EFFECTS  : Returns the player at the specified positions
    public Player getPlayer(int position) {
        return leaderboard.get(position);
    }

    // REQUIRES :
    // MODIFIES :
    // EFFECTS  : Returns the leaderboard
    public ArrayList<Player> getLeaderboard() {
        return leaderboard;
    }

    // REQUIRES :
    // MODIFIES :
    // EFFECTS  : Returns the size of the leaderboard
    public int getSize() {
        return leaderboard.size();
    }
}
