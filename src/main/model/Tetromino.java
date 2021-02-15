package model;

import java.util.ArrayList;

public enum Tetromino {
    shapeLine("Line",
            new Vector2D(0, 1),
            new Vector2D(0, 0),
            new Vector2D(0, -1),
            new Vector2D(0, -2)),
    shapeSquare("Square",
            new Vector2D(0, 0),
            new Vector2D(1, 0),
            new Vector2D(1, -1),
            new Vector2D(0, -1)),
    shapeT("T Shape",
            new Vector2D(0, 0),
            new Vector2D(-1, 0),
            new Vector2D(1, 0),
            new Vector2D(0, -1)),
    shapeLeftSkew("Left Skew",
            new Vector2D(0, 0),
            new Vector2D(0, 1),
            new Vector2D(1, 0),
            new Vector2D(1, -1)),
    shapeRightSkew("Right Skew",
            new Vector2D(0, 0),
            new Vector2D(0, 1),
            new Vector2D(-1, 0),
            new Vector2D(-1, -1)),
    shapeLeftL("Left L",
            new Vector2D(0, 0),
            new Vector2D(0, 1),
            new Vector2D(-1, 1),
            new Vector2D(0, -1)),
    shapeRightL("Right L",
            new Vector2D(0, 0),
            new Vector2D(0, 1),
            new Vector2D(1, 1),
            new Vector2D(0, -1)),
    nullShape("null",
            new Vector2D(0, 0),
            new Vector2D(0, 0),
            new Vector2D(0, 0),
            new Vector2D(0, 0));

    // TODO: Maybe store individual blocks as an arraylist from the get go instead of individually
    private Vector2D block1;
    private Vector2D block2;
    private Vector2D block3;
    private Vector2D block4;
    private String name;

    // REQUIRES :
    // MODIFIES : this
    // EFFECTS  : Creates a Tetromino given a name and
    //          four vectors describing the positions of the 4 blocks making up the Tetromino
    Tetromino(String name, Vector2D block1, Vector2D block2, Vector2D block3, Vector2D block4) {
        this.name = name;
        this.block1 = block1;
        this.block2 = block2;
        this.block3 = block3;
        this.block4 = block4;
    }

    // REQUIRES :
    // MODIFIES :
    // EFFECTS  : Returns the name of this Tetromino
    public String getName() {
        return this.name;
    }

    // REQUIRES :
    // MODIFIES :
    // EFFECTS  : Returns the positions of the blocks of the Tetrominos
    public ArrayList<Vector2D> getPositions() {
        ArrayList<Vector2D> positions = new ArrayList<>();
        positions.add(block1);
        positions.add(block2);
        positions.add(block3);
        positions.add(block4);
        return positions;
    }

    // REQUIRES :
    // MODIFIES : this
    // EFFECTS  : Moves Tetromino by adding a direction vector to all position vectors
    public void move(Vector2D direction) {
        Vector2D newBlock1 = this.block1.addVector(direction);
        Vector2D newBlock2 = this.block2.addVector(direction);
        Vector2D newBlock3 = this.block3.addVector(direction);
        Vector2D newBlock4 = this.block4.addVector(direction);

        this.block1 = newBlock1;
        this.block2 = newBlock2;
        this.block3 = newBlock3;
        this.block4 = newBlock4;
    }

    // REQUIRES :
    // MODIFIES :
    // EFFECTS  : Rotates Tetromino ninety degrees counterclockwise
    public void rotate() {
        RotationMatrix2x2 matrix = new RotationMatrix2x2();
        Vector2D newBlock1 = matrix.matrixVectorProduct(block1);
        Vector2D newBlock2 = matrix.matrixVectorProduct(block2);
        Vector2D newBlock3 = matrix.matrixVectorProduct(block3);
        Vector2D newBlock4 = matrix.matrixVectorProduct(block4);

        this.block1 = newBlock1;
        this.block2 = newBlock2;
        this.block3 = newBlock3;
        this.block4 = newBlock4;
    }
}
