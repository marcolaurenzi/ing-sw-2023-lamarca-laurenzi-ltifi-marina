package it.polimi.ingsw.View;

import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Model.GameState.GameState;
import it.polimi.ingsw.Utils.GameStatusToSend;
import it.polimi.ingsw.Utils.Utils;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import jdk.jshell.execution.Util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class GamePageController implements ViewController{
    private final LoginController loginController = new LoginController();

    private static String username;
    @FXML
    private Label messageLabel;

    @FXML
    private Label playerId0;
    @FXML
    private Label playerId1;
    @FXML
    private Label playerId2;
    @FXML
    private Label playerId3;

    @FXML
    private Label points0;
    @FXML
    private Label points1;
    @FXML
    private Label points2;
    @FXML
    private Label points3;

    @FXML
    private GridPane commonGoalsGridPane;
    @FXML
    private ImageView personalGoalImageView;
    @FXML
    private HBox mainHBox;

    @FXML
    private GridPane boardGridPane;

    @FXML
    private GridPane bookshelfGridPane0;

    @FXML
    private GridPane bookshelfGridPane1;

    @FXML
    private GridPane bookshelfGridPane2;

    @FXML
    private GridPane bookshelfGridPane3;

    private GUITurnSelectionHandler turnSelectionHandler;
    private static GUI gui;

    private static List<Button> buttons = new ArrayList<>();

    public void initialize() {
        GUI.setController(this);
        for(int i = 0; i < 5; i++) {
            Button currButton = (Button) mainHBox.lookup("#column" + i + "Button");
            final int finalI = i;
            buttons.add(currButton);
            currButton.setOnAction(event -> {
                System.out.println("bookShelf button " + finalI + " clicked");
                turnSelectionHandler.selectColumn(finalI);
            });

            currButton.setDisable(true);
        }
    }

    protected void uiUpdate(GameStatusToSend gameStatus) {
        turnSelectionHandler = new GUITurnSelectionHandler(gameStatus.getBoard(), boardGridPane, gameStatus.getBookshelf(gameStatus.getPlayers().indexOf(GUI.getPlayerId())));

        Platform.runLater(() -> {
            // First, clear all previous children
            boardGridPane.getChildren().clear();
            double height = boardGridPane.getHeight() / gameStatus.getBoard().getGameBoard().getColumnDimension();
            double width = boardGridPane.getWidth() / gameStatus.getBoard().getGameBoard().getRowDimension();

            // Item tiles on the Board
            for(int i = 0; i< gameStatus.getBoard().getGameBoard().getColumnDimension(); i++) {
                for (int j = 0; j <  gameStatus.getBoard().getGameBoard().getRowDimension(); j++) {
                    Item item = gameStatus.getBoard().getGameBoard().get(i, j).getPlacedItem();
                    if(item != null) {
                        ImageView imageView = new ImageView();
                        String itemPath = Utils.getAssetsPath() + "itemTiles" + File.separator + item.getType().toString() + item.getNum() + ".png";
                        imageView.imageProperty().setValue(new Image("file:" + itemPath));
                        imageView.setFitHeight(height);
                        imageView.setFitWidth(width);
                        Button button = new Button();
                        button.setStyle("-fx-background-color: black; -fx-padding: 0; -fx-border-width: 0; -fx-border-insets: 0; -fx-background-insets: 0;");
                        button.setMaxHeight(height);
                        button.setMaxWidth(width);
                        button.setMinHeight(height);
                        button.setMinWidth(width);
                        button.setPrefHeight(height);
                        button.setPrefWidth(width);
                        button.setGraphic(imageView);
                        button.setId("button" + i + j);

                        // Disable button if selection not valid

                        final int finalI = i;
                        final int finalJ = j;
                        if (isYourTurn()) {
                            System.out.println("Setting on action: ");
                            button.setOnAction(event -> {
                                turnSelectionHandler.select(finalI, finalJ);
                                System.out.println("Button" + finalI +" "+  finalJ + " selected");
                                Stream<Button> stream = buttons.stream();
                                stream.forEach(b -> turnSelectionHandler.disableButtons(b));
                            });
                        }
                        boardGridPane.add(button, j, i);
                    }
                }
            }

            ArrayList<GridPane> otherPlayersGridPaneList = new ArrayList<>();
            otherPlayersGridPaneList.add(bookshelfGridPane1);
            otherPlayersGridPaneList.add(bookshelfGridPane2);
            otherPlayersGridPaneList.add(bookshelfGridPane3);

            ArrayList<Label> otherPlayersPlayerIdLabelList = new ArrayList<>();
            otherPlayersPlayerIdLabelList.add(playerId1);
            otherPlayersPlayerIdLabelList.add(playerId2);
            otherPlayersPlayerIdLabelList.add(playerId3);

            ArrayList<Label> otherPlayersPointsLabelList = new ArrayList<>();
            otherPlayersPointsLabelList.add(points1);
            otherPlayersPointsLabelList.add(points2);
            otherPlayersPointsLabelList.add(points3);

            int bookshelfIndex = 0;
            for(int p = 0; p < gameStatus.getPlayers().size(); p ++) {
                Bookshelf currentBookshelf = gameStatus.getBookshelf(p);

                if(gameStatus.getPlayers().get(p).equals(GUI.getPlayerId())) {
                    //labels
                    playerId0.setText(GUI.getPlayerId());
                    points0.setText(gameStatus.getPoints().get(gameStatus.getPlayers().indexOf(GUI.getPlayerId())).toString());

                    //bookshelf
                    bookshelfGridPane0.getChildren().clear();
                    height = bookshelfGridPane0.getHeight() / currentBookshelf.getColumnDimension();
                    width = bookshelfGridPane0.getWidth() / currentBookshelf.getRowDimension();

                    for(int i = 0; i<currentBookshelf.getColumnDimension(); i++) {
                        for (int j = 0; j < currentBookshelf.getRowDimension(); j++) {
                            Item item = currentBookshelf.get(i, j);
                            if(item != null) {
                                ImageView imageView = new ImageView();
                                String itemPath = Utils.getAssetsPath() + "itemTiles" + File.separator + item.getType().toString() + item.getNum() + ".png";
                                imageView.imageProperty().setValue(new Image("file:" + itemPath));
                                imageView.setFitHeight(height);
                                imageView.setFitWidth(width);
                                imageView.setPreserveRatio(false);
                                bookshelfGridPane0.add(imageView, j, i);
                            }
                        }
                    }
                }
                else {
                    otherPlayersPlayerIdLabelList.get(bookshelfIndex).setText(gameStatus.getPlayers().get(p));
                    otherPlayersPointsLabelList.get(bookshelfIndex).setText(gameStatus.getPoints().get(p).toString());

                    otherPlayersGridPaneList.get(bookshelfIndex).getChildren().clear();
                    height = bookshelfGridPane1.getHeight() / currentBookshelf.getColumnDimension();
                    width = bookshelfGridPane1.getWidth() / currentBookshelf.getRowDimension();

                    for(int i = 0;  i <currentBookshelf.getColumnDimension(); i++) {
                        for (int j = 0; j < currentBookshelf.getRowDimension(); j++) {
                            Item item = currentBookshelf.get(i, j);
                            if(item != null) {
                                ImageView imageView = new ImageView();
                                String itemPath = Utils.getAssetsPath() + "itemTiles" + File.separator + item.getType().toString() + item.getNum() + ".png";
                                imageView.imageProperty().setValue(new Image("file:" + itemPath));
                                imageView.setFitHeight(height);
                                imageView.setFitWidth(width);
                                imageView.setPreserveRatio(false);
                                otherPlayersGridPaneList.get(bookshelfIndex).add(imageView, j, i);
                            }
                        }
                    }

                    bookshelfIndex++;
                }
            }

            //activate your bookshelf's buttons
            if(isYourTurn()) {
                for(int i = 0; i < 5; i++) {
                    Button currButton = (Button) mainHBox.lookup("#column" + i + "Button");
                    currButton.setDisable(false);
                }
            }

            //set up upper label
            if(isYourTurn()) {
                messageLabel.setText("Your turn!");
            } else {
                messageLabel.setText("");
            }

            //set up personal goal
            personalGoalImageView.imageProperty().setValue(new Image("file:" + Utils.getAssetsPath() + "personal_goal_cards" +  File.separator + gameStatus.getPersonalGoal().getPersonalGoalName() + ".png"));

            //set up common goals
            ImageView goal0ImageView = new ImageView();
            ImageView goal1ImageView = new ImageView();
            ImageView pointsImage0View = new ImageView();
            ImageView pointsImage1View = new ImageView();

            goal0ImageView.imageProperty().setValue(new Image("file:" + Utils.getAssetsPath() + "common_goal_cards" + File.separator + gameStatus.getCommonGoalPointStacksDescriptions()[0]+ ".jpg") );
            goal1ImageView.imageProperty().setValue(new Image("file:" + Utils.getAssetsPath() + "common_goal_cards" + File.separator + gameStatus.getCommonGoalPointStacksDescriptions()[1]+ ".jpg") );
            goal0ImageView.setFitHeight(104);
            goal1ImageView.setFitHeight(104);
            goal0ImageView.setFitWidth(166);
            goal1ImageView.setFitWidth(166);

            pointsImage0View.imageProperty().setValue(new Image("file:" + Utils.getAssetsPath() + "scoring_tokens" + File.separator + "scoring_" + gameStatus.getCommonGoalPointStacksTops()[0]+ ".jpg") );
            pointsImage1View.imageProperty().setValue(new Image("file:" + Utils.getAssetsPath() + "scoring_tokens" + File.separator + "scoring_" + gameStatus.getCommonGoalPointStacksTops()[1]+ ".jpg") );
            if(gameStatus.getIsCommonGoalAlreadyAchieved()[0])
                pointsImage0View.setOpacity(0.5);
            if(gameStatus.getIsCommonGoalAlreadyAchieved()[1])
                pointsImage1View.setOpacity(0.5);
            pointsImage0View.setFitHeight(104);
            pointsImage1View.setFitHeight(104);
            pointsImage0View.setFitWidth(104);
            pointsImage1View.setFitWidth(104);

            commonGoalsGridPane.getChildren().clear();

            commonGoalsGridPane.add(goal0ImageView, 0, 0);
            commonGoalsGridPane.add(goal1ImageView, 0, 1);
            commonGoalsGridPane.add(pointsImage0View, 1, 0);
            commonGoalsGridPane.add(pointsImage1View, 1, 1);

            /*
            ChangeListener<Number> resizeListener = new ChangeListener<>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    double cellWidth = gridPane.getWidth() / gridPane.getColumnConstraints().size();
                    double cellHeight = gridPane.getHeight() / gridPane.getRowConstraints().size();

                    for (Node child : gridPane.getChildren()) {
                        if (child instanceof ImageView imageView) {
                            imageView.setFitWidth(cellWidth);
                            imageView.setFitHeight(cellHeight);
                        }
                    }
                }
            };

            gridPane.widthProperty().addListener(resizeListener);
            };
            gridPane.heightProperty().addListener(resizeListener);*/

            Stage stage = (Stage) boardGridPane.getScene().getWindow();
            stage.show();
        });
    }

    private void enableBookshelfButtons() {
        Platform.runLater(() -> {
                for(int i = 0; i < 5; i++) {
                Button currButton = (Button) mainHBox.lookup("#column" + i + "Button");
                currButton.setDisable(false);
            }
        });

    }
    public void disableBookshelfButtons() {
        Platform.runLater(() -> {
            for(int i = 0; i < 5; i++) {
                Button currButton = (Button) mainHBox.lookup("#column" + i + "Button");
                currButton.setDisable(true);
            }
        });
    }
    private boolean isYourTurn() { return GUI.gameStatus.getCurrentPlayer().equals(GUI.getPlayerId()); }

    public GUITurnSelectionHandler getTurnSelectionHandler() {
        return turnSelectionHandler;
    }

}
