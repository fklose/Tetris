package ui;

import model.leaderboard.Leaderboard;
import model.leaderboard.Player;
import model.game.TetrisGame;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

// Methods and data to make a JPanel showing the leaderboard
public class LeaderboardPanel extends JPanel {

    private static final String JSON_STORE = "./data/leaderboard.json";
    private static final JsonReader JSON_READER = new JsonReader(JSON_STORE);
    private static final JsonWriter JSON_WRITER = new JsonWriter(JSON_STORE);
    private static Leaderboard LEADERBOARD = new Leaderboard();

    private JPanel entryPanel;
    private GridLayout entryLayout;
    private JPanel buttons;
    private final TetrisGame tetrisGame;
    private JLabel status;

    // MODIFIES : this, Leaderboard
    // EFFECTS  : Constructs a panel with all necessary components to display a leaderboard
    public LeaderboardPanel(TetrisGame tetrisGame) {
        this.tetrisGame = tetrisGame;
        this.setPreferredSize(new Dimension(TetrisGame.WIDTH * 45, TetrisGame.HEIGHT * 40));
        drawPanel();
        try {
            loadLeaderboard();
            status.setText("Leaderboard loaded from " + JSON_STORE + "!");
        } catch (IOException ioe) {
            status.setText("Unable to load Leaderboard from " + JSON_STORE + "!");
        }
        drawPanel();
    }

    // MODIFIES : this
    // EFFECTS  : Draws and places the leaderboard entries, as well as all buttons needed to interact with it
    private void drawPanel() {
        entryPanel = initializeEntries();
        buttons = initializeButtons();
        this.setLayout(new BorderLayout());
        this.add(new JScrollPane(entryPanel), BorderLayout.CENTER);
        this.add(buttons, BorderLayout.SOUTH);
    }

    // MODIFIES : this
    // EFFECTS  : updates the entries in the leaderboard and redraws the panel
    private void updateEntries() {
        this.removeAll();
        drawPanel();
        this.validate();
        this.repaint();
    }

    // EFFECTS  : Creates a new JPanel containing containing the information to be drawn on the leaderboard
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

    // EFFECTS  : Creates a JPanel containing all the buttons needed to interact with the leaderboardPanel
    private JPanel initializeButtons() {
        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(6,1));

        status = new JLabel();
        JTextField playerName = new JTextField("Your name");
        JButton addScore = new JButton("Add your score");

        addScore.addActionListener(e -> {
            LEADERBOARD.addPlayer(new Player(playerName.getText(), tetrisGame.getScore()));
            updateEntries();
            status.setText("Added " + playerName.getText() + " with score " + tetrisGame.getScore()
                    + " --- Leaderboard is not saved!");
        });

        buttons.add(status);
        buttons.add(playerName);
        buttons.add(addScore);
        buttons.add(loadButton());
        buttons.add(saveButton());
        buttons.add(clear());
        return buttons;
    }

    // MODIFIES : this, Leaderboard
    // EFFECTS  : Returns a button for clearing all entries from the leaderboard
    private JButton clear() {
        JButton b = new JButton("Clear!");

        b.addActionListener(e -> {
            LEADERBOARD.clear();
            updateEntries();
            status.setText("Leaderboard cleared! --- Leaderboard is not saved!");
        });
        return b;
    }

    // MODIFIES : this
    // EFFECTS  : Returns a button used for loading a leaderboard from a file
    private JButton loadButton() {
        JButton b = new JButton("Load Leaderboard!");

        b.addActionListener(e -> {
            try {
                loadLeaderboard();
                status.setText("Leaderboard loaded from " + JSON_STORE + "!");
            } catch (IOException ioe) {
                status.setText("Unable to load Leaderboard from " + JSON_STORE + "!");
            }
            updateEntries();
        });
        return b;
    }

    // MODIFIES : this
    // EFFECTS  : Returns a button used for saving a leaderboard to a file
    private JButton saveButton() {
        JButton b = new JButton("Save Leaderboard!");

        b.addActionListener(ioe -> {
            try {
                saveLeaderboard();
                status.setText("Leaderboard saved to " + JSON_STORE + "!");
            } catch (IOException e) {
                status.setText("Unable to write to file " + JSON_STORE + "!");
            }
            updateEntries();
        });
        return b;
    }

    // EFFECTS: loads leaderboard from file
    private static void loadLeaderboard() throws IOException {
        LEADERBOARD = JSON_READER.read();
    }

    // EFFECTS: saves the leaderboard to file
    private static void saveLeaderboard() throws IOException {
        JSON_WRITER.open();
        JSON_WRITER.write(LEADERBOARD);
        JSON_WRITER.close();
    }

}
