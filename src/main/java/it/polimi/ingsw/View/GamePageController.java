package it.polimi.ingsw.View;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Model.BoardTile;
import it.polimi.ingsw.Model.GameState.GameState;
import it.polimi.ingsw.Model.Item;
import it.polimi.ingsw.Model.Matrix;
import it.polimi.ingsw.Utils.GameStatusToSend;
import it.polimi.ingsw.Utils.Utils;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import jdk.jshell.execution.Util;

import java.io.File;

public class GamePageController implements ViewController{
    private final LoginController loginController = new LoginController();

    private static String username;

    @FXML
    private AnchorPane anchorPane;

    private static GUI gui;

    public void initialize() {
        GUI.setController(this);
    }

    protected void uiUpdate(GameStatusToSend gameStatus) {
        Matrix<BoardTile> matrix = gameStatus.getBoard().getGameBoard();
        GridPane gridPane = (GridPane)anchorPane.lookup("#bookshelfGrid");

        Platform.runLater(() -> {
            // First, clear all previous children
            gridPane.getChildren().clear();
            double height = gridPane.getHeight() / matrix.getColumnDimension();
            double width = gridPane.getWidth() / matrix.getRowDimension();

            for(int i = 0; i<matrix.getColumnDimension(); i++) {
                for (int j = 0; j < matrix.getRowDimension(); j++) {
                    Item item = matrix.get(i, j).getPlacedItem();
                    if(item != null) {
                        ImageView imageView = new ImageView();
                        String itemPath = Utils.getAssetsPath() + "itemTiles" + File.separator + item.getType().toString() + item.getNum() + ".png";
                        imageView.imageProperty().setValue(new Image("file:" + itemPath));
                        imageView.setFitHeight(height);
                        imageView.setFitWidth(width);
                        imageView.setPreserveRatio(true);
                        gridPane.add(imageView, i, j);
                    }
                }
            }
            Stage stage = (Stage) anchorPane.getScene().getWindow();
            stage.show();
        });
    }

    public void onClick() {
        username = loginController.getUsername();
        System.out.println("Username: " + username);
    }
}
