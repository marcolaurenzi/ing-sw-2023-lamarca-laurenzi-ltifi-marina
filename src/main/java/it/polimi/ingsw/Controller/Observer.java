package it.polimi.ingsw.Controller;

import it.polimi.ingsw.Model.Exceptions.DisconnectedPlayerException;
import it.polimi.ingsw.Model.Exceptions.WrongMessageClassEnumException;
import it.polimi.ingsw.Utils.GameStatusToSend;

import java.io.IOException;
import java.io.Serializable;

public interface Observer extends Serializable {

    /**
     * Updates the observer with the current game status.
     *
     * @param game the game status to send
     * @throws IOException if an I/O error occurs
     * @throws InterruptedException if the current thread is interrupted
     * @throws DisconnectedPlayerException if a player is disconnected
     * @throws WrongMessageClassEnumException if the wrong message class enum is used
     */
    void update(GameStatusToSend game) throws IOException, InterruptedException, DisconnectedPlayerException, WrongMessageClassEnumException;

    /**
     * Initiates the player's turn.
     *
     * @throws Exception if an error occurs during the turn
     * @throws DisconnectedPlayerException if a player is disconnected
     */
    void playTurn() throws Exception, DisconnectedPlayerException;

    /**
     * Ends the game with the winner's player name.
     *
     * @param winnerPlayer the name of the winning player
     * @throws IOException if an I/O error occurs
     * @throws DisconnectedPlayerException if a player is disconnected
     * @throws WrongMessageClassEnumException if the wrong message class enum is used
     * @throws InterruptedException if the current thread is interrupted
     */
    void endGame(String winnerPlayer) throws IOException, DisconnectedPlayerException, WrongMessageClassEnumException, InterruptedException;
}
