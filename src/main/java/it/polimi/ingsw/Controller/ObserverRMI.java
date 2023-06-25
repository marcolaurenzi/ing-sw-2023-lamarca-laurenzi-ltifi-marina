package it.polimi.ingsw.Controller;

import it.polimi.ingsw.Client.RemoteClient;
import it.polimi.ingsw.Model.Exceptions.*;
import it.polimi.ingsw.Utils.GameStatusToSend;

import java.io.EOFException;
import java.io.IOException;
import java.rmi.ConnectException;
import java.rmi.RemoteException;
import java.rmi.UnmarshalException;
/**
 *The ObserverRMI class represents an observer in the game.
 */
public class ObserverRMI implements Observer {

    private RemoteClient client;

    /**
     * Constructs an ObserverRMI object with the specified RemoteClient.
     *
     * @param client the RemoteClient object associated with the observer
     */
    public ObserverRMI(RemoteClient client) {
        this.client = client;
    }

    /**
     * Updates the observer with the game status.
     *
     * @param game the GameStatusToSend object containing the game status
     * @throws RemoteException            if a remote exception occurs
     * @throws DisconnectedPlayerException if the player is disconnected
     */
    public void update(GameStatusToSend game) throws RemoteException, DisconnectedPlayerException {
        try {
            client.update(game);
        } catch (ConnectException e) {
            throw new DisconnectedPlayerException();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Plays a turn in the game.
     *
     * @throws IOException                               if an I/O exception occurs
     * @throws VoidBoardTileException                    if a void board tile is encountered
     * @throws SelectionNotValidException                if the selection is not valid
     * @throws PlayerIsWaitingException                  if the player is waiting
     * @throws TilesSelectionSizeDifferentFromOrderLengthException if the tile selection size is different from the order length
     * @throws ColumnNotValidException                   if the column is not valid
     * @throws SelectionIsEmptyException                 if the selection is empty
     * @throws WrongConfigurationException               if the game configuration is wrong
     * @throws PickedColumnOutOfBoundsException         if the picked column is out of bounds
     * @throws PickDoesntFitColumnException              if the pick doesn't fit the column
     * @throws WrongMessageClassEnumException            if the message class enum is wrong
     * @throws InterruptedException                     if the thread is interrupted
     * @throws DisconnectedPlayerException               if the player is disconnected
     */
    @Override
    public void playTurn() throws IOException, VoidBoardTileException, SelectionNotValidException, PlayerIsWaitingException, TilesSelectionSizeDifferentFromOrderLengthException, ColumnNotValidException, SelectionIsEmptyException, WrongConfigurationException, PickedColumnOutOfBoundsException, PickDoesntFitColumnException, WrongMessageClassEnumException, InterruptedException, DisconnectedPlayerException {
        try {
            client.playTurn();
        } catch (ConnectException | EOFException | UnmarshalException e) {
            throw new DisconnectedPlayerException();
        }
    }

    /**
     * Ends the game and declares the winner player.
     *
     * @param winnerPlayer the name of the winner player
     * @throws RemoteException            if a remote exception occurs
     * @throws DisconnectedPlayerException if the player is disconnected
     */
    @Override
    public void endGame(String winnerPlayer) throws RemoteException, DisconnectedPlayerException {
        try {
            client.endGame(winnerPlayer);
        } catch (ConnectException e) {
            throw new DisconnectedPlayerException();
        }
    }
}
