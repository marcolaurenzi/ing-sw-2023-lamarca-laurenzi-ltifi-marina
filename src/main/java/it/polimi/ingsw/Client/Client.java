package it.polimi.ingsw.Client;

import it.polimi.ingsw.Controller.Observer;
import it.polimi.ingsw.Model.Coordinates;
import it.polimi.ingsw.Model.Exceptions.*;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * The Client interface represents a client in the game.
 * It provides methods to interact with the game server.
 */
public interface Client {

    /**
     * Chooses the player ID for the client.
     *
     * @param playerId the desired player ID
     * @throws PlayerIdAlreadyInUseException     if the player ID is already in use
     * @throws IOException                      if an I/O error occurs
     * @throws InterruptedException             if the thread is interrupted
     * @throws WrongMessageClassEnumException    if the message class enumeration is wrong
     * @throws PlayerOnlineException             if the player is already online
     */
    void choosePlayerId(String playerId) throws PlayerIdAlreadyInUseException, IOException, InterruptedException, WrongMessageClassEnumException, PlayerOnlineException;

    /**
     * Adds the player to a created game.
     *
     * @param playerId the player ID
     * @return the game ID
     * @throws AlreadyStartedGameException       if the game has already started
     * @throws CreateNewGameException            if a new game creation is needed
     * @throws IOException                      if an I/O error occurs
     * @throws InterruptedException             if the thread is interrupted
     * @throws WrongMessageClassEnumException    if the message class enumeration is wrong
     * @throws NotBoundException                 if the remote object is not bound
     */
    int addPlayerToCreatedGame(String playerId) throws AlreadyStartedGameException, CreateNewGameException, IOException, InterruptedException, WrongMessageClassEnumException, NotBoundException;

    /**
     * Creates a new game and adds the player to it.
     *
     * @param playerId   the player ID
     * @param maxPlayers the maximum number of players in the game
     * @return the game ID
     * @throws MaxNumberOfPlayersException       if the maximum number of players is exceeded
     * @throws GameAlreadyCreatedException       if a game has already been created
     * @throws AlreadyStartedGameException       if the game has already started
     * @throws IOException                      if an I/O error occurs
     * @throws InterruptedException             if the thread is interrupted
     * @throws WrongMessageClassEnumException    if the message class enumeration is wrong
     */
    int createNewGameAndAddPlayer(String playerId, int maxPlayers) throws MaxNumberOfPlayersException, GameAlreadyCreatedException, AlreadyStartedGameException, IOException, InterruptedException, WrongMessageClassEnumException;

    /**
     * Picks and inserts tiles into the bookshelf.
     *
     * @param tilesSelection                 the selected tiles' coordinates
     * @param column                         the target column index
     * @param order                          the order in which the tiles are picked
     * @param playerId                       the player ID
     * @throws PlayerIsWaitingException                  if the player is waiting
     * @throws SelectionIsEmptyException                 if the selection is empty
     * @throws SelectionNotValidException                if the selection is not valid
     * @throws ColumnNotValidException                    if the column is not valid
     * @throws PickedColumnOutOfBoundsException          if the picked column is out of bounds
     * @throws PickDoesntFitColumnException               if the picked tiles don't fit the column
     * @throws TilesSelectionSizeDifferentFromOrderLengthException if the size of tilesSelection is different from the length of order
     * @throws VoidBoardTileException                       if a board tile is void
     * @throws WrongConfigurationException                 if the configuration is wrong
     * @throws IOException                          if an I/O error occurs
     * @throws WrongMessageClassEnumException         if the message class enumeration is wrong
     * @throws InterruptedException                 if the thread is interrupted
     */
    void pickAndInsertInBookshelf(ArrayList<Coordinates> tilesSelection, int column, int[] order, String playerId) throws PlayerIsWaitingException, SelectionIsEmptyException, SelectionNotValidException, ColumnNotValidException, PickedColumnOutOfBoundsException, PickDoesntFitColumnException, TilesSelectionSizeDifferentFromOrderLengthException, VoidBoardTileException, WrongConfigurationException, IOException, WrongMessageClassEnumException, InterruptedException;

    /**
     * Checks the password for a player.
     *
     * @param playerId  the player ID
     * @param password  the password to check
     * @throws WrongPasswordException                 if the password is incorrect
     * @throws IOException                      if an I/O error occurs
     * @throws InterruptedException             if the thread is interrupted
     * @throws WrongMessageClassEnumException    if the message class enumeration is wrong
     * @throws AlreadyStartedGameException       if the game has already started
     * @throws NotBoundException                 if the remote object is not bound
     */
    void checkPassword(String playerId, String password) throws WrongPasswordException, IOException, InterruptedException, WrongMessageClassEnumException, AlreadyStartedGameException, NotBoundException;

    /**
     * Chooses the password for a player.
     *
     * @param playerId  the player ID
     * @param password  the password to choose
     * @throws IOException                      if an I/O error occurs
     * @throws InterruptedException             if the thread is interrupted
     * @throws WrongMessageClassEnumException    if the message class enumeration is wrong
     * @throws PlayerIdAlreadyInUseException     if the player ID is already in use
     */
    void choosePassword(String playerId, String password) throws IOException, InterruptedException, WrongMessageClassEnumException, PlayerIdAlreadyInUseException;

    /**
     *ping
     * @throws RemoteException if the remote object is not bound
     * @throws IOException if an I/O error occurs
     */
    void ping() throws RemoteException, IOException;
}
