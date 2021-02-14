package ui;

import java.util.Scanner;

public class Main {

    private static Scanner scanInput = new Scanner(System.in);

    public static void main(String[] args) {
        userInput();
    }

    // REQUIRES :
    // MODIFIES :
    // EFFECTS  : Handles user input
    private static void userInput() {
        System.out.println("Options:");
        System.out.println("1. Leaderboard");
        System.out.println("2. Queue");
        System.out.println("3. Exit!");
        System.out.println("Type the number associated with the option you would like to select.");

        String input = scanInput.nextLine();
        handleOptions(input);
    }

    // REQUIRES :
    // MODIFIES :
    // EFFECTS  : Given an input corresponds the corresponding task
    private static void handleOptions(String input) {
        switch (input) {
            case "1":
                new LeaderboardInterface();
                userInput();
                return;
            case "2":
                new QueueInterface();
                userInput();
                return;
            case "3":
        }
    }
}
