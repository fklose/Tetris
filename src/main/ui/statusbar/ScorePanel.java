package ui.statusbar;

import model.TetrisGame;

import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JPanel {

    JLabel scoreLabel;
    TetrisGame tetrisGame;

    public ScorePanel(TetrisGame tg) {
        tetrisGame = tg;
        setLayout(new GridLayout(1, 1));
        scoreLabel = new JLabel();
        add(scoreLabel);
        updateScore();
    }

    public void updateScore() {
        scoreLabel.setText("Score: " + tetrisGame.getScore());
    }

    public void changeText(String text) {
        scoreLabel.setText(text);
    }
}
