package it.polimi.ingsw.Client;

import it.polimi.ingsw.Model.Exceptions.*;
import it.polimi.ingsw.Utils.GameStatus;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteUI extends Remote, Serializable {
    void update(GameStatus game) throws RemoteException;
    void playTurn() throws IOException, VoidBoardTileException, SelectionNotValidException, PlayerIsWaitingException, TilesSelectionSizeDifferentFromOrderLengthException, ColumnNotValidException, WrongConfigurationException, PickedColumnOutOfBoundsException, PickDoesntFitColumnException, SelectionIsEmptyException;
    void endGame(String winnerPlayer) throws RemoteException;
}
