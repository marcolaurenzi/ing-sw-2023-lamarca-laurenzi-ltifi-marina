package it.polimi.ingsw.View;

import it.polimi.ingsw.Client.Client;
import it.polimi.ingsw.Client.ClientRMI;
import it.polimi.ingsw.Client.ClientSocket;
import it.polimi.ingsw.Client.RemoteUI;
import it.polimi.ingsw.Controller.Controller;
import it.polimi.ingsw.Model.Coordinates;
import it.polimi.ingsw.Model.Exceptions.*;
import it.polimi.ingsw.Utils.GameStatusToSend;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import static it.polimi.ingsw.View.LoginController.connectionMode;

public class GUI implements RemoteUI {
    private static GamePageController controller;
    private static String playerId;
    private static int gameId;
    private static Client client;
    private static boolean isEnded;
    protected static GameStatusToSend gameStatus;
    private static GUITurnSelectionHandler turnSelectionHandler;



    private static SharedObject sharedObject;

    public static void connectToServer() throws ConnectionException {
        try {
            if (connectionMode.equals("RMI")) {
                client = new ClientRMI(new GUI());
            }
            else if (connectionMode.equals("Socket"))
                client = new ClientSocket(new GUI());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConnectionException();
        }

        System.out.println("Connection to server: SUCCESSFUL");
    }

    public static void askForUsername() throws WrongPasswordException, IOException {

        boolean isUsernameAlreadyInUse = false;
        try {
            client.choosePlayerId(LoginController.getUsername());

            playerId = LoginController.getUsername();
        } catch (PlayerIdAlreadyInUseException e) {
            isUsernameAlreadyInUse = true;
        } catch (Exception e) {
            System.out.println("Exception in GUI ask for username " + e);
            e.printStackTrace();
            System.exit(-1);
        }


        if(isUsernameAlreadyInUse){
            boolean passwordok = false;
            while(!passwordok) {
                try {
                    client.checkPassword(LoginController.getUsername(), LoginController.getPassword());
                    playerId = LoginController.getUsername();
                    passwordok = true;
                } catch (WrongPasswordException e) { // print password is wrong and start again
                    throw new WrongPasswordException();
                } catch (Exception e) {
                    System.out.println("Exception in GUI ask for password " + e);
                    e.printStackTrace();
                    System.exit(-1);
                }
            }

        }
        else {
            try {
                client.choosePassword(LoginController.getUsername(), LoginController.getPassword());
            } catch (Exception e) {
                System.out.println("Exception in GUI ask for password " + e);
                e.printStackTrace();
                System.exit(-1);
            }
        }
    }


    @Override
    public void update(GameStatusToSend game) throws RemoteException {
        gameStatus = game;
        sharedObject = new SharedObject();
        controller.uiUpdate(gameStatus);
    }

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
        client.pickAndInsertInBookshelf(tilesSelection, turnSelectionHandler.getColumn(), order, playerId);
    }

    @Override
    public void endGame(String winnerPlayer) throws RemoteException {

    }

    public static void addPlayer(String username) throws AlreadyStartedGameException, CreateNewGameException, NotBoundException {
        try {
            gameId = client.addPlayerToCreatedGame(username);
        } catch (IOException | InterruptedException | WrongMessageClassEnumException e) {
            System.out.println("FATAL exception" + e);
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static void createNewGameAndAddPlayer(int i) throws WrongMessageClassEnumException, MaxNumberOfPlayersException, GameAlreadyCreatedException, AlreadyStartedGameException, IOException, InterruptedException {
        client.createNewGameAndAddPlayer(playerId, i);
    }

    public static void setController(GamePageController controller) {
        GUI.controller = controller;
    }
    public static String getPlayerId() {
        return GUI.playerId;
    }

    public static SharedObject getSharedObject() {
        return sharedObject;
    }
}
