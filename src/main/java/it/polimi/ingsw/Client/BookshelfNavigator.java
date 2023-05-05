package it.polimi.ingsw.Client;

import it.polimi.ingsw.Model.Bookshelf;
import it.polimi.ingsw.Utils.Utils;

import java.sql.SQLOutput;

public class BookshelfNavigator {
    private Bookshelf bookshelf;
    private int column;
    public BookshelfNavigator(Bookshelf bookshelf) {
        this.bookshelf = bookshelf;
        this.column = 0;
    }

    public void moveRight() {
        if(column < bookshelf.getRowDimension() - 1)
            column++;
    }
    public void moveLeft() {
        if(column > 0)
            column--;
    }

    public void print() {
        Utils.printBookshelf(bookshelf);
        System.out.print("  ");
        for(int i = 0; i < bookshelf.getRowDimension(); i++) {
            if(i == column)
                System.out.print("O ");
            else
                System.out.print("  ");
        }
        System.out.print("\n");
    }

    public int getColumn() {
        return column;
    }
}
