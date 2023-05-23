package it.polimi.ingsw.Server;

import java.net.Socket;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.*;
import java.rmi.registry.*;

import it.polimi.ingsw.Controller.Controller;
import it.polimi.ingsw.Utils.MessageDispatcher;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;


public class Server {
    public static Controller controller;

    private static void exit_routine() {
        Path directoryPath = Paths.get("SavedGames");

        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(directoryPath)) {
            for (Path path : directoryStream) {
                Files.delete(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.exit(0);
    }
    public static void main(String[] args) throws IOException, AlreadyBoundException {
        Scanner scanner = new Scanner(System.in);

        controller = new Controller();

        Registry registry = LocateRegistry.createRegistry(1099);
        registry.bind("controller", controller);

        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(59090);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // socket connections handling thread
        new Thread(() -> {
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
        }).start();


        System.out.println("Press enter to end the server");
        scanner.nextLine();
        exit_routine();
    }
}