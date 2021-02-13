package model;

// Stores information about a player, such as name and score
public class Player {

    private String name;
    private int score;

    // REQUIRES : score >= 0
    // MODIFIES :
    // EFFECTS  : Creates a new player with a given score and name
    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }

    // REQUIRES :
    // MODIFIES :
    // EFFECTS  : Returns the players name
    public String getName() {
        return this.name;
    }

    // REQUIRES :
    // MODIFIES :
    // EFFECTS  : Returns the players score
    public int getScore() {
        return this.score;
    }
}
