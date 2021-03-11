package model;

public enum Direction {
    DOWN(0, 1),
    LEFT(-1, 0),
    RIGHT(1, 0),
    NULL(0, 0);

    private Vector2D vector;
    private Vector2D oppositeVector;

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
