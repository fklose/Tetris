package model;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

// Stores different types of Tetrominos and methods relating to them
public enum Tetromino {
    shapeLine("Line", Color.CYAN,
            new Vector2D(0, 1),
            new Vector2D(0, 0),
            new Vector2D(0, -1),
            new Vector2D(0, -2)),
    shapeSquare("Square", Color.YELLOW,
            new Vector2D(1, 0),
            new Vector2D(0, 0),
            new Vector2D(1, -1),
            new Vector2D(0, -1)),
    shapeT("T Shape", Color.MAGENTA,
            new Vector2D(0, 0),
            new Vector2D(-1, 0),
            new Vector2D(1, 0),
            new Vector2D(0, -1)),
    shapeLeftSkew("Left Skew", Color.PINK,
            new Vector2D(0, 0),
            new Vector2D(0, 1),
            new Vector2D(1, 0),
            new Vector2D(1, -1)),
    shapeRightSkew("Right Skew", Color.GREEN,
            new Vector2D(0, 0),
            new Vector2D(0, 1),
            new Vector2D(-1, 0),
            new Vector2D(-1, -1)),
    shapeLeftL("Left L", Color.ORANGE,
            new Vector2D(0, 0),
            new Vector2D(0, 1),
            new Vector2D(-1, 1),
            new Vector2D(0, -1)),
    shapeRightL("Right L", Color.BLUE,
            new Vector2D(0, 0),
            new Vector2D(0, 1),
            new Vector2D(1, 1),
            new Vector2D(0, -1)),
    nullShape("null", Color.BLACK,
            new Vector2D(0, 0),
            new Vector2D(0, 0),
            new Vector2D(0, 0),
            new Vector2D(0, 0));

    private static final RotationMatrix2x2 COUNTER_CLOCKWISE = new RotationMatrix2x2();

    // Maybe store individual blocks as an arraylist from the get go instead of individually
    private final ArrayList<Block> blocks;
    private final String name;

    // MODIFIES : this
    // EFFECTS  : Creates a Tetromino given a name, a color and the positions of the four blocks that make up the
    //          Tetromino
    Tetromino(String name, Color color, Vector2D... positions) {
        this.name = name;
        this.blocks = new ArrayList<>();

        for (Vector2D pos: positions) {
            this.blocks.add(new Block(pos.getX(), pos.getY(), color));
        }
    }

    // EFFECTS  : Returns the name of this Tetromino
    public String getName() {
        return this.name;
    }

    // EFFECTS  : Returns the positions of the blocks of the Tetrominos
    public ArrayList<Vector2D> getPositions() {
        ArrayList<Vector2D> positions = new ArrayList<>();

        for (Block b : blocks) {
            positions.add(b.getPosition());
        }
        return positions;
    }

    // EFFECTS  : Returns copies of the positions of the blocks of the Tetrominos
    public ArrayList<Vector2D> getPositionsCopy() {
        ArrayList<Vector2D> positions = new ArrayList<>();

        for (Block b : blocks) {
            positions.add(b.getPositionCopy());
        }
        return positions;
    }

    // EFFECTS  : Returns the blocks making of the Tetromino
    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    // EFFECTS  : Returns the blocks making of the Tetromino
    public ArrayList<Block> getBlocksCopy() {
        ArrayList<Block> copy = new ArrayList<>();

        for (Block b : blocks) {
            copy.add(new Block(b.getX(), b.getY(), b.getColor()));
        }
        return copy;
    }

    // MODIFIES : this
    // EFFECTS  : Moves Tetromino by adding a direction vector to all position vectors
    public void move(Direction d) {
        for (Block b: blocks) {
            b.setPosition(b.getPosition().addVector(d.getVector()));
        }
    }

    // MODIFIES : this
    // EFFECTS  : Places Tetromino at given position
    public void setPosition(Vector2D pos) {
        for (Block b : blocks) {
            b.setPosition(b.getPosition().addVector(pos));
        }
    }

    // MODIFIES : this
    // EFFECTS  : Rotates Tetromino ninety degrees counterclockwise
    public void rotate() {
        for (Block b: blocks) {
            b.setPosition(COUNTER_CLOCKWISE.matrixVectorProduct(b.getPosition()));
        }
    }
}
