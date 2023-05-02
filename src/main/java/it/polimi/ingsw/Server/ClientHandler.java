package it.polimi.ingsw.Server;

import com.google.gson.Gson;
import it.polimi.ingsw.Controller.Observer;
import it.polimi.ingsw.Model.Exceptions.*;
import it.polimi.ingsw.Utils.Message;
import it.polimi.ingsw.Utils.MessageEnums.ExceptionEnum;
import it.polimi.ingsw.Utils.MessageEnums.MessageTypeEnum;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler extends Thread {

    final Socket socket;
    final DataInputStream socketDataInput;
    final DataOutputStream socketDataOutput;
    final Gson gson;

    public ClientHandler(Socket socket, DataInputStream socketDataInput, DataOutputStream socketDataOutput){
        this.socket = socket;
        this.socketDataInput = socketDataInput;
        this.socketDataOutput = socketDataOutput;
        this.gson = new Gson();
    }

    private void choosePlayerId(String playerId) throws IOException {
        Message toSend;
        try {

            Server.controller.choosePlayerId(playerId);
            toSend = new Message(MessageTypeEnum.success, null, null, null, null);
            socketDataOutput.writeUTF(gson.toJson(toSend));

        } catch (PlayerIdAlreadyInUseException e) {

            toSend = new Message(MessageTypeEnum.exception, ExceptionEnum.PlayerIdAlreadyInUseException, null, null, null);
            socketDataOutput.writeUTF(gson.toJson(toSend));
        }
    }
    private void addPlayerToCreatedGame(String playerId) throws IOException {
        Message toSend;
        try {

            int ret = Server.controller.addPlayerToCreatedGame(playerId);
            toSend = new Message(MessageTypeEnum.returnValue, null, ret, null, null);
            socketDataOutput.writeUTF(gson.toJson(toSend));

        } catch (CreateNewGameException e) {

            toSend = new Message(MessageTypeEnum.exception, ExceptionEnum.CreateNewGameException, null, null, null);
            socketDataOutput.writeUTF(gson.toJson(toSend));
        } catch (AlreadyStartedGameException e) {
            toSend = new Message(MessageTypeEnum.exception, ExceptionEnum.AlreadyStartedGameException, null, null, null);
            socketDataOutput.writeUTF(gson.toJson(toSend));
        }
    }
    private void createNewGameAndAddPlayer(String playerId, int maxPlayers) throws IOException {
        Message toSend;
        try {

            int ret = Server.controller.createNewGameAndAddPlayer(playerId, maxPlayers);
            toSend = new Message(MessageTypeEnum.returnValue, null, ret, null, null);
            socketDataOutput.writeUTF(gson.toJson(toSend));

        } catch (MaxNumberOfPlayersException e) {
            toSend = new Message(MessageTypeEnum.exception, ExceptionEnum.MaxNumberOfPlayersException, null, null, null);
            socketDataOutput.writeUTF(gson.toJson(toSend));
        } catch (AlreadyStartedGameException e) {
            toSend = new Message(MessageTypeEnum.exception, ExceptionEnum.AlreadyStartedGameException, null, null, null);
            socketDataOutput.writeUTF(gson.toJson(toSend));
        } catch (GameAlreadyCreatedException e) {
            toSend = new Message(MessageTypeEnum.exception, ExceptionEnum.GameAlreadyCreatedException, null, null, null);
            socketDataOutput.writeUTF(gson.toJson(toSend));
        }
    }
    private void addObserver(String playerId, Observer observer) throws IOException {
        Message toSend;

        Server.controller.addObserver(observer, playerId);
        toSend = new Message(MessageTypeEnum.success, null, null, null, null);
        socketDataOutput.writeUTF(gson.toJson(toSend));
    }
    public void run() {

        while(true) {
            try {
                Message received = gson.fromJson(socketDataInput.readUTF(), Message.class);
                switch (received.getMethod()) {
                    case choosePlayerId -> choosePlayerId((String)received.getParameters().get(0));
                    case addPlayerToCreatedGame -> addPlayerToCreatedGame((String)received.getParameters().get(0));
                    //IMPORTANT gson sees all numbers as Double, so I have to cast it to Double and then use intValue()
                    case createNewGameAndAddPlayer -> createNewGameAndAddPlayer((String)received.getParameters().get(0), ((Double)received.getParameters().get(1)).intValue());
                    case addObserver -> addObserver((String)received.getParameters().get(0), ((Observer)received.getParameters().get(1)));
                }

            } catch (IOException e) {
                return;
            }
        }
    }
}
