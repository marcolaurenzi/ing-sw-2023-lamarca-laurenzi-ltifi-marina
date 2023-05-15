package it.polimi.ingsw.Client;

import it.polimi.ingsw.Controller.ControllerRemoteInterface;
import it.polimi.ingsw.Controller.ObserverRMI;
import it.polimi.ingsw.Model.Coordinates;
import it.polimi.ingsw.Model.Exceptions.*;
import it.polimi.ingsw.Utils.GameStatus;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


public class ClientRMI extends UnicastRemoteObject implements Client, RemoteClient {
    private RemoteUI remoteUI;
    static ControllerRemoteInterface controller;
    public ClientRMI(RemoteUI remoteUI) throws MalformedURLException, NotBoundException, RemoteException {
        this.remoteUI = remoteUI;
        controller = (ControllerRemoteInterface ) Naming.lookup("rmi://localhost/controller");
    }
    @Override
    public void choosePlayerId(String playerId) throws PlayerIdAlreadyInUseException, RemoteException {
        controller.choosePlayerId(playerId);
    }
    @Override
    public int addPlayerToCreatedGame(String playerId) throws AlreadyStartedGameException, CreateNewGameException, RemoteException {
        return controller.addPlayerToCreatedGame(playerId);
    }

    @Override
    public int createNewGameAndAddPlayer(String playerId, int maxPlayers) throws MaxNumberOfPlayersException, GameAlreadyCreatedException, AlreadyStartedGameException, IOException {
        return controller.createNewGameAndAddPlayer(playerId, maxPlayers);
    }
    public void addObserver(String playerId) throws IOException, NotBoundException {
        controller.addObserver(new ObserverRMI(new ClientRMI(remoteUI)), playerId);
    }
    @Override
    public void pickAndInsertInBookshelf(ArrayList<Coordinates> tilesSelection, int column, int[] order, String playerId) throws PlayerIsWaitingException, SelectionIsEmptyException, SelectionNotValidException, ColumnNotValidException, PickedColumnOutOfBoundsException, PickDoesntFitColumnException, TilesSelectionSizeDifferentFromOrderLengthException, VoidBoardTileException, WrongConfigurationException, RemoteException {
        controller.pickAndInsertInBookshelf(tilesSelection, column, order, playerId);
    }
    public void riempiTutto() throws RemoteException, PickedColumnOutOfBoundsException, PickDoesntFitColumnException {
        controller.riempiTutto();
    }
    @Override
    public void update(GameStatus game) throws RemoteException {
        remoteUI.update(game);
    }
    @Override
    public void playTurn() throws IOException, VoidBoardTileException, SelectionNotValidException, PlayerIsWaitingException, TilesSelectionSizeDifferentFromOrderLengthException, ColumnNotValidException, SelectionIsEmptyException, WrongConfigurationException, PickedColumnOutOfBoundsException, PickDoesntFitColumnException, WrongMessageClassEnumException, InterruptedException {
        remoteUI.playTurn();
    }
    @Override
    public void endGame(String winnerPlayer) throws RemoteException {
        remoteUI.endGame(winnerPlayer);
    }
    public int getNumCurrentPlayers(int gameId) throws RemoteException {
        return controller.getNumCurrentPlayers(gameId);
    }
}
