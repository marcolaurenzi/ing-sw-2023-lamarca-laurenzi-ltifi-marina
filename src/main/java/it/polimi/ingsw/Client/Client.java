package it.polimi.ingsw.Client;

import it.polimi.ingsw.Model.Coordinates;
import it.polimi.ingsw.Model.Exceptions.*;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Client {

    void choosePlayerId(String playerId) throws PlayerIdAlreadyInUseException, IOException;

    int addPlayerToCreatedGame(String playerId) throws AlreadyStartedGameException, CreateNewGameException, IOException;

    int createNewGameAndAddPlayer(String playerId, int maxPlayers) throws MaxNumberOfPlayersException, GameAlreadyCreatedException, AlreadyStartedGameException, IOException;

    void addObserver(String playerId) throws IOException, NotBoundException;

    void pickAndInsertInBookshelf(ArrayList<Coordinates> tilesSelection, int column, int[] order, String playerId) throws PlayerIsWaitingException, SelectionIsEmptyException, SelectionNotValidException, ColumnNotValidException, PickedColumnOutOfBoundsException, PickDoesntFitColumnException, TilesSelectionSizeDifferentFromOrderLengthException, VoidBoardTileException, WrongConfigurationException, RemoteException;
    void riempiTutto() throws RemoteException, PickedColumnOutOfBoundsException, PickDoesntFitColumnException;
    int getNumCurrentPlayers(int gameId) throws RemoteException;

}