package ui.queuepanel;

import model.TetrisGame;
import model.Tetromino;
import model.TetrominoQueue;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class QueuePanel extends JPanel {

    TetrisGame tetrisGame;
    LinkedList<Tetromino> tetrominoQueue;

    public QueuePanel(TetrisGame tetrisGame) {
        this.tetrisGame = tetrisGame;
        this.tetrominoQueue = tetrisGame.getQueue().getQueue();

        this.setLayout(new GridLayout(5, 2));
        update();
    }

    public void update() {
        this.removeAll();
        int i = 1;
        for (Tetromino t : tetrominoQueue) {
            this.add(new JLabel(Integer.toString(i)));
            this.add(new JLabel(t.getName()));
            i++;
        }
        validate();
        repaint();
    }

    public void changeQueue(TetrisGame tetrisGame) {
        this.tetrominoQueue = tetrisGame.getQueue().getQueue();
    }
}
