package it.polimi.ingsw.Utils;

import java.io.IOException;

/**
 * The ProxyDataOutputStream class is responsible for writing data to an output stream
 * using a message dispatcher.
 */
public class ProxyDataOutputStream {
    /**
     * The message dispatcher used to send messages.
     */
    private final MessageDispatcher messageDispatcher;

    /**
     * Constructs a new ProxyDataOutputStream object with the specified message dispatcher.
     *
     * @param messageDispatcher the message dispatcher to use for sending messages
     */
    public ProxyDataOutputStream(MessageDispatcher messageDispatcher) {
        this.messageDispatcher = messageDispatcher;
    }

    /**
     * Writes a string message to the output stream.
     *
     * @param message the string message to write
     * @throws IOException if an I/O error occurs while sending the message
     */
    public void writeUTF(String message) throws IOException {
        messageDispatcher.send(message);
    }
}
