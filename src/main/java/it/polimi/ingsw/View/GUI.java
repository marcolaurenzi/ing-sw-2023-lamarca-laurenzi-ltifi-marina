package it.polimi.ingsw.View;

import it.polimi.ingsw.Client.Client;
import it.polimi.ingsw.Client.ClientRMI;
import it.polimi.ingsw.Client.ClientSocket;
import it.polimi.ingsw.Client.RemoteUI;
import it.polimi.ingsw.Controller.Controller;
import it.polimi.ingsw.Model.Coordinates;
import it.polimi.ingsw.Model.Exceptions.*;
import it.polimi.ingsw.Model.Player;
import it.polimi.ingsw.Utils.GameStatusToSend;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.UnmarshalException;
import java.util.ArrayList;
import java.util.List;

import static it.polimi.ingsw.View.LoginController.connectionMode;

/**
 * This class represents the GUI interface for the game.
 */
public class GUI implements RemoteUI {
    /**
     * The gui constructor.
     */
    public GUI() {

    }
    /**
     * The controller of the game.
     */
    private static GamePageController controller;
    /**
     * The ID of the player.
     */
    private static String playerId;
    /**
     * The ID of the game.
     */
    private static int gameId;
    /**
     * Threads.
     */
    public static Thread thread;
    /**
     * The client of the game.
     */
    private static Client client;
    /**
     * Boolean representing whether the game has ended.
     */
    private static boolean isEnded;
    /**
     * The game status.
     */
    protected static GameStatusToSend gameStatus;
    /**
     * Turn selection handler.
     */
    private static GUITurnSelectionHandler turnSelectionHandler;
    /**
     * The shared object.
     */
    private static SharedObject sharedObject;
    /**
     * Connects to the server based on the selected connection mode.
     *
     * @throws ConnectionException if there is an error in the connection
     */
    public static void connectToServer() throws ConnectionException {
        try {
            if (connectionMode.equals("RMI")) {
                client = new ClientRMI(new GUI());
            } else if (connectionMode.equals("Socket")) {
                client = new ClientSocket(new GUI());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConnectionException();
        }
        System.out.println("Connection to server: SUCCESSFUL");
        thread.start();
    }

    /**
     * Asks the player for their username and handles the username selection process.
     *
     * @throws WrongPasswordException if the entered password is wrong
     * @throws IOException            if there is an I/O error during the process
     * @throws PlayerOnlineException  if the player is already online
     * @throws WrongMessageClassEnumException if the message class enum is wrong
     * @throws InterruptedException if the thread is interrupted
     */
    public static void askForUsername() throws WrongPasswordException, IOException, PlayerOnlineException, WrongMessageClassEnumException, InterruptedException {
        boolean isUsernameAlreadyInUse = false;
        try {
            client.choosePlayerId(LoginController.getUsername());
            playerId = LoginController.getUsername();
        } catch (PlayerIdAlreadyInUseException e) {
            isUsernameAlreadyInUse = true;
        }

        if (isUsernameAlreadyInUse) {
            boolean passwordok = false;
            while (!passwordok) {
                try {
                    client.checkPassword(LoginController.getUsername(), LoginController.getPassword());
                    playerId = LoginController.getUsername();
                    passwordok = true;
                } catch (WrongPasswordException e) {
                    throw new WrongPasswordException();
                } catch (Exception e) {
                    System.out.println("Exception in GUI ask for password " + e);
                    e.printStackTrace();
                    System.exit(-1);
                }
            }
        } else {
            try {
                client.choosePassword(LoginController.getUsername(), LoginController.getPassword());
            } catch (Exception e) {
                System.out.println("Exception in GUI ask for password " + e);
                e.printStackTrace();
                System.exit(-1);
            }
        }
    }

    /**
     * ping.
     *
     * @throws IOException if there is an I/O error during the process
     */
    public static void ping() throws IOException {
        client.ping();
    }

    /**
     * Updates the game status and triggers the UI update.
     *
     * @param game the updated game status
     * @throws IOException if there is an I/O error during the process
     */
    @Override
    public void update(GameStatusToSend game) throws IOException {
        gameStatus = game;
        sharedObject = new SharedObject();
        controller.uiUpdate(gameStatus);
    }

    /**
     * Initiates the player's turn and handles the turn selection process.
     *
     * @throws IOException                                   if there is an I/O error during the process
     * @throws VoidBoardTileException                        if a board tile is void
     * @throws SelectionNotValidException                    if the selection is not valid
     * @throws PlayerIsWaitingException                      if the player is waiting
     * @throws TilesSelectionSizeDifferentFromOrderLengthException if the size of the tiles selection is different from the order length
     * @throws ColumnNotValidException                       if the column is not valid
     * @throws WrongConfigurationException                   if the configuration is wrong
     * @throws PickedColumnOutOfBoundsException              if the picked column is out of bounds
     * @throws PickDoesntFitColumnException                  if the pick doesn't fit in the column
     * @throws SelectionIsEmptyException                     if the selection is empty
     * @throws WrongMessageClassEnumException                if the message class enumeration is wrong
     * @throws InterruptedException                         if the process is interrupted
     */
    @Override
    public void playTurn() throws IOException, VoidBoardTileException, SelectionNotValidException, PlayerIsWaitingException, TilesSelectionSizeDifferentFromOrderLengthException, ColumnNotValidException, WrongConfigurationException, PickedColumnOutOfBoundsException, PickDoesntFitColumnException, SelectionIsEmptyException, WrongMessageClassEnumException, InterruptedException {
        sharedObject.waitForVariable();
        controller.disableBookshelfButtons();

        GUITurnSelectionHandler turnSelectionHandler = controller.getTurnSelectionHandler();

        ArrayList<Coordinates> tilesSelection = turnSelectionHandler.getSelection();
        int[] order = new int[tilesSelection.size()];
        for (int i = 0; i < tilesSelection.size(); i++) {
            order[i] = i;
        }
        try {
            client.pickAndInsertInBookshelf(tilesSelection, turnSelectionHandler.getColumn(), order, playerId);
        } catch (IOException e) {
            System.out.println("server crashed");
            controller.printError("Server crashed");
        }
    }

    /**
     * Notifies the end of the game and displays the winner.
     *
     * @param winnerPlayer the username of the winner player
     * @throws RemoteException if there is a remote exception during the process
     */
    @Override
    public void endGame(String winnerPlayer) throws RemoteException {
        System.out.println("THE GAME IS OVER!");
        isEnded = true;
        System.out.println("The WINNER is: " + winnerPlayer);
        controller.endGame(winnerPlayer);
    }


    /**
     * Adds a player to the game.
     *
     * @param username the username of the player to add
     * @throws AlreadyStartedGameException if the game has already started
     * @throws CreateNewGameException     if a new game cannot be created
     * @throws NotBoundException          if the game is not bound
     */
    public static void addPlayer(String username) throws AlreadyStartedGameException, CreateNewGameException, NotBoundException {
        try {
            gameId = client.addPlayerToCreatedGame(username);
        } catch (IOException | InterruptedException | WrongMessageClassEnumException e) {
            System.out.println("FATAL exception" + e);
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * Creates a new game and adds a player to it.
     *
     * @param i the number of players
     * @throws WrongMessageClassEnumException if the message class enumeration is wrong
     * @throws MaxNumberOfPlayersException   if the maximum number of players is reached
     * @throws GameAlreadyCreatedException   if the game has already been created
     * @throws AlreadyStartedGameException   if the game has already started
     * @throws IOException                   if there is an I/O error during the process
     * @throws InterruptedException          if the process is interrupted
     */
    public static void createNewGameAndAddPlayer(int i) throws WrongMessageClassEnumException, MaxNumberOfPlayersException, GameAlreadyCreatedException, AlreadyStartedGameException, IOException, InterruptedException {
        client.createNewGameAndAddPlayer(playerId, i);
    }

    /**
     * Sets the GamePageController.
     *
     * @param controller the GamePageController to set
     */
    public static void setController(GamePageController controller) {
        GUI.controller = controller;
    }

    /**
     * Returns the player ID.
     *
     * @return the player ID
     */
    public static String getPlayerId() {
        return GUI.playerId;
    }

    /**
     * Returns the shared object.
     *
     * @return the shared object
     */
    public static SharedObject getSharedObject() {
        return sharedObject;
    }
}
