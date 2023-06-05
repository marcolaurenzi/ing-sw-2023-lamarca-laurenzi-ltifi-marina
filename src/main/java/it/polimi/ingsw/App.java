package it.polimi.ingsw;

import it.polimi.ingsw.Client.TUI;
import it.polimi.ingsw.Model.Exceptions.*;
import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.GameState.GameStateFinished;
import it.polimi.ingsw.Model.Item;
import it.polimi.ingsw.Model.TypeEnum;
import it.polimi.ingsw.View.LoginController;

import javax.swing.*;
import java.io.IOException;
import java.rmi.NotBoundException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws WrongMessageClassEnumException, NotBoundException, MaxNumberOfPlayersException, AlreadyStartedGameException, IOException, InterruptedException {
        // GUI
        LoginController.main(args);

        // TUI
        // TUI tui = new TUI();
        // tui.run();
    }
}
