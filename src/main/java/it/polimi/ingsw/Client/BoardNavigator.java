package it.polimi.ingsw.Client;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Model.Coordinates;
import it.polimi.ingsw.Model.Exceptions.VoidBoardTileException;

import java.awt.*;
import java.util.ArrayList;

public class  BoardNavigator {

    private Board board;
    private ArrayList<Coordinates> selection;
    private Coordinates cursor;

    public BoardNavigator(Board board) {
        this.board = board;
        this.selection = new ArrayList<>();
        this.cursor = new Coordinates(0, 0);
    }
    private boolean hasTileOneSideFree()  {
        return board.hasFree(cursor.getX(), cursor.getY()) >= 1;
    }
    private boolean isTileEmpty() {
        return board.getGameBoard().get(cursor.getY(), cursor.getX()).isEmpty();
    }
    private boolean isAdjacentToOthers() {
        return isSameRow() || isSameColumn();
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
    public void select() {
        if(!selection.contains(cursor) && selection.size() < 3 && isAdjacentToOthers() && !isTileEmpty() && hasTileOneSideFree())
            selection.add(new Coordinates(cursor.getX(), cursor.getY()));
    }
    public void deselect() {

        if(selection.size() >= 3) {
            boolean toRemove = false;
            ArrayList<Coordinates> tempSelection = new ArrayList<>(selection);

            for (Coordinates c : tempSelection) {
                if (c.equals(cursor))
                    toRemove = true;
                if (toRemove)
                    selection.remove(cursor);
            }
        }

        else
            selection.remove(cursor);
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
        if(cursor.getY() < board.getGameBoard().getRowDimension() - 1)
            cursor = new Coordinates(cursor.getX() + 1, cursor.getY());
    }
    public void print() {
        System.out.println("-----------");
        System.out.println("  0 1 2 3 4 5 6 7 8");
        for(int i = 0; i < board.getGameBoard().getColumnDimension(); i++) {
            System.out.print(i + "|");
            for (int j = 0; j < board.getGameBoard().getRowDimension(); j++) {
                if(j == cursor.getX() && i == cursor.getY()) {
                    System.out.print("O ");
                } else if (selection.contains(new Coordinates(j, i))) {
                    System.out.print("X ");
                } else if (board.getGameBoard().get(i, j).getPlacedItem() != null) {
                    System.out.print(board.getGameBoard().get(i, j).getPlacedItem().toString() + " ");
                } else
                    System.out.print("# ");
            }
            System.out.println("| \n");
        }
        System.out.println("-----------");
    }

    public ArrayList<Coordinates> getSelection() {
        return selection;
    }
}
