package it.polimi.ingsw.Model;
/**
 * this class represents the game board of the game
 */
public class Board {
    private BoardTile[][] gameBoard;
    public Board() {
        for(int i = 0; i < 10;i++)
            for(int j = 0; j < 10;j++){
                gameBoard[i][j] = new BoardTile(); //capiamo come mettere i numeri nelle singole tile
            }
    }

    private boolean hasfree(int x, int y){
    /* to be finished */
    }

}
