package it.polimi.ingsw.View;

import it.polimi.ingsw.Model.Exceptions.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.NotBoundException;

/**
 * The LoginController class handles the user login process.
 */
public class LoginController extends Application implements ViewController {
    /**
     * Constructs a LoginController object.
     */
    public LoginController() {

    }
    /**
     * The text field for entering the username.
     */
    @FXML
    private TextField usernameTextField;
    /**
     * Menu for choosing the connection mode.
     */
    @FXML
    private ChoiceBox<String> connectionMenu;
    /**
     * Stage
     */
    private static Stage stage = null;
    /**
     * Username
     */
    private static String username;
    /**
     *Password
     */
    private static String password;
    /**
     * Connection mode
     */
    static String connectionMode;
    /**
     * The root of the scene
     */
    static Parent root;

    /**
     * The main method of the LoginController class.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * The start method initializes the login page.
     *
     * @param primaryStage The primary stage of the application.
     * @throws IOException In case of an input/output exception.
     */
    public void start(Stage primaryStage) throws IOException {
        root = FXMLLoader.load(ClassLoader.getSystemResource("fxml/LoginPage.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("style/mystyle.css");
        stage = primaryStage;
        primaryStage.setTitle("My Shelfie");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        connectionMenu = (ChoiceBox<String>) scene.lookup("#connectionMenu");

        connectionMenu.setItems(FXCollections.observableArrayList(
                "RMI",
                "Socket"
        ));

        connectionMenu.setValue("RMI");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * The login method handles the user login action.
     *
     * @throws IOException          In case of an input/output exception.
     * @throws NotBoundException    If a remote object is not currently bound to the specified name in the registry.
     * @throws ConnectionException  If there is an error in establishing a connection with the server.
     * @throws WrongPasswordException If the entered password is incorrect.
     * @throws AlreadyStartedGameException If a game has already started.
     * @throws CreateNewGameException If a new game creation failed.
     */
    public void login() throws IOException, NotBoundException, ConnectionException, WrongPasswordException,
            AlreadyStartedGameException, CreateNewGameException {
        root = FXMLLoader.load(ClassLoader.getSystemResource("fxml/GamePage.fxml"));
        Scene scene = usernameTextField.getScene();
        TextField username = (TextField) scene.lookup("#usernameTextField");
        TextField password = (TextField) scene.lookup("#passwordTextField");
        this.username = username.getText();
        this.password = password.getText();
        this.connectionMode = connectionMenu.getValue();

        try {
            connectToServer();
            askForUsername();
            addPlayer();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(true);
            stage.show();
        } catch (ConnectionException e) {
            printError("Connection to server: FAILED");
        } catch (WrongPasswordException e) {
            printError("Username already in use, Wrong password");
        } catch (AlreadyStartedGameException e) {
            printError("The game has already started");
        } catch (CreateNewGameException e) {
            stage.close();
            FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource("fxml/CreateNewGamePage.fxml"));
            Scene newScene = new Scene(loader.load());
            Stage stage = new Stage();
            CreateNewGameController controller = loader.getController();
            stage.setScene(newScene);
            stage.setResizable(false);
            stage.show();
            controller.setLayout(newScene);
        } catch (PlayerOnlineException e) {
            printError("This username belongs to a player already online");
        } catch (WrongMessageClassEnumException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Adds the player to the game.
     *
     * @throws CreateNewGameException     If a new game creation failed.
     * @throws NotBoundException          If a remote object is not currently bound to the specified name in the registry.
     * @throws AlreadyStartedGameException If a game has already started.
     */
    private void addPlayer() throws CreateNewGameException, NotBoundException, AlreadyStartedGameException {
        GUI.addPlayer(username);
    }

    /**
     * Asks for the username.
     *
     * @throws WrongPasswordException In case of an incorrect password.
     * @throws IOException            In case of an input/output exception.
     */
    private void askForUsername() throws WrongPasswordException, IOException, PlayerOnlineException, WrongMessageClassEnumException, InterruptedException {
        GUI.askForUsername();
    }

    /**
     * Returns the username.
     *
     * @return The username.
     */
    public static String getUsername() {
        return username;
    }

    /**
     * Returns the password.
     *
     * @return The password.
     */
    public static String getPassword() {
        return password;
    }

    /**
     * Connects to the server.
     *
     * @throws ConnectionException If there is an error in establishing a connection with the server.
     */
    public void connectToServer() throws ConnectionException {
        GUI.connectToServer();
    }

    /**
     * Prints an error message.
     *
     * @param string The error message to display.
     */
    public void printError(String string)  {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText(null);
        alert.initOwner(stage);
        alert.setContentText(string);
        alert.showAndWait();
    }


    /**
     * Returns the stage.
     *
     * @return The stage.
     */
    public static Stage getStage() {
        return stage;
    }
}
