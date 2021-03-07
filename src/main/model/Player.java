package model;

import org.json.JSONObject;
import persistence.Writeable;

// Stores information about a player, such as name and score
public class Player implements Writeable {

    private final String name;
    private final int score;

    // REQUIRES : score >= 0
    // EFFECTS  : Creates a new player with a given score and name
    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }

    // EFFECTS  : Returns the players name
    public String getName() {
        return this.name;
    }

    // EFFECTS  : Returns the players score
    public int getScore() {
        return this.score;
    }

    @Override
    // TODO: DO I NEED REQUIRES, MODIFIES, ... here???
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", this.name);
        json.put("score", this.score);
        return json;
    }
}
