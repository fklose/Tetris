package ui;

import model.game.TetrisGame;

import javax.swing.*;
import java.awt.*;

// Methods and data to create a JPanel containing information about the status of the game
public class StatusPanel extends JPanel {

    JLabel statusLabel;
    TetrisGame tetrisGame;

    // MODIFIES : this
    // EFFECTS  : Creates a new StatusPanel, showing information about the score and the state of the game
    public StatusPanel(TetrisGame tg) {
        tetrisGame = tg;
        setLayout(new GridLayout(1, 1));
        statusLabel = new JLabel();
        add(statusLabel);
        updateScore();
    }

    // MODIFIES : this
    // EFFECTS  : Updates the scoreLabel
    public void updateScore() {
        statusLabel.setText("Score: " + tetrisGame.getScore());
    }

    // MODIFIES : this
    // EFFECTS  : Sets the text in the statusLabel
    public void changeText(String text) {
        statusLabel.setText(text);
    }
}
