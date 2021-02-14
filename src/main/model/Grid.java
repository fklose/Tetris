package model;

import java.util.LinkedList;

// Information and methods to create and manage a grid
public class Grid {

    private int height;
    private int width;
    private LinkedList<Integer> grid;

    // REQUIRES : width > 0, height > 0
    // MODIFIES :
    // EFFECTS  : Creates a grid with a given width and height
    public Grid(int width, int height) {
        // STUB
    }

    // REQUIRES : columnNumber >= 0, lineNumber >= 0
    // MODIFIES :
    // EFFECTS  : Converts a line and column number into the right index
    public int getPosition(int columnNumber, int lineNumber) {
        return 0; // STUB
    }

    // REQUIRES :
    // MODIFIES : this.grid
    // EFFECTS  : Replaces a line by removing it and then adding an empty line at the top
    public void replaceLine(int lineNumber) {
        // STUB
    }

    // REQUIRES :
    // MODIFIES : this.grid
    // EFFECTS  : Replaces value in given line and column with given new value
    public void replaceValue(int newValue, int columnNumber, int lineNumber) {
        // STUB
    }

    // REQUIRES :
    // MODIFIES : this.grid
    // EFFECTS  : Removes a line with a given lineNumber
    private void removeLine(int lineNumber) {
        // STUB
    }

    // REQUIRES :
    // MODIFIES : this.grid
    // EFFECTS  : Adds a line to the top of the grid
    private void addLineToTop() {
        // STUB
    }

}
