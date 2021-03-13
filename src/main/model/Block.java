package model;

import java.awt.*;

public class Block {

    public static final int SIZE_X = 40;
    public static final int SIZE_Y = 40;

    private Vector2D position;
    private final Color color;

    // REQUIRES : 0 <= red, green, blue <= 255
    // MODIFIES : this
    // EFFECTS  : Creates a new block at the given position with the given color.
    public Block(int x, int y, Color color) {
        this.position = new Vector2D(x, y);
        this.color = color;
    }

    // REQUIRES : 0 <= red, green, blue <= 255
    // MODIFIES : this
    // EFFECTS  : Creates a new block at the given position with the given color.
    public Block(Vector2D position, Color color) {
        this.position = new Vector2D(position.getX(), position.getY());
        this.color = color;
    }

    // EFFECTS  : Returns the position of the block.
    public Vector2D getPosition() {
        return position;
    }

    public int getX() {
        return position.getX();
    }

    public int getY() {
        return position.getY();
    }

    // MODIFIES : this
    // EFFECTS  : Sets the position
    public void setPosition(Vector2D newPosition) {
        this.position = newPosition;
    }

    // EFFECTS  : Returns the color of the block.
    public Color getColor() {
        return color;
    }

    @Override
    // TODO: This does not seem to work properly, maybe remove it???
    // REQUIRES : Object o needs to be a Block
    // EFFECTS  : Compares a given object to this block
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Block)) {
            return false;
        } else {
            Block b = (Block) o;
            return ((b.getColor() == this.color) && (b.getPosition().equals(this.position)));
        }
    }

    @Override
    // EFFECTS  : Computes a hashcode for this Block
    public int hashCode() {
        return position.hashCode() * color.hashCode();
    }
}
