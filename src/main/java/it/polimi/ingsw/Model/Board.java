package it.polimi.ingsw.Model;
/**
 * this class represents the game board of the game
 */
public class Board {
    private BoardTile[][] gameBoard;
    public Board() {
//<<<<<<< HEAD
        for(int i = 0; i < 10;i++)
            for(int j = 0; j < 10;j++){
                gameBoard[i][j] = new BoardTile(); //capiamo come mettere i numeri nelle singole tile
            }
//=======

        // todo
        //>>>>>>> b075b195ee6f35b8c70f53bc2f5815003e49187b
    }

    private boolean hasFree(int x, int y){
        // todo

        return true;
    }

}
