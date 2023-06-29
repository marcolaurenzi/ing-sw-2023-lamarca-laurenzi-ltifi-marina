package it.polimi.ingsw.Client;

import it.polimi.ingsw.Model.Bookshelf;
import java.io.IOException;

/**
 * The BookshelfNavigator class represents a navigator for a bookshelf.
 * It allows moving the cursor, selecting a column, and printing the bookshelf.
 */
public class BookshelfNavigator {
    /**
     * The length of the selection.
     */
    private final int selectionLength;

    /**
     * The bookshelf to navigate.
     */
    private final Bookshelf bookshelf;

    /**
     * The current cursor position.
     */
    private int cursor;

    /**
     * The current selected column.
     */
    private int column;

    /**
     * Constructs a BookshelfNavigator object with the specified bookshelf and selection length.
     *
     * @param bookshelf        the bookshelf to navigate
     * @param selectionLength  the length of the selection
     */
    public BookshelfNavigator(Bookshelf bookshelf, int selectionLength) {
        this.bookshelf = bookshelf;
        this.selectionLength = selectionLength;
        this.cursor = 0;
        this.column = -1;
    }

    /**
     * Moves the cursor to the right.
     */
    public void moveRight() {
        if (cursor < bookshelf.getRowDimension() - 1)
            cursor++;
    }

    /**
     * Moves the cursor to the left.
     */
    public void moveLeft() {
        if (cursor > 0)
            cursor--;
    }

    /**
     * Selects the current column if it has enough non-null elements.
     */
    public void select() {
        int i = 0;

        while (i < bookshelf.getColumnDimension() && bookshelf.get(i, cursor) == null) {
            i++;
        }

        if (selectionLength <= i)
            column = cursor;
    }

    /**
     * Prints the bookshelf.
     *
     * @throws IOException if there is an I/O error
     */
    public void print() throws IOException {
        System.out.println("-------------------------------------");
        System.out.println("  |  0  ||  1  ||  2  ||  3  ||  4  |");

        for (int i = 0; i < bookshelf.getColumnDimension(); i++) {
            System.out.println("-------------------------------------");
            System.out.print(i + " ");
            for (int j = 0; j < bookshelf.getRowDimension(); j++) {
                if (column == j) {
                    System.out.print("[" + (bookshelf.get(i, j) == null ? "     " : bookshelf.get(i, j).toString()) + "]");
                } else {
                    System.out.print("|" + (bookshelf.get(i, j) == null ? "     " : bookshelf.get(i, j).toString()) + "|");
                }
            }
            System.out.print("\n");
        }
        System.out.println("-------------------------------------");

        System.out.print("  ");
        for (int i = 0; i < bookshelf.getRowDimension(); i++) {
            if (i == cursor)
                System.out.print("|^^^^^|");
            else
                System.out.print("       ");
        }
        System.out.print("\n");
    }

    /**
     * Returns the current column.
     *
     * @return the current column
     */
    public int getColumn() {
        return cursor;
    }

    /**
     * Returns the number of empty spaces in the given column of the bookshelf.
     *
     * @param column the column to count empty spaces
     * @return the number of empty spaces in the column
     */
    public int getEmptySpaces(int column) {
        int emptySpaces = 0;
        for (int j = 0; j < 6; j++) {
            if (bookshelf.get(j, column) == null)
                emptySpaces++;
        }
        return emptySpaces;
    }

    /**
     * Returns the number of empty spaces in the most empty column of the bookshelf.
     *
     * @return the number of empty spaces in the most empty column
     */
    public int getMaxEmptySpaces() {
        int maxEmptySpaces = 0;
        int emptySpaces;
        for (int i = 0; i < 5; i++) {
            emptySpaces = 0;
            for (int j = 0; j < 6; j++) {
                if (bookshelf.get(j, i) == null)
                    emptySpaces++;
            }
            if (emptySpaces > maxEmptySpaces)
                maxEmptySpaces = emptySpaces;
        }
        return maxEmptySpaces;
    }
}
