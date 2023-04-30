package it.polimi.ingsw.Client;

import it.polimi.ingsw.Controller.ControllerRemoteInterface;
import it.polimi.ingsw.Model.Coordinates;
import it.polimi.ingsw.Model.Exceptions.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.*;
import java.util.ArrayList;


public class ClientRMI implements Client {

    ControllerRemoteInterface controller;
    public ClientRMI() throws MalformedURLException, NotBoundException, RemoteException {
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
    public void addObserver(RemoteObserver observer, String playerId) throws IOException {
        controller.addObserver(observer, playerId);
    }
    @Override
    public void pickAndInsertInBookshelf(ArrayList<Coordinates> tilesSelection, int column, int[] order, String playerId) throws PlayerIsWaitingException, SelectionIsEmptyException, SelectionNotValidException, ColumnNotValidException, PickedColumnOutOfBoundsException, PickDoesntFitColumnException, TilesSelectionSizeDifferentFromOrderLengthException, VoidBoardTileException, WrongConfigurationException, RemoteException {
        controller.pickAndInsertInBookshelf(tilesSelection, column, order, playerId);
    }
    public void riempiTutto() throws RemoteException, PickedColumnOutOfBoundsException, PickDoesntFitColumnException {
        controller.riempiTutto();
    }
}
