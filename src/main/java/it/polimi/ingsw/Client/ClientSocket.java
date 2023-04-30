package it.polimi.ingsw.Client;

import com.google.gson.Gson;
import it.polimi.ingsw.Model.Coordinates;
import it.polimi.ingsw.Model.Exceptions.*;
import it.polimi.ingsw.Utils.Message;
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

public class ClientSocket implements Client{
    Socket socket;
    DataInputStream socketDataInput;
    DataOutputStream socketDataOutput;
    Gson gson;
    public ClientSocket() throws IOException {
        socket = new Socket("localhost", 59090);
        socketDataInput = new DataInputStream(socket.getInputStream());
        socketDataOutput = new DataOutputStream(socket.getOutputStream());
        gson = new Gson();
    }
    @Override
    public void choosePlayerId(String playerId) throws PlayerIdAlreadyInUseException, IOException {
        List<Object> parameters = new ArrayList<>();
        parameters.add(playerId);

        socketDataOutput.writeUTF(gson.toJson(new Message(MessageTypeEnum.methodCall, null, null, MethodNameEnum.choosePlayerId, parameters)));
        Message response = gson.fromJson(socketDataInput.readUTF(), Message.class);
        
        if(response.getType().equals(MessageTypeEnum.exception) && response.getException().equals(ExceptionEnum.PlayerIdAlreadyInUseException))
            throw new PlayerIdAlreadyInUseException();
    }

    @Override
    public int addPlayerToCreatedGame(String playerId) throws AlreadyStartedGameException, CreateNewGameException, IOException {
        //sending message to server
        List<Object> parameters = new ArrayList<>();
        parameters.add(playerId);
        socketDataOutput.writeUTF(gson.toJson(new Message(MessageTypeEnum.methodCall, null, null, MethodNameEnum.addPlayerToCreatedGame, parameters)));
        Message response = gson.fromJson(socketDataInput.readUTF(), Message.class);

        //reading server response
        //sending exceptions
        if(response.getType().equals(MessageTypeEnum.exception)) {
            switch (response.getException()) {
                case AlreadyStartedGameException -> throw new AlreadyStartedGameException();
                case CreateNewGameException -> throw new CreateNewGameException();
            }
        }

        // todo migliorare il cast
        return ((Double)response.getReturnValue()).intValue();
    }

    @Override
    public int createNewGameAndAddPlayer(String playerId, int maxPlayers) throws MaxNumberOfPlayersException, GameAlreadyCreatedException, AlreadyStartedGameException, IOException {
        //sending message to server
        List<Object> parameters = new ArrayList<>();
        parameters.add(playerId);
        parameters.add(maxPlayers);
        socketDataOutput.writeUTF(gson.toJson(new Message(MessageTypeEnum.methodCall, null, null, MethodNameEnum.createNewGameAndAddPlayer, parameters)));
        Message response = gson.fromJson(socketDataInput.readUTF(), Message.class);

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
    public void addObserver(RemoteObserver observer, String playerId) throws IOException {
        List<Object> parameters = new ArrayList<>();
        parameters.add(observer);
        parameters.add(playerId);
        socketDataOutput.writeUTF(gson.toJson(new Message(MessageTypeEnum.methodCall, null, null, MethodNameEnum.addObserver, parameters)));
    }

    @Override
    public void pickAndInsertInBookshelf(ArrayList<Coordinates> tilesSelection, int column, int[] order, String playerId) throws PlayerIsWaitingException, SelectionIsEmptyException, SelectionNotValidException, ColumnNotValidException, PickedColumnOutOfBoundsException, PickDoesntFitColumnException, TilesSelectionSizeDifferentFromOrderLengthException, VoidBoardTileException, WrongConfigurationException, RemoteException {
        //TODO no one uses socket
    }
    public void riempiTutto() {
        //TODO no one uses socket
    }
}
