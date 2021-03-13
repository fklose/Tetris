package model;

// Represents a matrix and its product with a vector to compute counterclockwise rotations
public enum RotationMatrix2x2 {
    CLOCKWISE(0,1, -1, 0),
    COUNTERCLOCKWISE(0, -1, 1, 0);

    private final int upperLeft;
    private final int upperRight;
    private final int lowerLeft;
    private final int lowerRight;

    RotationMatrix2x2(int upperLeft, int upperRight, int lowerLeft, int lowerRight) {
        this.upperLeft = upperLeft;
        this.upperRight = upperRight;
        this.lowerLeft = lowerLeft;
        this.lowerRight = lowerRight;
    }

    // EFFECTS  : Returns the result of multiplying this matrix by a given vector
    public Vector2D matrixVectorProductGetNewVec(Vector2D vec) {
        int newX = this.upperLeft * vec.getX() + this.upperRight * vec.getY();
        int newY = this.lowerLeft * vec.getX() + this.lowerRight * vec.getY();
        return new Vector2D(newX, newY);
    }

    // MODIFIES : vec
    // EFFECTS  : Changes the given vector to the result of multiplying the matrix by the given vector
    public void matrixVectorProductInPlace(Vector2D vec) {
        int newX = this.upperLeft * vec.getX() + this.upperRight * vec.getY();
        int newY = this.lowerLeft * vec.getX() + this.lowerRight * vec.getY();
        vec.setX(newX);
        vec.setY(newY);
    }
}
