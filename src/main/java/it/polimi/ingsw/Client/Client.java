package it.polimi.ingsw.Client;

import it.polimi.ingsw.Model.Coordinates;
import it.polimi.ingsw.Model.Exceptions.*;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Client {

    public void choosePlayerId(String playerId) throws PlayerIdAlreadyInUseException, IOException;

    public int addPlayerToCreatedGame(String playerId) throws AlreadyStartedGameException, CreateNewGameException, IOException;

    public int createNewGameAndAddPlayer(String playerId, int maxPlayers) throws MaxNumberOfPlayersException, GameAlreadyCreatedException, AlreadyStartedGameException, IOException;

    public void addObserver(RemoteObserver observer, String playerId) throws IOException;

    public void pickAndInsertInBookshelf(ArrayList<Coordinates> tilesSelection, int column, int[] order, String playerId) throws PlayerIsWaitingException, SelectionIsEmptyException, SelectionNotValidException, ColumnNotValidException, PickedColumnOutOfBoundsException, PickDoesntFitColumnException, TilesSelectionSizeDifferentFromOrderLengthException, VoidBoardTileException, WrongConfigurationException, RemoteException;
    public void riempiTutto() throws RemoteException, PickedColumnOutOfBoundsException, PickDoesntFitColumnException;
}