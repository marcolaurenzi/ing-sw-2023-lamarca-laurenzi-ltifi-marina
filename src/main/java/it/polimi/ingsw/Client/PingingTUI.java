package it.polimi.ingsw.Client;

import java.io.IOException;
import java.rmi.RemoteException;

/**
 * The PingingTUI class represents a thread that pings the server every 5 seconds.
 */
public class PingingTUI extends Thread{
    private final TUI tui;
    private boolean running = true;

    /**
     * Initializes a new instance of the PingingTUI class with the specified TUI.
     *
     * @param tui The TUI.
     */
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

}
