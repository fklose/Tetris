package ui;

import model.Leaderboard;
import model.Player;

import java.util.ArrayList;
import java.util.Scanner;

// Methods for interacting with a Leaderboard
public final class LeaderboardInterface {

    private static final Scanner SCAN_INPUT = new Scanner(System.in);
    private static final Leaderboard LEADERBOARD = new Leaderboard();

    // EFFECTS  : Creates a new Leaderboard interface
    private LeaderboardInterface() {}

    // EFFECTS  : Handles user input
    public static void userInput() {
        System.out.println("Options:");
        System.out.println("1. View Leaderboard");
        System.out.println("2. Add your score");
        System.out.println("3. Go back!");
        System.out.println("Type the number associated with the option you would like to select. Press ENTER to exit.");

        String input = SCAN_INPUT.nextLine();
        handleOptions(input);
    }

    // MODIFIES : LEADERBOARD
    // EFFECTS  : Adds a player with entered name and score to leaderboard
    private static void addScore() {
        String name;
        int score;
        Player player;

        System.out.println("------------------------------------");
        System.out.println("What's your name?");
        name = SCAN_INPUT.nextLine();
        System.out.println("What's your score?");
        score = SCAN_INPUT.nextInt();

        player = new Player(name, score);
        LEADERBOARD.addPlayer(player);
    }

    // EFFECTS  : Prints players in LEADERBOARD
    private static void printLeaderboard() {
        System.out.println("------------------------------------");
        ArrayList<Player> players = LEADERBOARD.getLeaderboard();

        if (players.size() == 0) {
            System.out.println("Leaderboard is empty");
            return;
        }
        System.out.println("There are " + LEADERBOARD.getSize() + " entries.");
        int position = 1;
        for (Player player : players) {
            System.out.print(position + ". ");
            System.out.print(player.getName() + " - " + player.getScore() + "\n");
            position++;
        }
    }

    // EFFECTS  : Method is done when Enter is pressed. Used to control output
    private static void waitForEnter() {
        System.out.println("----- Press Enter to continue! -----");
        SCAN_INPUT.nextLine();
    }

    // REQUIRES : input must be one of "1", "2" or "3"
    // EFFECTS  : Given an input performs the corresponding task
    private static void handleOptions(String input) {
        switch (input) {
            case "1":
                printLeaderboard();
                waitForEnter();
                userInput();
                return;
            case "2":
                addScore();
                waitForEnter();
                userInput();
                return;
            case "3":
                Main.userInput();
        }
    }
}
