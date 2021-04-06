package ui;

import model.TetrisGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

// TODO: ADD GRIDLINES ONTO BACKGROUND
// TODO: MAKE UI LOOK GOOD
// TODO: FIX BUG WHERE ROTATING A TETROMINO ROTATES EVERY TETROMINO OF ITS KIND
// TODO: ADD FUNCTION TO SAVE THE CURRENT TETROMINO FOR LATER AND HAVE A LITTLE PREVIEW WINDOW FOR IT.
// UI for the game
public class Tetris extends JFrame {

    TetrisGame tetrisGame;
    JPanel centerPanel;
    StatusPanel statusPanel;
    JPanel buttons;
    QueuePanel queuePanel;
    GamePanel gamePanel;

    CardLayout centerLayout;
    Timer timer;

    private boolean isGamePaused;
    private static final int INTERVAL = 5;

    // MODIFIES : this
    // EFFECTS  : Constructs a new window containing the game and its UI
    public Tetris() {
        super("TETRIS");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tetrisGame = new TetrisGame();

        statusPanel = new StatusPanel(tetrisGame);
        buttons = initializeButtons();
        centerPanel = initializeCenterPanel();

        add(statusPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);

        addKeyListener(new KeyHandler());
        addTimer();
        centreOnScreen();
        pack();
        setVisible(true);

        isGamePaused = false;
    }

    // EFFECTS  : Returns a JPanel containing all necessary buttons
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

    // MODIFIES : this, Timer
    // EFFECTS  : Returns a JButton used to pause the game
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

    // MODIFIES : this, TetrisGame
    // EFFECTS  : Returns a button used to restart the game
    private JButton restartButton() {
        JButton restart = new JButton("Restart");
        restart.addActionListener(e -> {
            tetrisGame.resetGame();
            queuePanel.changeQueue(tetrisGame);
            queuePanel.update();
            centerLayout.first(centerPanel);
            isGamePaused = false;
            timer.start();
            validate();
            repaint();
        });
        return restart;
    }

    // EFFECTS  : Returns a JPanel containing the game and the leaderboard in a card layout
    private JPanel initializeCenterPanel() {
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

    // MODIFIES : this
    // EFFECTS  : Starts a timer for updating the UI
    private void addTimer() {
        timer = new Timer(INTERVAL, e -> {
            tetrisGame.update();
            queuePanel.update();
            statusPanel.updateScore();
            validate();
            repaint();
            if (!tetrisGame.getGameActive()) {
                timer.stop();
                centerLayout.next(centerPanel);
                statusPanel.changeText("GAME OVER!!! Your score is: " + tetrisGame.getScore());
            }
        });
        timer.start();
    }

    // EFFECTS  : Returns a JPanel containing the game
    private JPanel initializeGamePanel() {
        JPanel gp = new JPanel();
        gp.setLayout(new BorderLayout());
        queuePanel = new QueuePanel(tetrisGame);
        gamePanel = new GamePanel(tetrisGame);
        setFocusable(true);
        gamePanel.setPreferredSize(new Dimension(400, 800));
        queuePanel.setPreferredSize(new Dimension(200, 800));
        gp.add(gamePanel, BorderLayout.CENTER);
        gp.add(queuePanel, BorderLayout.EAST);
        return gp;
    }

    // EFFECTS  : Returns a JPanel containing the leaderboard and its necessary buttons
    private JPanel initializeLeaderboard() {
        return new LeaderboardPanel(tetrisGame);
    }

    // MODIFIES : this
    // EFFECTS  : location of frame is set so frame is centred on desktop
    private void centreOnScreen() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screen.width - getWidth()) / 2 - 200, (screen.height - getHeight()) / 2 - 400);
    }

    // EFFECTS  : Handles user inputs
    private class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            tetrisGame.keyPressed(e.getKeyCode());
        }
    }

    // EFFECTS  : starts the game
    public static void main(String[] args) {
        new Tetris();
    }
}