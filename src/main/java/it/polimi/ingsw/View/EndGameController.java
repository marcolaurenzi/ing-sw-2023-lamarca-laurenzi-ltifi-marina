package it.polimi.ingsw.View;

import it.polimi.ingsw.Controller.Observer;
import it.polimi.ingsw.Model.Player;
import it.polimi.ingsw.Utils.GameStatusToSend;
import it.polimi.ingsw.Utils.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.util.*;

/**
 * Controller class for the end game screen.
 */
public class EndGameController {

    private Stage currentStage;

    /**
     * Constructs an EndGameController object.
     */
    public EndGameController() {
    }

    /**
     * The anchor pane of the scene.
     */
    @FXML
    private AnchorPane endGameAnchorPane;
    /**
     * The label for displaying the winner's name.
     */
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
        winnerLabel.setFont(javafx.scene.text.Font.font("Verdana", 20));
    }

    public void setCurrentStage(Stage currentStage) {
        this.currentStage = currentStage;
    }
}
