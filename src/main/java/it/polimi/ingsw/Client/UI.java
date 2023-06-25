package it.polimi.ingsw.Client;

import it.polimi.ingsw.Model.Exceptions.*;

import java.io.IOException;
import java.rmi.NotBoundException;

public interface UI {

    /**
     * Connects the client to the server.
     */
    void connectToServer();

    /**
     * Runs the user interface.
     *
     * @throws IOException                       if an I/O error occurs.
     * @throws AlreadyStartedGameException       if the game has already started.
     * @throws MaxNumberOfPlayersException       if the maximum number of players has been reached.
     * @throws InterruptedException            if the current thread is interrupted.
     * @throws PickedColumnOutOfBoundsException if the picked column is out of bounds.
     * @throws PickDoesntFitColumnException      if the picked element doesn't fit in the chosen column.
     * @throws NotBoundException                 if the remote object is not currently bound.
     * @throws WrongMessageClassEnumException    if the message class is not a valid enum.
     */
    void run() throws IOException, AlreadyStartedGameException, MaxNumberOfPlayersException,
            InterruptedException, PickedColumnOutOfBoundsException, PickDoesntFitColumnException,
            NotBoundException, WrongMessageClassEnumException;
    //TODO: Write all the UI methods present in CLI

}
