package it.polimi.ingsw.Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class NetworkHandler {
    private static Socket socket;
    private static DataInputStream socketInput;
    private static DataOutputStream socketOutput;

    public void createSocket() throws IOException {
        socket = new Socket("localhost", 59090);
        socketInput = new DataInputStream(socket.getInputStream());
        socketOutput = new DataOutputStream(socket.getOutputStream());
    }

    public void sendToServer(String message) throws IOException {
        socketOutput.writeUTF(message);
    }

    public String receiveFromServer() throws IOException {
        return socketInput.readUTF();
    }
}
