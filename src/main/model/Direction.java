package model;

public enum Direction {
    DOWN(0, 1),
    LEFT(-1, 0),
    RIGHT(1, 0);

    private Vector2D vector;

    Direction(int dx, int dy) {
        this.vector = new Vector2D(dx, dy);
    }

    public Vector2D getVector() {
        return this.vector;
    }
}
