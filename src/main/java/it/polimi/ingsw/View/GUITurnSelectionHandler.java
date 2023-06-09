package it.polimi.ingsw.View;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Model.Bookshelf;
import it.polimi.ingsw.Model.Coordinates;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class GUITurnSelectionHandler {
    private final Board board;
    private final ArrayList<Coordinates> selection;
    private final GridPane boardGridPane;
    private final Bookshelf bookshelf;

    public ArrayList<Coordinates> getSelection() {
        return selection;
    }

    public int getColumn() {
        return column;
    }

    private int column;





    public GUITurnSelectionHandler(Board board, GridPane boardGridPane, Bookshelf bookshelf) {
        this.board = board;
        this.boardGridPane = boardGridPane;
        this.bookshelf = bookshelf;
        this.selection = new ArrayList<>();
    }

    public void selectColumn(int j) {
        int i = 0;

        while(i < bookshelf.getColumnDimension() && bookshelf.get(i, j) == null) {
            i++;
        }

        if(selection.size() <= i && selection.size() > 0) {
            column = j;
            GUI.getSharedObject().setVariable();

        }

    }
    private boolean hasTileOneSideFree(int i, int j)  {
        return board.hasFree(i, j) >= 1;
    }
    private boolean isTileEmpty(int i, int j) {
        return board.getGameBoard().get(i, j).isEmpty();
    }
    private boolean isNextToOthers(int i, int j) {
        if(selection.size() == 0)
            return true;

        boolean ret = false;

        for (Coordinates c: selection) {
            if(Math.abs(c.getX() - j) == 1 && Math.abs(c.getY() - i) == 0 || Math.abs(c.getX() - j) == 0 && Math.abs(c.getY() - i) == 1) {
                ret = true;
            }
        }

        return ret;
    }
    private boolean isAdjacentToOthers(int i, int j) {
        return (isSameRow(i , j) || isSameColumn(i, j)) && isNextToOthers(i, j);
    }
    private boolean isSameColumn(int i, int j) {
        for (Coordinates coordinates : selection)
            if (j != coordinates.getX())
                return false;

        return true;
    }
    private boolean isSameRow(int i, int j) {
        for (Coordinates coordinates : selection)
            if (i != coordinates.getY())
                return false;

        return true;
    }
    public void select(int i, int j) {
        if(!selection.contains(new Coordinates(j, i)) && selection.size() < 3 && isAdjacentToOthers(i , j) && !isTileEmpty(i , j) && hasTileOneSideFree(i , j)) {
            selection.add(new Coordinates(j, i));

            Platform.runLater(() -> {
                Button button = (Button) boardGridPane.lookup("#button" + i + j);
                button.setOpacity(0.5);
                button.setOnAction(event -> {
                    System.out.println("Button " + i + " " + j + " deselected!");
                    deselect(i, j);
                });
            });
        }
    }
    public void deselect(int i, int j) {

        if(selection.size() >= 3) {
            boolean toRemove = false;
            ArrayList<Coordinates> tempSelection = new ArrayList<>(selection);

            for (Coordinates c : tempSelection) {
                if (c.equals(new Coordinates(j, i)))
                    toRemove = true;
                if (toRemove) {
                    selection.remove(new Coordinates(j, i));
                    Platform.runLater(() -> {
                        Button button = (Button) boardGridPane.lookup("#button" + i + j);
                        button.setOpacity(1);
                        button.setOnAction(event -> {
                            System.out.println("Button " + i + " " + j + " selected!");
                            select(i, j);
                        });
                    });
                }
            }
        }

        else
            selection.remove(new Coordinates(j, i));
            Platform.runLater(() -> {
                Button button = (Button) boardGridPane.lookup("#button" + i + j);
                button.setOpacity(1);
                button.setOnAction(event -> {
                    System.out.println("Button " + i + " " + j + " selected!");
                    select(i, j);
                });
            });
    }
}
