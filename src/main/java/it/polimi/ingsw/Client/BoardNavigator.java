package it.polimi.ingsw.Client;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Model.Coordinates;

import java.util.ArrayList;

public class  BoardNavigator {

    private Board board;
    private ArrayList<Coordinates> selection;
    private Coordinates cursor;
    private boolean middletile;

    public BoardNavigator(Board board) {
        this.board = board;
        this.selection = new ArrayList<>();
        this.cursor = new Coordinates(0, 0);
    }
    private boolean hasTileOneSideFree()  {
        return board.hasFree(cursor.getY(), cursor.getX()) >= 1;
    }
    private boolean isTileEmpty() {
        return board.getGameBoard().get(cursor.getY(), cursor.getX()).isEmpty();
    }

    private boolean isNextToOthers() {
        if(selection.size() == 0)
            return true;
        boolean ret = false;


        for (Coordinates c: selection) {
            if(Math.abs(c.getX() - cursor.getX()) == 1 && Math.abs(c.getY() - cursor.getY()) == 0 || Math.abs(c.getX() - cursor.getX()) == 0 && Math.abs(c.getY() - cursor.getY()) == 1) {
                ret = true;
            }
        }

        return ret;
    }
    private boolean isAdjacentToOthers() {
        return (isSameRow() || isSameColumn()) && isNextToOthers();
    }
    private boolean isSameColumn() {
        for (Coordinates coordinates : selection)
            if (cursor.getX() != coordinates.getX())
                return false;

        return true;
    }
    private boolean isSameRow() {
        for (Coordinates coordinates : selection)
            if (cursor.getY() != coordinates.getY())
                return false;

        return true;
    }
    private boolean selectionNotAdjacent() {
        if (selection.size() == 1) {
            if(Math.abs(selection.get(0).getX() - cursor.getX()) == 1 && Math.abs(selection.get(0).getY() - cursor.getY()) == 0 || Math.abs(selection.get(0).getX() - cursor.getX()) == 0 && Math.abs(selection.get(0).getY() - cursor.getY()) == 1) {
                return false;
            } else if(Math.abs(selection.get(0).getX() - cursor.getX()) == 2 && Math.abs(selection.get(0).getY() - cursor.getY()) == 0 || Math.abs(selection.get(0).getX() - cursor.getX()) == 0 && Math.abs(selection.get(0).getY() - cursor.getY()) == 2) {
                middletile = true;
                return true;
            }
        }
        return false;
    }
    public boolean select() {
        if(!selection.contains(cursor) && selection.size() < 3 && (isAdjacentToOthers() || selectionNotAdjacent()) && !isTileEmpty() && hasTileOneSideFree())
            if(middletile && selection.size() == 2){
                if(Math.abs(selection.get(0).getX() + selection.get(1).getX() - cursor.getX() * 2) == 0 && Math.abs(selection.get(0).getY() + selection.get(1).getY() - cursor.getY() * 2) == 0 || Math.abs(selection.get(0).getX() + selection.get(1).getX() - cursor.getX() * 2) == 0 && Math.abs(selection.get(0).getY() + selection.get(1).getY() - cursor.getY() * 2) == 0) {
                    selection.add(new Coordinates(cursor.getX(), cursor.getY()));
                    middletile = false;
                    return middletile;
                }
                return middletile;
            } else {
                selection.add(new Coordinates(cursor.getX(), cursor.getY()));
            }
        return middletile;
    }
    public boolean deselect() {

        if(selection.size() >= 3) {
            boolean toRemove = false;
            ArrayList<Coordinates> tempSelection = new ArrayList<>(selection);

            for (Coordinates c : tempSelection) {
                if (c.equals(cursor))
                    toRemove = true;
                if (toRemove) {
                    selection.remove(cursor);
                    middletile = false;
                }

            }
        }

        else {
            selection.remove(cursor);
            middletile = false;
        }
        return middletile;
    }
    public void moveUp() {
        if(cursor.getY() > 0)
            cursor = new Coordinates(cursor.getX(), cursor.getY() - 1);
    }
    public void moveDown() {
        if(cursor.getY() < board.getGameBoard().getColumnDimension() - 1)
            cursor = new Coordinates(cursor.getX(), cursor.getY() + 1);
    }
    public void moveLeft() {
        if(cursor.getX() > 0)
            cursor = new Coordinates(cursor.getX() - 1, cursor.getY());
    }
    public void moveRight() {
        if(cursor.getX() < board.getGameBoard().getRowDimension() - 1)
            cursor = new Coordinates(cursor.getX() + 1, cursor.getY());
    }
    public void print() {
        System.out.println("-----------------------------------------------------------------");
        System.out.println("  |  0  ||  1  ||  2  ||  3  ||  4  ||  5  ||  6  ||  7  ||  8  |");
        for(int i = 0; i < board.getGameBoard().getColumnDimension(); i++) {
            System.out.println("-----------------------------------------------------------------");
            System.out.print(i + " ");
            for (int j = 0; j < board.getGameBoard().getColumnDimension(); j++) {
                if(j == cursor.getX() && i == cursor.getY()) {
                    System.out.print("{" + (board.getGameBoard().get(i, j).getPlacedItem() != null ? board.getGameBoard().get(i, j).getPlacedItem().toString() :  "     ") + "}");
                }
                else if(selection.contains(new Coordinates(j, i))){
                    System.out.print("[" + (board.getGameBoard().get(i, j).getPlacedItem() != null ? board.getGameBoard().get(i, j).getPlacedItem().toString() :  "     ") + "]");
                }

                else if (board.getGameBoard().get(i, j).getPlacedItem() != null) {
                    System.out.print("|" + board.getGameBoard().get(i, j).getPlacedItem().toString() + "|");
                } else {
                    // void item is made of 5 spaces
                    System.out.print("|     |");
                }
            }
            System.out.print("\n");
        }
        System.out.println("-----------------------------------------------------------------");
    }

    public ArrayList<Coordinates> getSelection() {
        return selection;
    }
}
