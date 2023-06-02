package it.polimi.ingsw.Client;

import it.polimi.ingsw.Model.Exceptions.*;
import it.polimi.ingsw.View.GUI;
import it.polimi.ingsw.View.LoginController;

import java.io.IOException;
import java.rmi.NotBoundException;

public class UserApp {
    public static void main(String[] args) throws IOException, MaxNumberOfPlayersException, AlreadyStartedGameException, PickedColumnOutOfBoundsException, InterruptedException, PickDoesntFitColumnException, NotBoundException, WrongMessageClassEnumException {
        LoginController.main(args);
        //TUI tui = new TUI();
        //tui.run();

    }
}
