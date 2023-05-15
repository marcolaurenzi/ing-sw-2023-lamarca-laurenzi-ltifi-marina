package it.polimi.ingsw.Controller;

import it.polimi.ingsw.Client.RemoteClient;
import it.polimi.ingsw.Model.Exceptions.*;
import it.polimi.ingsw.Utils.GameStatus;

import java.io.IOException;
import java.rmi.RemoteException;

public class ObserverRMI implements Observer {
    private RemoteClient client;
    public ObserverRMI(RemoteClient client) {
        this.client = client;
    }
    public void update(GameStatus game) throws RemoteException {
        client.update(game);
    }
    @Override
    public void playTurn() throws IOException, VoidBoardTileException, SelectionNotValidException, PlayerIsWaitingException, TilesSelectionSizeDifferentFromOrderLengthException, ColumnNotValidException, SelectionIsEmptyException, WrongConfigurationException, PickedColumnOutOfBoundsException, PickDoesntFitColumnException, WrongMessageClassEnumException, InterruptedException {
        client.playTurn();
    }

    @Override
    public void endGame(String winnerPlayer) throws RemoteException {
        client.endGame(winnerPlayer);
    }
}
