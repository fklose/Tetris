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
import ui.statusbar.ScorePanel;

// TODO: MAKE A PANEL TO SEE UPCOMING TETROMINOS
//  >> FIRST TEXT BASED
//  >> LATER WITH LITTLE RENDERED TETROMINOS
// TODO: ADD GRIDLINES ONTO BACKGROUND
// TODO: MAKE BACKGROUND BLACK OR AT LEAST A BIT DARKER


public class Tetris extends JFrame {

    TetrisGame tetrisGame;
    JPanel centerPanel;
    JPanel scorePanel;
    JPanel buttons;

    CardLayout centerLayout;
    Timer timer;

    private boolean isGamePaused;

    private static final int INTERVAL = 5;

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
        return buttonPanel;
    }

    private JButton pauseButton() {
        JButton pause = new JButton("Pause");
        pause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isGamePaused) {
                    isGamePaused = false;
                    timer.start();
                } else {
                    isGamePaused = true;
                    timer.stop();
                }
                centerLayout.next(centerPanel);
            }
        });
        return pause;
    }

    private JButton restartButton() {
        JButton restart = new JButton("Restart");
        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tetrisGame = new TetrisGame();
                // TODO: this does not work yet
            }
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
        timer = new Timer(INTERVAL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tetrisGame.update();
                centerPanel.repaint();
                if (!tetrisGame.getGameActive()) {
                    timer.stop();
                }
            }
        });
        timer.start();
    }

    private JPanel initializeGamePanel() {
        setFocusable(true);
        return new GamePanel(tetrisGame);
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