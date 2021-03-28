package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

import model.TetrisGame;
import ui.game.GamePanel;
import ui.leaderboard.LeaderboardPanel;
import ui.queuepanel.QueuePanel;
import ui.statusbar.ScorePanel;

// TODO: HAVE NEXT TETROMINO PANEL SHOW LITTLE RENDERS OF NEXT TETROMINOS
// TODO: ADD GRIDLINES ONTO BACKGROUND

public class Tetris extends JFrame {

    TetrisGame tetrisGame;
    JPanel centerPanel;
    ScorePanel scorePanel;
    JPanel buttons;
    QueuePanel queuePanel;
    GamePanel gamePanel;

    CardLayout centerLayout;
    Timer timer;

    private boolean isGamePaused;

    private static final int INTERVAL = 1;

    public Tetris() {
        super("TETRIS");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tetrisGame = new TetrisGame();

        scorePanel = new ScorePanel(tetrisGame);
        buttons = initializeButtons();
        centerPanel = initializeCenterPanel(tetrisGame);

        add(scorePanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);

        addKeyListener(new KeyHandler());
        addTimer();
        centreOnScreen();
        pack();
        setVisible(true);

        isGamePaused = false;
    }

    private JPanel initializeButtons() {
        JPanel buttonPanel = new JPanel();

        JButton restart = restartButton();
        JButton pause = pauseButton();

        buttonPanel.setLayout(new GridLayout(1, 2));
        buttonPanel.add(restart);
        buttonPanel.add(pause);

        restart.setFocusable(false);
        pause.setFocusable(false);
        return buttonPanel;
    }

    private JButton pauseButton() {
        JButton pause = new JButton("Pause");
        pause.addActionListener(e -> {
            if (isGamePaused) {
                isGamePaused = false;
                timer.start();
            } else {
                isGamePaused = true;
                timer.stop();
            }
            centerLayout.next(centerPanel);
            validate();
            repaint();
        });
        return pause;
    }

    private JButton restartButton() {
        JButton restart = new JButton("Restart");
        restart.addActionListener(e -> {
            tetrisGame.resetGame();
            queuePanel.changeQueue(tetrisGame);
            queuePanel.update();
            centerLayout.first(centerPanel);
            isGamePaused = false;
            validate();
            repaint();
        });
        return restart;
    }

    private JPanel initializeCenterPanel(TetrisGame tetrisGame) {
        centerLayout = new CardLayout();
        JPanel centerPanel = new JPanel();

        JPanel gamePanel = initializeGamePanel();
        JPanel leaderboard = initializeLeaderboard();

        centerPanel.setLayout(new CardLayout());

        centerPanel.add(gamePanel);
        centerPanel.add(leaderboard);
        centerPanel.setLayout(centerLayout);
        return centerPanel;
    }

    private void addTimer() {
        timer = new Timer(INTERVAL, e -> {
            tetrisGame.update();
            queuePanel.update();
            scorePanel.updateScore();
            validate();
            repaint();
            if (!tetrisGame.getGameActive()) {
                timer.stop();
                centerLayout.next(centerPanel);
                scorePanel.changeText("GAME OVER!!! Your score is: " + tetrisGame.getScore());
            }
        });
        timer.start();
    }

    private JPanel initializeGamePanel() {
        JPanel gp = new JPanel();
        gp.setLayout(new BorderLayout());
        queuePanel = new QueuePanel(tetrisGame);
        gamePanel = new GamePanel(tetrisGame);
        setFocusable(true);
        gp.add(gamePanel, BorderLayout.CENTER);
        gp.add(queuePanel, BorderLayout.EAST);
        return gp;
    }

    private JPanel initializeLeaderboard() {
        return new LeaderboardPanel(tetrisGame);
    }

    // Centres frame on desktop
    // modifies: this
    // effects:  location of frame is set so frame is centred on desktop
    private void centreOnScreen() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screen.width - getWidth()) / 2 - 200, (screen.height - getHeight()) / 2 - 400);
    }

    private class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            tetrisGame.keyPressed(e.getKeyCode());
        }
    }

    public static void main(String[] args) {
        new Tetris();
    }
}