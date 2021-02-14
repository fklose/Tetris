package model;

import java.util.Vector;

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

    // REQUIRES :
    // MODIFIES : this
    // EFFECTS  : Returns the sum of this vector and a given vector
    public Vector2D addVector(Vector2D vec) {
        int newX = this.componentX + vec.getComponentX();
        int newY = this.componentY + vec.getComponentY();
        return new Vector2D(newX, newY);
    }

    // REQUIRES :
    // MODIFIES :
    // EFFECTS  : Returns x component of a given vector
    public int getComponentX() {
        return this.componentX;
    }

    // REQUIRES :
    // MODIFIES :
    // EFFECTS  : Returns y component of a given vector
    public int getComponentY() {
        return this.componentY;
    }
}
