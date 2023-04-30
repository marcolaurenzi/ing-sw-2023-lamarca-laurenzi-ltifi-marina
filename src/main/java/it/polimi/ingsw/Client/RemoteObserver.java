package it.polimi.ingsw.Client;

import it.polimi.ingsw.Utils.GameStatus;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteObserver extends Remote {
    public void update(GameStatus game) throws RemoteException;
    void playTurn() throws RemoteException;
}
