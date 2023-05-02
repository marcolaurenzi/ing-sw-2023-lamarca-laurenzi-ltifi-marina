package it.polimi.ingsw.Client;

import it.polimi.ingsw.Model.Exceptions.AlreadyStartedGameException;
import it.polimi.ingsw.Model.Exceptions.MaxNumberOfPlayersException;
import it.polimi.ingsw.Model.Exceptions.PickDoesntFitColumnException;
import it.polimi.ingsw.Model.Exceptions.PickedColumnOutOfBoundsException;

import java.io.IOException;
import java.rmi.NotBoundException;

public interface UI {
    void connectToServer();
    void run() throws IOException, AlreadyStartedGameException, MaxNumberOfPlayersException, InterruptedException, PickedColumnOutOfBoundsException, PickDoesntFitColumnException, NotBoundException;
    //scrivi tutti i metodi che servono per la UI presenti nel CLI
}
