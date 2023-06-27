package it.polimi.ingsw.Controller;

import com.google.gson.Gson;
import it.polimi.ingsw.Client.RemoteClient;
import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Model.Exceptions.*;
import it.polimi.ingsw.Model.GameState.GameStateLastTurn;
import it.polimi.ingsw.Model.GameState.GameStateRunning;
import it.polimi.ingsw.Utils.ControllerStatusToFile;
import it.polimi.ingsw.Utils.GameStatusToFile;
import it.polimi.ingsw.Utils.GameStatusToSend;
import it.polimi.ingsw.Utils.PlayerStatusToFile;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.nio.file.*;

/**
 * The Controller class handles the game logic and communication between the server and clients.
 */
public class Controller extends UnicastRemoteObject implements ControllerRemoteInterface {

    /**
     * Gson object used for JSON serialization and deserialization.
     */
    private static final Gson gson = new Gson();

    /**
     * List of active games.
     */
    private static List<Game> games;

    /**
     * Currently active game.
     */
    private static Game currentGame;

    /**
     * ID of the current game.
     */
    private static Integer currentGameId;

    /**
     * ID of the first player in the current game.
     */
    private static String currentGameFirstPlayerId = null;

    /**
     * Map of already used player IDs.
     */
    private static HashMap<String, Integer> alreadyUsedPlayerIds;

    /**
     * Map of player observers.
     */
    private static  HashMap<String, Observer> listObserver;

    /**
     * Map of connected players.
     */
    private static  HashMap<String, Boolean> listConnected;

    /**
     * Map of player credentials.
     */
    private static  HashMap<String, String> listCredentials;

    /**
     * RemoteClient object for controller communication.
     */
    private static RemoteClient controllerConnection;

    /**
     * Creates an instance of the Controller class.
     * Initializes the lists and variables used to manage games and players.
     * Loads the saved games from the file system, if any.
     *
     * @throws IOException if an I/O error occurs when reading from the file system.
     */
    public Controller() throws IOException {
        games = new ArrayList<>();
        listObserver = new HashMap<>();
        listConnected = new HashMap<>();
        currentGame = null;

        try {
            Path path = Paths.get("SavedGames");
            if (Files.exists(path)) {
                DirectoryStream<Path> dirStream = Files.newDirectoryStream(path);
                Iterator<Path> it = dirStream.iterator();
                if (it.hasNext()) {

                    try (BufferedReader reader = Files.newBufferedReader(it.next())) {
                        ControllerStatusToFile controllerStatus = gson.fromJson(reader.readLine(), ControllerStatusToFile.class);

                        currentGameId = controllerStatus.getCurrentGameId();
                        alreadyUsedPlayerIds = controllerStatus.getAlreadyUsedPlayerIds();
                        listCredentials = controllerStatus.getListCredentials();

                    } catch (Exception e) {
                        System.out.println("Exception " + e + " occurred when reading from SavedGames directory");
                        e.printStackTrace();
                        System.exit(-1);
                    }


                    while (it.hasNext()) {
                        try (BufferedReader reader = Files.newBufferedReader(it.next())) {
                            ArrayList<PlayerStatusToFile> playerStatus = new ArrayList<>();
                            GameStatusToFile gameStatus = gson.fromJson(reader.readLine(), GameStatusToFile.class);

                            for (int i = 0; i < gameStatus.getMaxPlayers(); i++) {
                                playerStatus.add(gson.fromJson(reader.readLine(), PlayerStatusToFile.class));
                            }

                            Game game = new Game(gameStatus, playerStatus);
                            games.add(game);
                            GameThread gameThread = new GameThread(game);
                            gameThread.start();


                            for (PlayerStatusToFile p : playerStatus) {
                                listConnected.put(p.getPlayerID(), false);
                            }

                        } catch (Exception e) {
                            System.out.println("Exception " + e + " occurred when reading from SavedGames directory");
                            e.printStackTrace();
                            System.exit(-1);
                        }
                    }
                }

            } else {
                currentGameId = 0;
                alreadyUsedPlayerIds = new HashMap<>();
                listCredentials = new HashMap<>();
            }

        } catch (Exception e) {
            System.out.println("Exception " + e + " occurred when reading from SavedGames directory");
            e.printStackTrace();
            System.exit(-1);
        }

    }

    /**
     * Saves the current state of the controller to a file.
     * The saved file includes the current game ID, already used player IDs, and player credentials.
     */
    public void saveControllerStatus() {
        Path path = Paths.get("SavedGames");
        //if directory SavedGames doesn't exist create it:
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                System.out.println("Failed to create SavedGames directory");
            }

        }

        try {
            File file = new File(System.getProperty("user.dir"), "/SavedGames/saved_status_controller");
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            String temp = gson.toJson(new ControllerStatusToFile(currentGameId, alreadyUsedPlayerIds, listCredentials));
            writer.write(temp);
            writer.flush();
        } catch (Exception e) {
            System.out.println("Unable to save controller status");
        }
    }

    /**
     * Allows a player to choose their ID.
     *
     * @param playerId the ID chosen by the player.
     * @throws PlayerIdAlreadyInUseException if the chosen player ID is already in use.
     * @throws PlayerOnlineException         if the player with the chosen ID is already online.
     */
    public synchronized void choosePlayerId(String playerId) throws PlayerIdAlreadyInUseException, PlayerOnlineException {
        if(alreadyUsedPlayerIds.containsKey(playerId) && listConnected.get(playerId))
            throw new PlayerOnlineException();
        else if(alreadyUsedPlayerIds.containsKey(playerId))
            throw new PlayerIdAlreadyInUseException();
        else {
            alreadyUsedPlayerIds.put(playerId, null);
        }
    }

    /**
     * Adds a player to a game that has already been created.
     *
     * @param observer the observer associated with the player.
     * @param playerId the ID of the player.
     * @return the ID of the game to which the player was added.
     * @throws CreateNewGameException      if there is no game currently available to join.
     * @throws AlreadyStartedGameException if the game has already started.
     */
    public synchronized int addPlayerToCreatedGame(Observer observer, String playerId) throws CreateNewGameException, AlreadyStartedGameException {
        if(!listConnected.containsKey(playerId)) {
            if(!listObserver.containsKey(playerId)) {
                addObserver(observer, playerId);
            }
            if (currentGame == null)
                throw new CreateNewGameException();

            else {
                currentGame.addPlayer(playerId);
                listConnected.put(playerId, true);
                alreadyUsedPlayerIds.replace(playerId, currentGame.getId());
                if (currentGame.getPlayers().size() == currentGame.getMaxPlayers()) {
                    currentGame.startGame();
                    saveControllerStatus();
                    GameThread gameThread = new GameThread(currentGame);
                    gameThread.start();
                    currentGame = null;
                }
                return currentGameId++;
            }
        }

        return alreadyUsedPlayerIds.get(playerId);
    }

    /**
     * Disconnects a client with the specified player ID.
     *
     * @param playerId the ID of the player to be disconnected.
     */
    public static void disconnectClient(String playerId) {
        listConnected.replace(playerId, false);
        if(listObserver.containsKey(playerId)) {
            listObserver.remove(playerId);
        }
    }

    /**
     * Reconnects a client with the specified player ID.
     *
     * @param playerId the ID of the player to be reconnected.
     * @param observer the observer associated with the player.
     * @throws AlreadyStartedGameException if the game has already started.
     */
    private static void reconnectClient(String playerId, Observer observer) throws AlreadyStartedGameException {
        listObserver.put(playerId, observer);
        listConnected.replace(playerId, true);
    }

    /**
     * Creates a new game and adds a player to it.
     *
     * @param playerId    the ID of the player.
     * @param maxPlayers  the maximum number of players for the game.
     * @return the ID of the newly created game.
     * @throws MaxNumberOfPlayersException  if the maximum number of players is exceeded.
     * @throws IOException                  if an I/O error occurs when reading from the file system.
     * @throws AlreadyStartedGameException  if a game has already been created.
     * @throws GameAlreadyCreatedException if the game has already started.
     */
    public synchronized int createNewGameAndAddPlayer(String playerId, int maxPlayers) throws MaxNumberOfPlayersException, IOException, AlreadyStartedGameException, GameAlreadyCreatedException {
        if(currentGame != null)
            throw new GameAlreadyCreatedException();
        Game game = new Game(currentGameId, maxPlayers);
        game.addPlayer(playerId);
        listConnected.put(playerId, true);
        currentGame = game;
        alreadyUsedPlayerIds.replace(playerId, currentGame.getId());
        games.add(game);

        return currentGame.getId();
    }

    /**
     * Adds an observer to the list of observers associated with the player.
     * If the player is already in a game and the game is in the "last turn" or "running" state,
     * the observer will be updated with the current game status.
     *
     * @param observer the observer to be added.
     * @param playerId the ID of the player.
     */
    private void addObserver(Observer observer, String playerId) {
        listObserver.put(playerId, observer);

        Game tempGame = null;
        for (Game game : games) {
            if (game.getPlayers().contains(playerId)) {
                tempGame = game;
                break;
            }
        }
        if (tempGame != null && (tempGame.getGameState() instanceof GameStateLastTurn || tempGame.getGameState() instanceof GameStateRunning)) {
            try {
                if (listConnected.get(playerId)) {
                    listObserver.get(playerId).update(retrieveGameStatus(games.get(alreadyUsedPlayerIds.get(playerId)), playerId));
                }
            } catch (DisconnectedPlayerException e) {
                System.out.println("Player " + playerId + " disconnected");
                disconnectClient(playerId);
            } catch (Exception e) {
                System.out.println("Exception in controller: update  " + e);
                e.printStackTrace();
                System.exit(-1);
            }
        }
    }

    /**
     * Updates the observers associated with the players in the specified game.
     *
     * @param gameID the ID of the game to be updated.
     * @throws RemoteException     if a remote error occurs during the update.
     * @throws MissingPlayerException if a player is missing from the game.
     */
    public static void update(int gameID) throws RemoteException, MissingPlayerException {
        String playerId = null;
        try {
            for (Player player : games.get(gameID).getPlayers()) {
                if(listConnected.get(player.getPlayerID())) {
                    playerId = player.getPlayerID();
                    if(listObserver.get(player.getPlayerID()) != null) {
                        listObserver.get(player.getPlayerID()).update(retrieveGameStatus(games.get(gameID), player.getPlayerID()));
                    }
                }
            }
        } catch (DisconnectedPlayerException e) {
            System.out.println("Player " + playerId + " disconnected");
            disconnectClient(playerId);
        } catch (Exception e) {
            System.out.println("Exception in controller: update  " + e);
            e.printStackTrace();
            System.exit(-1);
        }
    }
    public void ping() throws RemoteException {
        //do nothing
    }

    /**
     * Assigns the turn to the current player in the specified game.
     *
     * @param game the ID of the game.
     * @throws Exception if an error occurs during the assignment of the turn.
     */
    public static void assignTurn(int game) throws Exception {
        if(listConnected.get(games.get(game).getCurrentPlayer().getPlayerID())) {
            try {
                listObserver.get(games.get(game).getCurrentPlayer().getPlayerID()).playTurn();
            } catch (DisconnectedPlayerException e) {
                System.out.println("Player " + games.get(game).getCurrentPlayer().getPlayerID() + " disconnected");
                disconnectClient(games.get(game).getCurrentPlayer().getPlayerID());
            }
        }
    }

    /**
     * Retrieves the game status to be sent to the player.
     *
     * @param game     the game object.
     * @param playerId the ID of the player.
     * @return the GameStatusToSend object containing the game status.
     * @throws MissingPlayerException if a player is missing from the game.
     */
    protected static GameStatusToSend retrieveGameStatus(Game game, String playerId) throws MissingPlayerException {
        Integer[]commonGoalPointStacksTops = new Integer[2];
        String[] commonGoalPointStacksNames = new String[2];
        String[] commonGoalPointStacksDescription = new String[2];

        commonGoalPointStacksTops[0] = game.getCommonGoalPointStacks()[0].getTopPoints();
        commonGoalPointStacksTops[1] = game.getCommonGoalPointStacks()[1].getTopPoints();
        commonGoalPointStacksNames[0] = game.getCommonGoalPointStacks()[0].getCommonGoal().getGoalName();
        commonGoalPointStacksNames[1] = game.getCommonGoalPointStacks()[1].getCommonGoal().getGoalName();
        commonGoalPointStacksDescription[0] = game.getCommonGoalPointStacks()[0].getCommonGoal().getGoalDescription();
        commonGoalPointStacksDescription[1] = game.getCommonGoalPointStacks()[1].getCommonGoal().getGoalDescription();

        return new GameStatusToSend(
                game.getId(),
                commonGoalPointStacksTops,
                commonGoalPointStacksNames,
                commonGoalPointStacksDescription,
                game.getIsCommonGoalAchieved(playerId),
                game.getPersonalGoal(playerId),
                game.getCurrentPlayer().getPlayerID(),
                game.getPlayersId(),
                game.getPoints(),
                game.getBookshelves(),
                game.getBoard(),
                game.getGameState() instanceof GameStateLastTurn
        );
    }

    /**
     * Sends the information about the winner to the players in the specified game.
     *
     * @param gameId the ID of the game.
     * @throws IOException                    if an I/O error occurs when reading from the file system.
     * @throws WrongMessageClassEnumException if the message class is not valid.
     * @throws InterruptedException           if the current thread is interrupted.
     */
    public static void sendWinnerInfo(int gameId) throws IOException, WrongMessageClassEnumException, InterruptedException {
        String winnerPlayer = null;
        int tempMaxPoints = 0;

        for(Player player : games.get(gameId).getPlayers()){
            if(listConnected.get(player.getPlayerID())) {
                if (player.getTotalPoints() > tempMaxPoints) {
                    tempMaxPoints = player.getTotalPoints();
                    winnerPlayer = player.getPlayerID();
                }
            }
        }
        for (Player player : games.get(gameId).getPlayers()) {
            if(listConnected.get(player.getPlayerID())) {
                try {
                    listObserver.get(player.getPlayerID()).endGame(winnerPlayer);
                } catch (DisconnectedPlayerException e) {
                    disconnectClient(player.getPlayerID());
                }
            }
        }
    }

    /**
     * Picks and inserts tiles into the bookshelf of the current player in the specified game.
     *
     * @param tilesSelection the list of selected coordinates.
     * @param column         the index of the column where the tiles will be inserted.
     * @param order          the order in which the tiles will be inserted.
     * @param playerId       the ID of the player.
     * @throws RemoteException                          if a remote error occurs during the operation.
     * @throws PlayerIsWaitingException                  if the player is waiting for their turn.
     * @throws SelectionIsEmptyException                if the selection is empty.
     * @throws SelectionNotValidException               if the selection is not valid.
     * @throws ColumnNotValidException                  if the column index is not valid.
     * @throws PickedColumnOutOfBoundsException        if the picked column is out of bounds.
     * @throws PickDoesntFitColumnException             if the picked tiles do not fit in the column.
     * @throws TilesSelectionSizeDifferentFromOrderLengthException if the size of the tiles selection is different from the length of the order array.
     * @throws VoidBoardTileException                   if the board tile is void.
     * @throws WrongConfigurationException              if the configuration is wrong.
     */
    public void pickAndInsertInBookshelf(ArrayList<Coordinates> tilesSelection, int column, int[] order, String playerId) throws RemoteException, PlayerIsWaitingException, SelectionIsEmptyException, SelectionNotValidException, ColumnNotValidException, PickedColumnOutOfBoundsException, PickDoesntFitColumnException, TilesSelectionSizeDifferentFromOrderLengthException, VoidBoardTileException, WrongConfigurationException {
        int gameId = alreadyUsedPlayerIds.get(playerId);
        Game game = null;

        for(Game g: games) {
            if(g.getId() == (gameId))
                game = g;
        }

        for(Player player : game.getPlayers()) {
            if(player.getPlayerID().equals(playerId)){
                player.pickAndInsertInBookshelf(tilesSelection, column, order);
                break;
            }
        }
    }

    /**
     * Fills current player's bookshelf.
     *
     * @throws PickedColumnOutOfBoundsException if the picked column is out of bounds.
     * @throws PickDoesntFitColumnException      if the picked tiles do not fit in the column.
     */
    public void fillCompletely() throws PickedColumnOutOfBoundsException, PickDoesntFitColumnException {
        currentGame.getCurrentPlayer().setBookshelf();
    }

    /**
     * Calculates the SHA-256 digest of the specified password.
     *
     * @param password the password to calculate the digest for.
     * @return the calculated digest.
     */
    private String calcDigest(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes(StandardCharsets.UTF_8));
            byte[] digest = md.digest();
            return String.format("%064x", new BigInteger(1, digest));
        } catch (Exception e) {
            System.out.println("Exception in controller: retrieveDigest  " + e);
            e.printStackTrace();
            System.exit(-1);
        }
        return password;
    }

    /**
     * Checks the password entered by the player and reconnects the player if the password is correct.
     *
     * @param playerId the ID of the player.
     * @param password the password entered by the player.
     * @param observer the observer associated with the player.
     * @throws WrongPasswordException if the password is incorrect.
     * @throws RemoteException       if a remote error occurs during the operation.
     */
    public void checkPassword(String playerId, String password, Observer observer) throws WrongPasswordException, RemoteException {
        String temp = calcDigest(password);
        if(!listCredentials.get(playerId).equals(temp)) {
            throw new WrongPasswordException();
        } else {
            try {
                reconnectClient(playerId, observer);
            } catch (AlreadyStartedGameException e) {
            }
        }
    }

    /**
     * Allows the player to choose a password.
     *
     * @param playerId the ID of the player.
     * @param password the chosen password.
     * @throws RemoteException                  if a remote error occurs during the operation.
     * @throws PlayerIdAlreadyInUseException     if the chosen player ID is already in use.
     */
    public void choosePassword(String playerId, String password) throws RemoteException, PlayerIdAlreadyInUseException {
        if(listCredentials.containsKey(playerId)) {
            throw new PlayerIdAlreadyInUseException();
        } else {
            String temp = calcDigest(password);
            System.out.println("Password: " + temp);
            listCredentials.put(playerId, temp);
        }
    }

    /**
     * Checks if a player is connected.
     *
     * @param playerId the ID of the player.
     * @return true if the player is connected, false otherwise.
     */
    public static boolean isPlayerConnected(String playerId) {
        return listObserver.get(playerId) != null;
    }

    /**
     * Returns the list of games.
     *
     * @return the list of games.
     */
    public static List<Game> getGames() {
        return games;
    }

    /**
     * Returns the ID of the first player in the current game.
     *
     * @return the ID of the first player.
     */
    public static String getCurrentGameFirstPlayerId() {
        return currentGameFirstPlayerId;
    }

    /**
     * Returns the map of already used player IDs.
     *
     * @return the map of already used player IDs.
     */
    public static HashMap<String, Integer> getAlreadyUsedPlayerIds() {
        return alreadyUsedPlayerIds;
    }

    /**
     * Returns the map of player observers.
     *
     * @return the map of player observers.
     */
    public static HashMap<String, Observer> getListObserver() {
        return listObserver;
    }

    /**
     * Returns the map of connected players.
     *
     * @return the map of connected players.
     */
    public static HashMap<String, Boolean> getListConnected() {
        return listConnected;
    }

    /**
     * Returns the map of player credentials.
     *
     * @return the map of player credentials.
     */
    public static HashMap<String, String> getListCredentials() {
        return listCredentials;
    }
}
