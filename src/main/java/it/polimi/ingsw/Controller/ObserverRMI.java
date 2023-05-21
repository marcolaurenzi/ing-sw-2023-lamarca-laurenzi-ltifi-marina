package it.polimi.ingsw.Controller;

import it.polimi.ingsw.Client.RemoteClient;
import it.polimi.ingsw.Model.Exceptions.*;
import it.polimi.ingsw.Utils.GameStatus;

import java.io.IOException;
import java.rmi.ConnectException;
import java.rmi.RemoteException;

public class ObserverRMI implements Observer {
    private RemoteClient client;
    public ObserverRMI(RemoteClient client) {
        this.client = client;
    }
    public void update(GameStatus game) throws RemoteException, DisconnectedPlayerException {
        try {
            client.update(game);
        } catch(ConnectException e) {
            throw new DisconnectedPlayerException();
        }
    }
    @Override
    public void playTurn() throws IOException, VoidBoardTileException, SelectionNotValidException, PlayerIsWaitingException, TilesSelectionSizeDifferentFromOrderLengthException, ColumnNotValidException, SelectionIsEmptyException, WrongConfigurationException, PickedColumnOutOfBoundsException, PickDoesntFitColumnException, WrongMessageClassEnumException, InterruptedException, DisconnectedPlayerException {
        try {
            client.playTurn();
        } catch(ConnectException e) {
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
