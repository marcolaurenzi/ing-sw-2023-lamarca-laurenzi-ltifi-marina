package it.polimi.ingsw.Client;

import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Model.Exceptions.*;
import it.polimi.ingsw.Utils.GameStatus;

import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CLI extends UnicastRemoteObject implements RemoteObserver {
    static Scanner scanner;
    static int gameId;
    static Client client;
    static String playerId;
    static GameStatus gameStatus;
    protected CLI() throws RemoteException {
    }

    public static void connectToServer() throws IOException {
        String userInput;
        System.out.print("Choose the connection mode (socket, RMI): ");
        userInput = scanner.nextLine();

        while(!(userInput.equals("RMI") || userInput.equals("socket"))) {
            System.out.println("Wrong connection mode");
            System.out.print("Choose the connection mode (socket, RMI): ");
            userInput = scanner.nextLine();
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

    //@Override
    /*public void update(Object observable, GameStatus game) {
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
    }*/
    public void playTurn() {
        System.out.println("write playturn to play");
        synchronized (scanner) {
            System.out.println("It's your turn!");
            ArrayList<Coordinates> coordinates = new ArrayList<>();
            do {
                List<String> coordinateString = new ArrayList<>();
                while (coordinateString.size() < 3) {
                    System.out.print("Enter a coordinate in the format (a;b) (or press Enter to finish): ");
                    String input = scanner.nextLine();

                    if (input.isEmpty()) {
                        break;
                    }

                    if (!isCoordinateValid(input)) {
                        System.out.println("Invalid coordinate format. Make sure to follow the correct format.");
                        continue;
                    }

                    coordinateString.add(input);
                }
                for (String coordinate : coordinateString) {
                    String[] values = coordinate.replaceAll("[()\\s]", "").split(";");
                    coordinates.add(new Coordinates(Integer.parseInt(values[0]), Integer.parseInt(values[1])));
                }
                System.out.println("Coordinates entered:" + gameStatus.getBoard().getGameBoard().get(coordinates.get(0).getX(), coordinates.get(0).getY()).getPlacedItem().getType());
                if (!isSelectionValid(coordinates, gameStatus.getBoard())) {
                    System.out.println("Invalid selection, all the tiles must be next to each other and must have at least one side free, please try again");
                    coordinates.clear();
                }
            } while (!isSelectionValid(coordinates, gameStatus.getBoard()));
            printSelection(coordinates);
            scanner.notifyAll();
        }
    }
    private static boolean isCoordinateValid(String input) {
        String regex = "\\(\\s*\\d+\\s*;\\s*\\d+\\s*\\)";
        return input.matches(regex);
    }
    protected Boolean isSelectionValid(ArrayList<Coordinates> tilesSelection, Board board) {
        return (areAllSameColumnAndAdjacents(tilesSelection) || areAllSameRowAndAdjacents(tilesSelection)) && haveAllOneSidesFree(tilesSelection, board);
    }
    private Boolean areAllSameColumnAndAdjacents(ArrayList<Coordinates> tilesSelection) {
        Boolean ret = true;

        int x = tilesSelection.get(0).getX();
        int y = tilesSelection.get(0).getY();

        for(int i = 0; i < tilesSelection.size(); i++) {
            if (tilesSelection.get(i).getY() != y || tilesSelection.get(i).getX() != x + i) {
                ret = false;
                break;
            }
        }

        return ret;
    }
    private void printSelection(ArrayList<Coordinates> tilesSelection) {
        for(int i = 0; i < tilesSelection.size(); i++) {
            switch (gameStatus.getBoard().getGameBoard().get(tilesSelection.get(i).getX(), tilesSelection.get(i).getY()).getPlacedItem().getType()) {

                case CATS:
                    System.out.print('0');
                    break;
                case BOOKS:
                    System.out.print('1');
                    break;
                case GAMES:
                    System.out.print('2');
                    break;
                case FRAMES:
                    System.out.print('3');
                    break;
                case TROPHIES:
                    System.out.print('4');
                    break;
                case PLANTS:
                    System.out.print('5');
                    break;
            }
            System.out.printf(" ");
        }
    }
    private Boolean areAllSameRowAndAdjacents(ArrayList<Coordinates> tilesSelection) {
        Boolean ret = true;

        int x = tilesSelection.get(0).getX();
        int y = tilesSelection.get(0).getY();

        for(int i = 1; i < tilesSelection.size(); i++) {
            if (tilesSelection.get(i).getX() != x || tilesSelection.get(i).getY() != y + i ) {
                ret = false;
                break;
            }
        }

        return ret;
    }
    private Boolean haveAllOneSidesFree(ArrayList<Coordinates> tilesSelection, Board board) {
        Boolean ret = true;

        for(int i = 0; i < tilesSelection.size(); i++) {
            if (board.hasFree(tilesSelection.get(i).getX(), tilesSelection.get(i).getY()) == 0) {
                ret = false;
                break;
            }
        }

        return ret;
    }

    private void printPoints() {
        for(Integer i : gameStatus.getPoints()) {
            //TODO print points
        }
    }

    private static void printBoard() {
        Matrix<BoardTile> board = gameStatus.getBoard().getGameBoard();
        System.out.println("-----------");
        for(int i = 0; i < board.getColumnDimension(); i++) {
            System.out.print("|");
            for (int j = 0; j < board.getRowDimension(); j++)
                if (board.get(i, j).getPlacedItem() != null) {
                    switch (board.get(i, j).getPlacedItem().getType()) {
                        case CATS:
                            System.out.print('0');
                            break;
                        case BOOKS:
                            System.out.print('1');
                            break;
                        case GAMES:
                            System.out.print('2');
                            break;
                        case FRAMES:
                            System.out.print('3');
                            break;
                        case TROPHIES:
                            System.out.print('4');
                            break;
                        case PLANTS:
                            System.out.print('5');
                            break;
                    }
                } else
                    System.out.print('#');
            System.out.print("| ");
            System.out.print('\n');
        }
        System.out.println("-----------");
    }
    private static void printCommonGoals(){
        CommonGoalPointStack[] commonGoals = gameStatus.getCommonGoalPointStacks();
        for(int i = 0; i < commonGoals.length; i++) {
            System.out.printf("%d) %s: %d points, %s\n", i, commonGoals[i].getCommonGoal().printGoal(), commonGoals[i].getTopPoints(), gameStatus.getIsCommonGoalAlreadyAchieved()[i] ? "Achieved" : "Not achieved");
        }
    }
    private void printStack() {
        CommonGoalPointStack[] stack = gameStatus.getCommonGoalPointStacks();
        for(int i = 0; i < stack.length; i++) {
            //TODO print stack
        }
    }
    private static void listOfPlayers() {
        List<String> players = gameStatus.getPlayers();
        for(int i = 0; i < players.size(); i++) {
            System.out.printf("%d) %s\n", i, players.get(i));
        }
    }
    private static void printPersonalGoal(){
        System.out.printf("Your personal goal is: \n");
        printBookshelf(gameStatus.getPersonalGoal().getPersonalGoalBookshelf());
    }
    private static void bookshelfToPrint() throws IOException {
        List<Bookshelf> bookshelfList = gameStatus.getBookshelves();
        System.out.printf("This is the list of players: \n");
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
    private static void addObserver() throws IOException {
        client.addObserver(new CLI(), playerId);
    }
    public void update(GameStatus gameStatus) throws RemoteException{
        this.gameStatus = gameStatus;
    }

    private static void executePlayerCommand(String command) throws IOException {
        switch (command) {
            case "board" -> {
                printBoard();
                printCommonGoals();
            }
            case "bookshelf" -> bookshelfToPrint();
            case "personal" -> printPersonalGoal();
            default -> System.out.println("Command not recognized");
        }
    }
    private static void printBookshelf(Bookshelf bookshelf) {
        System.out.println("--------");
        for(int i = 0; i < bookshelf.getColumnDimension(); i++) {
            System.out.print("|");
            for (int j = 0; j < bookshelf.getRowDimension(); j++)
                if (bookshelf.get(i, j) != null) {
                    switch (bookshelf.get(i, j).getType()) {
                        case CATS:
                            System.out.print('0');
                            break;
                        case BOOKS:
                            System.out.print('1');
                            break;
                        case GAMES:
                            System.out.print('2');
                            break;
                        case FRAMES:
                            System.out.print('3');
                            break;
                        case TROPHIES:
                            System.out.print('4');
                            break;
                        case PLANTS:
                            System.out.print('5');
                            break;
                    }
                } else
                    System.out.print('#');
            System.out.print("| ");
            System.out.print('\n');
        }
        System.out.println("--------");
    }
    public static void main(String[] args) throws IOException, AlreadyStartedGameException, MaxNumberOfPlayersException, InterruptedException {
        scanner = new Scanner(new InputStreamReader(System.in));
        System.out.println("Welcome to MyShelfie!");

        connectToServer();
        askForUsername();
        addPlayer();
        addObserver();

        while(true) {
            synchronized (scanner){
                String command = scanner.nextLine();
                if(!command.equals("playturn")) {
                    executePlayerCommand(command);
                } else {
                    scanner.wait();
                }
                scanner.notifyAll();
            }
        }
    }
}
