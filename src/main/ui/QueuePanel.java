package ui;

import model.TetrisGame;
import model.Tetromino;

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
        this.setPreferredSize(new Dimension(TetrisGame.WIDTH * 10, TetrisGame.HEIGHT * 40));
        this.setLayout(new GridLayout(5, 1));
        update();
    }

    // MODIFIES : this
    // EFFECTS  : Redraws this QueuePanel
    public void update() {
        this.removeAll();
//        int i = 1;
        for (Tetromino t : tetrominoQueue) {
//            JLabel jl = new JLabel(Integer.toString(i));
//            jl.setBackground(Color.BLACK);
//            this.add(jl);
            this.add(new JLabel(t.getName()));
            this.add(new PreviewPanel(t.getTemplate(), t.getColor()));
//            i++;
        }
        validate();
        repaint();
    }

    // MODIFIES : this
    // EFFECTS  : Updates the reference to the games TetrominoQueue, needed when restarting the game
    public void changeQueue(TetrisGame tetrisGame) {
        this.tetrominoQueue = tetrisGame.getQueue().getQueue();
    }
}
