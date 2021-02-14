package model;

import java.util.ArrayList;

// Stores information about many players in a list ordered by score
public class Leaderboard {

    private ArrayList<Player> leaderboard;

    // REQUIRES :
    // MODIFIES :
    // EFFECTS  : Creates an empty Leaderboard
    public Leaderboard() {
        this.leaderboard = new ArrayList<Player>();
    }

    // REQUIRES :
    // MODIFIES : this.leaderboard
    // EFFECTS  : Adds a player in the correct place on the leaderboard
    public void addPlayer(Player player) {
        int position = 0;

        if (leaderboard.size() == 0) {
            leaderboard.add(player);
            return;
        }
        for (Player p: leaderboard) {
            if (p.getScore() >= player.getScore()) {
                position++;
            } else {
                leaderboard.add(position, player);
                break;
            }
        }
    }

    // REQUIRES : position >= 0
    // MODIFIES :
    // EFFECTS  : Returns the player at the specified positions
    public Player getPlayer(int position) {
        return leaderboard.get(position);
    }

}
