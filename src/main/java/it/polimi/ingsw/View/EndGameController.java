package it.polimi.ingsw.View;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class EndGameController {

    @FXML
    private AnchorPane endGameAnchorPane;

    @FXML
    private Label winnerLabel;
    public void endGame(String winnerPlayer) {
        printWinner(winnerPlayer);
    }

    public void printWinner(String winnerPlayer) {
        winnerLabel.setText("The winner is " + winnerPlayer);
    }
}
