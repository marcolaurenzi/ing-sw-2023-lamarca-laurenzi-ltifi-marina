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

public class ClientMethodCallHandler extends Thread {

    private final MessageDispatcher messageDispatcher;
    private final ProxyDataInputStream dataInput;
    private final ProxyDataOutputStream dataOutput;
    private final Gson gson = new Gson();;

    public ClientMethodCallHandler(MessageDispatcher messageDispatcher){
        this.messageDispatcher = messageDispatcher;
        this.dataInput = new ProxyDataInputStream(messageDispatcher, MessageClassEnum.methodCall);
        this.dataOutput = new ProxyDataOutputStream(messageDispatcher);
        messageDispatcher.start();
    }

    private void choosePlayerId(String playerId) throws IOException {
        Message toSend;
        try {

            Server.controller.choosePlayerId(playerId);
            toSend = new Message(MessageTypeEnum.success, null, null, null, null, null, null);
            dataOutput.writeUTF(gson.toJson(toSend));

        } catch (PlayerIdAlreadyInUseException e) {

            toSend = new Message(MessageTypeEnum.exception, ExceptionEnum.PlayerIdAlreadyInUseException, null, null, null, null, null);
            dataOutput.writeUTF(gson.toJson(toSend));
        }
    }
    private void addPlayerToCreatedGame(String playerId) throws IOException {
        Message toSend;
        try {

            int ret = Server.controller.addPlayerToCreatedGame(playerId);
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
    private void addObserver(String playerId) throws IOException {
        Message toSend;

        Server.controller.addObserver(new ObserverSocket(new ProxyDataInputStream(messageDispatcher, MessageClassEnum.response), new ProxyDataOutputStream(messageDispatcher)), playerId);
        toSend = new Message(MessageTypeEnum.success, null, null, null, null, null, null);
        dataOutput.writeUTF(gson.toJson(toSend));
    }

    private void pickAndInsertInBookshelf(ArrayList<Coordinates> tilesSelection, int column, int[] order, String playerId) throws IOException {
        Message toSend;


        try {
            Server.controller.pickAndInsertInBookshelf(tilesSelection, column, order, playerId);

            toSend = new Message(MessageTypeEnum.success, null, null,null, null, null, null);
            dataOutput.writeUTF(gson.toJson(toSend));
        } catch (SelectionNotValidException e) {
            toSend = new Message(MessageTypeEnum.exception, ExceptionEnum.MaxNumberOfPlayersException, null, null, null, null, null);
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
    public void run() {

        while(true) {
            try {
                Message received = gson.fromJson(dataInput.readUTF(), Message.class);
                switch (received.getMethod()) {
                    case choosePlayerId -> choosePlayerId((String)received.getParameters().get(0));
                    case addPlayerToCreatedGame -> addPlayerToCreatedGame((String)received.getParameters().get(0));
                    //IMPORTANT gson sees all numbers as Double, so I have to cast it to Double and then use intValue()
                    case createNewGameAndAddPlayer -> createNewGameAndAddPlayer((String)received.getParameters().get(0), ((Double)received.getParameters().get(1)).intValue());
                    case addObserver -> addObserver((String)received.getParameters().get(0));
                    case pickAndInsertInBookshelf -> {

                        //TODO change the order thing
                        ArrayList<Coordinates> selection = received.getSelectionParam();
                        int[] order = new int[selection.size()];
                        for(int i = 0; i < order.length; i++) {
                            order[i] = i;
                        }

                        pickAndInsertInBookshelf(received.getSelectionParam(), ((Double)received.getParameters().get(0)).intValue(), order, (String)received.getParameters().get(1));
                    }
                }

            } catch (Exception e) {
                System.out.println("Exception catch in ClientHandler" + e);
                e.printStackTrace();
                return;
            }
        }
    }
}
