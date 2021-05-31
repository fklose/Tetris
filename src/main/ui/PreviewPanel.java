package ui;

import model.game.Block;
import model.game.Vector2D;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// Class dealing with methods for displaying a preview of a single tetromino
public class PreviewPanel extends JPanel {

    private static final int BLOCK_WIDTH = 25;
    private static final int BLOCK_HEIGHT = 25;

    private final ArrayList<Block> blocks = new ArrayList<>();

    public PreviewPanel(ArrayList<Vector2D> template, Color color) {
        for (Vector2D pos : template) {
            blocks.add(new Block(pos, color));
        }
        setPreferredSize(new Dimension(50,50));
        setBackground(Color.DARK_GRAY);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawPanel(g);
    }

    // modifies: g
    // effects:  draws the game onto g
    private void drawPanel(Graphics g) {
        for (Block b : blocks) {
            drawBlock(g, b);
        }
    }

    // modifies: g
    // effects:  draws the block b onto g
    private void drawBlock(Graphics g, Block b) {
        Color savedCol = g.getColor();
        g.setColor(b.getColor());
        g.fillRect((b.getX() + 1) * BLOCK_WIDTH, (b.getY() + 2) * BLOCK_HEIGHT, BLOCK_WIDTH, BLOCK_HEIGHT);
        g.setColor(savedCol);
    }
}
