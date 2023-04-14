package it.polimi.ingsw.Model;

/**
 * this class represents the game board of the game
 */
public class Board {
    private Matrix<BoardTile> gameBoard;

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
     * @param itemToPlace
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

    public Matrix<BoardTile> getGameBoard() {
        return gameBoard;
    }

    public void printBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.println(gameBoard.get(i, j).getNumberOfPlayersSign());

            }
        }
    }
    public boolean toRefresh(){
        //todo method that checks if the board needs to be refreshed
        return false;
    }
    public void refreshBoard(){
        //todo method that refreshes the board
    }
}