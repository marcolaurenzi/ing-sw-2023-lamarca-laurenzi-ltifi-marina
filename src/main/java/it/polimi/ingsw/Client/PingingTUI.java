package it.polimi.ingsw.Client;

import java.io.IOException;
import java.rmi.RemoteException;

public class PingingTUI extends Thread{
    private final TUI tui;
    private boolean running = true;

    public PingingTUI(TUI tui) {
        this.tui = tui;
    }

    public void run() {
        while (running) {
            try {
                tui.client.ping();
            } catch (RemoteException e) {
                System.out.println("Server crashed :( Please try restart your client and login with your credentials again");
                running = false;
            } catch (IOException e) {
                System.out.println("Server crashed :( Please try restart your client and login with your credentials again");
                running = false;
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void stopThread() {
        running = false;
    }
}
