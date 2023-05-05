package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.Decks.ItemDeck;
import it.polimi.ingsw.Model.Exceptions.VoidBoardTileException;

import java.io.Serializable;

/**
 * this class represents the game board of the game
 */
public class Board implements Serializable {

    /* ************************************************************************************************************
     *                          START OF ATTRIBUTES DECLARATION
     ************************************************************************************************************ */
    private Matrix<BoardTile> gameBoard;
    private ItemDeck itemDeck;

    /* ************************************************************************************************************
     *                          END OF ATTRIBUTES DECLARATION
     *                          START OF CUSTOM METHODS
     ************************************************************************************************************ */

    /**
     * This method is used to determine how many free sides the single tile on the board has
     * @param x is the first axis of the board representing the row of the tile
     * @param y is the second axis of the board representing the column of the tile
     * @return returns the number of free sides in the range (0 to 4)
     */
    public int hasFree(int x, int y) {
        int freeSides = 0;
        for(int i = x - 1; i < x + 2;i += 2){
            if(i < 0) continue;
            if(i > 8) continue;
            if(gameBoard.get(i, y).isEmpty()) freeSides++;
        }
        for(int i = y - 1; i < y + 2;i += 2){
            if(i < 0) continue;
            if(i > 8) continue;
            if(gameBoard.get(x, i).isEmpty()) freeSides++;
        }
        return freeSides;
    }

    /**
     * This method is used to call the BoardTile class' method to place an item on the single tile
     * @param itemToPlace reference to the Item to place
     * @param x is the first axis of the board representing the row of the tile
     * @param y is the second axis of the board representing the column of the tile
     */
    private void placeItem(Item itemToPlace, int x, int y){
        this.gameBoard.get(x, y).placeItem(itemToPlace);
    }

    /**
     * This method is used to remove an item from the BoardTile and re-insert it in the ItemDeck
     * @param x is the first axis of the board representing the row of the tile
     * @param y is the second axis of the board representing the column of the tile
     */
    public void removeItem(int x, int y){
        if(!gameBoard.get(x,y).isEmpty()) {
            itemDeck.getDeck().add(gameBoard.get(x, y).getPlacedItem());
            gameBoard.get(x,y).brutePlaceItem(null);
        }
    }

    /**
     * This method is used to remove all the left items from the BoardTile and re-insert them in the ItemDeck
     */
    public void removeAllItems() {
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                removeItem(i, j);
            }
        }
    }

    /**
     * This method is used to call the BoardTile class' method to draw the item from the single tile
     * @param x is the first axis of the board representing the row of the tile
     * @param y is the second axis of the board representing the column of the tile
     * @return returns the tile if it is present, returns null otherwise
     */
    public Item drawItem(int x, int y){
        return this.gameBoard.get(x, y).drawItem();
    }

    /**
     * This method is used to call the BoardTile class' method to check the item present on the single tile
     *
     * @param x is the first axis of the board representing the row of the tile
     * @param y is the second axis of the board representing the column of the tile
     * @return returns the tile if it is present, returns null otherwise
     */
    private Item checkItem(int x, int y){
        return this.gameBoard.get(x, y).checkItem();
    }

    /**
     * This method prints the Board
     */
    public void printBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.println(gameBoard.get(i, j).getNumberOfPlayersSign());

            }
        }
    }

    /**
     * This method checks whether the game Board must be refreshed or not
     *
     * @return true if the Board must be refreshed, false otherwise
     */
    public boolean toRefresh(){
        int rows = gameBoard.getColumnDimension();
        int columns = gameBoard.getRowDimension();
        int temp = 0;
        for(int i = 0; i<rows; i++) {
            for(int j = 0; j<columns; j++) {

                BoardTile curr = gameBoard.get(i,j);
                if(curr.getNumberOfPlayersSign() != 5 && curr.getPlacedItem() != null) {
                    temp++;
                    if(!isFreeNorth(i,j) || !isFreeSouth(i,j) || !isFreeEast(i,j) || !isFreeWest(i,j)) {
                        return false;
                    }
                }
            }
        }
        return temp != 0;
    }

    /**
     * This method refreshes the game Board by drawing an Item for each valid empty BoardTile
     * @param game the reference to the current Game
     */
    public void refreshBoard(Game game){
        int rows = gameBoard.getColumnDimension();
        int columns = gameBoard.getRowDimension();

        // empty the board
        for(int i = 0; i<rows; i++) {
            for(int j = 0; j<columns; j++) {
                Item curr = gameBoard.get(i,j).getPlacedItem();
                if(curr != null) {
                    removeItem(i,j);
                }
            }
        }

        int max = game.getMaxPlayers();
        // refill the board
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                int curr = gameBoard.get(i, j).getNumberOfPlayersSign();
                if(curr <= max) {
                    gameBoard.get(i, j).placeItem(game.getItemDeck().draw());
                }
            }
        }
    }

    /**
     * This method initializes the game Board by drawing an Item for each valid Board tile
     * @param game the reference to the current Game
     */
    public void initializeBoard(Game game) {
        for(int i = 0; i < gameBoard.getColumnDimension(); i++) {
            for(int j = 0; j < gameBoard.getRowDimension(); j++) {
                if(gameBoard.get(i, j).getNumberOfPlayersSign() <= game.getMaxPlayers()) {
                    gameBoard.get(i, j).placeItem(game.getItemDeck().draw());
                }
            }
        }
    }

    /**
     * This method checks whether the selected board tile is free on the north or not
     * Note that this method returns true even if the tile doesn't have an item on it
     * @param i is the first axis of the board representing the row of the tile
     * @param j is the second axis of the board representing the column of the tile
     * @return true if the north tile is free, false otherwise
     */
    public boolean isFreeNorth(int i, int j) {
        return i != 0 && gameBoard.get(i - 1, j).getPlacedItem() == null;
    }

    /**
     * This method checks whether the selected board tile is free on the south or not
     * Note that this method returns true even if the tile doesn't have an item on it
     * @param i is the first axis of the board representing the row of the tile
     * @param j is the second axis of the board representing the column of the tile
     * @return true if the south tile is free, false otherwise
     */
    public boolean isFreeSouth(int i,int j) {
        return i != 8 && gameBoard.get(i + 1, j).getPlacedItem() == null;
    }

    /**
     * This method checks whether the selected board tile is free on the east or not
     * Note that this method returns true even if the tile doesn't have an item on it
     * @param i is the first axis of the board representing the row of the tile
     * @param j is the second axis of the board representing the column of the tile
     * @return true if the east tile is free, false otherwise
     */
    public boolean isFreeEast(int i,int j) {
        return j != 8 && gameBoard.get(i, j + 1).getPlacedItem() == null;
    }

    /**
     * This method checks whether the selected board tile is free on the west or not
     * Note that this method returns true even if the tile doesn't have an item on it
     * @param i is the first axis of the board representing the row of the tile
     * @param j is the second axis of the board representing the column of the tile
     * @return true if the west tile is free, false otherwise
     */
    public boolean isFreeWest(int i,int j) {
        return j != 0 && gameBoard.get(i, j - 1).getPlacedItem() == null;
    }

    /* ************************************************************************************************************
     *                          END OF CUSTOM METHODS
     *                          START OF GETTER METHODS
     ************************************************************************************************************ */

    public Matrix<BoardTile> getGameBoard() {
        return gameBoard;
    }

    public ItemDeck getItemDeck() {
        return itemDeck;
    }
    /* ************************************************************************************************************
     *                          END OF GETTER METHODS
     *                          START OF SETTER METHODS
     ************************************************************************************************************ */

    public void setItemDeck(ItemDeck deck) {
        this.itemDeck = deck;
    }

    /* ************************************************************************************************************
     *                          END OF SETTER METHODS
     ************************************************************************************************************ */
}