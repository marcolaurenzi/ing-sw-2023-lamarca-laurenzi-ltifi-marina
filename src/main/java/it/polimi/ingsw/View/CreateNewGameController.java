package it.polimi.ingsw.View;

import it.polimi.ingsw.Model.Exceptions.AlreadyStartedGameException;
import it.polimi.ingsw.Model.Exceptions.GameAlreadyCreatedException;
import it.polimi.ingsw.Model.Exceptions.MaxNumberOfPlayersException;
import it.polimi.ingsw.Model.Exceptions.WrongMessageClassEnumException;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * This class represents the controller for creating a new game.
 * It handles the user interface interactions and game creation logic.
 */
public class CreateNewGameController implements ViewController {
    /**
     * The anchor pane of the scene.
     */
    @FXML
    private AnchorPane anchorPane;
    /**
     * The text field for entering the number of players.
     */
    private static Stage stage = null;
    /**
     * The root of the scene.
     */
    static Parent root;
    /**
     * The global counter.
     */
    private int globalCounter;


    /**
     * Initializes the controller and sets up the buttons for choosing the number of players.
     */
    public void initialize() {

        Button button2 = new Button("2");
        anchorPane.getChildren().add(button2);

        Button button3 = new Button("3");
        anchorPane.getChildren().add(button3);

        Button button4 = new Button("4");
        anchorPane.getChildren().add(button4);

        anchorPane.getChildren().forEach(node -> {
            if (node instanceof Button) {
                ((Button) node).setOnAction(event -> {
                    onClick(((Button) node).getText());
                });
            }
        });
    }

    /**
     * Handles the click event when a number of players button is clicked.
     * It creates a new game and adds the selected number of players.
     *
     * @param text the text of the button clicked
     */
    public void onClick(String text) {
        try {
            root = FXMLLoader.load(ClassLoader.getSystemResource("fxml/GamePage.fxml"));
        }catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading GamePage.fxml");
            System.exit(-1);
        }
        try {
            GUI.createNewGameAndAddPlayer(Integer.parseInt(text));
            System.out.println("Game created");
        } catch (WrongMessageClassEnumException | MaxNumberOfPlayersException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } catch (GameAlreadyCreatedException e) {
            try {
                GUI.addPlayer(GUI.getPlayerId());
            } catch (Exception f) {
                throw new RuntimeException(f);
            }
        } catch (AlreadyStartedGameException e) {
            printError("Game already created");
            try {
                Thread.sleep(5*1000);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
        finally {
            Stage currentStage = (Stage) anchorPane.getScene().getWindow();
            currentStage.close();

            Scene scene = new Scene(root);
            stage = LoginController.getStage();
            stage.setScene(scene);
            stage.setResizable(true);
            stage.show();
        }
    }

    /**
     * Displays an error message in a dialog box.
     *
     * @param string the error message to display
     */
    private void printError(String string) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setContentText(string);
    }

    /**
     * Sets the layout of the buttons based on the size of the scene.
     *
     * @param scene the scene of the application
     */
    public void setLayout(Scene scene) {
        double width = scene.lookup("#background").getLayoutBounds().getWidth();
        double height = scene.lookup("#background").getLayoutBounds().getHeight();
        System.out.println(width);
        System.out.println(height);
        anchorPane.getChildren().forEach(node -> {
            if (node instanceof Button) {
                switch (((Button) node).getText()) {
                    case "2" -> {
                        node.setLayoutX(width / 2 - width / 3);
                        node.setLayoutY(2 * height / 3);
                    }
                    case "3" -> {
                        node.setLayoutX(width / 2);
                        node.setLayoutY(2 * height / 3);
                    }
                    case "4" -> {
                        node.setLayoutX(width / 2 + width / 3);
                        node.setLayoutY(2 * height / 3);
                    }
                }
            }
        });
    }
}
