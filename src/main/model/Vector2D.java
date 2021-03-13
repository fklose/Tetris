package model;

// A class containing information and methods relating to a 2d Vector
public class Vector2D {

    private int componentX;
    private int componentY;

    // REQUIRES : x, y need to be Integer
    // MODIFIES : this
    // EFFECTS  : Make a new 2d Vector
    public Vector2D(int x, int y) {
        this.componentX = x;
        this.componentY = y;
    }

    // EFFECTS  : Returns the sum of this vector and a given vector
    public Vector2D addVectorGetNewVector(Vector2D vec) {
        int newX = this.componentX + vec.getX();
        int newY = this.componentY + vec.getY();
        return new Vector2D(newX, newY);
    }

    // MODIFIES : this
    // EFFECTS  : Returns the sum of this vector and a given vector
    public void addVectorInPlace(Vector2D vec) {
        this.componentX += vec.getX();
        this.componentY += vec.getY();
    }

    // EFFECTS  : Returns x component of a given vector
    public int getX() {
        return this.componentX;
    }

    public void setX(int x) {
        this.componentX = x;
    }

    public void setY(int y) {
        this.componentY = y;
    }

    // EFFECTS  : Returns y component of a given vector
    public int getY() {
        return this.componentY;
    }

    @Override
    // TODO: Is there a way to have this throw an exception that tells you what part did not match?
    // TODO: Also how do I test this, do i even need to?
    public boolean equals(Object o) {
        if (o instanceof Vector2D) {
            Vector2D v = (Vector2D) o;
            return (this.componentX == v.componentX && this.componentY == v.componentY);
        } else {
            return false;
        }
    }
}
