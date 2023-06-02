package it.polimi.ingsw.View;

import it.polimi.ingsw.Client.Client;
import it.polimi.ingsw.Client.ClientRMI;
import it.polimi.ingsw.Client.ClientSocket;
import it.polimi.ingsw.Client.RemoteUI;
import it.polimi.ingsw.Model.Exceptions.*;
import it.polimi.ingsw.Utils.GameStatusToSend;
import javafx.application.Application;

import javax.swing.text.View;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static it.polimi.ingsw.View.LoginController.connectionMode;

public class GUI implements RemoteUI {
    private static ViewController controller;
    private static String playerId;
    private static int gameId;
    private static Client client;
    private static boolean isEnded;
    private static GameStatusToSend gameStatus;

    public static void connectToServer() throws ConnectionException {
        try {
            if (connectionMode.equals("RMI")) {
                client = new ClientRMI(new GUI());
            }
            else if (connectionMode.equals("socket"))
                client = new ClientSocket(new GUI());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConnectionException();
        }

        System.out.println("Connection to server: SUCCESSFUL");
    }

    public static void askForUsername() throws WrongPasswordException {

        boolean isUsernameAlreadyInUse = false;
        try {
            client.choosePlayerId(LoginController.getUsername());
        } catch (PlayerIdAlreadyInUseException e) {
            isUsernameAlreadyInUse = true;
        } catch (Exception e) {
            System.out.println("Exception in GUI ask for username " + e);
            e.printStackTrace();
            System.exit(-1);
        }


        if(isUsernameAlreadyInUse){
            try {
                client.checkPassword(LoginController.getUsername(), LoginController.getPassword());
            } catch (WrongPasswordException e) {
                throw e;
            } catch (Exception e) {
                System.out.println("Exception in GUI ask for password " + e);
                e.printStackTrace();
                System.exit(-1);
            }
        }
        try {
            client.choosePassword(LoginController.getUsername(), LoginController.getPassword());
        }
        catch (Exception e) {
            System.out.println("Exception in GUI ask for password " + e);
            e.printStackTrace();
            System.exit(-1);
        }
    }


    @Override
    public void update(GameStatusToSend game) throws RemoteException {

    }

    @Override
    public void playTurn() throws IOException, VoidBoardTileException, SelectionNotValidException, PlayerIsWaitingException, TilesSelectionSizeDifferentFromOrderLengthException, ColumnNotValidException, WrongConfigurationException, PickedColumnOutOfBoundsException, PickDoesntFitColumnException, SelectionIsEmptyException, WrongMessageClassEnumException, InterruptedException {

    }

    @Override
    public void endGame(String winnerPlayer) throws RemoteException {

    }

    public static void addPlayer() throws CreateNewGameException{
        try {
            gameId = client.addPlayerToCreatedGame(playerId);
        } catch (AlreadyStartedGameException | IOException | InterruptedException | WrongMessageClassEnumException e) {
            System.out.println("FATAL exception" + e);
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static void createNewGameAndAddPlayer(int i) throws WrongMessageClassEnumException, MaxNumberOfPlayersException, GameAlreadyCreatedException, AlreadyStartedGameException, IOException, InterruptedException {
        client.createNewGameAndAddPlayer(playerId, i);
    }

    public static void setController(ViewController controller) {
        GUI.controller = controller;
    }
}
