package model.tetromino;

import model.game.Block;
import model.game.Direction;
import model.game.RotationMatrix2D;
import model.game.Vector2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public abstract class NewTetromino {

    protected ArrayList<Vector2D> template = new ArrayList<>();
    private Vector2D center = new Vector2D(0, 0);
    private final String name;
    private final Color color;

    private static final RotationMatrix2D CCW = RotationMatrix2D.COUNTERCLOCKWISE;
    private static final RotationMatrix2D CW = RotationMatrix2D.CLOCKWISE;

    NewTetromino(String name, Color color, Vector2D... positions) {
        this.name = name;
        this.color = color;
        this.template.addAll(Arrays.asList(positions));
    }

    // MODIFIES : this
    // EFFECTS  : Rotates Tetromino ninety degrees clockwise
    public void rotateCW() {
        for (Vector2D vec : template) {
            CW.matrixVectorProductInPlace(vec);
        }
    }

    // MODIFIES : this
    // EFFECTS  : Rotates Tetromino ninety degrees counterclockwise
    public void rotateCCW() {
        for (Vector2D vec : template) {
            CCW.matrixVectorProductInPlace(vec);
        }
    }

    // MODIFIES : this
    // EFFECTS  : Sets the centre of the Tetromino
    public void setCentre(Vector2D newCentre) {
        this.center = newCentre;
    }

    // MODIFIES : this
    // EFFECTS  : Moves Tetromino by adding a direction vector to all position vectors
    public void move(Direction d) {
        this.center.addVectorInPlace(d.getVector());
    }

    // EFFECTS : Computes and returns the positions of all blocks of the Tetromino
    public ArrayList<Vector2D> getPositions() {
        ArrayList<Vector2D> positions = new ArrayList<>();
        for (Vector2D pos : this.template) {
            positions.add(pos.addVectorGetNewVector(this.center));
        }
        return positions;
    }

    // EFFECTS  : Creates and returns the blocks making up the Tetromino
    public ArrayList<Block> getBlocks() {
        ArrayList<Block> blocks = new ArrayList<>();

        for (Vector2D pos : template) {
            blocks.add(new Block(pos.addVectorGetNewVector(center), this.color));
        }
        return blocks;
    }

    public ArrayList<Vector2D> getTemplate() {
        return template;
    }

    public Vector2D getCentre() {
        return center;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public NewTetromino copy() {
        switch (name) {
            case "LeftL":
                return new LeftL();
            case "LeftSkew":
                return new LeftSkew();
            case "Line":
                return new Line();
            case "RightL":
                return new RightL();
            case "RightSkew":
                return new RightSkew();
            case "Square":
                return new Square();
            case "TShape":
                return new TShape();
            default:
                return new SingleBlock();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NewTetromino)) {
            return false;
        }
        NewTetromino that = (NewTetromino) o;
        return getTemplate().equals(that.getTemplate())
                && center.equals(that.center)
                && getName().equals(that.getName())
                && getColor().equals(that.getColor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTemplate(), center, getName(), getColor());
    }
}
