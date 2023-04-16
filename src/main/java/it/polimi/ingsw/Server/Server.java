package it.polimi.ingsw.Server;

import it.polimi.ingsw.Model.Game;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    static List<Game> games = new ArrayList<Game>();
    static Game currentGame = null;
    static Integer currentGameId = 0;
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(59090);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //statement that listens for incoming connections
        while (true) {
            Socket s = null;
            try {
                s = serverSocket.accept();
                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                System.out.println("New client connected");
                System.out.println("Assign new thread for this client");

                //call a new thread whenever a new client connects
                Thread t = new ClientHandler(s, dis, dos);
                t.start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}