package it.polimi.ingsw.Client;

import com.google.gson.Gson;
import it.polimi.ingsw.Model.Exceptions.*;
import it.polimi.ingsw.Model.Player;
import it.polimi.ingsw.Utils.*;
import it.polimi.ingsw.Utils.MessageEnums.ExceptionEnum;
import it.polimi.ingsw.Utils.MessageEnums.MessageTypeEnum;

import java.io.IOException;
import java.util.List;

/**
 *The ServerMethodCallHandler class represents a handler for a method call from the server.
 */
public class ServerMethodCallHandler extends Thread {
    /**
     * The client socket associated with the handler.
     */
    private final ClientSocket clientSocket;
    /**
     * The data output stream used to send messages.
     */
    private final ProxyDataOutputStream dataOutput;
    /**
     * The data input stream used to receive messages.
     */
    private final ProxyDataInputStream dataInput;
    /**
     * The Gson object used to parse messages.
     */
    private final Gson gson = new Gson();

    /**
     * Constructs a ServerMethodCallHandler object.
     *
     * @param messageDispatcher The message dispatcher used to send and receive messages.
     * @param clientSocket      The client socket associated with the handler.
     */
    public ServerMethodCallHandler(MessageDispatcher messageDispatcher, ClientSocket clientSocket) {
        this.clientSocket = clientSocket;
        dataInput = new ProxyDataInputStream(messageDispatcher, MessageClassEnum.methodCall);
        dataOutput = new ProxyDataOutputStream(messageDispatcher);
    }

    /**
     * Sends the updated game status to the client and acknowledges the success.
     *
     * @param gameStatus The updated game status to be sent.
     * @throws IOException If an I/O error occurs.
     */
    private void update(GameStatusToSend gameStatus) throws IOException {
        clientSocket.update(gameStatus);
        dataOutput.writeUTF(gson.toJson(new Message(MessageTypeEnum.success, null, null, null, null,null, null)));
    }

    /**
     * Executes the client's turn and acknowledges the success or handles any exception that may occur.
     *
     * @throws IOException                       If an I/O error occurs.
     * @throws WrongMessageClassEnumException     If the received message has the wrong message class enum.
     * @throws InterruptedException              If the thread is interrupted while waiting.
     */
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

    /**
     * Ends the game and sends the winner's name to the client, acknowledging the success.
     *
     * @param winnerPlayer The name of the winner player.
     * @throws IOException If an I/O error occurs.
     */
    private void endGame(String winnerPlayer) throws IOException {
        clientSocket.endGame(winnerPlayer);
        dataOutput.writeUTF(gson.toJson(new Message(MessageTypeEnum.success, null, null, null, null,null, null)));
    }

    /**
     * The main execution method of the thread.
     * It continuously listens for messages from the client and performs corresponding actions.
     * If any exception occurs during the execution, it will be caught and printed.
     */
    public void run() {
        while(true) {
            try {
                Message received = gson.fromJson(dataInput.readUTF(), Message.class);
                switch (received.getMethod()) {
                    case update -> update(received.getGameStatusParam());
                    case playTurn -> playTurn();
                    case endGame -> endGame((String)received.getParameters().get(0));
                }
            } catch (IOException e) {
                System.out.println("server crashed");
            } catch (Exception e) {
                System.out.println("Exception catch in ClientHandler" + e);
                e.printStackTrace();
                return;
            }
        }
    }
}
