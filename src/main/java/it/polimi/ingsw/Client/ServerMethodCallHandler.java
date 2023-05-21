package it.polimi.ingsw.Client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.polimi.ingsw.Model.Exceptions.*;
import it.polimi.ingsw.Utils.*;
import it.polimi.ingsw.Utils.MessageEnums.ExceptionEnum;
import it.polimi.ingsw.Utils.MessageEnums.MessageTypeEnum;

import java.io.IOException;
import java.rmi.RemoteException;

public class ServerMethodCallHandler extends Thread {
    private final ClientSocket clientSocket;
    private final ProxyDataOutputStream dataOutput;
    private final ProxyDataInputStream dataInput;
    private final Gson gson = new Gson();
    public ServerMethodCallHandler(MessageDispatcher messageDispatcher, ClientSocket clientSocket) {
        this.clientSocket = clientSocket;
        dataInput = new ProxyDataInputStream(messageDispatcher, MessageClassEnum.methodCall);
        dataOutput = new ProxyDataOutputStream(messageDispatcher);
    }

    private void update(GameStatus gameStatus) throws IOException {
        clientSocket.update(gameStatus);
        dataOutput.writeUTF(gson.toJson(new Message(MessageTypeEnum.success, null, null, null, null,null, null)));
    }
    private void playTurn() throws IOException, WrongMessageClassEnumException, InterruptedException {
        try {
            clientSocket.playTurn();

            dataOutput.writeUTF(gson.toJson(new Message(MessageTypeEnum.success, null, null, null, null,null, null)));
        } catch (VoidBoardTileException e) {
            dataOutput.writeUTF(gson.toJson(new Message(MessageTypeEnum.exception, ExceptionEnum.VoidBoardTileException, null, null, null,null, null)));
        } catch (SelectionNotValidException e) {
            dataOutput.writeUTF(gson.toJson(new Message(MessageTypeEnum.exception, ExceptionEnum.SelectionNotValidException, null, null, null, null, null)));
        } catch (PlayerIsWaitingException e) {
            dataOutput.writeUTF(gson.toJson(new Message(MessageTypeEnum.exception, ExceptionEnum.PlayerIsWaitingException, null, null, null, null, null)));

        } catch (TilesSelectionSizeDifferentFromOrderLengthException e) {
            dataOutput.writeUTF(gson.toJson(new Message(MessageTypeEnum.exception, ExceptionEnum.TilesSelectionSizeDifferentFromOrderLengthException, null, null, null, null, null)));

        } catch (ColumnNotValidException e ) {
            dataOutput.writeUTF(gson.toJson(new Message(MessageTypeEnum.exception, ExceptionEnum.ColumnNotValidException, null, null, null, null, null)));

        } catch (SelectionIsEmptyException e ) {
            dataOutput.writeUTF(gson.toJson(new Message(MessageTypeEnum.exception, ExceptionEnum.SelectionIsEmptyException, null, null, null, null, null)));

        } catch (WrongConfigurationException e ) {
            dataOutput.writeUTF(gson.toJson(new Message(MessageTypeEnum.exception, ExceptionEnum.WrongConfigurationException, null, null, null, null, null)));

        } catch (PickedColumnOutOfBoundsException e) {
            dataOutput.writeUTF(gson.toJson(new Message(MessageTypeEnum.exception, ExceptionEnum.PickedColumnOutOfBoundsException, null, null, null, null, null)));

        } catch (PickDoesntFitColumnException e ) {
            dataOutput.writeUTF(gson.toJson(new Message(MessageTypeEnum.exception, ExceptionEnum.PickDoesntFitColumnException, null, null, null, null, null)));
        }
    }
    private void endGame(String winnerPlayer) throws IOException {
        clientSocket.endGame(winnerPlayer);
        dataOutput.writeUTF(gson.toJson(new Message(MessageTypeEnum.success, null, null, null, null,null, null)));
    }
    public void run() {

        while(true) {
            try {
                Message received = gson.fromJson(dataInput.readUTF(), Message.class);
                System.out.println(received);
                switch (received.getMethod()) {
                    case update -> update(received.getGameStatusParam());
                    case playTurn -> playTurn();
                    case endGame -> endGame((String)received.getParameters().get(0));
                }

            } catch (Exception e) {
                System.out.println("Exception catch in ClientHandler" + e);
                e.printStackTrace();
                return;
            }
        }
    }
}
