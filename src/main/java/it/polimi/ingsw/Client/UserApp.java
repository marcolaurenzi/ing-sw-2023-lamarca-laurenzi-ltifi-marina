package it.polimi.ingsw.Client;

import it.polimi.ingsw.Model.Exceptions.*;
import it.polimi.ingsw.View.GUI;
import it.polimi.ingsw.View.LoginController;

import java.io.IOException;
import java.rmi.NotBoundException;

/**
 *The UI interface represents a user interface.
 */
public class UserApp {
    /**
     * Constructs a UserApp object.
     */
    public UserApp() {

    }
    /**
     * The main method of the UserApp class.
     * It initializes the user interface (UI) and starts the application.
     *
     * @param args the command-line arguments
     * @throws IOException                      if an I/O error occurs
     * @throws MaxNumberOfPlayersException      if the maximum number of players is exceeded
     * @throws AlreadyStartedGameException      if the game has already started
     * @throws PickedColumnOutOfBoundsException if the picked column is out of bounds
     * @throws InterruptedException            if the current thread is interrupted while waiting
     * @throws PickDoesntFitColumnException     if the picked item doesn't fit in the column
     * @throws NotBoundException                if the specified name is not currently bound
     * @throws WrongMessageClassEnumException   if the wrong message class enum is received
     */
    public static void main(String[] args) throws IOException, MaxNumberOfPlayersException, AlreadyStartedGameException, PickedColumnOutOfBoundsException, InterruptedException, PickDoesntFitColumnException, NotBoundException, WrongMessageClassEnumException {
        UI ui = new TUI();
        ui.run();
    }
}
