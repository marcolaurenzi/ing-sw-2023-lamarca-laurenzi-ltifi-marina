package it.polimi.ingsw.Controller;

import it.polimi.ingsw.Client.RemoteClient;
import it.polimi.ingsw.Model.Exceptions.*;
import it.polimi.ingsw.Utils.GameStatusToSend;

import java.io.EOFException;
import java.io.IOException;
import java.rmi.ConnectException;
import java.rmi.RemoteException;
import java.rmi.UnmarshalException;

public class ObserverRMI implements Observer {
    private RemoteClient client;
    public ObserverRMI(RemoteClient client) {
        this.client = client;
    }
    public void update(GameStatusToSend game) throws RemoteException, DisconnectedPlayerException {
        try {
            client.update(game);
        } catch(ConnectException e) {
            throw new DisconnectedPlayerException();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void playTurn() throws IOException, VoidBoardTileException, SelectionNotValidException, PlayerIsWaitingException, TilesSelectionSizeDifferentFromOrderLengthException, ColumnNotValidException, SelectionIsEmptyException, WrongConfigurationException, PickedColumnOutOfBoundsException, PickDoesntFitColumnException, WrongMessageClassEnumException, InterruptedException, DisconnectedPlayerException {
        try {
            client.playTurn();
        } catch(ConnectException | EOFException | UnmarshalException e ) {
            throw new DisconnectedPlayerException();
        }
    }

    @Override
    public void endGame(String winnerPlayer) throws RemoteException, DisconnectedPlayerException {
        try {
            client.endGame(winnerPlayer);
        } catch(ConnectException e) {
            throw new DisconnectedPlayerException();
        }
    }
}
