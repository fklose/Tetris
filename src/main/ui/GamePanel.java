package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import model.Block;
import model.TetrisGame;
import model.Tetromino;

/*
 * The panel in which the game is rendered.
 */
@SuppressWarnings("serial")
public class GamePanel extends JPanel {

    private TetrisGame tetrisGame;

    // Constructs a game panel
    // effects:  sets size and background colour of panel,
    //           updates this with the game to be displayed
    public GamePanel(TetrisGame g) {
        setPreferredSize(new Dimension(400, 800));
        setBackground(Color.GRAY);
        this.tetrisGame = g;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawGame(g);

    }

    // Draws the game
    // modifies: g
    // effects:  draws the game onto g
    private void drawGame(Graphics g) {
        drawBoard(g);
        drawTetromino(g);
    }

    // Draw the current Tetromino
    // modifies: g
    // effects:  draws the current Tetromino onto g
    private void drawTetromino(Graphics g) {
        Tetromino t = tetrisGame.getCurrentTetro();
        drawBlocks(g, t.getBlocks());
    }

    // Draw the board
    // modifies: g
    // effects : draws the board with all blocks currently on it. does nto draw the current tetromino
    private void drawBoard(Graphics g) {
        drawBlocks(g, tetrisGame.getBoard());
    }

    // Draw the blocks
    // modifies: g
    // effects:  draws the blocks onto g
    private void drawBlocks(Graphics g, ArrayList<Block> blocks) {
        for (Block b : blocks) {
            drawBlock(g, b);
        }
    }

    // Draw a block
    // modifies: g
    // effects:  draws the block b onto g
    private void drawBlock(Graphics g, Block b) {
        Color savedCol = g.getColor();
        g.setColor(b.getColor());
        g.fillRect(b.getX() * 40 - Block.SIZE_X / 2, b.getY() * 40 - Block.SIZE_Y / 2, Block.SIZE_X, Block.SIZE_Y);
        g.setColor(savedCol);
    }
}
