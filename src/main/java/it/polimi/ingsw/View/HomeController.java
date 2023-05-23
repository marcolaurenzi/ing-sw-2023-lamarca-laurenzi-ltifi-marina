/* package it.polimi.ingsw.View;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController extends Application {


    private TextField usernameTextField;

    Parent root;

    public static void main(String[] args) {
        launch(args);
    }


    public void start(Stage primaryStage) throws IOException {
        root = FXMLLoader.load(ClassLoader.getSystemResource("fxml/Home.fxml"));
        Set scene with the FXML
        Scene scene = new Scene(root);
        primaryStage.setTitle("My Shelfie");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    public void loginOnClick() throws IOException {

        Loads the scene from the node
        Scene scene = usernameTextField.getScene();

        Gets the username from the text field
        TextField usernameTextField = (TextField) scene.lookup("#usernameTextField");
        String username = usernameTextField.getText();

        AnchorPane anchorPane = new AnchorPane();

        // Loads the scene from the node
        Text text = new Text();
        text.setText("Hello " + username + "!");
        text.setX(50);
        text.setY(50);
        Stage stage = new Stage();
        VBox root = new VBox();
        root.getChildren().add(anchorPane);
        root.getChildren().add(text);
        stage.setScene(new Scene(root));
        stage.show();

    }
}
*/