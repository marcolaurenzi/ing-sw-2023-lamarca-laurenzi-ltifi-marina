package it.polimi.ingsw.Client;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Model.Coordinates;

import java.util.ArrayList;
/**
 * This class represents the navigator of the game board.
 */
public class BoardNavigator {

    /**
     * The game board.
     */
    private Board board;

    /**
     * The list of selected tiles.
     */
    private ArrayList<Coordinates> selection;

    /**
     * The current cursor position.
     */
    private Coordinates cursor;

    /**
     * Flag indicating if the current tile is a middle tile.
     */
    private boolean middleTile;

    /**
     * Initializes a new instance of the BoardNavigator class with the specified board.
     *
     * @param board The game board.
     */
    public BoardNavigator(Board board) {
        this.board = board;
        this.selection = new ArrayList<>();
        this.cursor = new Coordinates(0, 0);
    }

    /**
     * Checks if the current tile has at least one free side.
     *
     * @return True if the tile has at least one free side, false otherwise.
     */
    private boolean hasTileOneSideFree()  {
        return board.hasFree(cursor.getY(), cursor.getX()) >= 1;
    }

    /**
     * Checks if the current tile is empty.
     *
     * @return True if the tile is empty, false otherwise.
     */
    private boolean isTileEmpty() {
        return board.getGameBoard().get(cursor.getY(), cursor.getX()).isEmpty();
    }

    /**
     * Checks if the current tile is next to any selected tile.
     *
     * @return True if the tile is next to any selected tile, false otherwise.
     */
    private boolean isNextToOthers() {
        if(selection.size() == 0)
            return true;
        boolean ret = false;

        for (Coordinates c: selection) {
            if (Math.abs(c.getX() - cursor.getX()) == 1 && Math.abs(c.getY() - cursor.getY()) == 0 || Math.abs(c.getX() - cursor.getX()) == 0 && Math.abs(c.getY() - cursor.getY()) == 1) {
                ret = true;
                break;
            }
        }

        return ret;
    }

    /**
     * Checks if the current tile is adjacent to any selected tile on the same row or column.
     *
     * @return True if the tile is adjacent to any selected tile on the same row or column, false otherwise.
     */
    private boolean isAdjacentToOthers() {
        return (isSameRow() || isSameColumn()) && isNextToOthers();
    }

    /**
     * Checks if all selected tiles are in the same column.
     *
     * @return True if all selected tiles are in the same column, false otherwise.
     */
    private boolean isSameColumn() {
        for (Coordinates coordinates : selection)
            if (cursor.getX() != coordinates.getX())
                return false;

        return true;
    }

    /**
     * Checks if all selected tiles are in the same row.
     *
     * @return True if all selected tiles are in the same row, false otherwise.
     */
    private boolean isSameRow() {
        for (Coordinates coordinates : selection)
            if (cursor.getY() != coordinates.getY())
                return false;

        return true;
    }

    /**
     * Checks if the current selection is not adjacent to the cursor.
     *
     * @return True if the selection is not adjacent to the cursor, false otherwise.
     */
    private boolean selectionNotAdjacent() {
        if (selection.size() == 1) {
            if(Math.abs(selection.get(0).getX() - cursor.getX()) == 1 && Math.abs(selection.get(0).getY() - cursor.getY()) == 0 ||
                    Math.abs(selection.get(0).getX() - cursor.getX()) == 0 && Math.abs(selection.get(0).getY() - cursor.getY()) == 1) {
                return false;
            } else if(Math.abs(selection.get(0).getX() - cursor.getX()) == 2 && Math.abs(selection.get(0).getY() - cursor.getY()) == 0 ||
                    Math.abs(selection.get(0).getX() - cursor.getX()) == 0 && Math.abs(selection.get(0).getY() - cursor.getY()) == 2) {
                middleTile = true;
                return true;
            }
        }
        return false;
    }

    /**
     * Selects the current tile if it meets the selection criteria.
     *
     * @return True if the current tile was successfully selected, false otherwise.
     */
    public boolean select() {
        if(!selection.contains(cursor) && selection.size() < 3 && (isAdjacentToOthers() || selectionNotAdjacent()) && !isTileEmpty() && hasTileOneSideFree())
            if(middleTile && selection.size() == 2){
                if(Math.abs(selection.get(0).getY() + selection.get(1).getY() - cursor.getY() * 2) == 0 || Math.abs(selection.get(0).getX() + selection.get(1).getX() - cursor.getX() * 2) == 0 && Math.abs(selection.get(0).getY() + selection.get(1).getY() - cursor.getY() * 2) == 0) {
                    selection.add(new Coordinates(cursor.getX(), cursor.getY()));
                    middleTile = false;
                    //return middleTile;
                }
                //return middleTile;
            } else {
                selection.add(new Coordinates(cursor.getX(), cursor.getY()));
            }
        return middleTile;
    }

    /**
     * Deselects the current tile if it is part of the selection.
     *
     * @return True if the current tile was successfully deselected, false otherwise.
     */
    public boolean deselect() {
        if(selection.size() >= 3) {
            boolean toRemove = false;
            ArrayList<Coordinates> tempSelection = new ArrayList<>(selection);

            for (Coordinates c : tempSelection) {
                if (c.equals(cursor))
                    toRemove = true;
                if (toRemove) {
                    selection.remove(cursor);
                    middleTile = false;
                }
            }
        } else {
            selection.remove(cursor);
            middleTile = false;
        }
        return middleTile;
    }

    /**
     * Moves the cursor up one row if possible.
     */
    public void moveUp() {
        if(cursor.getY() > 0)
            cursor = new Coordinates(cursor.getX(), cursor.getY() - 1);
    }

    /**
     * Moves the cursor down one row if possible.
     */
    public void moveDown() {
        if(cursor.getY() < board.getGameBoard().getColumnDimension() - 1)
            cursor = new Coordinates(cursor.getX(), cursor.getY() + 1);
    }

    /**
     * Moves the cursor left one column if possible.
     */
    public void moveLeft() {
        if(cursor.getX() > 0)
            cursor = new Coordinates(cursor.getX() - 1, cursor.getY());
    }

    /**
     * Moves the cursor right one column if possible.
     */
    public void moveRight() {
        if(cursor.getX() < board.getGameBoard().getRowDimension() - 1)
            cursor = new Coordinates(cursor.getX() + 1, cursor.getY());
    }

    /**
     * Prints the game board with the current cursor and selection highlighted.
     */
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
                } else if (board.getGameBoard().get(i, j).getPlacedItem() != null) {
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

    /**
     * Returns the current selection of tiles.
     *
     * @return The list of selected tiles.
     */
    public ArrayList<Coordinates> getSelection() {
        return selection;
    }
}
