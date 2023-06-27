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

/**
 * The server class for the game.
 */
public class Server {
    /**
     * Constructs a Server object.
     */
    public Server() {

    }

    /**
     * The controller instance for managing the game logic.
     */
    public static Controller controller;

    /**
     * Handles the exit routine for the server, deleting saved games if they exist and exiting the program.
     */
    private static void exit_routine() {
        Path directoryPath = Paths.get("SavedGames");

        if (Files.exists(directoryPath)) {
            try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(directoryPath)) {
                for (Path path : directoryStream) {
                    Files.delete(path);
                }
                Files.delete(directoryPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.exit(0);
    }

    /**
     * The main method for starting the server.
     *
     * @param args command line arguments
     * @throws IOException          if an I/O error occurs when creating the ServerSocket
     * @throws AlreadyBoundException if the registry is already bound to the specified name
     */
    public static void main(String[] args) throws IOException, AlreadyBoundException {
        System.setProperty("java.rmi.server.hostname", "169.254.18.181" /*"localhost" */);
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