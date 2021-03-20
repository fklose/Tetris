package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.Timer;

import model.TetrisGame;

// TODO: MAKE A PANEL TO KEEP TRACK OF SCORE
// TODO: MAKE A PANEL TO VIEW HIGHSCORES AFTER GAME IS DONE
// TODO: AFTER LOSING GAME LET USER CHOOSE TO EXIT DIRECTLY
//      OR
//  VIEW THEIR OWN SCORE AND LEADERBOARD AND ADD THEIR SCORE TO LEADERBOARD THEN START AGAIN OR EXIT
// TODO: MAKE A PANEL TO SEE UPCOMING TETROMINOS
//  >> FIRST TEXT BASED
//  >> LATER WITH LITTLE RENDERED TETROMINOS
// TODO: ADD GRIDLINES ONTO BACKGROUND
// TODO: MAKE BACKGROUND BLACK OR AT LEAST A BIT DARKER
// TODO: USE SIMPLE SHAPE PLAYER TO MAKE A PROPER WINDOWS "WINDOW"


/*
 * Represents the main window in which the space invaders
 * game is played
 */
@SuppressWarnings("serial")
public class Tetris extends JFrame {

    private static final int INTERVAL = 5;
    private TetrisGame game;
    private GamePanel gp;

    // Constructs main window
    // EFFECTS  : sets up window in which Space Invaders game will be played
    public Tetris() {
        super("TETRIS");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game = new TetrisGame();
        gp = new GamePanel(game);
        add(gp);
        addKeyListener(new KeyHandler());
        pack();
        centreOnScreen();
        addTimer();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Set up timer
    // modifies: none
    // effects:  initializes a timer that updates game each
    //           INTERVAL milliseconds
    private void addTimer() {
        Timer t = new Timer(INTERVAL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                game.update();
                gp.repaint();

                if (!game.getGameActive()) {
                    System.exit(0);
                }
            }
        });

        t.start();
    }

    // Centres frame on desktop
    // modifies: this
    // effects:  location of frame is set so frame is centred on desktop
    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }

    /*
     * A key handler to respond to key events
     */
    private class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            game.keyPressed(e.getKeyCode());
        }
    }

    /*
     * Play the game
     */
    public static void main(String[] args) {
        new Tetris();
    }
}