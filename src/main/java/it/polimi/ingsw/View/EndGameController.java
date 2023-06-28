package it.polimi.ingsw.View;

import it.polimi.ingsw.Controller.Observer;
import it.polimi.ingsw.Model.Player;
import it.polimi.ingsw.Utils.GameStatusToSend;
import it.polimi.ingsw.Utils.Utils;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.util.*;

/**
 * Controller class for the end game screen.
 */
public class EndGameController {

    private Stage currentStage;

    private Scene currentScene;

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

    private HashMap<String, Integer> playerPoints;

    public HashMap<String, Integer> getPointsMap() {
        return playerPoints;
    }

    public void setPlayerPoints(HashMap<String, Integer> playerPoints) {
        this.playerPoints = playerPoints;
    }

    private HashMap<Integer, String> playersId;

    public HashMap<Integer, String> getPlayersId() {
        return playersId;
    }

    public void setPlayersId(HashMap<Integer, String> players) {
        this.playersId = players;
    }

    public void initialize() {

    }

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
        Platform.runLater(()-> {
            Label winnerLabel = currentScene.lookup("#winnerLabel") != null ? (Label) currentScene.lookup("#winnerLabel") : new Label();
            winnerLabel.setText("The winner is: \"" + winnerPlayer + "\"" );
            winnerLabel.setFont(javafx.scene.text.Font.font("Arial Bold", 30));
        });
    }

    public void setCurrentStage(Stage currentStage) {
        this.currentStage = currentStage;
    }

    public void printPlayerInfo() {

        Platform.runLater(()-> {
            Label rankingLabel = currentScene.lookup("#rankingLabel") != null ? (Label) currentScene.lookup("#rankingLabel") : new Label();
            StringBuilder ranking = new StringBuilder();
            List<Map.Entry<String, Integer>> list = new ArrayList<>(playerPoints.entrySet());

            // Sort the list based on the Integer values in descending order
            Collections.sort(list, Comparator.comparing(Map.Entry<String, Integer>::getValue).reversed());

            ranking.append("Classifica finale:\n");
            for (Map.Entry<String, Integer> entry : list) {
                ranking.append(entry.getKey()).append(": ").append(entry.getValue()).append(" points").append("\n");
            }

            rankingLabel.setFont(javafx.scene.text.Font.font("Arial Bold", 30));
            rankingLabel.setText(ranking.toString());
            double x = currentScene.lookup("#winnerLabel").getLayoutX();
            rankingLabel.setLayoutX(x);
            double y = currentScene.lookup("#winnerLabel").getLayoutY() + 50;
            rankingLabel.setLayoutY(y);

        });
    }

    public void setScene(Scene scene) {
        currentScene = scene;
    }
}
