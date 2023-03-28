package it.polimi.ingsw.Model;

/**
 * this class represents the game board of the game
 */
public class Board {
    private Matrix<BoardTile> gameBoard;

    /**
     * This method overrides the constructor, and it initializes to the correct value each tile present in the board
     * The number 5 is set to represent a tile which is not physically available on the board
     */
    public Board() {

            gameBoard.set(0, 0, new BoardTile(5));
            gameBoard.set(0, 1, new BoardTile(5));
            gameBoard.set(0, 2, new BoardTile(5));
            gameBoard.set(0, 5, new BoardTile(5));
            gameBoard.set(0, 6, new BoardTile(5));
            gameBoard.set(0, 7, new BoardTile(5));
            gameBoard.set(0, 8, new BoardTile(5));

            gameBoard.set(1, 0, new BoardTile(5));
            gameBoard.set(1, 1, new BoardTile(5));
            gameBoard.set(1, 2, new BoardTile(5));
            gameBoard.set(1, 6, new BoardTile(5));
            gameBoard.set(1, 7, new BoardTile(5));
            gameBoard.set(1, 8, new BoardTile(5));

            gameBoard.set(2, 0, new BoardTile(5));
            gameBoard.set(2, 1, new BoardTile(5));
            gameBoard.set(2, 7, new BoardTile(5));
            gameBoard.set(2, 8, new BoardTile(5));

            gameBoard.set(3, 0, new BoardTile(5));

            gameBoard.set(5, 8, new BoardTile(5));

            gameBoard.set(6, 0, new BoardTile(5));
            gameBoard.set(6, 1, new BoardTile(5));
            gameBoard.set(6, 7, new BoardTile(5));
            gameBoard.set(6, 8, new BoardTile(5));

            gameBoard.set(7, 0, new BoardTile(5));
            gameBoard.set(7, 1, new BoardTile(5));
            gameBoard.set(7, 2, new BoardTile(5));
            gameBoard.set(7, 6, new BoardTile(5));
            gameBoard.set(7, 7, new BoardTile(5));
            gameBoard.set(7, 8, new BoardTile(5));

            gameBoard.set(8, 0, new BoardTile(5));
            gameBoard.set(8, 1, new BoardTile(5));
            gameBoard.set(8, 2, new BoardTile(5));
            gameBoard.set(8, 3, new BoardTile(5));
            gameBoard.set(8, 6, new BoardTile(5));
            gameBoard.set(8, 7, new BoardTile(5));
            gameBoard.set(8, 8, new BoardTile(5));

            gameBoard.set(0, 3, new BoardTile(3));
            gameBoard.set(0, 4, new BoardTile(4));
            gameBoard.set(1, 5, new BoardTile(4));
            gameBoard.set(2, 6, new BoardTile(3));
            gameBoard.set(3, 8, new BoardTile(3));
            gameBoard.set(4, 8, new BoardTile(4));
            gameBoard.set(5, 7, new BoardTile(4));
            gameBoard.set(6, 6, new BoardTile(3));
            gameBoard.set(8, 5, new BoardTile(3));
            gameBoard.set(8, 4, new BoardTile(4));
            gameBoard.set(8, 3, new BoardTile(4));
            gameBoard.set(7, 2, new BoardTile(3));
            gameBoard.set(5, 0, new BoardTile(3));
            gameBoard.set(4, 0, new BoardTile(4));
            gameBoard.set(3, 1, new BoardTile(4));
            gameBoard.set(2, 2, new BoardTile(3));

            for(int i = 0; i < 9; i++){
                for(int j = 0; j < 9; j++){
                    if(gameBoard.get(i, j) == null) {
                        gameBoard.set(i, j, new BoardTile(2));
                    }
                }
            }



    }

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
}