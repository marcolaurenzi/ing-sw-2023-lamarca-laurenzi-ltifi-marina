package it.polimi.ingsw;

import it.polimi.ingsw.Utils.IpConfig;
import it.polimi.ingsw.Client.TUI;
import it.polimi.ingsw.Model.Exceptions.AlreadyStartedGameException;
import it.polimi.ingsw.Model.Exceptions.MaxNumberOfPlayersException;
import it.polimi.ingsw.Model.Exceptions.WrongMessageClassEnumException;
import it.polimi.ingsw.View.LoginController;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.util.Scanner;

/**
 * The main class that serves as the entry point for the application.
 */
public class App {
    /**
     * Constructs an App object.
     */
    public App() {

    }

    /**
     * The main method of the application.
     *
     * @param args the command-line arguments
     * @throws WrongMessageClassEnumException if an incorrect message class enum is encountered
     * @throws NotBoundException if the object is not bound to the registry
     * @throws MaxNumberOfPlayersException if the maximum number of players is exceeded
     * @throws AlreadyStartedGameException if the game has already been started
     * @throws IOException if an I/O error occurs
     * @throws InterruptedException if the thread is interrupted while waiting
     */
    public static void main(String[] args) throws WrongMessageClassEnumException, NotBoundException,
            MaxNumberOfPlayersException, AlreadyStartedGameException, IOException, InterruptedException {

        System.out.println("Welcome to MyShelfie! Type your own IP address.");      /*localhost*/
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        IpConfig.ipClient = scanner.nextLine();

        System.out.println("Type the Server IP you want to connect to.");      /*localhost*/
        IpConfig.ipServer = scanner.nextLine();

        System.out.println("Welcome to MyShelfie! Please select the UI you want to use.");
        System.out.println("TUI - GUI");
        //Scanner scanner = new Scanner(new InputStreamReader(System.in));
        String ui = scanner.nextLine();

        boolean started = false;
        while (!started) {
            if (ui.equals("TUI") || ui.equals("tui") || ui.equals("Tui")) {
                TUI tui = new TUI();
                tui.run();
                started = true;
            } else if (ui.equals("GUI") || ui.equals("gui") || ui.equals("Gui")) {
                LoginController.main(args);
                started = true;
            } else {
                System.out.println("Invalid input. Please try again.");
                ui = scanner.nextLine();
            }
        }
    }
}
