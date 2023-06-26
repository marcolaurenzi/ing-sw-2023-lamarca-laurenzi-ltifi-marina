package it.polimi.ingsw.Client;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Observer;
import it.polimi.ingsw.Model.Coordinates;
import it.polimi.ingsw.Model.Exceptions.*;
import it.polimi.ingsw.Utils.*;
import it.polimi.ingsw.Utils.MessageEnums.ExceptionEnum;
import it.polimi.ingsw.Utils.MessageEnums.MessageTypeEnum;
import it.polimi.ingsw.Utils.MessageEnums.MethodNameEnum;

import java.io.IOException;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * The ClientSocket class represents a socket-based client for the game.
 * It implements the Client and RemoteClient interfaces.
 */
public class ClientSocket implements Client, RemoteClient {
    private final Socket socket;
    private final MessageDispatcher messageDispatcher;
    private ServerMethodCallHandler serverMethodCallHandler;

    private RemoteUI remoteUI;

    private ProxyDataInputStream dataInput;
    private ProxyDataOutputStream dataOutput;
    Gson gson = new Gson();;

    /**
     * Constructs a ClientSocket object with the specified RemoteUI.
     * It establishes a connection to the server.
     *
     * @param remoteUI The RemoteUI object to be associated with the client.
     * @throws IOException If an I/O error occurs when creating the socket.
     */
    public ClientSocket(RemoteUI remoteUI) throws IOException {
        this.remoteUI = remoteUI;
        socket = new Socket("172.16.1.146", 59090);
        messageDispatcher = new MessageDispatcher(socket);
        dataInput = new ProxyDataInputStream(messageDispatcher, MessageClassEnum.response);
        dataOutput = new ProxyDataOutputStream(messageDispatcher);
        serverMethodCallHandler = new ServerMethodCallHandler(messageDispatcher, this);
        serverMethodCallHandler.start();
        messageDispatcher.start();
    }

    /**
     * Chooses a player ID for the client.
     *
     * @param playerId The ID of the player.
     * @throws PlayerIdAlreadyInUseException      If the specified player ID is already in use.
     * @throws IOException                        If an I/O error occurs.
     * @throws InterruptedException               If the current thread is interrupted while waiting.
     * @throws WrongMessageClassEnumException      If the received message class is not valid.
     */
    @Override
    public void choosePlayerId(String playerId) throws PlayerIdAlreadyInUseException, IOException, InterruptedException, WrongMessageClassEnumException {
        List<Object> parameters = new ArrayList<>();
        parameters.add(playerId);

        dataOutput.writeUTF(gson.toJson(new Message(MessageTypeEnum.methodCall, null, null, MethodNameEnum.choosePlayerId, parameters, null, null)));
        Message response = gson.fromJson(dataInput.readUTF(), Message.class);

        if(response.getType().equals(MessageTypeEnum.exception) && response.getException().equals(ExceptionEnum.PlayerIdAlreadyInUseException))
            throw new PlayerIdAlreadyInUseException();
    }

    /**
     * Adds the player to a created game.
     *
     * @param playerId The ID of the player.
     * @return The ID of the created game.
     * @throws AlreadyStartedGameException        If the game has already started.
     * @throws CreateNewGameException             If a new game cannot be created.
     * @throws IOException                        If an I/O error occurs.
     * @throws InterruptedException               If the current thread is interrupted while waiting.
     * @throws WrongMessageClassEnumException      If the received message class is not valid.
     */
    @Override
    public int addPlayerToCreatedGame(String playerId) throws AlreadyStartedGameException, CreateNewGameException, IOException, InterruptedException, WrongMessageClassEnumException {
        //sending message to server
        List<Object> parameters = new ArrayList<>();
        parameters.add(playerId);
        dataOutput.writeUTF(gson.toJson(new Message(MessageTypeEnum.methodCall, null, null, MethodNameEnum.addPlayerToCreatedGame, parameters, null, null)));
        Message response = gson.fromJson(dataInput.readUTF(), Message.class);

        //reading server response
        //sending exceptions
        if(response.getType().equals(MessageTypeEnum.exception)) {
            switch (response.getException()) {
                case AlreadyStartedGameException -> throw new AlreadyStartedGameException();
                case CreateNewGameException -> throw new CreateNewGameException();
            }
        }

        // todo avoid casting
        return ((Double)response.getReturnValue()).intValue();
    }

    /**
     * Creates a new game and adds the player to it.
     *
     * @param playerId    The ID of the player.
     * @param maxPlayers  The maximum number of players for the game.
     * @return The ID of the created game.
     * @throws MaxNumberOfPlayersException        If the maximum number of players is exceeded.
     * @throws GameAlreadyCreatedException        If a game has already been created.
     * @throws AlreadyStartedGameException        If the game has already started.
     * @throws IOException                        If an I/O error occurs.
     * @throws InterruptedException               If the current thread is interrupted while waiting.
     * @throws WrongMessageClassEnumException      If the received message class is not valid.
     */
    @Override
    public int createNewGameAndAddPlayer(String playerId, int maxPlayers) throws MaxNumberOfPlayersException, GameAlreadyCreatedException, AlreadyStartedGameException, IOException, InterruptedException, WrongMessageClassEnumException {
        //sending message to server
        List<Object> parameters = new ArrayList<>();
        parameters.add(playerId);
        parameters.add(maxPlayers);
        dataOutput.writeUTF(gson.toJson(new Message(MessageTypeEnum.methodCall, null, null, MethodNameEnum.createNewGameAndAddPlayer, parameters, null, null)));
        Message response = gson.fromJson(dataInput.readUTF(), Message.class);

        //reading server response
        //sending exceptions
        if(response.getType().equals(MessageTypeEnum.exception)) {
            switch (response.getException()) {
                case MaxNumberOfPlayersException -> throw new MaxNumberOfPlayersException();
                case GameAlreadyCreatedException -> throw new GameAlreadyCreatedException();
                case AlreadyStartedGameException -> throw new AlreadyStartedGameException();
            }
        }

        //return
        return  ((Double)response.getReturnValue()).intValue();
    }

    /**
     * Adds an observer to the game.
     *
     * @param playerId The ID of the player.
     * @throws IOException If an I/O error occurs.
     */
    public void addObserver(String playerId) throws IOException {
        List<Object> parameters = new ArrayList<>();
        parameters.add(playerId);
        dataOutput.writeUTF(gson.toJson(new Message(MessageTypeEnum.methodCall, null, null, MethodNameEnum.addObserver, parameters, null, null)));
    }

    /**
     * Picks tiles from the grid and inserts them in the bookshelf.
     *
     * @param tilesSelection                   The list of selected tiles.
     * @param column                           The column index in the bookshelf.
     * @param order                            The order of tiles to be inserted.
     * @param playerId                         The ID of the player.
     * @throws PlayerIsWaitingException         If the player is waiting for their turn.
     * @throws SelectionIsEmptyException        If the selection is empty.
     * @throws SelectionNotValidException       If the selection is not valid.
     * @throws ColumnNotValidException          If the column index is not valid.
     * @throws PickedColumnOutOfBoundsException If the picked column is out of bounds.
     * @throws PickDoesntFitColumnException     If the picked tiles don't fit in the column.
     * @throws TilesSelectionSizeDifferentFromOrderLengthException If the size of the tile selection is different from the length of the order array.
     * @throws VoidBoardTileException           If the selected tile is void.
     * @throws WrongConfigurationException      If the game configuration is wrong.
     * @throws IOException                      If an I/O error occurs.
     * @throws WrongMessageClassEnumException    If the received message class is not valid.
     * @throws InterruptedException             If the current thread is interrupted while waiting.
     */
    @Override
    public void pickAndInsertInBookshelf(ArrayList<Coordinates> tilesSelection, int column, int[] order, String playerId) throws PlayerIsWaitingException, SelectionIsEmptyException, SelectionNotValidException, ColumnNotValidException, PickedColumnOutOfBoundsException, PickDoesntFitColumnException, TilesSelectionSizeDifferentFromOrderLengthException, VoidBoardTileException, WrongConfigurationException, IOException, WrongMessageClassEnumException, InterruptedException {
        List<Object> parameters = new ArrayList<>();
        parameters.add(column);
        parameters.add(playerId);

        dataOutput.writeUTF(gson.toJson(new Message(MessageTypeEnum.methodCall, null, null, MethodNameEnum.pickAndInsertInBookshelf, parameters, null, tilesSelection)));
        Message response = gson.fromJson(dataInput.readUTF(), Message.class);
        if(response.getType().equals(MessageTypeEnum.exception)) {
            switch (response.getException()) {
                case PlayerIsWaitingException -> throw new PlayerIsWaitingException();
                case SelectionIsEmptyException -> throw new SelectionIsEmptyException();
                case SelectionNotValidException -> throw new SelectionNotValidException();
                case ColumnNotValidException -> throw new ColumnNotValidException();
                case PickedColumnOutOfBoundsException -> throw new PickedColumnOutOfBoundsException();
                case PickDoesntFitColumnException -> throw new PickDoesntFitColumnException();
                case TilesSelectionSizeDifferentFromOrderLengthException -> throw new TilesSelectionSizeDifferentFromOrderLengthException();
                case VoidBoardTileException -> throw new VoidBoardTileException();
                case WrongConfigurationException -> throw new WrongConfigurationException();
            }
        }
    }

    /**
     * Updates the game status.
     *
     * @param game The updated game status.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public void update(GameStatusToSend game) throws IOException {
        remoteUI.update(game);
    }

    /**
     * Starts the player's turn.
     *
     * @throws IOException                        If an I/O error occurs.
     * @throws VoidBoardTileException              If a void board tile is encountered.
     * @throws SelectionNotValidException          If the selection is not valid.
     * @throws PlayerIsWaitingException            If the player is waiting for their turn.
     * @throws TilesSelectionSizeDifferentFromOrderLengthException If the size of the tile selection is different from the length of the order array.
     * @throws ColumnNotValidException             If the column index is not valid.
     * @throws SelectionIsEmptyException           If the selection is empty.
     * @throws WrongConfigurationException         If the game configuration is wrong.
     * @throws PickedColumnOutOfBoundsException    If the picked column is out of bounds.
     * @throws PickDoesntFitColumnException        If the picked tiles don't fit in the column.
     * @throws WrongMessageClassEnumException       If the received message class is not valid.
     * @throws InterruptedException                If the current thread is interrupted while waiting.
     */
    @Override
    public void playTurn() throws IOException, VoidBoardTileException, SelectionNotValidException, PlayerIsWaitingException, TilesSelectionSizeDifferentFromOrderLengthException, ColumnNotValidException, SelectionIsEmptyException, WrongConfigurationException, PickedColumnOutOfBoundsException, PickDoesntFitColumnException, WrongMessageClassEnumException, InterruptedException {
        remoteUI.playTurn();
    }

    /**
     * Ends the game and announces the winner.
     *
     * @param winnerPlayer The ID of the winning player.
     * @throws RemoteException If a remote exception occurs.
     */
    @Override
    public void endGame(String winnerPlayer) throws RemoteException {
        remoteUI.endGame(winnerPlayer);
    }

    /**
     * Chooses a password for the player ID.
     *
     * @param playerId The ID of the player.
     * @param password The password to be set.
     * @throws RemoteException                   If a remote exception occurs.
     * @throws IOException                       If an I/O error occurs.
     * @throws WrongMessageClassEnumException     If the received message class is not valid.
     * @throws InterruptedException              If the current thread is interrupted while waiting.
     * @throws PlayerIdAlreadyInUseException      If the specified player ID is already in use.
     */
    public void choosePassword(String playerId, String password) throws RemoteException, IOException, WrongMessageClassEnumException, InterruptedException, PlayerIdAlreadyInUseException {
        List<Object> parameters = new ArrayList<>();
        parameters.add(playerId);
        parameters.add(password);
        dataOutput.writeUTF(gson.toJson(new Message(MessageTypeEnum.methodCall, null, null, MethodNameEnum.choosePassword, parameters, null, null)));

        Message response = gson.fromJson(dataInput.readUTF(), Message.class);
        if(response.getType().equals(MessageTypeEnum.exception) && response.getException().equals(ExceptionEnum.PlayerIdAlreadyInUseException))
            throw new PlayerIdAlreadyInUseException();

    }

    /**
     * Checks if the password for the player ID is correct.
     *
     * @param playerId The ID of the player.
     * @param password The password to be checked.
     * @throws WrongPasswordException             If the specified password is wrong.
     * @throws RemoteException                   If a remote exception occurs.
     * @throws IOException                       If an I/O error occurs.
     * @throws AlreadyStartedGameException        If the game has already started.
     * @throws WrongMessageClassEnumException     If the received message class is not valid.
     * @throws InterruptedException              If the current thread is interrupted while waiting.
     */
    public void checkPassword(String playerId, String password) throws WrongPasswordException, RemoteException, IOException, AlreadyStartedGameException, WrongMessageClassEnumException, InterruptedException {
        List<Object> parameters = new ArrayList<>();
        parameters.add(playerId);
        parameters.add(password);
        dataOutput.writeUTF(gson.toJson(new Message(MessageTypeEnum.methodCall, null, null, MethodNameEnum.checkPassword, parameters, null, null)));
        Message response = gson.fromJson(dataInput.readUTF(), Message.class);
        if(response.getType().equals(MessageTypeEnum.exception) && response.getException().equals(ExceptionEnum.WrongPasswordException))
            throw new WrongPasswordException();
        else if(response.getType().equals(MessageTypeEnum.exception) && response.getException().equals(ExceptionEnum.AlreadyStartedGameException))
            throw new AlreadyStartedGameException();
    }

}
