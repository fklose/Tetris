package ui;

import model.Leaderboard;
import model.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class LeaderboardInterface {

    private static Scanner scan;
    private static Leaderboard leaderboard = new Leaderboard();

    // REQUIRES :
    // MODIFIES :
    // EFFECTS  : Creates a new Leaderboard interface
    public LeaderboardInterface() {
        scan = new Scanner(System.in);

        userInput();
    }

    // REQUIRES :
    // MODIFIES :
    // EFFECTS  : Handles user input
    public static void userInput() {
        System.out.println("Options:");
        System.out.println("1. View Leaderboard");
        System.out.println("2. Add your score");
        System.out.println("3. Exit!");
        System.out.println("Type the number associated with the option you would like to select.");

        String input = scan.nextLine();
        handleOptions(input);
    }

    // REQUIRES :
    // MODIFIES :
    // EFFECTS  : Gets next Tetromino from queue and prints its name
    private static void addScore() {
        String name;
        int score;
        Player player;

        System.out.println("------------------------------------");
        System.out.println("What's your name?");
        name = scan.nextLine();
        System.out.println("What's your score?");
        score = scan.nextInt();

        player = new Player(name, score);
        leaderboard.addPlayer(player);
    }

    // REQUIRES :
    // MODIFIES :
    // EFFECTS  : Prints the queue
    private static void printLeaderboard() {
        System.out.println("------------------------------------");
        ArrayList<Player> players = leaderboard.getLeaderboard();

        if (players.size() == 0) {
            System.out.println("Leaderboard is empty");
            return;
        }
        System.out.println("There are " + leaderboard.getLeaderboard().size());
        int position = 1;
        for (Player player : players) {
            System.out.print(position + ". ");
            System.out.print(player.getName() + " - " + player.getScore() + "\n");
            position++;
        }
    }

    // REQUIRES :
    // MODIFIES :
    // EFFECTS  : Method is done when Enter is pressed. Used to control output
    private static void waitForEnter() {
        System.out.println("----- Press Enter to continue! -----");
        String input = scan.nextLine();
    }

    // REQUIRES : input must be one of "1", "2" or "3"
    // MODIFIES :
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
                String[] args = new String[1];
                Main.main(args);
        }
    }
}
