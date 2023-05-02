package it.polimi.ingsw.Client;

import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Model.Exceptions.*;
import it.polimi.ingsw.Utils.GameStatus;

import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class TUI implements RemoteUI, UI {
    Scanner scanner;
    int gameId;
    Client client;
    String playerId;
    boolean isEnded;
    GameStatus gameStatus;
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
        int maxPlayers = -1; // why do we need initialization? good domanda
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

    public void playTurn() throws VoidBoardTileException, SelectionNotValidException, PlayerIsWaitingException, TilesSelectionSizeDifferentFromOrderLengthException, ColumnNotValidException, SelectionIsEmptyException, WrongConfigurationException, PickedColumnOutOfBoundsException, RemoteException, PickDoesntFitColumnException {
        System.out.println("write playturn to play");
        synchronized (scanner) {
            System.out.println("It's your turn!");
            ArrayList<Coordinates> coordinates = new ArrayList<>();
            do {
                coordinates.clear();
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
                if (!isSelectionValid(coordinates, gameStatus.getBoard())) {
                    System.out.println("Invalid selection, all the tiles must be next to each other and must have at least one side free, please try again");
                }
            } while (!isSelectionValid(coordinates, gameStatus.getBoard()));
            insertTiles(coordinates);

            scanner.notifyAll();
        }
    }
    private boolean isCoordinateValid(String input) {
        String regex = "\\(\\s*\\d+\\s*;\\s*\\d+\\s*\\)";
        return input.matches(regex);
    }
    protected Boolean isSelectionValid(ArrayList<Coordinates> tilesSelection, Board board) throws VoidBoardTileException {
        return !isAnyTileNull(tilesSelection, board) && (areAllSameColumnAndAdjacents(tilesSelection) || areAllSameRowAndAdjacents(tilesSelection)) && haveAllOneSidesFree(tilesSelection, board);
    }
    private boolean isAnyTileNull(ArrayList<Coordinates> tilesSelection, Board board) {
        boolean ret = false;

        for(int i = 0; i < tilesSelection.size(); i++) {
            if (board.getGameBoard().get(tilesSelection.get(i).getX(), tilesSelection.get(i).getY()).getPlacedItem() == null) {
                ret = true;
                break;
            }
        }
        return ret;
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
    private void insertTiles(ArrayList<Coordinates> tilesSelection) throws SelectionNotValidException, PlayerIsWaitingException, TilesSelectionSizeDifferentFromOrderLengthException, ColumnNotValidException, SelectionIsEmptyException, WrongConfigurationException, PickedColumnOutOfBoundsException, RemoteException, PickDoesntFitColumnException, VoidBoardTileException {
        String input = null;
        boolean acceptedInsertion = false;
        printSelection(tilesSelection);
        System.out.println("This is your bookshelf: ");
        printBookshelf(gameStatus.getBookshelves().get(gameStatus.getPlayers().indexOf(playerId)));
        System.out.println("Please provide the indexes in order corresponding to your tiles selection: ");
        while (!acceptedInsertion) {
            do {
                input = scanner.nextLine();

                if (!isOrderValid(input, tilesSelection.size()) || !isIndexInRange(input, tilesSelection.size()))
                    System.out.println("Invalid input, please try again");

            } while (!isOrderValid(input, tilesSelection.size()) || !isIndexInRange(input, tilesSelection.size()));
            System.out.print("This is the order in which the tiles will be inserted: ");
            printItemsInOrder(tilesSelection, input);
            System.out.println("Are you sure you want to proceed? y to accept");
            String answer = scanner.nextLine();
            if (answer.equals("y") || answer.equals("Y")){
                acceptedInsertion = true;
            } else {
                System.out.println("Please provide the indexes in order corresponding to your tiles selection: ");
            }
        }
        String[] stringNumbers = input.split(" ");
        int[] order = new int[stringNumbers.length];
        for (int i = 0; i < stringNumbers.length; i++) {
            order[i] = Integer.parseInt(stringNumbers[i]);
        }
        printBookshelf(gameStatus.getBookshelves().get(gameStatus.getPlayers().indexOf(playerId)));
        System.out.print("Choose the column in which the tiles has to be inserted: ");
        int column = -1;
        boolean validInput = false;
        while(!validInput) {
            try {
                column = Integer.parseInt(scanner.nextLine());

                if (column >= 0 && column < 5)
                    validInput = true;
                else
                    System.out.print("Input must be a number (0 - 5) , try again: ");

            } catch (NumberFormatException f) {
                System.out.print("Input must be a number (0 - 5) , try again: ");
            }
        }
        client.pickAndInsertInBookshelf(tilesSelection, column, order, playerId);
    }
    private boolean isOrderValid(String input, int numberOfTiles) {
        String regex = "\\s*\\d+";
        for (int i = 1; i < numberOfTiles; i++) {
            regex += "\\s+\\d+";                 //regex is being built to match the input format
        }
        regex += "\\s*";
        return input.matches(regex);
    }
    private void printItemsInOrder(ArrayList<Coordinates> tilesSelection, String input) {
        String[] stringNumbers = input.split(" "); //----->>> gucci cane sas     |||| 2 0 1
        int[] order = new int[stringNumbers.length];
        for (int i = 0; i < stringNumbers.length; i++) {
            order[i] = Integer.parseInt(stringNumbers[i]);
        }
        ArrayList<Coordinates> orderedPickedItems = new ArrayList<>();
        for(int i = 0; i < tilesSelection.size(); i++)
            orderedPickedItems.add(null);
        int i = 0;
        for (int n : order) {
            orderedPickedItems.set(i, tilesSelection.get(n));
            i++;
        }
        printSelection(orderedPickedItems);
    }
    private boolean isIndexInRange(String input, int numberOfTiles) {
        String[] stringNumbers = input.split(" ");
        HashSet<Integer> numbers = new HashSet<>();
        for (int i = 0; i < stringNumbers.length; i++) {
            numbers.add(Integer.parseInt(stringNumbers[i]));
        }
        return numbers.size() == numberOfTiles && numbers.stream().allMatch(n -> n >= 0 && n < numberOfTiles );
    }
    private void printSelection(ArrayList<Coordinates> tilesSelection) {
        System.out.print("You selected the following tiles: ");
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
        System.out.printf("\n");
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
    private Boolean haveAllOneSidesFree(ArrayList<Coordinates> tilesSelection, Board board) throws VoidBoardTileException {
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

    private void printBoard() {
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
    private void printCommonGoals(){
        CommonGoalPointStack[] commonGoals = gameStatus.getCommonGoalPointStacks();
        for(int i = 0; i < commonGoals.length; i++) {
            System.out.printf("%d) %s: %d points, %s\n", i, commonGoals[i].getCommonGoal().printGoal(), commonGoals[i].getTopPoints(), gameStatus.getIsCommonGoalAlreadyAchieved()[i] ? "Achieved" : "Not achieved");
        }
    }
    public void endGame(String winnerPlayer){
        System.out.println("The game is over!");
        isEnded = true;
        System.out.println("The winner is player: " + winnerPlayer);
    }
    private void printStack() {
        CommonGoalPointStack[] stack = gameStatus.getCommonGoalPointStacks();
        for(int i = 0; i < stack.length; i++) {
            //TODO print stack
        }
    }
    private void listOfPlayers() {
        List<String> players = gameStatus.getPlayers();
        for(int i = 0; i < players.size(); i++) {
            System.out.printf("%d) %s\n", i, players.get(i));
        }
    }
    private void printPersonalGoal(){
        System.out.printf("Your personal goal is: \n");
        printBookshelf(gameStatus.getPersonalGoal().getPersonalGoalBookshelf());
    }
    private void bookshelfToPrint() throws IOException {
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
            default -> System.out.println("Command not recognized");
        }
    }
    private void printBookshelf(Bookshelf bookshelf) {
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
    public void riempiTutto() throws RemoteException, PickedColumnOutOfBoundsException, PickDoesntFitColumnException {
        client.riempiTutto();
    }
    public void run() throws IOException, AlreadyStartedGameException, MaxNumberOfPlayersException, InterruptedException, PickedColumnOutOfBoundsException, PickDoesntFitColumnException, NotBoundException {
        scanner = new Scanner(new InputStreamReader(System.in));
        System.out.println("Welcome to MyShelfie!");

        connectToServer();
        askForUsername();
        addPlayer();
        addObserver();

        while(!isEnded) {
            synchronized (scanner){
                String command = scanner.nextLine();
                if(command.equals("truccomagico")) {
                    riempiTutto();
                }
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
