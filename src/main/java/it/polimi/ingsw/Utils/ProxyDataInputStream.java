package it.polimi.ingsw.Utils;

import it.polimi.ingsw.Model.Exceptions.WrongMessageClassEnumException;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.InputStream;

public class ProxyDataInputStream {
    private final MessageDispatcher messageDispatcher;
    private final MessageClassEnum messageClass;

    public ProxyDataInputStream(MessageDispatcher messageDispatcher, MessageClassEnum messageClass) {
        this.messageDispatcher = messageDispatcher;
        this.messageClass = messageClass;
    }

    public String readUTF() throws InterruptedException, WrongMessageClassEnumException {
        return messageDispatcher.receive(messageClass);
    }
}
