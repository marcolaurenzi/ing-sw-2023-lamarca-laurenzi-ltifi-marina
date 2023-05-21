package it.polimi.ingsw.Client;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.ObserverSocket;
import it.polimi.ingsw.Model.Coordinates;
import it.polimi.ingsw.Model.Exceptions.*;
import it.polimi.ingsw.Utils.*;
import it.polimi.ingsw.Utils.MessageEnums.ExceptionEnum;
import it.polimi.ingsw.Utils.MessageEnums.MessageTypeEnum;
import it.polimi.ingsw.Utils.MessageEnums.MethodNameEnum;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class ClientSocket implements Client, RemoteClient {
    private final Socket socket;
    private final MessageDispatcher messageDispatcher;
    private ServerMethodCallHandler serverMethodCallHandler;

    private RemoteUI remoteUI;

    private ProxyDataInputStream dataInput;
    private ProxyDataOutputStream dataOutput;
    Gson gson = new Gson();;
    public ClientSocket(RemoteUI remoteUI) throws IOException {
        this.remoteUI = remoteUI;
        socket = new Socket("localhost", 59090);
        messageDispatcher = new MessageDispatcher(socket);
        dataInput = new ProxyDataInputStream(messageDispatcher, MessageClassEnum.response);
        dataOutput = new ProxyDataOutputStream(messageDispatcher);
        serverMethodCallHandler = new ServerMethodCallHandler(messageDispatcher, this);
        serverMethodCallHandler.start();
        messageDispatcher.start();
    }
    @Override
    public void choosePlayerId(String playerId) throws PlayerIdAlreadyInUseException, IOException, InterruptedException, WrongMessageClassEnumException {
        List<Object> parameters = new ArrayList<>();
        parameters.add(playerId);

        dataOutput.writeUTF(gson.toJson(new Message (MessageTypeEnum.methodCall, null, null, MethodNameEnum.choosePlayerId, parameters, null, null)));
        Message response = gson.fromJson(dataInput.readUTF(), Message.class);
        
        if(response.getType().equals(MessageTypeEnum.exception) && response.getException().equals(ExceptionEnum.PlayerIdAlreadyInUseException))
            throw new PlayerIdAlreadyInUseException();
    }
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
    public void addObserver(String playerId) throws IOException {
        List<Object> parameters = new ArrayList<>();
        parameters.add(playerId);
        dataOutput.writeUTF(gson.toJson(new Message(MessageTypeEnum.methodCall, null, null, MethodNameEnum.addObserver, parameters, null, null)));
    }
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

    @Override
    public void update(GameStatus game) throws RemoteException {
        remoteUI.update(game);
    }
    @Override
    public void playTurn() throws IOException, VoidBoardTileException, SelectionNotValidException, PlayerIsWaitingException, TilesSelectionSizeDifferentFromOrderLengthException, ColumnNotValidException, SelectionIsEmptyException, WrongConfigurationException, PickedColumnOutOfBoundsException, PickDoesntFitColumnException, WrongMessageClassEnumException, InterruptedException {
        remoteUI.playTurn();
    }
    @Override
    public void endGame(String winnerPlayer) throws RemoteException {
        remoteUI.endGame(winnerPlayer);
    }
    public int getNumCurrentPlayers(int gameId) {
        //TODO no one uses socket
        return 0;
    }
    public void choosePassword(String playerId, String password) throws RemoteException, IOException, WrongMessageClassEnumException, InterruptedException, PlayerIdAlreadyInUseException {
        List<Object> parameters = new ArrayList<>();
        parameters.add(playerId);
        parameters.add(password);
        dataOutput.writeUTF(gson.toJson(new Message(MessageTypeEnum.methodCall, null, null, MethodNameEnum.choosePassword, parameters, null, null)));

        Message response = gson.fromJson(dataInput.readUTF(), Message.class);
        if(response.getType().equals(MessageTypeEnum.exception) && response.getException().equals(ExceptionEnum.PlayerIdAlreadyInUseException))
            throw new PlayerIdAlreadyInUseException();

    }
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
