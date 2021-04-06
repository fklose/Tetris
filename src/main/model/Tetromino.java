package model;

import java.awt.*;
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
            new Vector2D(0, -1)) {

        @Override
        public void rotateCCW() {
        }

        @Override
        public void rotateCW() {
        }
    },
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
            new Vector2D(0, 0));

    private final ArrayList<Vector2D> template;
    private Vector2D centre;
    private final String name;
    private final Color color;

    private static final RotationMatrix2D CCW = RotationMatrix2D.COUNTERCLOCKWISE;
    private static final RotationMatrix2D CW = RotationMatrix2D.CLOCKWISE;

    // REQUIRES : One of the vectors in position needs to be the zero vector i.e. like new Vector2D(0, 0)
    //          which will act as the center of the block
    // MODIFIES : this
    // EFFECTS  : Creates a Tetromino given a name, a color and the positions of the four blocks that make up the
    //          Tetromino
    Tetromino(String name, Color color, Vector2D... positions) {
        this.name = name;
        this.color = color;
        this.centre = new Vector2D(0,0);
        this.template = new ArrayList<>();
        this.template.addAll(Arrays.asList(positions));
    }

    public String getName() {
        return this.name;
    }

    public Vector2D getCentre() {
        return centre;
    }

    public ArrayList<Vector2D> getTemplate() {
        return template;
    }

    // EFFECTS  : Creates and returns the blocks making up the Tetromino
    public ArrayList<Block> getBlocks() {
        ArrayList<Block> blocks = new ArrayList<>();

        for (Vector2D pos : template) {
            blocks.add(new Block(pos.addVectorGetNewVector(centre), this.color));
        }
        return blocks;
    }

    // MODIFIES : this
    // EFFECTS  : Moves Tetromino by adding a direction vector to all position vectors
    public void move(Direction d) {
        this.centre.addVectorInPlace(d.getVector());
    }

    // MODIFIES : this
    // EFFECTS  : Sets the centre of the Tetromino
    public void setCentre(Vector2D newCentre) {
        this.centre = newCentre;
    }

    // MODIFIES : this
    // EFFECTS  : Rotates Tetromino ninety degrees counterclockwise
    public void rotateCCW() {
        for (Vector2D vec : template) {
            CCW.matrixVectorProductInPlace(vec);
        }
    }

    // MODIFIES : this
    // EFFECTS  : Rotates Tetromino ninety degrees clockwise
    public void rotateCW() {
        for (Vector2D vec : template) {
            CW.matrixVectorProductInPlace(vec);
        }
    }

    // EFFECTS : Computes and returns the positions of all blocks of the Tetromino
    public ArrayList<Vector2D> getPositions() {
        ArrayList<Vector2D> positions = new ArrayList<>();
        for (Vector2D pos : this.template) {
            positions.add(pos.addVectorGetNewVector(this.centre));
        }
        return positions;
    }

    // TODO: NEEDS TESTING
    public Color getColor() {
        return color;
    }
}
