package it.polimi.ingsw.View;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Model.Bookshelf;
import it.polimi.ingsw.Model.Coordinates;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.stream.Stream;

public class GUITurnSelectionHandler {
    private final Board board;
    private final ArrayList<Coordinates> selection;
    private final GridPane boardGridPane;
    private final Bookshelf bookshelf;
    private boolean middletile;

    public ArrayList<Coordinates> getSelection() {
        return selection;
    }

    public int getColumn() {
        return column;
    }

    private int column;

    public void disableButtons(Button button) {
        button.setDisable(middletile);

        if(middletile)
            System.out.println("Button disabled");
        else
            System.out.println("Button enabled");
    }

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
        int countAdjacent = 0;
        if(selection.size() == 0)
            return true;

        boolean ret = false;

        if (middletile) {
            for (Coordinates c: selection) {
                if(Math.abs(c.getX() - j) == 1 && Math.abs(c.getY() - i) == 0 || Math.abs(c.getX() - j) == 0 && Math.abs(c.getY() - i) == 1) {
                    countAdjacent++;
                }
            }
            ret = countAdjacent == selection.size();
        } else {
            for (Coordinates c: selection) {
                if(Math.abs(c.getX() - j) == 1 && Math.abs(c.getY() - i) == 0 || Math.abs(c.getX() - j) == 0 && Math.abs(c.getY() - i) == 1) {
                    ret = true;
                }
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
    private boolean selectionNotAdjacent(int i, int j) {
        if (selection.size() == 1) {
            if(Math.abs(selection.get(0).getX() - j) == 1 && Math.abs(selection.get(0).getY() - i) == 0 || Math.abs(selection.get(0).getX() - j) == 0 && Math.abs(selection.get(0).getY() - i) == 1) {
                return false;
            } else if(Math.abs(selection.get(0).getX() - j) == 2 && Math.abs(selection.get(0).getY() - i) == 0 || Math.abs(selection.get(0).getX() - j) == 0 && Math.abs(selection.get(0).getY() - i) == 2) {
                middletile = true;
                return true;
            }
        }
        return false;
    }
    public boolean select(int i, int j) {
        // is selectable
        if(!selection.contains(new Coordinates(j, i)) && selection.size() < 3 && (isAdjacentToOthers(i , j) || selectionNotAdjacent(i, j)) && !isTileEmpty(i , j) && hasTileOneSideFree(i , j)) {
            if(middletile && selection.size() == 2) {
                if(Math.abs(selection.get(0).getX() + selection.get(1).getX() - j * 2) == 0 && Math.abs(selection.get(0).getY() + selection.get(1).getY() - i * 2) == 0 || Math.abs(selection.get(0).getX() + selection.get(1).getX() - j * 2) == 0 && Math.abs(selection.get(0).getY() + selection.get(1).getY() - i * 2) == 0) {
                    selection.add(new Coordinates(j, i));
                    middletile = false;
                    Platform.runLater(() -> {
                        Button button = (Button) boardGridPane.lookup("#button" + i + j);
                        button.setOpacity(0.5);
                        button.setOnAction(event -> {
                            System.out.println("Button " + i + " " + j + " deselected!");
                            deselect(i, j);
                            Stream<Button> stream = GamePageController.buttons.stream();
                            stream.forEach(b -> GamePageController.turnSelectionHandler.disableButtons(b));
                        });
                    });
                    return middletile;
                }
                return middletile;
            } else {
                if(selectionNotAdjacent(i, j)) {
                    middletile = true;
                }
                selection.add(new Coordinates(j, i));
                Platform.runLater(() -> {
                    Button button = (Button) boardGridPane.lookup("#button" + i + j);
                    button.setOpacity(0.5);
                    button.setOnAction(event -> {
                        System.out.println("Button " + i + " " + j + " deselected!");
                        deselect(i, j);
                        Stream<Button> stream = GamePageController.buttons.stream();
                        stream.forEach(b -> GamePageController.turnSelectionHandler.disableButtons(b));
                    });
                });
            }
        }
        return middletile;
    }
    public boolean deselect(int i, int j) {

        if (selection.size() >= 3) {
            boolean toRemove = false;
            ArrayList<Coordinates> tempSelection = new ArrayList<>(selection);

            selection.remove(new Coordinates(j, i));
            if((selection.get(0).getX() == selection.get(1).getX() && selection.get(0).getY() + selection.get(1).getY() - i * 2 == 0 || selection.get(0).getX() + selection.get(1).getX() - j * 2 == 0 && selection.get(0).getY() == selection.get(1).getY())) {
                middletile = true;
            }
            Platform.runLater(() -> {
                Button button = (Button) boardGridPane.lookup("#button" + i + j);
                button.setOpacity(1);
                button.setOnAction(event -> {
                    System.out.println("Button " + i + " " + j + " selected!");
                    select(i, j);
                    Stream<Button> stream = GamePageController.buttons.stream();
                    stream.forEach(b -> GamePageController.turnSelectionHandler.disableButtons(b));
                });
            });


        } else {
            selection.remove(new Coordinates(j, i));
            middletile = false;
            Platform.runLater(() -> {
                Button button = (Button) boardGridPane.lookup("#button" + i + j);
                button.setOpacity(1);
                button.setOnAction(event -> {
                    System.out.println("Button " + i + " " + j + " selected!");
                    select(i, j);
                    Stream<Button> stream = GamePageController.buttons.stream();
                    stream.forEach(b -> GamePageController.turnSelectionHandler.disableButtons(b));
                });
            });
        }
        return middletile;
    }
}
