package ui;

import model.game.Block;
import model.game.TetrisGame;
import model.tetromino.Tetromino;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

// Methods and data needed to draw the game onto a JPanel
public class GamePanel extends JPanel {

    private final TetrisGame tetrisGame;
    private int blockSize;

    // MODIFIES : this
    // EFFECTS  : sets size and background colour of panel, updates this with the game to be displayed
    public GamePanel(TetrisGame game, int blockSize) {
        this.blockSize = blockSize;
        setPreferredSize(new Dimension(TetrisGame.WIDTH * blockSize, TetrisGame.HEIGHT * blockSize));
        setBackground(Color.BLACK);
        this.tetrisGame = game;
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
        g.fillRect(b.getX() * blockSize, b.getY() * blockSize, blockSize, blockSize);
        g.setColor(savedCol);
    }
}
