package it.polimi.ingsw.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client2 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        try {
            Socket socket = new Socket("localhost", 59090);
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            System.out.println("Please enter your username: ");
            String username = scn.nextLine();
            dos.writeUTF(username);


            while(true) {
                System.out.println(dis.readUTF());
                String serverResponse = dis.readUTF();
                if(serverResponse.equals("Server has created a new game, please insert the number of players: ")) {
                    System.out.println(serverResponse);
                    String numPlayers = scn.nextLine();
                    while(Integer.parseInt(numPlayers) > 4 || Integer.parseInt(numPlayers) < 2){
                        System.out.println("Please insert a number between 2 and 4");
                        numPlayers = scn.nextLine();
                    }
                    dos.writeUTF(numPlayers);
                }
                String twoSend = scn.nextLine();
                dos.writeUTF(twoSend);
                if(twoSend.equals("Exit")) {
                    socket.close();
                    break;
                }
                String received = dis.readUTF();
                System.out.println(received);
            }
            scn.close();
            dis.close();
            dos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
