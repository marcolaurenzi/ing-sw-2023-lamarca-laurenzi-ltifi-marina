package it.polimi.ingsw.Client;

import it.polimi.ingsw.Model.Exceptions.*;

import java.io.IOException;
import java.rmi.NotBoundException;

public class UserApp {
    public static void main(String[] args) throws IOException, MaxNumberOfPlayersException, AlreadyStartedGameException, PickedColumnOutOfBoundsException, InterruptedException, PickDoesntFitColumnException, NotBoundException, WrongMessageClassEnumException {
        UI ui = new TUI();
        ui.run();

    }
}
