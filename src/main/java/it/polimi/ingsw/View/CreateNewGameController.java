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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateNewGameController implements ViewController {

    @FXML
    private AnchorPane anchorPane;

    private static Stage stage = null;
    static Parent root;


    public void initialize() {

        Button button2 = new Button("2");
        button2.setLayoutX(100);
        button2.setLayoutY(97);
        anchorPane.getChildren().add(button2);

        Button button3 = new Button("3");
        button3.setLayoutX(275);
        button3.setLayoutY(97);
        anchorPane.getChildren().add(button3);

        Button button4 = new Button("4");
        button4.setLayoutX(450);
        button4.setLayoutY(97);
        anchorPane.getChildren().add(button4);

        anchorPane.getChildren().forEach(node -> {
            if (node instanceof Button) {
                ((Button) node).setOnAction(event -> {
                        onClick(((Button) node).getText());
                });
            }
        });
    }

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
        } catch (WrongMessageClassEnumException | MaxNumberOfPlayersException | IOException | InterruptedException | GameAlreadyCreatedException e) {
            throw new RuntimeException(e);
        } catch (AlreadyStartedGameException e) {
            printError("Game already created");
            try {
                Thread.sleep(5*1000);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
        finally {
            Scene scene = new Scene(root);
            stage = LoginController.getStage();
            stage.setScene(scene);
            stage.setResizable(true);
            //stage.setMaximized(true);
            //stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
            stage.show();
        }
    }

    private void printError(String string) {
        TextField error = (TextField) anchorPane.lookup("#errorTextField");
        error.setText(string);
    }
}
