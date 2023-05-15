package it.polimi.ingsw.Controller;

import it.polimi.ingsw.Utils.GameStatus;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;

public interface Observer extends Serializable {
    void update(GameStatus game) throws IOException, InterruptedException;
    void playTurn() throws Exception;
    void endGame(String winnerPlayer) throws IOException;
}
