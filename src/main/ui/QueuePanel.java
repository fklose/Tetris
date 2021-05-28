package ui;

import model.game.TetrisGame;
import model.game.Tetromino;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

// TODO: MAKE IT LOOK NICER
// Methods and data to create a JPanel showing the queue of tetrominos
public class QueuePanel extends JPanel {

    TetrisGame tetrisGame;
    LinkedList<Tetromino> tetrominoQueue;

    // MODIFIES : this
    // EFFECTS  : Constructs a new QueuePanel
    public QueuePanel(TetrisGame tetrisGame) {
        this.tetrisGame = tetrisGame;
        this.tetrominoQueue = tetrisGame.getQueue().getQueue();
        this.setPreferredSize(new Dimension(TetrisGame.WIDTH * 5, TetrisGame.HEIGHT * 40));
        this.setLayout(new GridLayout(7, 1));
        this.setBackground(Color.DARK_GRAY);
        update();
    }

    // MODIFIES : this
    // EFFECTS  : Redraws this QueuePanel
    public void update() {
        this.removeAll();
        for (Tetromino t : tetrominoQueue) {
            this.add(new PreviewPanel(t.getTemplate(), t.getColor()));
        }
        JLabel jl = new JLabel("Saved Tetromino");
        jl.setBackground(Color.BLACK);
        jl.setForeground(Color.WHITE);
        this.add(jl);
        this.add(new PreviewPanel(tetrisGame.getSavedTetromino().getTemplate(),
                tetrisGame.getSavedTetromino().getColor()));
        validate();
        repaint();
    }

    // MODIFIES : this
    // EFFECTS  : Updates the reference to the games TetrominoQueue, needed when restarting the game
    public void changeQueue(TetrisGame tetrisGame) {
        this.tetrominoQueue = tetrisGame.getQueue().getQueue();
    }
}
