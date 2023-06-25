package it.polimi.ingsw.Utils;

import com.google.gson.Gson;
import it.polimi.ingsw.Model.Exceptions.MissingPlayerException;
import it.polimi.ingsw.Model.Exceptions.WrongMessageClassEnumException;
import it.polimi.ingsw.Server.Server;
import it.polimi.ingsw.Utils.MessageEnums.ExceptionEnum;
import it.polimi.ingsw.Utils.MessageEnums.MessageTypeEnum;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * The MessageDispatcher class handles the communication between the server and the client.
 * It manages the input and output streams and queues for method calls and responses.
 */
public class MessageDispatcher extends Thread {
    private final Gson gson = new Gson();
    private String playerId;
    private final DataInputStream inputStream;
    private final DataOutputStream outputStream;
    private final BlockingQueue<String> methodCallsQueue = new LinkedBlockingQueue<>();
    private final BlockingQueue<String> responsesQueue = new LinkedBlockingQueue<>();

    /**
     * Constructs a MessageDispatcher object with the specified socket.
     *
     * @param socket the socket for communication with the client
     * @throws IOException if an I/O error occurs when creating the input and output streams
     */
    public MessageDispatcher(Socket socket) throws IOException {
        this.inputStream = new DataInputStream(socket.getInputStream());
        this.outputStream = new DataOutputStream(socket.getOutputStream());
    }

    /**
     * Sets the player ID associated with the MessageDispatcher.
     *
     * @param playerId the player ID
     */
    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    /**
     * Runs the MessageDispatcher thread, continuously reading messages from the input stream
     * and putting them into the appropriate queues.
     */
    public void run() {
        boolean connected = true;
        while (connected) {
            try {
                String message = inputStream.readUTF();
                System.out.println(message);
                if (gson.fromJson(message, Message.class).getType().equals(MessageTypeEnum.methodCall))
                    methodCallsQueue.put(message);
                else
                    responsesQueue.put(message);
            } catch (EOFException e) {
                try {
                    responsesQueue.put(gson.toJson(new Message(MessageTypeEnum.exception, ExceptionEnum.DisconnectedPlayerException, null, null, null, null, null)));
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                connected = false;
            } catch (Exception e) {
                System.out.println("Exception found in MessageDispatcher: " + e);
                e.printStackTrace();
                System.exit(-1);
            }
        }
    }

    /**
     * Sends a message to the client.
     *
     * @param message the message to send
     * @throws IOException if an I/O error occurs when writing the message to the output stream
     */
    public void send(String message) throws IOException {
        synchronized (outputStream) {
            outputStream.writeUTF(message);
        }
    }

    /**
     * Receives a message of the specified message class from the appropriate queue.
     *
     * @param messageClassEnum the message class enum
     * @return the received message
     * @throws InterruptedException             if the thread is interrupted while waiting for the message
     * @throws WrongMessageClassEnumException    if the specified message class enum is invalid
     */
    public String receive(MessageClassEnum messageClassEnum) throws InterruptedException, WrongMessageClassEnumException {
        return switch (messageClassEnum) {
            case methodCall -> methodCallsQueue.take();
            case response -> responsesQueue.take();
            default -> throw new WrongMessageClassEnumException();
        };
    }
}
