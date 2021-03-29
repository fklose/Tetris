package ui.old;

import java.util.Scanner;

// Main program
public final class Main {

    private static Scanner scanInput = new Scanner(System.in);

    public static void main(String[] args) {
        QueueInterface.initializeQueue();
        userInput();
    }

    // EFFECTS  : Handles user input
    public static void userInput() {
        System.out.println("Options:");
        System.out.println("1. Leaderboard");
        System.out.println("2. Queue");
        System.out.println("3. Exit!");
        System.out.println("Type the number associated with the option you would like to select.");

        String input = scanInput.nextLine();
        handleOptions(input);
    }

    // EFFECTS  : Given an input corresponds the corresponding task
    private static void handleOptions(String input) {
        switch (input) {
            case "1":
                LeaderboardInterface.userInput();
                return;
            case "2":
                QueueInterface.userInput();
                return;
            case "3":
        }
    }
}
