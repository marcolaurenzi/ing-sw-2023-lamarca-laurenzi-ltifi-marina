package it.polimi.ingsw.Model;

/**
 * this class represents the game board of the game
 */
public class Board {

    /* ************************************************************************************************************
     *                          START OF ATTRIBUTES DECLARATION
     ************************************************************************************************************ */
    private Matrix<BoardTile> gameBoard;

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
     *
     * @param itemToPlace reference to the Item to place
     * @param x is the first axis of the board representing the row of the tile
     * @param y is the second axis of the board representing the column of the tile
     */
    private void placeItem(Item itemToPlace, int x, int y){
        this.gameBoard.get(x, y).placeItem(itemToPlace);
    }

    /**
     * This method is used to call the BoardTile class' method to draw the item from the single tile
     *
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
        for(int i = 0; i<rows; i++) {
            for(int j = 0; j<columns; j++) {

                BoardTile curr = gameBoard.get(i,j);
                if(curr != null &&
                        !isFreeNorth(i,j) ||
                        !isFreeSouth(i,j) ||
                        !isFreeEast(i,j) ||
                        !isFreeWest(i,j)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * This method refreshes the game Board by drawing an Item for each valid empty BoardTile
     * @param game the reference to the current Game
     */
    public void refreshBoard(Game game){
        int rows = gameBoard.getColumnDimension();
        int columns = gameBoard.getRowDimension();
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                BoardTile curr = gameBoard.get(i, j);
                if(curr != null && curr.getNumberOfPlayersSign() <= game.getMaxPlayers()) {
                    gameBoard.get(i, j).placeItem(game.getItemDeck().draw());
                }
            }
        }
    }

    /**
     * This method initializes the game Board by drawing an Item for each valid Boardtile
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
     * This method returns whether the northern BoardTile is empty or not
     * @param i row index
     * @param j column index
     *
     * @return true if it's empty, false otherwise
     */
    public boolean isFreeNorth(int i,int j) {
        if(i == 0) {
            return true;
        }
        else {
            return gameBoard.get(i-1,j) == null;
        }
    }

    /**
     * This method returns whether the southern BoardTile is empty or not
     * @param i row index
     * @param j column index
     *
     * @return true if it's empty, false otherwise
     */
    public boolean isFreeSouth(int i,int j) {
        if(i == 5) {
            return true;
        }
        else {
            return gameBoard.get(i+1,j) == null;
        }
    }

    /**
     * This method returns whether the eastern BoardTile is empty or not
     * @param i row index
     * @param j column index
     *
     * @return true if it's empty, false otherwise
     */
    public boolean isFreeEast(int i,int j) {
        if(j == 4) {
            return true;
        }
        else {
            return gameBoard.get(i,j+1) == null;
        }
    }

    /**
     * This method returns whether the western BoardTile is empty or not
     * @param i row index
     * @param j column index
     *
     * @return true if it's empty, false otherwise
     */
    public boolean isFreeWest(int i,int j) {
        if(j == 0) {
            return true;
        }
        else {
            return gameBoard.get(i,j-1) == null;
        }
    }

    /* ************************************************************************************************************
     *                          END OF CUSTOM METHODS
     *                          START OF GETTER METHODS
     ************************************************************************************************************ */

    public Matrix<BoardTile> getGameBoard() {
        return gameBoard;
    }

    /* ************************************************************************************************************
     *                          END OF GETTER METHODS
     ************************************************************************************************************ */
}