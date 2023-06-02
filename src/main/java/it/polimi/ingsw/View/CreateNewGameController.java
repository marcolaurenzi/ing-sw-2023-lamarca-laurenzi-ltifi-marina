package it.polimi.ingsw.View;

import it.polimi.ingsw.Model.Exceptions.AlreadyStartedGameException;
import it.polimi.ingsw.Model.Exceptions.GameAlreadyCreatedException;
import it.polimi.ingsw.Model.Exceptions.MaxNumberOfPlayersException;
import it.polimi.ingsw.Model.Exceptions.WrongMessageClassEnumException;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateNewGameController implements ViewController {

    private static GUI gui;

    private static Scene scene;

    private static Parent root;

    public void initialize(Stage stage) throws Exception {
        Scene scene = stage.getScene();
        Parent root = scene.getRoot();
        this.root = root;
        this.scene = scene;
        root.getChildrenUnmodifiable().forEach(node -> {
            if (node instanceof Button) {
                ((Button) node).setOnAction(event -> {
                        onClick(((Button) node).getText());
                });
            }
        });
    }

    public void onClick(String text) {
        try {
            gui.createNewGameAndAddPlayer(Integer.parseInt(text));
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
    }

    private void printError(String string) {
        TextField error = (TextField) root.lookup("#errorTextField");
        error.setText(string);
    }
}
