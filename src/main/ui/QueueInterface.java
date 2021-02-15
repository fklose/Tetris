package ui;

import model.TetrominoQueue;
import model.Tetromino;

import java.util.LinkedList;
import java.util.Scanner;

// Methods for interacting with a Queue
public final class QueueInterface {

    private static final Scanner SCAN_INPUT = new Scanner(System.in);
    private static final TetrominoQueue TETROMINO_QUEUE = new TetrominoQueue();

    // REQUIRES :
    // MODIFIES : this
    // EFFECTS  : Creates a new QueueInterface
    private QueueInterface() {}

    // REQUIRES :
    // MODIFIES :
    // EFFECTS  : Handles user input
    public static void userInput() {
        System.out.println("Options:");
        System.out.println("1. View queue");
        System.out.println("2. Get next Tetromino from queue");
        System.out.println("3. Exit!");
        System.out.println("Type the number associated with the option you would like to select.");

        String input = SCAN_INPUT.nextLine();
        handleOptions(input);
    }

    // REQUIRES :
    // MODIFIES :
    // EFFECTS  : Gets next Tetromino from queue and prints its name
    private static void printNextItem() {
        System.out.println("------------------------------------");
        Tetromino nextTetromino = TETROMINO_QUEUE.getNextTetromino();
        System.out.println("The current item is:    " + nextTetromino.getName());
    }

    // REQUIRES :
    // MODIFIES :
    // EFFECTS  : Prints the queue
    private static void printQueue() {
        System.out.println("------------------------------------");
        System.out.print("The queue is:           ");
        LinkedList<Tetromino> tetrominos = TETROMINO_QUEUE.getQueue();
        for (Tetromino tetromino : tetrominos) {
            System.out.print(tetromino.getName() + "    ");
        }
        System.out.println(" ");
    }

    // REQUIRES :
    // MODIFIES :
    // EFFECTS  : Method is done when Enter is pressed. Used to control output
    private static void waitForEnter() {
        System.out.println("----- Press Enter to continue! -----");
        String input = SCAN_INPUT.nextLine();
    }

    // REQUIRES : input must be one of "1", "2" or "3"
    // MODIFIES :
    // EFFECTS  : Given an input performs the corresponding task
    private static void handleOptions(String input) {
        switch (input) {
            case "1":
                printQueue();
                waitForEnter();
                userInput();
                return;
            case "2":
                printNextItem();
                printQueue();
                waitForEnter();
                userInput();
                return;
            case "3":
                String[] args = new String[1];
                Main.main(args);
        }
    }
}
