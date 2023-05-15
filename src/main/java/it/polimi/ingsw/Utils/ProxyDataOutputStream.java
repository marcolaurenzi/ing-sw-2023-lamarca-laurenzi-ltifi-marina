package it.polimi.ingsw.Utils;

import java.io.IOException;

public class ProxyDataOutputStream {

    private final MessageDispatcher messageDispatcher;

    public ProxyDataOutputStream(MessageDispatcher messageDispatcher) {
        this.messageDispatcher = messageDispatcher;
    }

    public void writeUTF(String message) throws IOException {
        messageDispatcher.send(message);
    }
}
