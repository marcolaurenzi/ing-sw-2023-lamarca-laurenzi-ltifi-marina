package it.polimi.ingsw.Model;

/**
 * this class represents the game board of the game
 */
public class Board {
    private BoardTile[][] gameBoard;

    /**
     * This method overrides the constructor, and it initializes to the correct value each tile present in the board
     * The number 5 is set to represent a tile which is not physically available on the board
     */
    public Board() {

            gameBoard[0][0] = new BoardTile(5);
            gameBoard[0][1] = new BoardTile(5);
            gameBoard[0][2] = new BoardTile(5);
            gameBoard[0][5] = new BoardTile(5);
            gameBoard[0][6] = new BoardTile(5);
            gameBoard[0][7] = new BoardTile(5);
            gameBoard[0][8] = new BoardTile(5);

            gameBoard[1][0] = new BoardTile(5);
            gameBoard[1][1] = new BoardTile(5);
            gameBoard[1][2] = new BoardTile(5);
            gameBoard[1][6] = new BoardTile(5);
            gameBoard[1][7] = new BoardTile(5);
            gameBoard[1][8] = new BoardTile(5);

            gameBoard[2][0] = new BoardTile(5);
            gameBoard[2][1] = new BoardTile(5);
            gameBoard[2][7] = new BoardTile(5);
            gameBoard[2][8] = new BoardTile(5);

            gameBoard[3][0] = new BoardTile(5);

            gameBoard[5][8] = new BoardTile(5);

            gameBoard[6][0] = new BoardTile(5);
            gameBoard[6][1] = new BoardTile(5);
            gameBoard[6][7] = new BoardTile(5);
            gameBoard[6][8] = new BoardTile(5);

            gameBoard[7][0] = new BoardTile(5);
            gameBoard[7][1] = new BoardTile(5);
            gameBoard[7][2] = new BoardTile(5);
            gameBoard[7][6] = new BoardTile(5);
            gameBoard[7][7] = new BoardTile(5);
            gameBoard[7][8] = new BoardTile(5);

            gameBoard[8][0] = new BoardTile(5);
            gameBoard[8][1] = new BoardTile(5);
            gameBoard[8][2] = new BoardTile(5);
            gameBoard[8][3] = new BoardTile(5);
            gameBoard[8][6] = new BoardTile(5);
            gameBoard[8][7] = new BoardTile(5);
            gameBoard[8][8] = new BoardTile(5);

            gameBoard[0][3] = new BoardTile(3);
            gameBoard[0][4] = new BoardTile(4);
            gameBoard[1][5] = new BoardTile(4);
            gameBoard[2][6] = new BoardTile(3);
            gameBoard[3][8] = new BoardTile(3);
            gameBoard[4][8] = new BoardTile(4);
            gameBoard[5][7] = new BoardTile(4);
            gameBoard[6][6] = new BoardTile(3);
            gameBoard[8][5] = new BoardTile(3);
            gameBoard[8][4] = new BoardTile(4);
            gameBoard[8][3] = new BoardTile(4);
            gameBoard[7][2] = new BoardTile(3);
            gameBoard[5][0] = new BoardTile(3);
            gameBoard[4][0] = new BoardTile(4);
            gameBoard[3][1] = new BoardTile(4);
            gameBoard[2][2] = new BoardTile(3);

            for(int i = 0; i < 9; i++){
                for(int j = 0; j < 9; j++){
                    if(gameBoard[i][j] == null) {
                        gameBoard[i][j] = new BoardTile(2);
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
    private int hasFree(int x, int y) {
        int freeSides = 0;
        for(int i = x - 1; i < x + 2;i += 2){
            if(i < 0) continue;
            if(i > 8) continue;
            if(gameBoard[i][y].isEmpty()) freeSides++;
        }
        for(int i = y - 1; i < y + 2;i += 2){
            if(i < 0) continue;
            if(i > 8) continue;
            if(gameBoard[x][i].isEmpty()) freeSides++;
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
        this.gameBoard[x][y].placeItem(itemToPlace);
    }

    /**
     * This method is used to call the BoardTile class' method to draw the item from the single tile
     *
     * @param x is the first axis of the board representing the row of the tile
     * @param y is the second axis of the board representing the column of the tile
     * @return returns the tile if it is present, returns null otherwise
     */
    private Item drawItem(int x, int y){
        return this.gameBoard[x][y].drawItem();
    }

    /**
     * This method is used to call the BoardTile class' method to check the item present on the single tile
     *
     * @param x is the first axis of the board representing the row of the tile
     * @param y is the second axis of the board representing the column of the tile
     * @return returns the tile if it is present, returns null otherwise
     */
    private Item checkItem(int x, int y){
        return this.gameBoard[x][y].checkItem();
    }
}