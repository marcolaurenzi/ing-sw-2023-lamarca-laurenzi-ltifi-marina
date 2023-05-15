package it.polimi.ingsw.Utils;

import com.google.gson.Gson;
import it.polimi.ingsw.Model.Exceptions.WrongMessageClassEnumException;
import it.polimi.ingsw.Utils.MessageEnums.MessageTypeEnum;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MessageDispatcher extends Thread{
    //TODO good synchronization
    private final Gson gson = new Gson();
    private final DataInputStream inputStream;
    private final DataOutputStream outputStream;
    private final BlockingQueue<String> methodCallsQueue = new LinkedBlockingQueue<>();
    private final BlockingQueue<String> responsesQueue = new LinkedBlockingQueue<>();

    public MessageDispatcher(Socket socket) throws IOException {
        this.inputStream = new DataInputStream(socket.getInputStream());
        this.outputStream = new DataOutputStream(socket.getOutputStream());
    }

    public void run() {
        while(true) {
            try {
                String message = inputStream.readUTF();
                System.out.println(message);
                if(gson.fromJson(message, Message.class).getType().equals(MessageTypeEnum.methodCall))
                    methodCallsQueue.put(message);
                else
                    responsesQueue.put(message);
            } catch (Exception e) {
                System.out.println("Exception found in MessageDispatcher: " + e);
                e.printStackTrace();
                System.exit(-1);
            }

        }
    }
    public void send(String message) throws IOException {
        synchronized(outputStream) {
            outputStream.writeUTF(message);
        }
    }

    public String receive(MessageClassEnum messageClassEnum) throws InterruptedException, WrongMessageClassEnumException {
            switch (messageClassEnum) {

                case methodCall -> {
                    return methodCallsQueue.take();
                }

                case response -> {
                    return responsesQueue.take();
                }

                default -> throw new WrongMessageClassEnumException();

            }
        }
}
