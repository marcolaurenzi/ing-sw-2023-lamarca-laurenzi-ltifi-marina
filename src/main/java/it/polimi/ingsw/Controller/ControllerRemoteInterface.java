package it.polimi.ingsw.Controller;

import it.polimi.ingsw.Model.Coordinates;
import it.polimi.ingsw.Model.Exceptions.*;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
/**
 *The ControllerRemoteInterface interface represents the remote interface of the controller.
 */
public interface ControllerRemoteInterface extends Remote {
        /**
         * Chooses the player ID for a new player.
         *
         * @param playerId The ID of the player.
         * @throws RemoteException                    If a communication-related error occurs.
         * @throws PlayerIdAlreadyInUseException       If the player ID is already in use.
         * @throws PlayerOnlineException               If the player is already online.
         */
        void choosePlayerId(String playerId) throws RemoteException, PlayerIdAlreadyInUseException, PlayerOnlineException;

        /**
         * Adds a player to a created game.
         *
         * @param observer  The observer object to be registered for updates.
         * @param playerID  The ID of the player.
         * @return          The ID of the game the player was added to.
         * @throws RemoteException                If a communication-related error occurs.
         * @throws CreateNewGameException         If a new game cannot be created.
         * @throws AlreadyStartedGameException    If the game has already started.
         */
        int addPlayerToCreatedGame(Observer observer, String playerID) throws RemoteException, CreateNewGameException, AlreadyStartedGameException;

        /**
         * Creates a new game and adds a player to it.
         *
         * @param playerID           The ID of the player.
         * @param maxPlayers         The maximum number of players allowed in the game.
         * @return                   The ID of the newly created game.
         * @throws RemoteException                    If a communication-related error occurs.
         * @throws MaxNumberOfPlayersException         If the maximum number of players is exceeded.
         * @throws IOException                        If an I/O error occurs while creating the game.
         * @throws AlreadyStartedGameException         If a game has already been started.
         * @throws GameAlreadyCreatedException        If a game has already been created.
         */
        int createNewGameAndAddPlayer(String playerID, int maxPlayers) throws RemoteException, MaxNumberOfPlayersException, IOException, AlreadyStartedGameException, GameAlreadyCreatedException;

        /**
         * Picks and inserts tiles into the bookshelf.
         *
         * @param tilesSelection                          The coordinates of the tiles to be picked.
         * @param column                                  The column index where the tiles will be inserted.
         * @param order                                   The order of the tiles to be picked.
         * @param playerId                                The ID of the player performing the action.
         * @throws PlayerIsWaitingException               If the player is currently waiting.
         * @throws SelectionIsEmptyException              If the selection of tiles is empty.
         * @throws SelectionNotValidException             If the selection of tiles is not valid.
         * @throws ColumnNotValidException                If the column index is not valid.
         * @throws PickedColumnOutOfBoundsException       If the picked column is out of bounds.
         * @throws PickDoesntFitColumnException           If the picked tiles do not fit into the column.
         * @throws TilesSelectionSizeDifferentFromOrderLengthException   If the size of the tiles selection is different from the length of the order array.
         * @throws VoidBoardTileException                 If a board tile is void.
         * @throws WrongConfigurationException            If the game configuration is incorrect.
         * @throws RemoteException                       If a communication-related error occurs.
         */
        void pickAndInsertInBookshelf(ArrayList<Coordinates> tilesSelection, int column, int[] order, String playerId) throws PlayerIsWaitingException, SelectionIsEmptyException, SelectionNotValidException, ColumnNotValidException, PickedColumnOutOfBoundsException, PickDoesntFitColumnException, TilesSelectionSizeDifferentFromOrderLengthException, VoidBoardTileException, WrongConfigurationException, RemoteException;

        /**
         * Fills the board completely.
         *
         * @throws RemoteException                      If a communication-related error occurs.
         * @throws PickedColumnOutOfBoundsException     If the picked column is out of bounds.
         * @throws PickDoesntFitColumnException         If the picked tiles do not fit into the column.
         */
        void fillCompletely() throws RemoteException, PickedColumnOutOfBoundsException, PickDoesntFitColumnException;

        /**
         * Checks the password for a player.
         *
         * @param playerId      The ID of the player.
         * @param password      The password to be checked.
         * @param observer      The observer object to be registered for updates.
         * @throws WrongPasswordException                 If the password is incorrect.
         * @throws RemoteException                       If a communication-related error occurs.
         * @throws AlreadyStartedGameException            If a game has already been started.
         */
        void checkPassword(String playerId, String password, Observer observer) throws WrongPasswordException, RemoteException, AlreadyStartedGameException;

        /**
         * Chooses the password for a player.
         *
         * @param playerId      The ID of the player.
         * @param password      The password to be set.
         * @throws RemoteException                       If a communication-related error occurs.
         * @throws PlayerIdAlreadyInUseException          If the player ID is already in use.
         */
        void choosePassword(String playerId, String password) throws RemoteException, PlayerIdAlreadyInUseException;

        /**
         * ping
         * @throws RemoteException If a communication-related error occurs.
         */

        void ping() throws RemoteException;
}
