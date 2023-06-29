package it.polimi.ingsw.View;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.util.*;

/**
 * Controller class for the end game screen.
 */
public class EndGameController {

    /**
     * The current stage.
     */
    private Stage currentStage;

    /**
     * The current scene.
     */
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

    /**
     * The map containing the points of each player.
     */
    private HashMap<String, Integer> playerPoints;

    /**
     * Sets player's points.
     *
     * @param playerPoints the map containing the points of each player
     */
    public void setPlayerPoints(HashMap<String, Integer> playerPoints) {
        this.playerPoints = playerPoints;
    }

    /**
     * Initializes the scene.
     */
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

    /**
     * Sets the current stage.
     * @param currentStage the current stage
     */
    public void setCurrentStage(Stage currentStage) {
        this.currentStage = currentStage;
    }

    /**
     * Prints the ranking of the players on the screen.
     */

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

    /**
     * Sets the current scene.
     * @param scene the current scene
     */
    public void setScene(Scene scene) {
        currentScene = scene;
    }
}
