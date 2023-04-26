package it.polimi.ingsw.Client;

import it.polimi.ingsw.Controller.ControllerRemoteInterface;
import it.polimi.ingsw.Model.Exceptions.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.*;


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
}
