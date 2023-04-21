package it.polimi.ingsw;

import it.polimi.ingsw.Model.Exceptions.*;
import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.GameState.GameStateFinished;

import javax.swing.*;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws MaxNumberOfPlayersException, IOException, AlreadyStartedGameException, NumberOfPlayersException, FinishedGameException, WrongConfigurationException {

        // connessione roba varia

        /*Game game = new Game("1234", 4);
        game.addPlayer("Marco");
        game.addPlayer("Ale");
        game.addPlayer("Ric");
        game.addPlayer("Mo");
        game.startGame();
        while(game.getGameState().getClass() != GameStateFinished.class) {

            game.refreshBoard();
            String comando = "colonna 3";
            String comando2 = "riga 3";
            String comando3 = "colonna 3";
            String comando4 = "riga 4";
            game.getCurrentPlayer().endTurn();
            game.nextPlayer();
        }

         */

    }
}
