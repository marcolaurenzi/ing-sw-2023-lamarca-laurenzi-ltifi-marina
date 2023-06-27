package it.polimi.ingsw.Client;

import it.polimi.ingsw.Controller.ControllerRemoteInterface;
import it.polimi.ingsw.Controller.ObserverRMI;
import it.polimi.ingsw.Model.Coordinates;
import it.polimi.ingsw.Model.Exceptions.*;
import it.polimi.ingsw.Utils.GameStatusToSend;
import it.polimi.ingsw.Utils.IpConfig;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Represents a client using the RMI protocol.
 */
public class ClientRMI extends UnicastRemoteObject implements Client, RemoteClient {
    /**
     * The remote user interface.
     */
    private RemoteUI remoteUI;
    /**
     * The controller for remote interface.
     */
    static ControllerRemoteInterface controller;

    /**
     * Constructs a ClientRMI object.
     *
     * @param remoteUI the remote user interface.
     * @throws MalformedURLException if the URL is malformed.
     * @throws NotBoundException if the object is not bound to the registry.
     * @throws RemoteException if a remote communication error occurs.
     */
    public ClientRMI(RemoteUI remoteUI) throws MalformedURLException, NotBoundException, RemoteException {
        this.remoteUI = remoteUI;
        System.setProperty("java.rmi.server.hostname", IpConfig.ipClient /*"localhost"*/);
        //qui va l'ip di chi ha solo client
        controller = (ControllerRemoteInterface) Naming.lookup("rmi://"+IpConfig.ipServer+"/controller");
    }

    /**
     * Sends the chosen player ID to the controller.
     *
     * @param playerId the player ID to choose.
     * @throws PlayerIdAlreadyInUseException if the player ID is already in use.
     * @throws RemoteException if a remote communication error occurs.
     * @throws PlayerOnlineException if the player is already online.
     */
    @Override
    public void choosePlayerId(String playerId) throws PlayerIdAlreadyInUseException, RemoteException, PlayerOnlineException {
        controller.choosePlayerId(playerId);
    }

    /**
     * Adds a player to a created game.
     *
     * @param playerId the ID of the player to add.
     * @return the game ID.
     * @throws AlreadyStartedGameException if the game has already started.
     * @throws CreateNewGameException if a new game cannot be created.
     * @throws RemoteException if a remote communication error occurs.
     * @throws MalformedURLException if the URL is malformed.
     * @throws NotBoundException if the object is not bound to the registry.
     */
    @Override
    public int addPlayerToCreatedGame(String playerId) throws AlreadyStartedGameException, CreateNewGameException, RemoteException, MalformedURLException, NotBoundException {
        return controller.addPlayerToCreatedGame(new ObserverRMI(new ClientRMI(remoteUI)), playerId);
    }

    /**
     * Creates a new game and adds a player to it.
     *
     * @param playerId the ID of the player to add.
     * @param maxPlayers the maximum number of players in the game.
     * @return the game ID.
     * @throws MaxNumberOfPlayersException if the maximum number of players is exceeded.
     * @throws GameAlreadyCreatedException if a game has already been created.
     * @throws AlreadyStartedGameException if the game has already started.
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public int createNewGameAndAddPlayer(String playerId, int maxPlayers) throws MaxNumberOfPlayersException, GameAlreadyCreatedException, AlreadyStartedGameException, IOException {
        return controller.createNewGameAndAddPlayer(playerId, maxPlayers);
    }

    /**
     * Picks and inserts tiles into the bookshelf.
     *
     * @param tilesSelection the selected tiles to insert.
     * @param column the column in which to insert the tiles.
     * @param order the order in which to insert the tiles.
     * @param playerId the ID of the player performing the action.
     * @throws PlayerIsWaitingException if the player is waiting for their turn.
     * @throws SelectionIsEmptyException if the selection is empty.
     * @throws SelectionNotValidException if the selection is not valid.
     * @throws ColumnNotValidException if the column is not valid.
     * @throws PickedColumnOutOfBoundsException if the picked column is out of bounds.
     * @throws PickDoesntFitColumnException if the pick doesn't fit the column.
     * @throws TilesSelectionSizeDifferentFromOrderLengthException if the size of the tiles selection is different from the order length.
     * @throws VoidBoardTileException if a board tile is void.
     * @throws WrongConfigurationException if the configuration is wrong.
     * @throws RemoteException if a remote communication error occurs.
     */
    @Override
    public void pickAndInsertInBookshelf(ArrayList<Coordinates> tilesSelection, int column, int[] order, String playerId) throws PlayerIsWaitingException, SelectionIsEmptyException, SelectionNotValidException, ColumnNotValidException, PickedColumnOutOfBoundsException, PickDoesntFitColumnException, TilesSelectionSizeDifferentFromOrderLengthException, VoidBoardTileException, WrongConfigurationException, RemoteException {
        controller.pickAndInsertInBookshelf(tilesSelection, column, order, playerId);
    }

    /**
     * Fills the bookshelf completely.
     *
     * @throws RemoteException if a remote communication error occurs.
     * @throws PickedColumnOutOfBoundsException if the picked column is out of bounds.
     * @throws PickDoesntFitColumnException if the pick doesn't fit the column.
     */
    public void fillCompletely() throws RemoteException, PickedColumnOutOfBoundsException, PickDoesntFitColumnException {
        controller.fillCompletely();
    }

    /**
     * Updates the game status.
     *
     * @param game the updated game status.
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public void update(GameStatusToSend game) throws IOException {
        remoteUI.update(game);
    }

    /**
     * Plays a turn in the game.
     *
     * @throws IOException if an I/O error occurs.
     * @throws VoidBoardTileException if a board tile is void.
     * @throws SelectionNotValidException if the selection is not valid.
     * @throws PlayerIsWaitingException if the player is waiting for their turn.
     * @throws TilesSelectionSizeDifferentFromOrderLengthException if the size of the tiles selection is different from the order length.
     * @throws ColumnNotValidException if the column is not valid.
     * @throws SelectionIsEmptyException if the selection is empty.
     * @throws WrongConfigurationException if the configuration is wrong.
     * @throws PickedColumnOutOfBoundsException if the picked column is out of bounds.
     * @throws PickDoesntFitColumnException if the pick doesn't fit the column.
     * @throws WrongMessageClassEnumException if the message class enum is wrong.
     * @throws InterruptedException if the thread is interrupted.
     */
    @Override
    public void playTurn() throws IOException, VoidBoardTileException, SelectionNotValidException, PlayerIsWaitingException, TilesSelectionSizeDifferentFromOrderLengthException, ColumnNotValidException, SelectionIsEmptyException, WrongConfigurationException, PickedColumnOutOfBoundsException, PickDoesntFitColumnException, WrongMessageClassEnumException, InterruptedException {
        remoteUI.playTurn();
    }

    /**
     * Ends the game and announces the winner.
     *
     * @param winnerPlayer the ID of the winner player.
     * @throws RemoteException if a remote communication error occurs.
     */
    @Override
    public void endGame(String winnerPlayer) throws RemoteException {
        remoteUI.endGame(winnerPlayer);
    }

    public void ping() throws RemoteException {
        controller.ping();
    }

    /**
     * Checks the password for a player.
     *
     * @param playerId the ID of the player to check the password for.
     * @param password the password to check.
     * @throws WrongPasswordException if the password is incorrect.
     * @throws RemoteException if a remote communication error occurs.
     * @throws AlreadyStartedGameException if the game has already started.
     * @throws MalformedURLException if the URL is malformed.
     * @throws NotBoundException if the object is not bound to the registry.
     */
    public void checkPassword(String playerId, String password) throws WrongPasswordException, RemoteException, AlreadyStartedGameException, MalformedURLException, NotBoundException {
        controller.checkPassword(playerId, password, new ObserverRMI(new ClientRMI(remoteUI)));
    }

    /**
     * Chooses a password for a player.
     *
     * @param playerId the ID of the player to choose the password for.
     * @param password the password to choose.
     * @throws RemoteException if a remote communication error occurs.
     * @throws PlayerIdAlreadyInUseException if the player ID is already in use.
     */
    public void choosePassword(String playerId, String password) throws RemoteException, PlayerIdAlreadyInUseException {
        controller.choosePassword(playerId, password);
    }
}
