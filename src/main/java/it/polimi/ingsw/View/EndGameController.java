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

/**
 * Controller class for the end game screen.
 */
public class EndGameController {

    @FXML
    private AnchorPane endGameAnchorPane;

    @FXML
    private Label winnerLabel;

    /**
     * Displays the end game screen with the winner's name.
     *
     * @param winnerPlayer the name of the winning player
     */
    public void endGame(String winnerPlayer) {
        printWinner(winnerPlayer);
    }

    /**
     * Prints the name of the winner on the screen.
     *
     * @param winnerPlayer the name of the winning player
     */
    public void printWinner(String winnerPlayer) {
        winnerLabel.setText("The winner is " + winnerPlayer);
    }
}
