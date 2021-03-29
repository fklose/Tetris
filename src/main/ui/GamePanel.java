package ui;

import model.Block;
import model.TetrisGame;
import model.Tetromino;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

// Methods and data needed to draw the game onto a JPanel
public class GamePanel extends JPanel {

    private TetrisGame tetrisGame;

    // MODIFIES : this
    // EFFECTS  : sets size and background colour of panel, updates this with the game to be displayed
    public GamePanel(TetrisGame g) {
        setPreferredSize(new Dimension(400, 800));
        setBackground(Color.BLACK);
        this.tetrisGame = g;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGame(g);
    }

    // modifies: g
    // effects:  draws the game onto g
    private void drawGame(Graphics g) {
        drawBoard(g);
        drawTetromino(g);
    }

    // modifies: g
    // effects:  draws the current Tetromino onto g
    private void drawTetromino(Graphics g) {
        Tetromino t = tetrisGame.getCurrentTetro();
        drawBlocks(g, t.getBlocks());
    }

    // modifies: g
    // effects : draws the board with all blocks currently on it. does nto draw the current tetromino
    private void drawBoard(Graphics g) {
        drawBlocks(g, tetrisGame.getBoard());
    }

    // modifies: g
    // effects:  draws the blocks onto g
    private void drawBlocks(Graphics g, ArrayList<Block> blocks) {
        for (Block b : blocks) {
            drawBlock(g, b);
        }
    }

    // modifies: g
    // effects:  draws the blocks onto g
    private void drawBlocks(Graphics g, HashSet<Block> blocks) {
        for (Block b : blocks) {
            drawBlock(g, b);
        }
    }

    // modifies: g
    // effects:  draws the block b onto g
    private void drawBlock(Graphics g, Block b) {
        Color savedCol = g.getColor();
        g.setColor(b.getColor());
        g.fillRect(b.getX() * 40, b.getY() * 40, Block.SIZE_X, Block.SIZE_Y);
        g.setColor(savedCol);
    }
}
