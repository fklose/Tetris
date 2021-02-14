package model;

import java.util.ArrayList;
import java.util.LinkedList;

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
            new Vector2D(0, -1));

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
        ArrayList<Vector2D> positions = new ArrayList<Vector2D>();
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
        // STUB
    }

    // REQUIRES :
    // MODIFIES :
    // EFFECTS  : Rotates Tetromino ninety degrees counterclockwise
    public void rotate() {
        // STUB
    }
}
