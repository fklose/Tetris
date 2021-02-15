package model;

// Represents a matrix and its product with a vector to compute counterclockwise rotations
public class RotationMatrix2x2 {

    private static final int ENTRY_UPPER_LEFT = 0;
    private static final int ENTRY_UPPER_RIGHT = 1;
    private static final int ENTRY_LOWER_LEFT = -1;
    private static final int ENTRY_LOWER_RIGHT = 0;

    // EFFECTS  : Returns the result of multiplying this matrix by a given vector
    public Vector2D matrixVectorProduct(Vector2D vec) {
        int newX = this.ENTRY_UPPER_LEFT * vec.getComponentX() + this.ENTRY_UPPER_RIGHT * vec.getComponentY();
        int newY = this.ENTRY_LOWER_LEFT * vec.getComponentX() + this.ENTRY_LOWER_RIGHT * vec.getComponentY();
        return new Vector2D(newX, newY);
    }
}
