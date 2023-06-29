package it.polimi.ingsw.Client;

import it.polimi.ingsw.Model.Exceptions.*;
import it.polimi.ingsw.Utils.GameStatusToSend;
import java.io.IOException;
import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * The RemoteClient interface represents a remote client in the game.
 * It extends the Remote and Serializable interfaces.
 */
public interface RemoteClient extends Remote, Serializable {

    /**
     * Updates the client with the current game status.
     *
     * @param game the GameStatusToSend object representing the current game status.
     * @throws IOException if there is an I/O error while updating the client.
     */
    void update(GameStatusToSend game) throws IOException;

    /**
     * Allows the client to play a turn in the game.
     *
     * @throws IOException                                if there is an I/O error while playing the turn.
     * @throws VoidBoardTileException                     if there is an attempt to place a tile on a void board tile.
     * @throws SelectionNotValidException                 if the selected action is not valid.
     * @throws PlayerIsWaitingException                   if the player is waiting for another player to complete their turn.
     * @throws TilesSelectionSizeDifferentFromOrderLengthException if the size of the selected tiles is different from the length of the order list.
     * @throws ColumnNotValidException                    if the selected column is not valid.
     * @throws SelectionIsEmptyException                  if the selected tiles list is empty.
     * @throws WrongConfigurationException               if the configuration of the game is wrong.
     * @throws PickedColumnOutOfBoundsException          if the picked column is out of bounds.
     * @throws PickDoesntFitColumnException               if the picked tile doesn't fit the selected column.
     * @throws WrongMessageClassEnumException             if the class of the received message is not valid.
     * @throws InterruptedException                       if the thread is interrupted while waiting for a response.
     */
    void playTurn() throws IOException, VoidBoardTileException, SelectionNotValidException, PlayerIsWaitingException, TilesSelectionSizeDifferentFromOrderLengthException, ColumnNotValidException, SelectionIsEmptyException, WrongConfigurationException, PickedColumnOutOfBoundsException, PickDoesntFitColumnException, WrongMessageClassEnumException, InterruptedException;

    /**
     * Ends the game and declares the winner.
     *
     * @param winnerPlayer the name of the winning player.
     * @throws RemoteException if there is a remote communication error while ending the game.
     */
    void endGame(String winnerPlayer) throws RemoteException;
}
