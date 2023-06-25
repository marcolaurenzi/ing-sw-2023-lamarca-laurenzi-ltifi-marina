package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.Decks.ItemDeck;
import it.polimi.ingsw.Model.Exceptions.VoidBoardTileException;

import java.io.Serializable;

/**
 * This class represents the game board of the game.
 */
public class Board implements Serializable {

    private Matrix<BoardTile> gameBoard;
    private ItemDeck itemDeck;

    /**
     * Determines how many free sides the single tile on the board has.
     *
     * @param x the first axis of the board representing the row of the tile
     * @param y the second axis of the board representing the column of the tile
     * @return the number of free sides in the range (0 to 4)
     */
    public int hasFree(int x, int y) {
        int freeSides = 0;
        for (int i = x - 1; i < x + 2; i += 2) {
            if (i < 0) continue;
            if (i > 8) continue;
            if (gameBoard.get(i, y).isEmpty()) freeSides++;
        }
        for (int i = y - 1; i < y + 2; i += 2) {
            if (i < 0) continue;
            if (i > 8) continue;
            if (gameBoard.get(x, i).isEmpty()) freeSides++;
        }
        return freeSides;
    }

    /**
     * Calls the BoardTile class' method to place an item on the single tile.
     *
     * @param itemToPlace reference to the Item to place
     * @param x           the first axis of the board representing the row of the tile
     * @param y           the second axis of the board representing the column of the tile
     */
    private void placeItem(Item itemToPlace, int x, int y) {
        this.gameBoard.get(x, y).placeItem(itemToPlace);
    }

    /**
     * Removes an item from the BoardTile and re-inserts it in the ItemDeck.
     *
     * @param x the first axis of the board representing the row of the tile
     * @param y the second axis of the board representing the column of the tile
     */
    public void removeItem(int x, int y) {
        if (!gameBoard.get(x, y).isEmpty()) {
            itemDeck.getDeck().add(gameBoard.get(x, y).getPlacedItem());
            gameBoard.get(x, y).brutePlaceItem(null);
        }
    }

    /**
     * Removes all the items from the BoardTile and re-inserts them in the ItemDeck.
     */
    public void removeAllItems() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                removeItem(i, j);
            }
        }
    }

    /**
     * Calls the BoardTile class' method to draw the item from the single tile.
     *
     * @param x the first axis of the board representing the row of the tile
     * @param y the second axis of the board representing the column of the tile
     * @return the tile if it is present, null otherwise
     */
    public Item drawItem(int x, int y) {
        return this.gameBoard.get(x, y).drawItem();
    }

    /**
     * Calls the BoardTile class' method to check the item present on the single tile.
     *
     * @param x the first axis of the board representing the row of the tile
     * @param y the second axis of the board representing the column of the tile
     * @return the tile if it is present, null otherwise
     */
    private Item checkItem(int x, int y) {
        return this.gameBoard.get(x, y).checkItem();
    }

    /**
     * Prints the Board.
     */
    public void printBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.println(gameBoard.get(i, j).getNumberOfPlayersSign());
            }
        }
    }

    /**
     * Checks whether the game Board must be refreshed or not.
     *
     * @return true if the Board must be refreshed, false otherwise
     */
    public boolean toRefresh() {
        int rows = gameBoard.getColumnDimension();
        int columns = gameBoard.getRowDimension();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {

                BoardTile curr = gameBoard.get(i, j);

                // if the Board tile is valid and there is an Item on it
                if (curr.getNumberOfPlayersSign() != 5 && curr.getPlacedItem() != null) {
                    // if the Item has a free side the board must not be refreshed
                    if (!isFreeNorth(i, j) || !isFreeSouth(i, j) || !isFreeEast(i, j) || !isFreeWest(i, j)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Refreshes the game Board by drawing an Item for each valid empty BoardTile.
     *
     * @param game the reference to the current Game
     */
    public void refreshBoard(Game game) {
        int rows = gameBoard.getColumnDimension();
        int columns = gameBoard.getRowDimension();

        // empty the board
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Item curr = gameBoard.get(i, j).getPlacedItem();
                if (curr != null) {
                    removeItem(i, j);
                }
            }
        }

        int max = game.getMaxPlayers();
        // refill the board
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int curr = gameBoard.get(i, j).getNumberOfPlayersSign();
                if (curr <= max) {
                    gameBoard.get(i, j).placeItem(game.getItemDeck().draw());
                }
            }
        }
    }

    /**
     * Initializes the game Board by drawing an Item for each valid Board tile.
     *
     * @param game the reference to the current Game
     */
    public void initializeBoard(Game game) {
        for (int i = 0; i < gameBoard.getColumnDimension(); i++) {
            for (int j = 0; j < gameBoard.getRowDimension(); j++) {
                if (gameBoard.get(i, j).getNumberOfPlayersSign() <= game.getMaxPlayers()) {
                    gameBoard.get(i, j).placeItem(game.getItemDeck().draw());
                }
            }
        }
    }

    /**
     * Checks whether the selected board tile is free on the north or not.
     * Note that this method returns true even if the tile doesn't have an item on it.
     *
     * @param i the first axis of the board representing the row of the tile
     * @param j the second axis of the board representing the column of the tile
     * @return true if the north tile is free, false otherwise
     */
    public boolean isFreeNorth(int i, int j) {
        return i != 0 && gameBoard.get(i - 1, j).getPlacedItem() == null;
    }

    /**
     * Checks whether the selected board tile is free on the south or not.
     * Note that this method returns true even if the tile doesn't have an item on it.
     *
     * @param i the first axis of the board representing the row of the tile
     * @param j the second axis of the board representing the column of the tile
     * @return true if the south tile is free, false otherwise
     */
    public boolean isFreeSouth(int i, int j) {
        return i != 8 && gameBoard.get(i + 1, j).getPlacedItem() == null;
    }

    /**
     * Checks whether the selected board tile is free on the east or not.
     * Note that this method returns true even if the tile doesn't have an item on it.
     *
     * @param i the first axis of the board representing the row of the tile
     * @param j the second axis of the board representing the column of the tile
     * @return true if the east tile is free, false otherwise
     */
    public boolean isFreeEast(int i, int j) {
        return j != 8 && gameBoard.get(i, j + 1).getPlacedItem() == null;
    }

    /**
     * Checks whether the selected board tile is free on the west or not.
     * Note that this method returns true even if the tile doesn't have an item on it.
     *
     * @param i the first axis of the board representing the row of the tile
     * @param j the second axis of the board representing the column of the tile
     * @return true if the west tile is free, false otherwise
     */
    public boolean isFreeWest(int i, int j) {
        return j != 0 && gameBoard.get(i, j - 1).getPlacedItem() == null;
    }

    public Matrix<BoardTile> getGameBoard() {
        return gameBoard;
    }

    public ItemDeck getItemDeck() {
        return itemDeck;
    }

    public void setItemDeck(ItemDeck deck) {
        this.itemDeck = deck;
    }

}
