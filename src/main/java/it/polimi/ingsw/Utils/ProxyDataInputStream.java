package it.polimi.ingsw.Utils;

import it.polimi.ingsw.Model.Exceptions.WrongMessageClassEnumException;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.InputStream;

/**
 * The ProxyDataInputStream class is used to read UTF-encoded strings from a message dispatcher based on the message class.
 */
public class ProxyDataInputStream {
    /**
     * The message dispatcher used to receive messages.
     */
    private final MessageDispatcher messageDispatcher;
    /**
     * The message class to filter the received messages.
     */
    private final MessageClassEnum messageClass;

    /**
     * Constructs a ProxyDataInputStream with the specified message dispatcher and message class.
     *
     * @param messageDispatcher the message dispatcher used to receive messages
     * @param messageClass      the message class to filter the received messages
     */
    public ProxyDataInputStream(MessageDispatcher messageDispatcher, MessageClassEnum messageClass) {
        this.messageDispatcher = messageDispatcher;
        this.messageClass = messageClass;
    }

    /**
     * Reads a UTF-encoded string from the message dispatcher based on the message class.
     *
     * @return the received UTF-encoded string
     * @throws InterruptedException            if the thread is interrupted while waiting for a message
     * @throws WrongMessageClassEnumException   if the received message does not match the specified message class
     */
    public String readUTF() throws InterruptedException, WrongMessageClassEnumException {
        return messageDispatcher.receive(messageClass);
    }
}
