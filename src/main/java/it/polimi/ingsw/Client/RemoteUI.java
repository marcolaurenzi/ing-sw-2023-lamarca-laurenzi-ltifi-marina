package it.polimi.ingsw.Client;

import it.polimi.ingsw.Model.Exceptions.*;
import it.polimi.ingsw.Model.Player;
import it.polimi.ingsw.Utils.GameStatusToSend;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * The RemoteUI interface represents the remote user interface for the client.
 * It provides methods for updating the game status, playing a turn, and ending the game.
 * This interface extends the Remote and Serializable interfaces to support remote method invocation.
 */
public interface RemoteUI extends Remote, Serializable {


    /**
     * Updates the game status on the client.
     *
     * @param game the updated game status to be sent to the client
     * @throws IOException if an I/O error occurs while updating the game status
     */
    void update(GameStatusToSend game) throws IOException;

    /**
     * Allows the client to play their turn in the game.
     *
     * @throws IOException                            if an I/O error occurs while playing the turn
     * @throws VoidBoardTileException                 if a board tile is null during the turn
     * @throws SelectionNotValidException             if the selected action is not valid
     * @throws PlayerIsWaitingException               if the player is waiting for other players to take their turn
     * @throws TilesSelectionSizeDifferentFromOrderLengthException
     *                                                if the size of the tiles selection is different from the length of the order
     * @throws ColumnNotValidException                if the selected column is not valid
     * @throws WrongConfigurationException           if the game configuration is incorrect
     * @throws PickedColumnOutOfBoundsException       if the picked column is out of bounds
     * @throws PickDoesntFitColumnException           if the picked dice doesn't fit in the selected column
     * @throws SelectionIsEmptyException              if the selection is empty
     * @throws WrongMessageClassEnumException         if the message class enum is incorrect
     * @throws InterruptedException                  if the thread is interrupted while waiting
     */
    void playTurn() throws IOException, VoidBoardTileException, SelectionNotValidException,
            PlayerIsWaitingException, TilesSelectionSizeDifferentFromOrderLengthException,
            ColumnNotValidException, WrongConfigurationException, PickedColumnOutOfBoundsException,
            PickDoesntFitColumnException, SelectionIsEmptyException, WrongMessageClassEnumException,
            InterruptedException;

    /**
     * Ends the game and declares the winner player.
     *
     * @param winnerPlayer the name of the winner player
     * @throws RemoteException if a remote error occurs while ending the game
     */
    void endGame(String winnerPlayer) throws RemoteException;
}
