package model;

import java.awt.*;
import java.util.Objects;

public class Block {

    public static final int SIZE_X = 40;
    public static final int SIZE_Y = 40;

    private Vector2D position;
    private final Color color;

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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Block block = (Block) o;
        return position.equals(block.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }
}
