package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writeable;

import java.util.ArrayList;

// Stores information about many players in an ArrayList ordered by score
public class Leaderboard implements Writeable {

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

    // REQUIRES : 0 <= position < leaderboard.size()
    // EFFECTS  : Returns the player at the specified position
    public Player getPlayer(int position) {
        return leaderboard.get(position);
    }

    public ArrayList<Player> getLeaderboard() {
        return leaderboard;
    }

    public int getSize() {
        return leaderboard.size();
    }

    @Override
    // EFFECTS  : Returns a JSONObject containing all relevant information about this leaderboard
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("players", playersToJson());
        return json;
    }

    // EFFECTS  : Returns the players in this leaderboard as a JSON array
    private JSONArray playersToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Player p : leaderboard) {
            jsonArray.put(p.toJson());
        }
        return jsonArray;
    }
}
