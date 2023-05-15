package it.polimi.ingsw.Server;

import java.net.Socket;
import java.rmi.*;
import java.rmi.registry.*;

import it.polimi.ingsw.Controller.Controller;
import it.polimi.ingsw.Utils.MessageDispatcher;

import java.io.IOException;
import java.net.ServerSocket;


public class Server {
    public static Controller controller;
    public static void main(String[] args) throws IOException, AlreadyBoundException {
        controller = new Controller();

        Registry registry = LocateRegistry.createRegistry(1099);
        registry.bind("controller", controller);

        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(59090);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        while (true) {
            Socket s = null;
            try {
                s = serverSocket.accept();
                System.out.println("New client connected");
                System.out.println("Assign new thread for this client");

                //call a new thread whenever a new client connects
                ClientMethodCallHandler t = new ClientMethodCallHandler(new MessageDispatcher(s));
                t.start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}