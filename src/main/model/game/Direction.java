package model.game;

// Enumerates all possible directions needed for the game
public enum Direction {
    DOWN(0, 1),
    LEFT(-1, 0),
    RIGHT(1, 0),
    NULL(0, 0);

    private final Vector2D vector;
    private final Vector2D oppositeVector;

    // EFFECTS  : Creates a new direction vector with given vector components
    Direction(int dx, int dy) {
        this.vector = new Vector2D(dx, dy);
        this.oppositeVector = new Vector2D(-dx, -dy);
    }

    public Vector2D getVector() {
        return this.vector;
    }

    public Vector2D getOppositeVector() {
        return this.oppositeVector;
    }
}
