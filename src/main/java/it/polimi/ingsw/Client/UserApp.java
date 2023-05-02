package it.polimi.ingsw.Client;

import it.polimi.ingsw.Model.Exceptions.AlreadyStartedGameException;
import it.polimi.ingsw.Model.Exceptions.MaxNumberOfPlayersException;
import it.polimi.ingsw.Model.Exceptions.PickDoesntFitColumnException;
import it.polimi.ingsw.Model.Exceptions.PickedColumnOutOfBoundsException;

import java.io.IOException;
import java.rmi.NotBoundException;

public class UserApp {
    public static void main(String[] args) throws IOException, MaxNumberOfPlayersException, AlreadyStartedGameException, PickedColumnOutOfBoundsException, InterruptedException, PickDoesntFitColumnException, NotBoundException {
        UI ui = new TUI();
        ui.run();

    }
}
