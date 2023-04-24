package it.polimi.ingsw.Controller;

import it.polimi.ingsw.Model.Exceptions.*;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ControllerRemoteInterface extends Remote {
        void choosePlayerId(String playerId) throws RemoteException, PlayerIdAlreadyInUseException;
        int addPlayerToCreatedGame(String playerID) throws RemoteException, CreateNewGameException, AlreadyStartedGameException;
        int createNewGameAndAddPlayer(String playerID, int maxPlayers) throws RemoteException, MaxNumberOfPlayersException, IOException, AlreadyStartedGameException, GameAlreadyCreatedException;
}
