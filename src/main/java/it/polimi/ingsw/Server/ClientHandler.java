package it.polimi.ingsw.Server;

import it.polimi.ingsw.Model.Exceptions.AlreadyStartedGameException;
import it.polimi.ingsw.Model.Exceptions.MaxNumberOfPlayersException;
import it.polimi.ingsw.Model.Game;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler extends Thread {
    final Socket socket;
    static Object currentGameLock = new Object();
    private String playerId;
    final DataInputStream socketInput;
    final DataOutputStream socketOutput;
    private Game game;
    public ClientHandler(Socket socket, DataInputStream socketInput, DataOutputStream socketOutput){
        this.socket = socket;
        this.socketInput = socketInput;
        this.socketOutput = socketOutput;
    }
    public void run() {

        //asking the user's id
        try {

            askForUsername();

            synchronized (currentGameLock) {

                //if there is no game running or all the other games are already full,
                // create a new game and ask the number of players to the first client
                if (Server.currentGame == null) {

                    int numPlayers = askForNumberOfPlayers();
                    game = new Game(Server.currentGameId.toString(), numPlayers);
                    game.initializeGame();
                    game.addPlayer(playerId);
                    Server.currentGame = game;
                    Server.currentGameId++;
                    Server.games.add(game);

                    socketOutput.writeUTF("Server has created a game" +
                            "\nGame ID: " + game.getId() +
                            "\nMax number of players: " + numPlayers +
                            "\nPlayers already in the game: " + Server.currentGame.getPlayers().size());
                } else {
                    Server.currentGame.addPlayer(playerId);
                    socketOutput.writeUTF("Server has found a game" +
                            "\nGame ID: " + Server.currentGame.getId() +
                            "\nMax number of players: " + Server.currentGame.getMaxPlayers() +
                            "\nPlayers already in the game: " + Server.currentGame.getPlayers().size());
                }
                if(Server.currentGame.getPlayers().size() == Server.currentGame.getMaxPlayers()) {
                    Server.currentGame.startGame();
                    System.out.println("Game starting...");
                    Server.currentGame = null;
                }
                else {
                    socketOutput.writeUTF("Please wait the start of the Game");
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (MaxNumberOfPlayersException e) {
            throw new RuntimeException(e);
        } catch (AlreadyStartedGameException e) {
            throw new RuntimeException(e);
        }
        //TODO: add a while loop to wait for the game to reach the max number of players and then start the game

        //this statement keeps the connection with the client alive
        while(true){
            try{
                String received = socketInput.readUTF();
                System.out.println("I've received from " + playerId + " playing in Game " + game.getId() + ": " + received);
                if(received.equals("Exit")){
                    this.socket.close();
                    break;
                }
                socketOutput.writeUTF("Server has received your message: " + received);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        try {
            this.socketInput.close();
            this.socketOutput.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String askForUsername() throws IOException {

        socketOutput.writeUTF("Insert username: ");
        playerId = socketInput.readUTF();
        System.err.println("Id received: " + playerId);

        // ack message
        //socketOutput.writeUTF("Server correctly received you id");

        while(Server.alreadyUsedPlayerIds.contains(playerId)) {

            socketOutput.writeUTF("Username already in use");
            playerId = socketInput.readUTF();
            System.err.println("Id received: " + playerId);

            // ack message
            //socketOutput.writeUTF("Server correctly received you id");
        }

        Server.alreadyUsedPlayerIds.add(playerId);

        // confirmation message
        socketOutput.writeUTF("Available username");
        return playerId;
    }


    public int askForNumberOfPlayers() throws IOException {

        socketOutput.writeUTF("Server has created a new game, insert number of players: ");
        int numPlayers = Integer.parseInt(socketInput.readUTF());
        while (numPlayers > 4 || numPlayers < 2) {
            socketOutput.writeUTF("Invalid number of players, please insert a number between 2 and 4");
            numPlayers = Integer.parseInt(socketInput.readUTF());
        }

        // confirmation message
        socketOutput.writeUTF("Valid number of players");

        return numPlayers;

    }

}
