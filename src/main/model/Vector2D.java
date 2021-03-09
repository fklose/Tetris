package model;

// A class containing information and methods relating to a 2d Vector
public class Vector2D {

    private final int componentX;
    private final int componentY;

    // REQUIRES : x, y need to be Integer
    // MODIFIES : this
    // EFFECTS  : Make a new 2d Vector
    public Vector2D(int x, int y) {
        this.componentX = x;
        this.componentY = y;
    }

    // MODIFIES : this
    // EFFECTS  : Returns the sum of this vector and a given vector
    public Vector2D addVector(Vector2D vec) {
        int newX = this.componentX + vec.getX();
        int newY = this.componentY + vec.getY();
        return new Vector2D(newX, newY);
    }

    // EFFECTS  : Returns x component of a given vector
    public int getX() {
        return this.componentX;
    }

    // EFFECTS  : Returns y component of a given vector
    public int getY() {
        return this.componentY;
    }

    @Override
    // REQUIRES :
    // MODIFIES :
    // EFFECTS  :
    public boolean equals(Object o) {
        if (o instanceof Vector2D) {
            Vector2D v = (Vector2D) o;
            return (this.componentX == v.componentX && this.componentY == v.componentY);
        } else {
            return false;
        }
    }
}
