package it.polimi.ingsw.Server;

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
        while(true){
            try{
                Playerid = dis.readUTF();
                System.out.println("I've received the id: " + Playerid);
                twoRet = "Server has received your id: " + Playerid;
                dos.writeUTF(twoRet);

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
                }else {
                    System.out.println("DIomascio");
                }

                received = dis.readUTF();
                System.out.println("I've received from " + Playerid + ": " + received);
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
