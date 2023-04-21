package it.polimi.ingsw.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Client {

    static NetworkHandler networkHandler = new NetworkHandler();
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        networkHandler.createSocket();

        while(true) {

            String received = networkHandler.receiveFromServer();
            System.out.println(received);

            switch (received) {
                case "Insert username: ": {
                    sendUsername();
                    break;
                }
                case "Server has created a new game, insert number of players: ": {
                    sendNumberOfPlayers();
                    break;
                }
            }
        }

    }

    /**
     * This method sends the username to the server
     * @throws IOException if the username is already in use
     */
    public static void sendUsername() throws IOException {

        String playerId = reader.readLine();
        networkHandler.sendToServer(playerId);

        String received = networkHandler.receiveFromServer();

        while (received.equals("Username already in use")) {

            System.out.println("Username already in use, insert username: ");
            playerId = reader.readLine();
            networkHandler.sendToServer(playerId);

        }

        // availability message
        System.err.println(received);

    }


    /**
     * This method sends the number of players to the server
     * @throws IOException if the number of players is not valid
     */
    private static void sendNumberOfPlayers() throws IOException {

        Integer numberOfPlayers = Integer.parseInt(reader.readLine());
        networkHandler.sendToServer(numberOfPlayers.toString());

        // ricevo valid
        String received = networkHandler.receiveFromServer();

        while (received.equals("Invalid number of players, please insert a number between 2 and 4")) {
            System.out.println(received);
            numberOfPlayers = Integer.parseInt(reader.readLine());
            networkHandler.sendToServer(numberOfPlayers.toString());
            received = networkHandler.receiveFromServer();
        }

        // validity message
        System.err.println(received);

    }


}
