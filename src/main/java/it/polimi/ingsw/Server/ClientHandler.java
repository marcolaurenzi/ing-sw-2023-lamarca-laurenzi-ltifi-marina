package it.polimi.ingsw.Server;

import it.polimi.ingsw.Model.Exceptions.MaxNumberOfPlayersException;
import it.polimi.ingsw.Model.Game;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler extends Thread {
    final Socket socket;
    private String Playerid;
    final DataInputStream dis;
    final DataOutputStream dos;
    private Game Game;
    public ClientHandler(Socket socket, DataInputStream dis, DataOutputStream dos){
        this.socket = socket;
        this.dis = dis;
        this.dos = dos;
    }
    public void run() {
        String received;
        String twoRet;
        //asking the user's id
        try {
            Playerid = dis.readUTF();
            System.out.println("I've received the id: " + Playerid);
            twoRet = "Server has received your id: " + Playerid;
            dos.writeUTF(twoRet);

            //if there is no game running or all the other games are already full, create a new game and ask the number of players to the first client
            if(Server.currentGame == null){
                twoRet = "Server has created a new game, please insert the number of players: ";
                dos.writeUTF(twoRet);
                int numPlayers = Integer.parseInt(dis.readUTF());
                while(numPlayers > 4 || numPlayers < 2){
                    dos.writeUTF("Please insert a number between 2 and 4");
                    numPlayers = Integer.parseInt(dis.readUTF());
                }
                Game = new Game(Server.currentGameId.toString(), numPlayers);
                Server.currentGame = Game;
                Server.currentGameId++;
                Server.games.add(Server.currentGame);
                dos.writeUTF("Server has created a game with id: " + Server.currentGame.getId() + " and max " + numPlayers + " players, please wait for the game to start");
            }else {
                dos.writeUTF("Server has found a game with id: " + Server.currentGame.getId() + " and " + Server.currentGame.getPlayers().size() + " players, please wait for the game to start");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (MaxNumberOfPlayersException e) {
            throw new RuntimeException(e);
        }
        //TODO: add a while loop to wait for the game to reach the max number of players and then start the game

        //this statement keeps the connection with the client alive
        while(true){
            try{
                received = dis.readUTF();
                System.out.println("I've received from " + Playerid + " playing in Game " + Game.getId() + ": " + received);
                if(received.equals("Exit")){
                    this.socket.close();
                    break;
                }
                twoRet = "Server has received your message: " + received;
                dos.writeUTF(twoRet);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        try {
            this.dis.close();
            this.dos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
