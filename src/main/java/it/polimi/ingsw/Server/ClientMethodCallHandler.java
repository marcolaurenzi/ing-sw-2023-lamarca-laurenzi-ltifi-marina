package it.polimi.ingsw.Server;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.ObserverSocket;
import it.polimi.ingsw.Model.Coordinates;
import it.polimi.ingsw.Model.Exceptions.*;
import it.polimi.ingsw.Utils.*;
import it.polimi.ingsw.Utils.MessageEnums.ExceptionEnum;
import it.polimi.ingsw.Utils.MessageEnums.MessageTypeEnum;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * This class handles the method calls received from the client.
 */
public class ClientMethodCallHandler extends Thread {
    /**
     * Represents the message dispatcher to handle the communication with the client.
     */
    private final MessageDispatcher messageDispatcher;
    /**
     * Represents the data input stream to receive messages from the client.
     */
    private final ProxyDataInputStream dataInput;
    /**
     * Represents the data output stream to send messages to the client.
     */
    private final ProxyDataOutputStream dataOutput;
    /**
     * Represents the Gson object to convert Java objects into JSON strings and vice versa.
     */
    private final Gson gson = new Gson();

    /**
     * Constructs a ClientMethodCallHandler object.
     *
     * @param messageDispatcher the message dispatcher to handle the communication with the client
     */
    public ClientMethodCallHandler(MessageDispatcher messageDispatcher) {
        this.messageDispatcher = messageDispatcher;
        this.dataInput = new ProxyDataInputStream(messageDispatcher, MessageClassEnum.methodCall);
        this.dataOutput = new ProxyDataOutputStream(messageDispatcher);
        messageDispatcher.start();
    }

    /**
     * Handles the method call to choose the player ID.
     *
     * @param playerId the player ID to choose
     * @throws IOException if an I/O error occurs
     */
    private void choosePlayerId(String playerId) throws IOException {
        Message toSend;
        try {
            messageDispatcher.setPlayerId(playerId);
            Server.controller.choosePlayerId(playerId);
            toSend = new Message(MessageTypeEnum.success, null, null, null, null, null, null);
            dataOutput.writeUTF(gson.toJson(toSend));
        } catch (PlayerIdAlreadyInUseException e) {
            toSend = new Message(MessageTypeEnum.exception, ExceptionEnum.PlayerIdAlreadyInUseException, null, null, null, null, null);
            dataOutput.writeUTF(gson.toJson(toSend));
        } catch (PlayerOnlineException e) {
            toSend = new Message(MessageTypeEnum.exception, ExceptionEnum.PlayerOnlineException, null, null, null, null, null);
            dataOutput.writeUTF(gson.toJson(toSend));
        }
    }

    /**
     * Handles the method call to choose the password.
     *
     * @param playerId the player ID
     * @param password the password to choose
     * @throws IOException if an I/O error occurs
     */
    private void choosePassword(String playerId, String password) throws IOException {
        Message toSend;
        try {
            Server.controller.choosePassword(playerId, password);
            toSend = new Message(MessageTypeEnum.success, null, null, null, null, null, null);
            dataOutput.writeUTF(gson.toJson(toSend));
        } catch (PlayerIdAlreadyInUseException e) {
            toSend = new Message(MessageTypeEnum.exception, ExceptionEnum.PlayerIdAlreadyInUseException, null, null, null, null, null);
        }
    }

    /**
     * Handles the method call to check the password.
     *
     * @param playerId the player ID
     * @param password the password to check
     * @throws IOException                  if an I/O error occurs
     * @throws AlreadyStartedGameException  if the game has already started
     */
    private void checkPassword(String playerId, String password) throws IOException, AlreadyStartedGameException {
        Message toSend;
        try {
            Server.controller.checkPassword(playerId, password, new ObserverSocket(new ProxyDataInputStream(messageDispatcher, MessageClassEnum.response), new ProxyDataOutputStream(messageDispatcher)));
            toSend = new Message(MessageTypeEnum.success, null, null, null, null, null, null);
            dataOutput.writeUTF(gson.toJson(toSend));
        } catch (WrongPasswordException e) {
            toSend = new Message(MessageTypeEnum.exception, ExceptionEnum.WrongPasswordException, null, null, null, null, null);
            dataOutput.writeUTF(gson.toJson(toSend));
        }
    }

    /**
     * Handles the method call to add a player to the created game.
     *
     * @param playerId the player ID to add
     * @throws IOException if an I/O error occurs
     */
    private void addPlayerToCreatedGame(String playerId) throws IOException {
        Message toSend;
        try {
            int ret = Server.controller.addPlayerToCreatedGame(new ObserverSocket(new ProxyDataInputStream(messageDispatcher, MessageClassEnum.response), new ProxyDataOutputStream(messageDispatcher)), playerId);
            toSend = new Message(MessageTypeEnum.returnValue, null, ret, null, null, null, null);
            dataOutput.writeUTF(gson.toJson(toSend));
        } catch (CreateNewGameException e) {
            toSend = new Message(MessageTypeEnum.exception, ExceptionEnum.CreateNewGameException, null, null, null, null, null);
            dataOutput.writeUTF(gson.toJson(toSend));
        } catch (AlreadyStartedGameException e) {
            toSend = new Message(MessageTypeEnum.exception, ExceptionEnum.AlreadyStartedGameException, null, null, null, null, null);
            dataOutput.writeUTF(gson.toJson(toSend));
        }
    }

    /**
     * Handles the method call to create a new game and add a player.
     *
     * @param playerId    the player ID to add
     * @param maxPlayers  the maximum number of players in the game
     * @throws IOException if an I/O error occurs
     */
    private void createNewGameAndAddPlayer(String playerId, int maxPlayers) throws IOException {
        Message toSend;
        try {
            int ret = Server.controller.createNewGameAndAddPlayer(playerId, maxPlayers);
            toSend = new Message(MessageTypeEnum.returnValue, null, ret, null, null, null, null);
            dataOutput.writeUTF(gson.toJson(toSend));
        } catch (MaxNumberOfPlayersException e) {
            toSend = new Message(MessageTypeEnum.exception, ExceptionEnum.MaxNumberOfPlayersException, null, null, null, null, null);
            dataOutput.writeUTF(gson.toJson(toSend));
        } catch (AlreadyStartedGameException e) {
            toSend = new Message(MessageTypeEnum.exception, ExceptionEnum.AlreadyStartedGameException, null, null, null, null, null);
            dataOutput.writeUTF(gson.toJson(toSend));
        } catch (GameAlreadyCreatedException e) {
            toSend = new Message(MessageTypeEnum.exception, ExceptionEnum.GameAlreadyCreatedException, null, null, null, null, null);
            dataOutput.writeUTF(gson.toJson(toSend));
        }
    }
    private void ping() throws IOException {
        Server.controller.ping();
    }

    /**
     * Handles the method call to pick and insert tiles in the bookshelf.
     *
     * @param tilesSelection the selected tiles
     * @param column         the column index to place the tiles
     * @param order          the order of the tiles
     * @param playerId       the player ID
     * @throws IOException                             if an I/O error occurs
     * @throws SelectionNotValidException               if the selection is not valid
     * @throws PlayerIsWaitingException                 if the player is waiting
     * @throws TilesSelectionSizeDifferentFromOrderLengthException if the size of the selection is different from the length of the order
     * @throws ColumnNotValidException                  if the column index is not valid
     * @throws SelectionIsEmptyException                if the selection is empty
     * @throws WrongConfigurationException              if the configuration is wrong
     * @throws PickedColumnOutOfBoundsException         if the picked column is out of bounds
     * @throws PickDoesntFitColumnException             if the pick doesn't fit in the column
     * @throws VoidBoardTileException                   if the board tile is void
     */
    private void pickAndInsertInBookshelf(ArrayList<Coordinates> tilesSelection, int column, int[] order, String playerId) throws IOException, SelectionNotValidException, PlayerIsWaitingException, TilesSelectionSizeDifferentFromOrderLengthException, ColumnNotValidException, SelectionIsEmptyException, WrongConfigurationException, PickedColumnOutOfBoundsException, PickDoesntFitColumnException, VoidBoardTileException {
        Message toSend;
        try {
            Server.controller.pickAndInsertInBookshelf(tilesSelection, column, order, playerId);
            toSend = new Message(MessageTypeEnum.success, null, null, null, null, null, null);
            dataOutput.writeUTF(gson.toJson(toSend));
        } catch (SelectionNotValidException e) {
            toSend = new Message(MessageTypeEnum.exception, ExceptionEnum.SelectionNotValidException, null, null, null, null, null);
            dataOutput.writeUTF(gson.toJson(toSend));
        } catch (PlayerIsWaitingException e) {
            toSend = new Message(MessageTypeEnum.exception, ExceptionEnum.PlayerIsWaitingException, null, null, null, null, null);
            dataOutput.writeUTF(gson.toJson(toSend));
        } catch (TilesSelectionSizeDifferentFromOrderLengthException e) {
            toSend = new Message(MessageTypeEnum.exception, ExceptionEnum.TilesSelectionSizeDifferentFromOrderLengthException, null, null, null, null, null);
            dataOutput.writeUTF(gson.toJson(toSend));
        } catch (ColumnNotValidException e) {
            toSend = new Message(MessageTypeEnum.exception, ExceptionEnum.ColumnNotValidException, null, null, null, null, null);
            dataOutput.writeUTF(gson.toJson(toSend));
        } catch (SelectionIsEmptyException e) {
            toSend = new Message(MessageTypeEnum.exception, ExceptionEnum.SelectionIsEmptyException, null, null, null, null, null);
            dataOutput.writeUTF(gson.toJson(toSend));
        } catch (WrongConfigurationException e) {
            toSend = new Message(MessageTypeEnum.exception, ExceptionEnum.WrongConfigurationException, null, null, null, null, null);
            dataOutput.writeUTF(gson.toJson(toSend));
        } catch (PickedColumnOutOfBoundsException e) {
            toSend = new Message(MessageTypeEnum.exception, ExceptionEnum.PickedColumnOutOfBoundsException, null, null, null, null, null);
            dataOutput.writeUTF(gson.toJson(toSend));
        } catch (PickDoesntFitColumnException e) {
            toSend = new Message(MessageTypeEnum.exception, ExceptionEnum.PickDoesntFitColumnException, null, null, null, null, null);
            dataOutput.writeUTF(gson.toJson(toSend));
        } catch (VoidBoardTileException e) {
            toSend = new Message(MessageTypeEnum.exception, ExceptionEnum.VoidBoardTileException, null, null, null, null, null);
            dataOutput.writeUTF(gson.toJson(toSend));
        }
    }



    /**
     * Starts the thread to handle client method calls.
     */
    public void run() {
        while (true) {
            try {
                Message received = gson.fromJson(dataInput.readUTF(), Message.class);
                switch (received.getMethod()) {
                    case choosePlayerId -> choosePlayerId((String) received.getParameters().get(0));
                    case addPlayerToCreatedGame -> addPlayerToCreatedGame((String) received.getParameters().get(0));
                    // IMPORTANT: Gson sees all numbers as Double, so I have to cast it to Double and then use intValue()
                    case createNewGameAndAddPlayer -> createNewGameAndAddPlayer((String) received.getParameters().get(0), ((Double) received.getParameters().get(1)).intValue());
                    case pickAndInsertInBookshelf -> {
                        // TODO: change the order thing
                        ArrayList<Coordinates> selection = received.getSelectionParam();
                        int[] order = new int[selection.size()];
                        for (int i = 0; i < order.length; i++) {
                            order[i] = i;
                        }
                        pickAndInsertInBookshelf(received.getSelectionParam(), ((Double) received.getParameters().get(0)).intValue(), order, (String) received.getParameters().get(1));
                    }
                    case choosePassword -> choosePassword((String) received.getParameters().get(0), (String) received.getParameters().get(1));
                    case checkPassword -> checkPassword((String) received.getParameters().get(0), (String) received.getParameters().get(1));
                    case ping -> ping();
                }
            } catch (Exception e) {
                System.out.println("Exception caught in ClientHandler: " + e);
                e.printStackTrace();
                return;
            }
        }
    }
}
