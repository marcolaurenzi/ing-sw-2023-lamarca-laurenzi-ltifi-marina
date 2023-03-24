package it.polimi.ingsw.Model;
/**
 * this class represents the game board of the game
 */
public class Board {
    private BoardTile[][] gameBoard;

    /**
     * This method overrides the constructor and it initialize to the correct value each tile present in the board
     * The number 5 is set to represent a tile which is not physically available on the board
     */
    public Board() {

        for(int i = 0; i < 10;i++)
            for(int j = 0; j < 10;j++){
                if(i == 0 && j == 0) gameBoard[i][j] = new BoardTile(5);
                if(i == 0 && j == 1) gameBoard[i][j] = new BoardTile(5);
                if(i == 0 && j == 2) gameBoard[i][j] = new BoardTile(5);
                if(i == 0 && j == 5) gameBoard[i][j] = new BoardTile(5);
                if(i == 0 && j == 6) gameBoard[i][j] = new BoardTile(5);
                if(i == 0 && j == 7) gameBoard[i][j] = new BoardTile(5);
                if(i == 0 && j == 8) gameBoard[i][j] = new BoardTile(5);

                if(i == 1 && j == 0) gameBoard[i][j] = new BoardTile(5);
                if(i == 1 && j == 1) gameBoard[i][j] = new BoardTile(5);
                if(i == 1 && j == 2) gameBoard[i][j] = new BoardTile(5);
                if(i == 1 && j == 6) gameBoard[i][j] = new BoardTile(5);
                if(i == 1 && j == 7) gameBoard[i][j] = new BoardTile(5);
                if(i == 1 && j == 8) gameBoard[i][j] = new BoardTile(5);

                if(i == 2 && j == 0) gameBoard[i][j] = new BoardTile(5);
                if(i == 2 && j == 1) gameBoard[i][j] = new BoardTile(5);
                if(i == 2 && j == 7) gameBoard[i][j] = new BoardTile(5);
                if(i == 2 && j == 8) gameBoard[i][j] = new BoardTile(5);

                if(i == 3 && j == 0) gameBoard[i][j] = new BoardTile(5);

                if(i == 5 && j == 8) gameBoard[i][j] = new BoardTile(5);

                if(i == 6 && j == 0) gameBoard[i][j] = new BoardTile(5);
                if(i == 6 && j == 1) gameBoard[i][j] = new BoardTile(5);
                if(i == 6 && j == 7) gameBoard[i][j] = new BoardTile(5);
                if(i == 6 && j == 8) gameBoard[i][j] = new BoardTile(5);

                if(i == 7 && j == 0) gameBoard[i][j] = new BoardTile(5);
                if(i == 7 && j == 1) gameBoard[i][j] = new BoardTile(5);
                if(i == 7 && j == 2) gameBoard[i][j] = new BoardTile(5);
                if(i == 7 && j == 6) gameBoard[i][j] = new BoardTile(5);
                if(i == 7 && j == 7) gameBoard[i][j] = new BoardTile(5);
                if(i == 7 && j == 8) gameBoard[i][j] = new BoardTile(5);

                if(i == 8 && j == 0) gameBoard[i][j] = new BoardTile(5);
                if(i == 8 && j == 1) gameBoard[i][j] = new BoardTile(5);
                if(i == 8 && j == 2) gameBoard[i][j] = new BoardTile(5);
                if(i == 8 && j == 3) gameBoard[i][j] = new BoardTile(5);
                if(i == 8 && j == 6) gameBoard[i][j] = new BoardTile(5);
                if(i == 8 && j == 7) gameBoard[i][j] = new BoardTile(5);
                if(i == 8 && j == 8) gameBoard[i][j] = new BoardTile(5);

                if(i == 0 && j == 3) gameBoard[i][j] = new BoardTile(3);
                if(i == 0 && j == 4) gameBoard[i][j] = new BoardTile(4);
                if(i == 1 && j == 5) gameBoard[i][j] = new BoardTile(4);
                if(i == 2 && j == 6) gameBoard[i][j] = new BoardTile(3);
                if(i == 3 && j == 8) gameBoard[i][j] = new BoardTile(3);
                if(i == 4 && j == 8) gameBoard[i][j] = new BoardTile(4);
                if(i == 5 && j == 7) gameBoard[i][j] = new BoardTile(4);
                if(i == 6 && j == 6) gameBoard[i][j] = new BoardTile(3);
                if(i == 8 && j == 5) gameBoard[i][j] = new BoardTile(3);
                if(i == 8 && j == 4) gameBoard[i][j] = new BoardTile(4);
                if(i == 7 && j == 3) gameBoard[i][j] = new BoardTile(4);
                if(i == 6 && j == 2) gameBoard[i][j] = new BoardTile(3);
                if(i == 5 && j == 0) gameBoard[i][j] = new BoardTile(3);
                if(i == 4 && j == 0) gameBoard[i][j] = new BoardTile(4);
                if(i == 3 && j == 1) gameBoard[i][j] = new BoardTile(4);
                if(i == 2 && j == 2) gameBoard[i][j] = new BoardTile(3);
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