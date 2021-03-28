package ui.leaderboard;

import model.Leaderboard;
import model.Player;
import model.TetrisGame;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LeaderboardPanel extends JPanel {

    private static final String JSON_STORE = "./data/leaderboard.json";
    private static final JsonReader JSON_READER = new JsonReader(JSON_STORE);
    private static final JsonWriter JSON_WRITER = new JsonWriter(JSON_STORE);
    private static Leaderboard LEADERBOARD = new Leaderboard();

    private JPanel entryPanel;
    private GridLayout entryLayout;
    private JPanel buttons;
    private TetrisGame tetrisGame;
    private JLabel status;

    public LeaderboardPanel(TetrisGame tetrisGame) {
        this.tetrisGame = tetrisGame;
        loadLeaderboard();
        drawPanel();
    }

    private void drawPanel() {
        entryPanel = initializeEntries();
        buttons = initializeButtons();

        this.setLayout(new BorderLayout());
        this.add(new JScrollPane(entryPanel), BorderLayout.CENTER);
        this.add(buttons, BorderLayout.SOUTH);
    }

    private void updateEntries() {
        this.removeAll();
        drawPanel();
        this.validate();
        this.repaint();
    }

    private JPanel initializeEntries() {
        entryLayout = new GridLayout(LEADERBOARD.getSize() + 1, 3);
        JPanel entries = new JPanel();
        entries.setLayout(entryLayout);
        entries.add(new JLabel("Rank"));
        entries.add(new JLabel("Name"));
        entries.add(new JLabel("Score"));
        for (int position = 0; position < LEADERBOARD.getSize(); position++) {
            entries.add(new JLabel(Integer.toString(position + 1)));
            entries.add(new JLabel(LEADERBOARD.getPlayer(position).getName()));
            entries.add(new JLabel(Integer.toString(LEADERBOARD.getPlayer(position).getScore())));
        }

        return entries;
    }

    private JPanel initializeButtons() {
        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(5,1));

        status = new JLabel();
        JTextField playerName = new JTextField("Your name");
        JButton addScore = new JButton("Add your score");

        addScore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LEADERBOARD.addPlayer(new Player(playerName.getText(), tetrisGame.getScore()));
                updateEntries();
                status.setText("Added " + playerName.getText() + " with score " + tetrisGame.getScore());
            }
        });

        buttons.add(status);
        buttons.add(playerName);
        buttons.add(addScore);
        buttons.add(loadButton());
        buttons.add(saveButton());
        return buttons;
    }

    private JButton loadButton() {
        JButton b = new JButton("Load Highscores!");

        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadLeaderboard();
                updateEntries();
                status.setText("Leaderboard loaded!");
            }
        });
        return b;
    }

    private JButton saveButton() {
        JButton b = new JButton("Save Highscores!");

        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveLeaderboard();
                updateEntries();
                status.setText("Leaderboard saved!");
            }
        });
        return b;
    }

    // MODIFIES: this
    // EFFECTS: loads leaderboard from file
    private static void loadLeaderboard() {
        try {
            LEADERBOARD = JSON_READER.read();
            System.out.println("Loaded the leaderboard from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }

    }

    // EFFECTS: saves the leaderboard to file
    private static void saveLeaderboard() {
        try {
            JSON_WRITER.open();
            JSON_WRITER.write(LEADERBOARD);
            JSON_WRITER.close();
            System.out.println("Saved this leaderboard to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

}
