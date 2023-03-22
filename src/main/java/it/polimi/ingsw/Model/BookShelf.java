package it.polimi.ingsw.Model;
import java.util.ArrayList;


public class BookShelf {
    private final Matrix<Item> items;
    private int freeTiles;

    public BookShelf() {
        items = new Matrix<>(6, 5);
        freeTiles = 30;
    }
    public boolean checkIfFull() {
        return freeTiles == 0;
    }
    public void Insert(int column, ArrayList<Item> pick) {
        //TODO
    }

    public void set(int i, int j, Item item) {
        items.set(i, j, item);
    }
}
