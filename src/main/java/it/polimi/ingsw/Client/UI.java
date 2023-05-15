package it.polimi.ingsw.Client;

import it.polimi.ingsw.Model.Exceptions.*;

import java.io.IOException;
import java.rmi.NotBoundException;

public interface UI {
    void connectToServer();
    void run() throws IOException, AlreadyStartedGameException, MaxNumberOfPlayersException, InterruptedException, PickedColumnOutOfBoundsException, PickDoesntFitColumnException, NotBoundException, WrongMessageClassEnumException;
    //scrivi tutti i metodi che servono per la UI presenti nel CLI
}
