package it.polimi.ingsw.Client;

import it.polimi.ingsw.Utils.GameStatus;

import java.rmi.Remote;

public interface RemoteObserver extends Remote {
    public void update(Object observable, GameStatus game);
}
