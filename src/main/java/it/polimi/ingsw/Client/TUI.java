package it.polimi.ingsw.Client;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Model.Bookshelf;
import it.polimi.ingsw.Model.CommonGoalPointStack;
import it.polimi.ingsw.Model.Coordinates;
import it.polimi.ingsw.Model.Exceptions.*;
import it.polimi.ingsw.Utils.GameStatus;
import it.polimi.ingsw.Utils.Utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TUI implements RemoteUI, UI {
    private Scanner scanner;
    private int gameId;
    private Client client;
    private String playerId;
    private boolean isEnded;
    private GameStatus gameStatus;
    protected TUI() throws RemoteException {
    }
    public void connectToServer() {
        String userInput;
        System.out.print("Choose the connection mode (socket, RMI): ");
        userInput = scanner.nextLine();

        while(!(userInput.equals("RMI") || userInput.equals("socket"))) {
            System.out.println("Wrong connection mode");
            System.out.print("Choose the connection mode (socket, RMI): ");
            userInput = scanner.nextLine();
        }

        try {
            if (userInput.equals("RMI")) {
                client = new ClientRMI(this);
            }
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
    public void askForUsername() throws IOException {
        boolean isUsernameAlreadyInUse = true;
        String userInput;

        while(isUsernameAlreadyInUse) {
            try {
                System.out.print("Insert your username: ");
                userInput = scanner.nextLine();
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
    public void addPlayer() throws IOException, AlreadyStartedGameException, MaxNumberOfPlayersException {
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
                        maxPlayers = Integer.parseInt(scanner.nextLine());

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
    public void playTurn() throws VoidBoardTileException, SelectionNotValidException, PlayerIsWaitingException, TilesSelectionSizeDifferentFromOrderLengthException, ColumnNotValidException, SelectionIsEmptyException, WrongConfigurationException, PickedColumnOutOfBoundsException, IOException, PickDoesntFitColumnException {
        if(gameStatus.isLastTurn()) {
            System.out.println("This is the last turn!");
        }
        System.out.println("It is your turn write playturn to play");
        synchronized (scanner) {
            BoardNavigator boardNavigator = new BoardNavigator(gameStatus.getBoard());
            String input = "";
            while(boardNavigator.getSelection().size() == 0) {
                while (!input.equals("x")) {
                    boardNavigator.print();
                    System.out.println("Navigate into the board. (w, a, s, d to move; space to select; c to deselect; x to submit selection) : ");
                    input = scanner.nextLine();

                    if (input.length() != 1) {
                        System.out.print("Input must be a single character: ");
                        continue;
                    }
                    switch (input) {
                        case "w" -> boardNavigator.moveUp();
                        case "d" -> boardNavigator.moveRight();
                        case "s" -> boardNavigator.moveDown();
                        case "a" -> boardNavigator.moveLeft();
                        case " " -> boardNavigator.select();
                        case "c" -> boardNavigator.deselect();
                        case "x" -> {
                            if(boardNavigator.getSelection().size() == 0)
                                input = "";
                        } //do nothing
                        default -> input = "";
                    }
                }
            }
            insertTiles(boardNavigator.getSelection());
            System.out.println("Turn ended");
            scanner.notifyAll();
        }


    }

    private void insertTiles(ArrayList<Coordinates> tilesSelection) throws SelectionNotValidException, PlayerIsWaitingException, TilesSelectionSizeDifferentFromOrderLengthException, ColumnNotValidException, SelectionIsEmptyException, WrongConfigurationException, PickedColumnOutOfBoundsException, IOException, PickDoesntFitColumnException, VoidBoardTileException {
        String input = "";
        BookshelfNavigator bookshelfNavigator = new BookshelfNavigator(gameStatus.getBookshelves().get(gameStatus.getPlayers().indexOf(playerId)), tilesSelection.size());

        while(!input.equals("x")) {
            bookshelfNavigator.print();
            System.out.println("Navigate into the bookshelf. (a, d to move; space to select and x to submit): ");
            input =  scanner.nextLine();

            if(input.length() != 1) {
                System.out.print("Input must be a single character: ");
                continue;
            }
            switch (input) {
                case "d" -> bookshelfNavigator.moveRight();
                case "a" -> bookshelfNavigator.moveLeft();
                case " " -> bookshelfNavigator.select();
                case "x" -> {
                    if (bookshelfNavigator.getColumn() == -1)
                        input = "";
                }
                default -> input = "";
            }
        }
        int[] order = new int[tilesSelection.size()];
        for (int i = 0; i < tilesSelection.size(); i++) {
            order[i] = i;
        }
        client.pickAndInsertInBookshelf(tilesSelection, bookshelfNavigator.getColumn(), order, playerId);
    }

    private void printPoints() {
        for(int i = 0; i < gameStatus.getPlayers().size(); i++) {
            System.out.printf("%d) %s: %d points \n", i, gameStatus.getPlayers().get(i), gameStatus.getPoints().get(i));
        }
    }

    private void printBoard() throws IOException {
        Board board = gameStatus.getBoard();
        Utils.printBoard(board);

    }
    private void printCommonGoals(){
        CommonGoalPointStack[] commonGoals = gameStatus.getCommonGoalPointStacks();
        for(int i = 0; i < commonGoals.length; i++) {
            System.out.printf("%d) %s: %d points, %s\n", i, commonGoals[i].getCommonGoal().getGoalName(), commonGoals[i].getTopPoints(), gameStatus.getIsCommonGoalAlreadyAchieved()[i] ? "Achieved" : "Not achieved");
        }
    }
    public void endGame(String winnerPlayer){
        System.out.println("The game is over!");
        isEnded = true;
        System.out.println("The winner is player: " + winnerPlayer);
    }

    private void listOfPlayers() {
        List<String> players = gameStatus.getPlayers();
        for(int i = 0; i < players.size(); i++) {
            System.out.printf("%d) %s\n", i, players.get(i));
        }
    }
    private void printPersonalGoal() throws IOException {
        System.out.print("Your personal goal is: \n");
        printBookshelf(gameStatus.getPersonalGoal().getPersonalGoalBookshelf());
    }
    private void bookshelfToPrint() throws IOException {
        List<Bookshelf> bookshelfList = gameStatus.getBookshelves();
        System.out.print("This is the list of players: \n");
        listOfPlayers();
        System.out.printf("Which player books do you want to see? (0-%d): \n", bookshelfList.size() - 1);

        boolean validInput = false;
        int userInput = -1;
        while(!validInput) {
            try {
                userInput = Integer.parseInt(scanner.nextLine());

                if (userInput >= 0 && userInput <= bookshelfList.size() - 1)
                    validInput = true;
                else
                    System.out.printf("Input must be a number between (0-%d) \n", bookshelfList.size() - 1);

            } catch (NumberFormatException f) {
                System.out.printf("Input must be a number between (0-%d) \n", bookshelfList.size() - 1);
            }
        }
        System.out.printf("This is the bookshelf of %s: \n", gameStatus.getPlayers().get(userInput));
        printBookshelf(bookshelfList.get(userInput));
    }
    private void addObserver() throws IOException, NotBoundException {
        client.addObserver(playerId);
    }
    public void update(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    private void executePlayerCommand(String command) throws IOException {
        switch (command) {
            case "board" -> {
                printBoard();
                printCommonGoals();
            }
            case "bookshelf" -> bookshelfToPrint();
            case "personal" -> printPersonalGoal();
            case "common" -> {
                printCommonGoals();
                printCommonGoalsDescription();
            }
            case "points" -> printPoints();
            default -> System.out.println("Command not recognized");
        }
    }
    private void printCommonGoalsDescription() {
        for(CommonGoalPointStack commonGoalPointStack : gameStatus.getCommonGoalPointStacks()) {
            System.out.printf("%s\n", commonGoalPointStack.getCommonGoal().getGoalName());
            commonGoalPointStack.getCommonGoal().printGoalDescription();
        }
    }

    private void printBookshelf(Bookshelf bookshelf) throws IOException {
        Utils.printBookshelf(bookshelf);
    }
    public void run() throws IOException, AlreadyStartedGameException, MaxNumberOfPlayersException, InterruptedException,  NotBoundException {
        scanner = new Scanner(new InputStreamReader(System.in));
        Utils.printLogo();
        System.out.println("Welcome to MyShelfie!");

        connectToServer();
        askForUsername();
        addPlayer();
        addObserver();

        while(!isEnded) {
            synchronized (scanner){
                System.out.println("Write one of the commands (board, bookshelf, personal, common, points): ");
                String command = scanner.nextLine();
                if(!command.equals("playturn")) {
                    executePlayerCommand(command);
                } else if(gameStatus.getCurrentPlayer().equals(playerId)) {
                    scanner.wait();
                } else {
                    System.out.println("It's not your turn, please use another command");
                }
                scanner.notifyAll();
            }
        }
    }
}
