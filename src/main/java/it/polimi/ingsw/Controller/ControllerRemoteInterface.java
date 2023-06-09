package it.polimi.ingsw.Controller;

import it.polimi.ingsw.Model.Coordinates;
import it.polimi.ingsw.Model.Exceptions.*;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ControllerRemoteInterface extends Remote {
        void choosePlayerId(String playerId) throws RemoteException, PlayerIdAlreadyInUseException;
        int addPlayerToCreatedGame(Observer observer, String playerID) throws RemoteException, CreateNewGameException, AlreadyStartedGameException;
        int createNewGameAndAddPlayer(String playerID, int maxPlayers) throws RemoteException, MaxNumberOfPlayersException, IOException, AlreadyStartedGameException, GameAlreadyCreatedException;
        void pickAndInsertInBookshelf(ArrayList<Coordinates> tilesSelection, int column, int[] order, String playerId) throws PlayerIsWaitingException, SelectionIsEmptyException, SelectionNotValidException, ColumnNotValidException, PickedColumnOutOfBoundsException, PickDoesntFitColumnException, TilesSelectionSizeDifferentFromOrderLengthException, VoidBoardTileException, WrongConfigurationException, RemoteException;
        void riempiTutto() throws RemoteException, PickedColumnOutOfBoundsException, PickDoesntFitColumnException;
        void checkPassword(String playerId, String password, Observer observer) throws WrongPasswordException, RemoteException, AlreadyStartedGameException;
        void choosePassword(String playerId, String password) throws RemoteException, PlayerIdAlreadyInUseException;
}
