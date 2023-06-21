package it.polimi.ingsw.Client;

import it.polimi.ingsw.Controller.Observer;
import it.polimi.ingsw.Model.Coordinates;
import it.polimi.ingsw.Model.Exceptions.*;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Client {

    void choosePlayerId(String playerId) throws PlayerIdAlreadyInUseException, IOException, InterruptedException, WrongMessageClassEnumException, PlayerOnlineException;

    int addPlayerToCreatedGame(String playerId) throws AlreadyStartedGameException, CreateNewGameException, IOException, InterruptedException, WrongMessageClassEnumException, NotBoundException;

    int createNewGameAndAddPlayer(String playerId, int maxPlayers) throws MaxNumberOfPlayersException, GameAlreadyCreatedException, AlreadyStartedGameException, IOException, InterruptedException, WrongMessageClassEnumException;

    void pickAndInsertInBookshelf(ArrayList<Coordinates> tilesSelection, int column, int[] order, String playerId) throws PlayerIsWaitingException, SelectionIsEmptyException, SelectionNotValidException, ColumnNotValidException, PickedColumnOutOfBoundsException, PickDoesntFitColumnException, TilesSelectionSizeDifferentFromOrderLengthException, VoidBoardTileException, WrongConfigurationException, IOException, WrongMessageClassEnumException, InterruptedException;
    void checkPassword(String playerId, String password) throws WrongPasswordException, IOException, InterruptedException, WrongMessageClassEnumException, AlreadyStartedGameException, NotBoundException;
    void choosePassword(String platerId, String password) throws IOException, InterruptedException, WrongMessageClassEnumException, PlayerIdAlreadyInUseException;
}