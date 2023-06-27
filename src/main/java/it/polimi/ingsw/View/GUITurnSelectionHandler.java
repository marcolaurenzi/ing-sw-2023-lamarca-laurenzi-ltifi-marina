package it.polimi.ingsw.View;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Model.Bookshelf;
import it.polimi.ingsw.Model.Coordinates;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;
import java.util.stream.Stream;
/**
 * This class represents Turn Selection Handler.
 */
public class GUITurnSelectionHandler {
    /**
     * The board of the game.
     */
    private final Board board;
    /**
     * The current selection of coordinates.
     */
    private final ArrayList<Coordinates> selection;
    /**
     * The GridPane representing the board.
     */
    private final GridPane boardGridPane;
    /**
     * The Bookshelf of the game.
     */
    private final Bookshelf bookshelf;
    /**
     * Boolean representing whether the selected tile is in the middle.
     */
    private boolean middletile;
    /**
     * The selected column.
     */
    private int column;
    /**
     * The selected row.
     */
    private int row;


    private GamePageController controller;

    /**
     * Returns the current selection of coordinates.
     *
     * @return the current selection of coordinates
     */
    public ArrayList<Coordinates> getSelection() {
        return selection;
    }

    /**
     * Returns the selected column.
     *
     * @return the selected column
     */
    public int getColumn() {
        return column;
    }


    /**
     * Disables the given button based on the value of `middletile`.
     *
     * @param button the button to disable
     */
    public void disableButtons(Button button) {
        button.setDisable(middletile);

        if(middletile)
            System.out.println("Button disabled");
        else
            System.out.println("Button enabled");
    }

    /**
     * Constructs a new GUITurnSelectionHandler with the given parameters.
     *
     * @param controller the GamePageController instance
     * @param board the Board instance
     * @param boardGridPane the GridPane representing the board
     * @param bookshelf the Bookshelf instance
     */
    public GUITurnSelectionHandler(GamePageController controller, Board board, GridPane boardGridPane, Bookshelf bookshelf) {
        this.controller = controller;
        this.board = board;
        this.boardGridPane = boardGridPane;
        this.bookshelf = bookshelf;
        this.selection = new ArrayList<>();
    }

    /**
     * Selects the specified column and updates the selection.
     *
     * @param j the index of the column to select
     */
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

    /**
     * Checks if the tile at the specified coordinates has at least one side free.
     *
     * @param i the row index of the tile
     * @param j the column index of the tile
     * @return true if the tile has at least one side free, false otherwise
     */
    /**
     * Checks if the tile at the specified coordinates has at least one side free.
     *
     * @param i the row index of the tile
     * @param j the column index of the tile
     * @return true if the tile has at least one side free, false otherwise
     */
    private boolean hasTileOneSideFree(int i, int j) {
        return board.hasFree(i, j) >= 1;
    }

    /**
     * Checks if the tile at the specified coordinates is empty.
     *
     * @param i the row index of the tile
     * @param j the column index of the tile
     * @return true if the tile is empty, false otherwise
     */
    private boolean isTileEmpty(int i, int j) {
        return board.getGameBoard().get(i, j).isEmpty();
    }

    /**
     * Checks if the tile at the specified coordinates is adjacent to the tiles in the current selection.
     *
     * @param i the row index of the tile
     * @param j the column index of the tile
     * @return true if the tile is adjacent to the tiles in the selection, false otherwise
     */
    private boolean isNextToOthers(int i, int j) {
        int countAdjacent = 0;
        if (selection.size() == 0)
            return true;

        boolean ret = false;

        if (middletile) {
            for (Coordinates c : selection) {
                if (Math.abs(c.getX() - j) == 1 && Math.abs(c.getY() - i) == 0 || Math.abs(c.getX() - j) == 0 && Math.abs(c.getY() - i) == 1) {
                    countAdjacent++;
                }
            }
            ret = countAdjacent == selection.size();
        } else {
            for (Coordinates c : selection) {
                if (Math.abs(c.getX() - j) == 1 && Math.abs(c.getY() - i) == 0 || Math.abs(c.getX() - j) == 0 && Math.abs(c.getY() - i) == 1) {
                    ret = true;
                    break;
                }
            }
        }

        return ret;
    }

    /**
     * Checks if the tile at the specified coordinates is adjacent to the tiles in the current selection and shares the same row or column.
     *
     * @param i the row index of the tile
     * @param j the column index of the tile
     * @return true if the tile is adjacent to the tiles in the selection and shares the same row or column, false otherwise
     */
    private boolean isAdjacentToOthers(int i, int j) {
        return (isSameRow(i, j) || isSameColumn(i, j)) && isNextToOthers(i, j);
    }

    /**
     * Checks if all the tiles in the current selection share the same column.
     *
     * @param i the row index of the tile
     * @param j the column index of the tile
     * @return true if all the tiles in the selection share the same column, false otherwise
     */
    private boolean isSameColumn(int i, int j) {
        for (Coordinates coordinates : selection)
            if (j != coordinates.getX())
                return false;

        return true;
    }

    /**
     * Checks if all the tiles in the current selection share the same row.
     *
     * @param i the row index of the tile
     * @param j the column index of the tile
     * @return true if all the tiles in the selection share the same row, false otherwise
     */
    private boolean isSameRow(int i, int j) {
        for (Coordinates coordinates : selection)
            if (i != coordinates.getY())
                return false;

        return true;
    }

    /**
     * Checks if the specified tile is not adjacent to the only tile in the current selection.
     *
     * @param i the row index of the tile
     * @param j the column index of the tile
     * @return true if the tile is not adjacent to the only tile in the selection, false otherwise
     */
    private boolean selectionNotAdjacent(int i, int j) {
        if (selection.size() == 1) {
            if (Math.abs(selection.get(0).getX() - j) == 1 && Math.abs(selection.get(0).getY() - i) == 0 || Math.abs(selection.get(0).getX() - j) == 0 && Math.abs(selection.get(0).getY() - i) == 1) {
                return false;
            } else if(Math.abs(selection.get(0).getX() - j) == 2 && Math.abs(selection.get(0).getY() - i) == 0 || Math.abs(selection.get(0).getX() - j) == 0 && Math.abs(selection.get(0).getY() - i) == 2) {
                middletile = true;
                return true;
            }
        }
        return false;
    }


    /**
     * Selects the specified tile and updates the selection.
     *
     * @param i the row index of the tile
     * @param j the column index of the tile
     * @param imageView the ImageView associated with the tile
     * @param button the Button associated with the tile
     * @return true if the tile is a middletile, false otherwise
     */
    public boolean select(int i, int j, ImageView imageView, Button button) {
        ArrayList<Coordinates> prevSelection = new ArrayList<>(selection);
        // is selectable
        if(!selection.contains(new Coordinates(j, i)) && selection.size() < 3 && (isAdjacentToOthers(i , j) || selectionNotAdjacent(i, j)) && !isTileEmpty(i , j) && hasTileOneSideFree(i , j)) {
            GamePageController.incrementGlobalPickCounter();
            GamePageController.incrementCurrentPickDimension();
            //pickLabelSetUp(button, imageView);
            if(middletile && selection.size() == 2) {
                if(Math.abs(selection.get(0).getX() + selection.get(1).getX() - j * 2) == 0 && Math.abs(selection.get(0).getY() + selection.get(1).getY() - i * 2) == 0) {
                    selection.add(new Coordinates(j, i));
                    middletile = false;
                    Platform.runLater(() -> {
                        button.setOpacity(0.5);
                        button.setOnAction(event -> {
                            System.out.println("Button " + i + " " + j + " deselected!");
                            deselect(i, j, imageView, button);
                            Stream<Button> stream = GamePageController.buttons.stream();
                            stream.forEach(b -> GamePageController.turnSelectionHandler.disableButtons(b));
                        });
                    });
                    updateLittleNumbers(selection, prevSelection);
                    return middletile;
                }
                updateLittleNumbers(selection, prevSelection);
                return middletile;
            } else {
                if(selectionNotAdjacent(i, j)) {
                    middletile = true;
                }
                selection.add(new Coordinates(j, i));
                Platform.runLater(() -> {
                    button.setOpacity(0.5);
                    button.setOnAction(event -> {
                        System.out.println("Button " + i + " " + j + " deselected!");
                        deselect(i, j, imageView, button);
                        Stream<Button> stream = GamePageController.buttons.stream();
                        stream.forEach(b -> GamePageController.turnSelectionHandler.disableButtons(b));
                    });
                });
            }
        }
        updateLittleNumbers(selection, prevSelection);
        return middletile;
    }

    /**
     * Deselects the specified tile and updates the selection.
     *
     * @param i the row index of the tile
     * @param j the column index of the tile
     * @param imageView the ImageView associated with the tile
     * @param button the Button associated with the tile
     * @return true if the tile is a middletile, false otherwise
     */
    public boolean deselect(int i, int j, ImageView imageView, Button button) {
        ArrayList<Coordinates> prevSelection = new ArrayList<>(selection);

        GamePageController.decrementGlobalPickCounter();
        GamePageController.decrementCurrentPickDimension();
        //pickLabelClear(button);
        if (selection.size() >= 3) {
            boolean toRemove = false;
            ArrayList<Coordinates> tempSelection = new ArrayList<>(selection);

            selection.remove(new Coordinates(j, i));
            if((selection.get(0).getX() == selection.get(1).getX() && selection.get(0).getY() + selection.get(1).getY() - i * 2 == 0 || selection.get(0).getX() + selection.get(1).getX() - j * 2 == 0 && selection.get(0).getY() == selection.get(1).getY())) {
                middletile = true;
            }
            Platform.runLater(() -> {
                button.setOpacity(1);
                button.setOnAction(event -> {
                    System.out.println("Button " + i + " " + j + " selected!");
                    select(i, j, imageView, button);
                    Stream<Button> stream = GamePageController.buttons.stream();
                    stream.forEach(b -> GamePageController.turnSelectionHandler.disableButtons(b));
                });
            });
        } else {
            selection.remove(new Coordinates(j, i));
            middletile = false;
            Platform.runLater(() -> {
                button.setOpacity(1);
                button.setOnAction(event -> {
                    System.out.println("Button " + i + " " + j + " selected!");
                    select(i, j, imageView, button);
                    Stream<Button> stream = GamePageController.buttons.stream();
                    stream.forEach(b -> GamePageController.turnSelectionHandler.disableButtons(b));
                });
            });
        }

        updateLittleNumbers(selection, prevSelection);
        return middletile;
    }

    private void updateLittleNumbers(ArrayList<Coordinates> currSelection, ArrayList<Coordinates> prevSelection) {
        //case of deselection
            //remove all little numbers from prev selection
        for (Coordinates c: prevSelection) {
            Button button = (Button) controller.getBoardGridPane().lookup("#button" + c.getY() + c.getX());
            removeLittleNumberFromButton(button);
        }

        //add the little button to the currSelection
        for(Coordinates c: currSelection) {
            Button button = (Button) controller.getBoardGridPane().lookup("#button" + c.getY() + c.getX());
            putLittleNumberOnButton(button, currSelection.indexOf(c) + 1);
        }
    }

    private void removeLittleNumberFromButton (Button button) {
        StackPane stackPane = (StackPane) button.getGraphic();

        for(Node n : stackPane.getChildren()) {
            if(n instanceof Label) {
                ((Label) n).setText("");
            }
        }
    }

    private void putLittleNumberOnButton (Button button, int val){
        Node n = button.getGraphic();
        if(n instanceof ImageView) {
            ImageView imageView = (ImageView) button.getGraphic();
            Label label = new Label(String.valueOf(val));
            label.setStyle("-fx-text-fill: black; -fx-font-weight: bold;");
            label.setAlignment(Pos.TOP_RIGHT);
            label.setPrefSize(GamePageController.getWidth(), GamePageController.getHeight());
            label.setMouseTransparent(true);
            StackPane stackPane = new StackPane(imageView, label);
            button.setGraphic(stackPane);
        } else {
            StackPane stackPane = (StackPane) button.getGraphic();
            for(Node e : stackPane.getChildren()) {
                if(e instanceof Label) {
                    ((Label) e).setText(String.valueOf(val));
                }
            }
        }
    }

    /**
     * Sets up the pick label on the specified button.
     *
     * @param button the button to set up the pick label on
     * @param imageView the ImageView associated with the button
     */
    /*public void pickLabelSetUp(Button button, ImageView imageView) {
        Label label = new Label(String.valueOf(GamePageController.getGlobalPickCounter()));
        label.setStyle("-fx-text-fill: black; -fx-font-weight: bold;");
        label.setAlignment(Pos.TOP_RIGHT);
        label.setPrefSize(GamePageController.getWidth(), GamePageController.getHeight());
        label.setMouseTransparent(true);
        StackPane stackPane = new StackPane(imageView, label);
        button.setGraphic(stackPane);
    }*/

    /**
     * Clears the pick label on the specified button.
     *
     * @param button the button to clear the pick label from
     */
    /*public void pickLabelClear(Button button) {
        StackPane stackPane = (StackPane) button.getGraphic();

        int x = 0;
        for(Node n : stackPane.getChildren()) {
            if(n instanceof Label) {
                x = Integer.parseInt(((Label) n).getText());
                ((Label) n).setText("");
            }
        }

        if(x <= GamePageController.getGlobalPickCounter()) {
            for(Node n : controller.getBoardGridPane().getChildren()) {
                Button b = (Button) n;
                StackPane sp = null;
                if(b.getGraphic() instanceof StackPane) {
                    sp = (StackPane) b.getGraphic();
                }
                if(sp != null) {
                    for(Node e : sp.getChildren()) {
                        if(e instanceof Label) {
                            // If the label is not empty, the tile has a value that must be decremented and is not the first tile (as it must stay the same) then I decrement its value
                            if (!((Label) e).getText().equals("") && (Integer.parseInt(((Label) e).getText()) > 1)) {
                                ((Label) e).setText(String.valueOf(Integer.parseInt(((Label) e).getText()) - 1));
                            }
                        }
                    }
                }
            }
        }
    }*/
}
