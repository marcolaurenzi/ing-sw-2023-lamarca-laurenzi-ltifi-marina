package it.polimi.ingsw.View;
import it.polimi.ingsw.Model.Exceptions.ConnectionException;
import it.polimi.ingsw.Model.Exceptions.CreateNewGameException;
import it.polimi.ingsw.Model.Exceptions.WrongPasswordException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController extends Application implements ViewController{

    @FXML
    private TextField usernameTextField;
    @FXML
    private ChoiceBox<String> connectionMenu;
    private static Stage stage = null;
    private static String username;
    private static String password;
    static String connectionMode;
    static Parent root;

public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws IOException {
        root = FXMLLoader.load(ClassLoader.getSystemResource("fxml/LoginPage.fxml"));
        Scene scene = new Scene(root);
        stage = primaryStage;
        primaryStage.setTitle("My Shelfie");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        connectionMenu = (ChoiceBox<String>) scene.lookup("#connectionMenu");

        connectionMenu.setItems(FXCollections.observableArrayList(
                "RMI",
                "Socket"
        ));

        // Impostazione dell'opzione predefinita
        connectionMenu.setValue("RMI");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void login() throws IOException {

        Scene scene = usernameTextField.getScene();
        TextField username = (TextField) scene.lookup("#usernameTextField");
        TextField password = (TextField) scene.lookup("#passwordTextField");
        this.username = username.getText();
        this.password = password.getText();

        this.connectionMode = connectionMenu.getValue();


        System.out.println(username.getText() + password.getText() + connectionMode);


        try {
            connectToServer();
            askForUsername();
            addPlayer();
            //addObserver();
            
            root = FXMLLoader.load(ClassLoader.getSystemResource("fxml/GamePage.fxml"));
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

        }
        catch (ConnectionException e) {
            printError("Connection to server: FAILED");
        } catch (WrongPasswordException e) {
            printError("Username already in use, Wrong password");
        } catch (CreateNewGameException e) {
            FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource("fxml/CreateNewGamePage.fxml"));
            Scene newScene = new Scene(loader.load());
            Stage stage = new Stage();
            CreateNewGameController controller = loader.getController();
            stage.setScene(newScene);
            stage.setResizable(false);
            stage.show();
        }


    }

    private void addPlayer() throws CreateNewGameException {
        GUI.addPlayer();
    }

    /*
    private void addObserver() {
        gui.addObserver();
    }*/

    private void askForUsername() throws WrongPasswordException {
        GUI.askForUsername();
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }

    public void connectToServer() throws ConnectionException {
        GUI.connectToServer();
    }

    public void printError(String string) {
        TextField error = (TextField) root.lookup("#errorTextField");
        error.setText(string);
    }
}
