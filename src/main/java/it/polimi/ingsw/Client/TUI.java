package it.polimi.ingsw.Client;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Model.Bookshelf;
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
    //private int bookshelfIndex;
    private Client client;
    private String playerId;
    private boolean isEnded;
    private GameStatus gameStatus;
    protected TUI() throws RemoteException {
    }
    public void connectToServer() {
        String userInput;
        System.out.print("Choose a connection mode (socket, RMI): ");
        userInput = scanner.nextLine();

        while(!(userInput.equals("RMI") || userInput.equals("socket"))) {
            System.out.println("Wrong input for connection mode");
            System.out.print("Choose a connection mode (socket, RMI): ");
            userInput = scanner.nextLine();
        }

        try {
            if (userInput.equals("RMI")) {
                client = new ClientRMI(this);
            }
            else if (userInput.equals("socket"))
                client = new ClientSocket(this);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }

        System.out.println("Connection to server: SUCCESSFUL");

    }

    public void askForUsername() {
        boolean isUsernameAlreadyInUse = false;
        boolean isPasswordCorrect = false;
        boolean chooseUsername = true;
        String userInput = null;

        while(chooseUsername) {
            try {
                System.out.print("Type your username: ");
                userInput = scanner.nextLine();
                client.choosePlayerId(userInput);
                playerId = userInput;
                chooseUsername = false;
            } catch (PlayerIdAlreadyInUseException e) {
                System.out.println("Username already in use!");
                System.out.println("Do you want to have to access it? (y/n)");
                if(scanner.nextLine().equals("y")) {
                    playerId = userInput;
                    chooseUsername = false;
                }
                isUsernameAlreadyInUse = true;
            } catch (Exception e) {
                System.out.println("Exception in TUI ask for username " + e);
                e.printStackTrace();
                System.exit(-1);
            }
        }
        while(!isPasswordCorrect){
            System.out.println("Please provide the password linked to this username: ");
            userInput = scanner.nextLine();
            if(isUsernameAlreadyInUse){
                try {
                    client.checkPassword(playerId, userInput);
                    isPasswordCorrect = true;
                } catch (WrongPasswordException e) {
                    System.out.println("Wrong password!");
                } catch (Exception e) {
                    System.out.println("Exception in TUI ask for password " + e);
                    e.printStackTrace();
                    System.exit(-1);
                }
            } else {
                try {
                    client.choosePassword(playerId, userInput);
                    isPasswordCorrect = true;
                } catch (Exception e) {
                    System.out.println("Exception in TUI ask for password " + e);
                    e.printStackTrace();
                    System.exit(-1);
                }
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
    public void addPlayer() throws IOException, AlreadyStartedGameException, MaxNumberOfPlayersException, WrongMessageClassEnumException, InterruptedException {
        int maxPlayers = -1; // why do we need initialization?
        boolean success = false;

        while(!success) {
            try {
                gameId = client.addPlayerToCreatedGame(playerId);
                success = true;
                //bookshelfIndex = client.getNumCurrentPlayers(gameId) - 1;
                System.out.println("You've been added to an existing game (gameId = " + gameId + ")");
            } catch (CreateNewGameException e) {
                System.out.print("Creating a new game. Choose the number of players in this game (2, 3 or 4): ");

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
                    //bookshelfIndex = 0;
                    System.out.println("New game created (gameId = " + gameId + ")");
                } catch (GameAlreadyCreatedException f) {
                    System.out.println("Too slow, another player has already created a new game. Trying to connect to an existing game");
                }
            }
        }
    }
    public void playTurn() throws VoidBoardTileException, SelectionNotValidException, PlayerIsWaitingException, TilesSelectionSizeDifferentFromOrderLengthException, ColumnNotValidException, SelectionIsEmptyException, WrongConfigurationException, PickedColumnOutOfBoundsException, IOException, PickDoesntFitColumnException, WrongMessageClassEnumException, InterruptedException {
        if(gameStatus.isLastTurn()) {
            System.out.println("LAST TURN!");
        }
        System.out.println("It is your turn, type 'p' to play");
        synchronized (scanner) {
            BoardNavigator boardNavigator = new BoardNavigator(gameStatus.getBoard());
            BookshelfNavigator bookshelfNavigator = new BookshelfNavigator(gameStatus.getBookshelves().get(gameStatus.getPlayers().indexOf(playerId)), boardNavigator.getSelection().size());

            String input = "";
            while(boardNavigator.getSelection().size() == 0) {
                while (!input.equals("x")) {
                    boardNavigator.print();
                    System.out.println("Move into the board. ('w', 'a', 's', 'd' to MOVE; 'space' to SELECT; 'c' to DESELECT; 'x' to SUBMIT SELECTION) : ");
                    System.out.println("You can select up to " + Math.min(3,bookshelfNavigator.getMaxEmptySpaces()) + " tiles during this turn");
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
                        case " " -> {if (boardNavigator.getSelection().size()< bookshelfNavigator.getMaxEmptySpaces()) {boardNavigator.select();}
                                    else {System.out.println("You can't select any more tiles");}}
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

    private void insertTiles(ArrayList<Coordinates> tilesSelection) throws SelectionNotValidException, PlayerIsWaitingException, TilesSelectionSizeDifferentFromOrderLengthException, ColumnNotValidException, SelectionIsEmptyException, WrongConfigurationException, PickedColumnOutOfBoundsException, IOException, PickDoesntFitColumnException, VoidBoardTileException, WrongMessageClassEnumException, InterruptedException {
        String input = "";
        BookshelfNavigator bookshelfNavigator = new BookshelfNavigator(gameStatus.getBookshelves().get(gameStatus.getPlayers().indexOf(playerId)), tilesSelection.size());

        while(!input.equals("y")) {
            bookshelfNavigator.print();
            System.out.println("Move into the bookshelf. ('a', 'd' to MOVE; 'space' to SELECT): ");
            input =  scanner.nextLine();

            if(input.length() != 1) {
                System.out.print("Input must be a single character: ");
                continue;
            }
            switch (input) {
                case "d" -> bookshelfNavigator.moveRight();
                case "a" -> bookshelfNavigator.moveLeft();
                case " " -> {if (tilesSelection.size()< bookshelfNavigator.getEmptySpaces(bookshelfNavigator.getColumn())) {
                                    bookshelfNavigator.select();
                                    bookshelfNavigator.print();
                                    System.out.println("Column selected: " + bookshelfNavigator.getColumn() + "\nAre you sure? (y/n)");

                                    input =  scanner.nextLine();
                                    while(input.length() != 1 || (!input.equals("y") && !input.equals("n"))) { //until input is valid
                                        System.out.print("Input must be a single character (y/n)! \nTry again: ");
                                        input =  scanner.nextLine();
                                    }

                                    if(input.equals("y")) {
                                        if (bookshelfNavigator.getColumn() == -1)
                                            input = ""; //do nothing in case column is wrong
                                    }

                                    else if(input.equals("n")) {
                                        //bookshelfNavigator.deselect();
                                        //bookshelfNavigator.print();
                                        System.out.print("Just move and select another column\n");
                                    }



                                }
                            else {System.out.println("There's not enough space in this column, try another one.");}}
                /*case "x" -> {                    //moved this into the "select" case to avoid submitting without a selection
                    if (bookshelfNavigator.getColumn() == -1)
                        input = "";
                }*/
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
        for(int i = 0; i < gameStatus.getCommonGoalPointStacksNames().length; i++) {
            System.out.printf("%d) %s: %d points, %s\n", i, gameStatus.getCommonGoalPointStacksNames()[i], gameStatus.getCommonGoalPointStacksTops()[i], gameStatus.getIsCommonGoalAlreadyAchieved()[i] ? "Achieved" : "Not achieved");
        }
    }
    public void endGame(String winnerPlayer){
        System.out.println("THE GAME IS OVER!");
        isEnded = true;
        System.out.println("The WINNER is: " + winnerPlayer);
    }

    private void listOfPlayers() {
        List<String> players = gameStatus.getPlayers();
        for(int i = 0; i < players.size(); i++) {
            System.out.printf("%d) %s\n", i, players.get(i));
        }
    }
    private void printPersonalGoal() {
        System.out.print("Your personal goal is: \n");
        printBookshelf(gameStatus.getPersonalGoal().getPersonalGoalBookshelf());
    }
    private void bookshelfToPrint() {
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
        if(gameStatus == null) {
            return;
        }
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
            default -> System.out.println("Invalid command");
        }
    }
    private void printCommonGoalsDescription() {
        for(int i = 0; i < gameStatus.getCommonGoalPointStacksNames().length; i++) {
            System.out.printf("%s\n%s\n", gameStatus.getCommonGoalPointStacksNames()[i], gameStatus.getCommonGoalPointStacksDescriptions()[i]);
        }
    }

    private void printBookshelf(Bookshelf bookshelf)  {
        Utils.printBookshelf(bookshelf);
    }
    public void run() throws IOException, AlreadyStartedGameException, MaxNumberOfPlayersException, InterruptedException, NotBoundException, WrongMessageClassEnumException {
        scanner = new Scanner(new InputStreamReader(System.in));
        Utils.printLogo();
        System.out.println("Welcome to MyShelfie!");

        connectToServer();
        askForUsername();
        addPlayer();
        addObserver();

        while(!isEnded) {
            synchronized (scanner){
                // todo fix this
                if(gameStatus != null) {
                    System.out.println("Type a command (board, bookshelf, personal, common, points): ");
                }
                String command = scanner.nextLine();
                if(!command.equals("p")) {
                    executePlayerCommand(command);
                } else if(gameStatus.getCurrentPlayer().equals(playerId)) {
                    scanner.wait();
                } else {
                    System.out.println("It's not your turn, please choose another command");
                }
                scanner.notifyAll();
            }
        }
    }
}
