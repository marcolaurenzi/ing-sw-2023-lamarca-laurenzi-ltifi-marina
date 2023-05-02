package it.polimi.ingsw.Controller;

import it.polimi.ingsw.Utils.GameStatus;

import java.io.Serializable;
import java.rmi.RemoteException;

public interface Observer extends Serializable {
    void update(GameStatus game) throws RemoteException;
    void playTurn() throws Exception;
    void endGame(String winnerPlayer) throws RemoteException;
}
