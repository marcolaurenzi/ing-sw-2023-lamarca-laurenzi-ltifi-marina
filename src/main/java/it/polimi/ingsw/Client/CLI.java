package it.polimi.ingsw.Client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Model.Bookshelf;
import it.polimi.ingsw.Model.CommonGoalPointStack;
import it.polimi.ingsw.Model.Exceptions.*;
import it.polimi.ingsw.Utils.GameStatus;

public class CLI implements RemoteObserver {
    static BufferedReader reader;
    static int gameId;
    static Client client;
    static String playerId;
    static GameStatus game;

    public static void connectToServer() throws IOException {
        String userInput;
        System.out.print("Choose the connection mode (socket, RMI): ");
        userInput = reader.readLine();

        while(!(userInput.equals("RMI") || userInput.equals("socket"))) {
            System.out.println("Wrong connection mode");
            System.out.print("Choose the connection mode (socket, RMI): ");
            userInput = reader.readLine();
        }

        try {
            if (userInput.equals("RMI"))
                client = new ClientRMI();
            else if (userInput.equals("socket"))
                client = new ClientSocket();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }

        System.out.println("Connection with server successful");

    }
    /**
     * Asks the player the username and tells the controller to add it to the set of used playerIds
     * @throws IOException if there is an input/output error while reading the user's input.
     */
    public static void askForUsername() throws IOException {
        boolean isUsernameAlreadyInUse = true;
        String userInput;

        while(isUsernameAlreadyInUse) {
            try {
                System.out.print("Insert your username: ");
                userInput = reader.readLine();
                client.choosePlayerId(userInput);

                //executed only if exception not thrown
                isUsernameAlreadyInUse = false;
                playerId = userInput;
            } catch (PlayerIdAlreadyInUseException e) {
                System.out.println("Username already in use!");
            }
        }
    }
    //TODO understand why the compiler tells "Variable 'maxPlayers' might not have been initialized" if not initialized at the start of the code. May lead to a bug
    /**
     * Adds the player to an existing game or creates a new game and adds the player to it.
     * @throws IOException if there is an input/output error while reading the user's input.
     * @throws AlreadyStartedGameException //TODO.
     * @throws MaxNumberOfPlayersException if the maximum number of players is exceeded.
     */
    public static void addPlayer() throws IOException, AlreadyStartedGameException, MaxNumberOfPlayersException {
        int maxPlayers = -1; // why do we need initialization?
        boolean success = false;

        while(!success) {
            try {
                gameId = client.addPlayerToCreatedGame(playerId);
                success = true;
                System.out.println("You were assigned to game with gameId = " + gameId);
            } catch (CreateNewGameException e) {
                System.out.print("Need to create a new game, insert the maximum number of player you want in this game (2, 3 or 4): ");

                boolean validInput = false;
                while(!validInput) {
                    try {
                        maxPlayers = Integer.parseInt(reader.readLine());

                        if (maxPlayers >= 2 && maxPlayers <= 4)
                            validInput = true;
                        else
                            System.out.print("Input must be a number (2, 3 or 4) , try again: ");

                    } catch (NumberFormatException f) {
                        System.out.print("Input must be a number (2, 3 or 4) , try again: ");
                    }
                }

                try {
                    gameId = client.createNewGameAndAddPlayer(playerId, maxPlayers);
                    success = true;
                    System.out.println("You created the game with gameId = " + gameId);
                } catch (GameAlreadyCreatedException f) {
                    System.out.println("Too slow, a player already created a new game, trying to connect to a created game");
                }
            }
        }
    }

    @Override
    public void update(Object observable, GameStatus game) {
        this.game = game;
        printBookshelf();
        printStack();
        printBoard();
        printPoints();
        if(game.getPlayers().get(game.getCurrentPlayer()).equals(playerId)) {
            System.out.println("It's your turn!");
            playTurn();
        }

        else{
            System.out.println("It's " + game.getCurrentPlayer() + "'s turn");
        }
    }

    private void playTurn() {
        //TODO
    }

    private void printPoints() {
        for(Integer i : game.getPoints()) {
            //TODO print points
        }
    }

    private void printBoard() {
        Board board = game.getBoard();
        //TODO print board
    }

    private void printStack() {
        CommonGoalPointStack[] stack = game.getCommonGoalPointStacks();
        for(int i = 0; i < stack.length; i++) {
            //TODO print stack
        }
    }

    private void printBookshelf() {
        List<Bookshelf> bookshelfList = game.getBookshelves();
        for(Bookshelf bookshelf : bookshelfList) {
            //TODO print bookshelf
        }
    }

    public static void main(String[] args) throws IOException, AlreadyStartedGameException, MaxNumberOfPlayersException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome to MyShelfie!");

        connectToServer();
        askForUsername();
        addPlayer();

        while(true);
    }
}
