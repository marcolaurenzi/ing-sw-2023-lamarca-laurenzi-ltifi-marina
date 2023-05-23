package it.polimi.ingsw.Controller;

import it.polimi.ingsw.Model.Exceptions.DisconnectedPlayerException;
import it.polimi.ingsw.Model.Exceptions.WrongMessageClassEnumException;
import it.polimi.ingsw.Utils.GameStatusToSend;

import java.io.IOException;
import java.io.Serializable;

public interface Observer extends Serializable {
    void update(GameStatusToSend game) throws IOException, InterruptedException, DisconnectedPlayerException, WrongMessageClassEnumException;
    void playTurn() throws Exception, DisconnectedPlayerException;
    void endGame(String winnerPlayer) throws IOException, DisconnectedPlayerException, WrongMessageClassEnumException, InterruptedException;
}
