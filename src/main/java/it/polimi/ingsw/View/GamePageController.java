package it.polimi.ingsw.View;

import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Utils.GameStatusToSend;
import it.polimi.ingsw.Utils.Utils;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
 * Controller class for the game page view.
 */
public class GamePageController implements ViewController{

    /**
     * The login controller.
     */
    private final LoginController loginController = new LoginController();

    /**
     * The username.
     */
    private static String username;

    /**
     * The height of the game page.
     */
    private static double height;

    /**
     * The width of the game page.
     */
    private static double width;

    /**
     * The message label.
     */
    @FXML
    private Label messageLabel;

    /**
     * The playerID of player 0.
     */
    @FXML
    private Label playerId0;

    /**
     * The playerID of player 1.
     */
    @FXML
    private Label playerId1;

    /**
     * The playerID of player 2.
     */
    @FXML
    private Label playerId2;

    /**
     * The playerID of player 3.
     */
    @FXML
    private Label playerId3;

    /**
     * The points of player 0.
     */
    @FXML
    private Label points0;

    /**
     * The points of player 1.
     */
    @FXML
    private Label points1;

    /**
     * The points of player 2.
     */
    @FXML
    private Label points2;

    /**
     * The points of player 3.
     */
    @FXML
    private Label points3;

    /**
     * The Common Goals Grid.
     */
    @FXML
    private GridPane commonGoalsGridPane;

    /**
     * The Personal Goals View.
     */
    @FXML
    private ImageView personalGoalImageView;

    /**
     * The GUI background.
     */
    @FXML
    private HBox mainHBox;

    /**
     * The board grid pane.
     */
    @FXML
    private GridPane boardGridPane;

    /**
     * The bookshelf grid pane for player 0.
     */
    @FXML
    private GridPane bookshelfGridPane0;

    /**
     * The bookshelf grid pane for player 1.
     */
    @FXML
    private GridPane bookshelfGridPane1;

    /**
     * The bookshelf grid pane for player 2.
     */
    @FXML
    private GridPane bookshelfGridPane2;

    /**
     * The bookshelf grid pane for player 3.
     */
    @FXML
    private GridPane bookshelfGridPane3;

    /**
     * The global pick counter.
     */
    private static int globalPickCounter = 0;

    /**
     * The current pick dimension.
     */
    private static int currentPickDimension = 0;

    /**
     * The turn selection handler.
     */
    protected static GUITurnSelectionHandler turnSelectionHandler;

    /**
     * The GUI.
     */
    private static GUI gui;

    /**
     * The list of buttons.
     */
    protected static List<Button> buttons = new ArrayList<>();

    /**
     * The player that triggered the end of the game.
     */
    private String endGamePlayer;

    /**
     * Initializes the game page view.
     */
    public void initialize() {
        GUI.setController(this);
        setEndGamePlayer(null);
        for(int i = 0; i < 5; i++) {
            Button currButton = (Button) mainHBox.lookup("#column" + i + "Button");
            final int finalI = i;
            buttons.add(currButton);
            currButton.setOnAction(event -> {
                System.out.println("bookShelf button " + finalI + " clicked");
                turnSelectionHandler.selectColumn(finalI);
                resetGlobalPickCounter();
            });
            Label label = new Label(String.valueOf(i+1));
            label.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
            currButton.setGraphic(label);
            currButton.setDisable(true);
        }
    }

    /**
     * Updates the UI with the given game status.
     *
     * @param gameStatus the game status to update the UI with
     */
    protected void uiUpdate(GameStatusToSend gameStatus){
        turnSelectionHandler = new GUITurnSelectionHandler(this, gameStatus.getBoard(), boardGridPane, gameStatus.getBookshelf(gameStatus.getPlayers().indexOf(GUI.getPlayerId())));

        Platform.runLater(() -> {
            // First, clear all previous children
            boardGridPane.getChildren().clear();
            height = boardGridPane.getHeight() / gameStatus.getBoard().getGameBoard().getColumnDimension();
            width = boardGridPane.getWidth() / gameStatus.getBoard().getGameBoard().getRowDimension();

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
                                turnSelectionHandler.select(finalI, finalJ, imageView, button);
                                System.out.println("Button " + finalI + " " + finalJ + " selected");
                                Stream<Button> stream = GamePageController.buttons.stream();
                                stream.forEach(b -> GamePageController.turnSelectionHandler.disableButtons(b));
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

                // Player's bookshelf
                if(gameStatus.getPlayers().get(p).equals(GUI.getPlayerId())) {
                    //labels
                    playerId0.setText(GUI.getPlayerId());
                    playerId0.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
                    points0.setText(gameStatus.getPoints().get(gameStatus.getPlayers().indexOf(GUI.getPlayerId())).toString());
                    points0.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
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
                // Others' bookshelf
                else {
                    otherPlayersPlayerIdLabelList.get(bookshelfIndex).setText(gameStatus.getPlayers().get(p));
                    otherPlayersPlayerIdLabelList.get(bookshelfIndex).setFont(Font.font("Verdana", FontWeight.BOLD, 20));
                    otherPlayersPointsLabelList.get(bookshelfIndex).setText(gameStatus.getPoints().get(p).toString());
                    otherPlayersPointsLabelList.get(bookshelfIndex).setFont(Font.font("Verdana", FontWeight.BOLD, 20));

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

            // Check if the endGamePLayer, the player that triggered the end of the game, is null. By doing this we can
            // know if the game is ended or not and we are sure to enter this if only once as the player is set once entered, so it'll no longer be null
            if(isLastTurn() && getEndGamePlayer() == null) {

                // if the current player is the first player, the endGamePlayer is the last player
                if(gameStatus.getCurrentPlayer().equals(0)) {
                    setEndGamePlayer(gameStatus.getPlayers().get(gameStatus.getPlayers().size()-1));
                }else {
                    setEndGamePlayer(gameStatus.getPlayers().get(gameStatus.getPlayers().indexOf(gameStatus.getCurrentPlayer())-1));
                }

                Scene scene = messageLabel.getScene();

                // if the endGamePlayer is the current player, the endGameToken label is the first one
                if(getEndGamePlayer().equals(GUI.getPlayerId())) {
                    System.out.println("Endgame1");
                    ImageView imageView = (ImageView) scene.lookup("#endGame1");
                    imageView.setImage(new Image("file:" + Utils.getAssetsPath() + File.separator + "scoring_tokens" + File.separator + "end game.jpg"));
                }
                else {
                    // if the endGamePLayer is not the current player, then all the other players are stored in an arraylist so that
                    // their indexes are used to set the right label
                    ArrayList<String> players = gameStatus.getPlayers();
                    players.remove(GUI.getPlayerId());
                    int index = players.indexOf(getEndGamePlayer());
                    switch (index) {
                        case 0 -> {
                            System.out.println("Endgame2");
                            ImageView imageView = (ImageView) scene.lookup("#endGame2");
                            imageView.setImage(new Image("file:" + Utils.getAssetsPath() + File.separator + "scoring_tokens" + File.separator + "end game.jpg"));
                        }
                        case 1 -> {
                            System.out.println("Endgame3");
                            ImageView imageView = (ImageView) scene.lookup("#endGame3");
                            imageView.setImage(new Image("file:" + Utils.getAssetsPath() + File.separator + "scoring_tokens" + File.separator + "end game.jpg"));
                        }
                        case 2 -> {
                            System.out.println("Endgame4");
                            ImageView imageView = (ImageView) scene.lookup("#endGame4");
                            imageView.setImage(new Image("file:" + Utils.getAssetsPath() + File.separator + "scoring_tokens" + File.separator + "end game.jpg"));
                        }
                    }
                }
            }

            // The first player is always starting first, so if the current player is the first player, the firstPlayerToken label is the first one
            if(GUI.getPlayerId().equals(gameStatus.getPlayers().get(0))) {
                Scene scene = messageLabel.getScene();
                ImageView imageView = (ImageView) scene.lookup("#chair1");
                imageView.setImage(new Image("file:" + Utils.getAssetsPath() + File.separator + "misc" + File.separator + "firstplayertoken.png"));
            }
            // if the current player is not the first player then the label is the second one
            // as the first player in the list is always stored in the TOP-LEFT bookshelf
            else {
                Scene scene = messageLabel.getScene();
                ImageView imageView = (ImageView) scene.lookup("#chair2");
                imageView.setImage(new Image("file:" + Utils.getAssetsPath() + File.separator + "misc" + File.separator + "firstplayertoken.png"));
            }

            if (isLastTurn() && isYourTurn()) {

                // Upper Label set up
                messageLabel.setText("Last Turn, make your last move!");
                messageLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
                messageLabel.setPrefWidth(300);

                // Activate bookshelf buttons
                for(int i = 0; i < 5; i++) {
                    Button currButton = (Button) mainHBox.lookup("#column" + i + "Button");
                    currButton.setDisable(false);
                }
            }
            else if(isLastTurn()) {

                // Upper Label set up
                messageLabel.setText("Last Turn, wait for other players to finish!");

                // Deactivate bookshelf buttons
                for(int i = 0; i < 5; i++) {
                    Button currButton = (Button) mainHBox.lookup("#column" + i + "Button");
                    currButton.setDisable(true);
                }
            }
            else if(isYourTurn()) {

                // Upper Label set up
                messageLabel.setText("Your turn!");

                // Activate bookshelf buttons
                for(int i = 0; i < 5; i++) {
                    Button currButton = (Button) mainHBox.lookup("#column" + i + "Button");
                    currButton.setDisable(false);
                }
            }
            else {
                messageLabel.setText("");

                // Deactivate bookshelf buttons
                for(int i = 0; i < 5; i++) {
                    Button currButton = (Button) mainHBox.lookup("#column" + i + "Button");
                    currButton.setDisable(true);
                }
            }

            //set up personal goal
            personalGoalImageView.imageProperty().setValue(new Image("file:" + Utils.getAssetsPath() + "personal_goal_cards" +  File.separator + gameStatus.getPersonalGoal().getPersonalGoalName() + ".png"));

            //set up common goals
            ImageView goal0ImageView = new ImageView();
            ImageView goal1ImageView = new ImageView();
            ImageView pointsImage0View = new ImageView();
            ImageView pointsImage1View = new ImageView();

            goal0ImageView.imageProperty().setValue(new Image("file:" + Utils.getAssetsPath() + "common_goal_cards" + File.separator + gameStatus.getCommonGoalPointStacksNames()[0]+ ".jpg") );
            goal1ImageView.imageProperty().setValue(new Image("file:" + Utils.getAssetsPath() + "common_goal_cards" + File.separator + gameStatus.getCommonGoalPointStacksNames()[1]+ ".jpg") );
            goal0ImageView.setFitHeight(104);
            goal1ImageView.setFitHeight(104);
            goal0ImageView.setFitWidth(166);
            goal1ImageView.setFitWidth(166);

            // If common goal 0 is not null -> still achievable
            if(gameStatus.getCommonGoalPointStacksTops()[0] != null) {
                pointsImage0View.imageProperty().setValue(new Image("file:" + Utils.getAssetsPath() + "scoring_tokens" + File.separator + "scoring_" + gameStatus.getCommonGoalPointStacksTops()[0]+ ".jpg") );
                if(gameStatus.getIsCommonGoalAlreadyAchieved()[0]) {
                    pointsImage0View.setOpacity(0.5);
                }
                pointsImage0View.setFitHeight(104);
                pointsImage0View.setFitWidth(104);
            }

            // If common goal 1 is not null -> still achievable
            if(gameStatus.getCommonGoalPointStacksTops()[1] != null) {
                pointsImage1View.imageProperty().setValue(new Image("file:" + Utils.getAssetsPath() + "scoring_tokens" + File.separator + "scoring_" + gameStatus.getCommonGoalPointStacksTops()[1]+ ".jpg") );
                if(gameStatus.getIsCommonGoalAlreadyAchieved()[1]) {
                    pointsImage1View.setOpacity(0.5);
                }
                pointsImage1View.setFitHeight(104);
                pointsImage1View.setFitWidth(104);
            }

            commonGoalsGridPane.getChildren().clear();

            commonGoalsGridPane.add(goal0ImageView, 0, 0);
            commonGoalsGridPane.add(goal1ImageView, 0, 1);
            commonGoalsGridPane.add(pointsImage0View, 1, 0);
            commonGoalsGridPane.add(pointsImage1View, 1, 1);

            Stage stage = (Stage) boardGridPane.getScene().getWindow();
            stage.show();
        });
    }

    /**
     * Enables the bookshelf buttons.
     */
    private void enableBookshelfButtons() {
        Platform.runLater(() -> {
            for(int i = 0; i < 5; i++) {
                Button currButton = (Button) mainHBox.lookup("#column" + i + "Button");
                currButton.setDisable(false);
            }
        });

    }

    /**
     * Disables the bookshelf buttons.
     */
    public void disableBookshelfButtons() {
        Platform.runLater(() -> {
            for(int i = 0; i < 5; i++) {
                Button currButton = (Button) mainHBox.lookup("#column" + i + "Button");
                currButton.setDisable(true);
            }
        });
    }

    /**
     * Checks if it's the player's turn.
     * @return true if it's the player's turn, false otherwise.
     */
    private boolean isYourTurn() {
        return GUI.gameStatus.getCurrentPlayer().equals(GUI.getPlayerId());
    }

    /**
     * Checks if it's the last turn of the game.
     * @return true if it's the last turn, false otherwise.
     */
    private boolean isLastTurn() {
        return GUI.gameStatus.isLastTurn(); }

    /**
     * Returns the turn selection handler.
     * @return the turn selection handler.
     */
    public GUITurnSelectionHandler getTurnSelectionHandler() {
        return turnSelectionHandler;
    }

    /**
     * Displays the end game screen.
     * @param winner the winner of the game.
     */
    public void endGame(String winner) {
        Platform.runLater(() -> {
            System.out.println("Last turn");
            FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource("fxml/EndGamePage.fxml"));
            Scene newScene = null;
            try {
                newScene = new Scene(loader.load());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            EndGameController endGameController = loader.getController();
            Stage stage = new  Stage();
            stage.setScene(newScene);
            stage.setResizable(false);
            endGameController.endGame(winner);
            stage.show();
        });

    }

    /**
     * Returns the global pick counter.
     * @return the global pick counter.
     */
    public static int getGlobalPickCounter() {
        return globalPickCounter;
    }

    /**
     * Increments the global pick counter by 1.
     */
    public static void incrementGlobalPickCounter() {
        globalPickCounter++;
    }

    /**
     * Decrements the global pick counter by 1.
     */
    public static void decrementGlobalPickCounter() {
        globalPickCounter--;
    }

    /**
     * Resets the global pick counter to 0.
     */
    public static void resetGlobalPickCounter() {
        globalPickCounter = 0;
    }

    /**
     * Sets the global pick counter to the given value.
     * @param i the value to set the global pick counter.
     */
    public static void setGlobalPickCounter(int i) {
        globalPickCounter = i;
    }

    /**
     * Returns the current pick dimension.
     * @return the current pick dimension.
     */
    public static int getCurrentPickDimension() {return currentPickDimension;}

    /**
     * Increments the current pick dimension by 1.
     */
    public static void incrementCurrentPickDimension() {
        currentPickDimension++;
    }

    /**
     * Decrements the current pick dimension by 1.
     */
    public static void decrementCurrentPickDimension() {currentPickDimension--;}

    /**
     * Resets the current pick dimension to 0.
     */
    public static void resetCurrentPickDimension() {
        currentPickDimension = 0;
    }

    /**
     * Returns the height of the game page.
     * @return the height of the game page.
     */
    public static double getHeight() {
        return height;
    }

    /**
     * Returns the width of the game page.
     * @return the width of the game page.
     */
    public static double getWidth() {
        return width;
    }

    /**
     * Returns the board grid pane.
     * @return the board grid pane.
     */
    public GridPane getBoardGridPane() {
        return this.boardGridPane;
    }

    /**
     * Returns the end game player.
     * @return the end game player.
     */
    public String getEndGamePlayer() {
        return endGamePlayer;
    }

    /**
     * Sets the end game player.
     * @param endGamePlayer the end game player to set.
     */
    public void setEndGamePlayer(String endGamePlayer) {
        this.endGamePlayer = endGamePlayer;
    }
}
