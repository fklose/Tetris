package model;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vector2D vector2D = (Vector2D) o;
        return componentX == vector2D.componentX
                && componentY == vector2D.componentY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(componentX, componentY);
    }
}
