package model;

import java.awt.*;
import java.util.ArrayList;

// Stores different types of Tetrominos and methods relating to them
public enum Tetromino {
    shapeLine("Line", Color.CYAN,
            new Vector2D(0, 1),
            new Vector2D(0, 0),
            new Vector2D(0, -1),
            new Vector2D(0, -2)),
    shapeSquare("Square", Color.YELLOW,
            new Vector2D(0, 0),
            new Vector2D(1, 0),
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

    // Maybe store individual blocks as an arraylist from the get go instead of individually
    private final Block block1;
    private final Block block2;
    private final Block block3;
    private final Block block4;
    private final String name;

    // MODIFIES : this
    // EFFECTS  : Creates a Tetromino given a name, a color and the positions of the four blocks that make up the
    //          Tetromino
    Tetromino(String name, Color color, Vector2D pos1, Vector2D pos2, Vector2D pos3, Vector2D pos4) {
        this.name = name;
        this.block1 = new Block(pos1.getX(), pos1.getY(), color);
        this.block2 = new Block(pos2.getX(), pos2.getY(), color);
        this.block3 = new Block(pos3.getX(), pos3.getY(), color);
        this.block4 = new Block(pos4.getX(), pos4.getY(), color);
    }

    // EFFECTS  : Returns the name of this Tetromino
    public String getName() {
        return this.name;
    }

    // EFFECTS  : Returns the positions of the blocks of the Tetrominos
    public ArrayList<Vector2D> getPositions() {
        ArrayList<Vector2D> positions = new ArrayList<>();
        positions.add(block1.getPosition());
        positions.add(block2.getPosition());
        positions.add(block3.getPosition());
        positions.add(block4.getPosition());
        return positions;
    }

    // EFFECTS  : Returns the blocks making of the Tetromino
    public ArrayList<Block> getBlocks() {
        ArrayList<Block> blocks = new ArrayList<>();
        blocks.add(block1);
        blocks.add(block2);
        blocks.add(block3);
        blocks.add(block4);
        return blocks;
    }

    // MODIFIES : this
    // EFFECTS  : Moves Tetromino by adding a direction vector to all position vectors
    public void move(Direction d) {
        block1.setPosition(block1.getPosition().addVector(d.getVector()));
        block2.setPosition(block2.getPosition().addVector(d.getVector()));
        block3.setPosition(block3.getPosition().addVector(d.getVector()));
        block4.setPosition(block4.getPosition().addVector(d.getVector()));
    }

    // MODIFIES : this
    // EFFECTS  : Rotates Tetromino ninety degrees counterclockwise
    public void rotate() {
        RotationMatrix2x2 matrix = new RotationMatrix2x2();
        block1.setPosition(matrix.matrixVectorProduct(block1.getPosition()));
        block2.setPosition(matrix.matrixVectorProduct(block2.getPosition()));
        block3.setPosition(matrix.matrixVectorProduct(block3.getPosition()));
        block4.setPosition(matrix.matrixVectorProduct(block4.getPosition()));
    }
}
