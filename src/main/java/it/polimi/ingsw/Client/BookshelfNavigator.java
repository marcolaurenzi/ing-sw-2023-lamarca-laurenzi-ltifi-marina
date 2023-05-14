package it.polimi.ingsw.Client;

import it.polimi.ingsw.Model.Bookshelf;
import it.polimi.ingsw.Utils.Utils;

import java.io.IOException;


public class BookshelfNavigator {
    private final int selectionLength;
    private final Bookshelf bookshelf;
    private int cursor;
    private int column;

    public BookshelfNavigator(Bookshelf bookshelf, int selectionLength) {
        this.bookshelf = bookshelf;
        this.selectionLength = selectionLength;
        this.cursor = 0;
        this.column = -1;
    }

    public void moveRight() {
        if(cursor < bookshelf.getRowDimension() - 1)
            cursor++;
    }
    public void moveLeft() {
        if(cursor > 0)
            cursor--;
    }

    public void select() {
        int i = 0;

        while(i < bookshelf.getColumnDimension() && bookshelf.get(i, cursor) == null) {
            i++;
        }

        if(selectionLength <= i)
            column = cursor;
    }

    public void print() throws IOException {
        System.out.println("-------------------------------------");
        System.out.println("  |  0  ||  1  ||  2  ||  3  ||  4  |");

        for(int i = 0; i < bookshelf.getColumnDimension(); i++) {
            System.out.println("-------------------------------------");
            System.out.print(i + " ");
            for (int j = 0; j < bookshelf.getRowDimension(); j++) {
                if(column == j) {
                    System.out.print("[" + (bookshelf.get(i, j) == null ? "     " : bookshelf.get(i, j).toString() ) + "]");
                }
                else {
                    System.out.print("|" + (bookshelf.get(i, j) == null ? "     " : bookshelf.get(i, j).toString()) + "|");
                }
            }
            System.out.print("\n");
        }
        System.out.println("-------------------------------------");

        System.out.print("  ");
        for(int i = 0; i < bookshelf.getRowDimension(); i++) {
            if(i == cursor)
                System.out.print("|^^^^^|");
            else
                System.out.print("       ");
        }
        System.out.print("\n");
    }

    public int getColumn() {
        return cursor;
    }


    // Returns the number of empty spaces in the given column of the bookshelf
    public int getEmptySpaces(int column){
        int emptySpaces = 0;
        for(int j = 0; j < 6; j++) {
            if(bookshelf.get(j, column) == null)
                emptySpaces++;
        }
        return emptySpaces;
    }

    // Returns the number of empty spaces in the most empty column of all the bookshelf
    public int getMaxEmptySpaces() {
        int maxEmptySpaces = 0;
        int emptySpaces;
        for (int i = 0; i < 5; i++) {
            emptySpaces = 0;
            for (int j = 0; j < 6; j++) {
                if(bookshelf.get(j, i) == null)
                    emptySpaces++;
                }
                if(emptySpaces > maxEmptySpaces)
                    maxEmptySpaces = emptySpaces;
            }
        return maxEmptySpaces;
    }

    public void deselect() {
        //todo
    }
}
