package it.polimi.ingsw.View;

import javafx.application.Application;
import javafx.stage.Stage;

public class GamePageController implements ViewController{
    private final LoginController loginController = new LoginController();

    private static String username;

    private static GUI gui;

    public void onClick() {
        username = loginController.getUsername();
        System.out.println("Username: " + username);
    }
}
