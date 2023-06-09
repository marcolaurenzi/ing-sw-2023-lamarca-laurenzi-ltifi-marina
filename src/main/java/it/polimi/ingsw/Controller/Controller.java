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
public class Controller extends UnicastRemoteObject implements ControllerRemoteInterface {

    //TODO why is everything static? GUCCI
    private static final Gson gson = new Gson();
    private static List<Game> games;
    private static Game currentGame;
    private static Integer currentGameId;
    private static String currentGameFirstPlayerId = null;
    private static HashMap<String, Integer> alreadyUsedPlayerIds;
    private static  HashMap<String, Observer> listObserver;
    private static  HashMap<String, Boolean> listConnected; //if connected cant choose that user id
    private static  HashMap<String, String> listCredentials;
    private static RemoteClient controllerConnection;

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
    //the controller status is saved when a new game is started
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


    public synchronized void choosePlayerId(String playerId) throws PlayerIdAlreadyInUseException {
        if(alreadyUsedPlayerIds.containsKey(playerId))
            throw new PlayerIdAlreadyInUseException();
        else {
            alreadyUsedPlayerIds.put(playerId, null);
        }
    }

    public synchronized int addPlayerToCreatedGame(Observer observer, String playerId) throws CreateNewGameException, AlreadyStartedGameException {
        if(!listConnected.containsKey(playerId)) { //gestire meglio questo metodo
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
    public static void disconnectClient(String playerId) {
        listConnected.replace(playerId, false);
        if(listObserver.containsKey(playerId)) {
            listObserver.remove(playerId);
        }
    }
    private static void reconnectClient(String playerId, Observer observer) throws AlreadyStartedGameException {
        listObserver.put(playerId, observer);
        listConnected.replace(playerId, true);
    }
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
    protected static GameStatusToSend retrieveGameStatus(Game game, String playerId) throws MissingPlayerException {
        int[]commonGoalPointStacksTops = new int[2];
        String[] commonGoalPointStacksNames = new String[2];
        String[] commonGoalPointStacksDescription = new String[2];

        commonGoalPointStacksTops[0] = game.getCommonGoalPointStacks()[0].getTopPoints();
        commonGoalPointStacksTops[1] = game.getCommonGoalPointStacks()[1].getTopPoints();
        commonGoalPointStacksNames[0] = game.getCommonGoalPointStacks()[0].getCommonGoal().getGoalName();
        commonGoalPointStacksNames[1] = game.getCommonGoalPointStacks()[1].getCommonGoal().getGoalName();
        commonGoalPointStacksDescription[0] = game.getCommonGoalPointStacks()[0].getCommonGoal().getGoalName();
        commonGoalPointStacksDescription[1] = game.getCommonGoalPointStacks()[1].getCommonGoal().getGoalName();

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
    public void riempiTutto() throws PickedColumnOutOfBoundsException, PickDoesntFitColumnException {
        currentGame.getCurrentPlayer().setBookshelf();
    }
    private String calcDigest(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes(StandardCharsets.UTF_8));
            byte[] digest = md.digest();
            String hex = String.format("%064x", new BigInteger(1, digest));
            return hex;
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Exception in controller: retrieveDigest  " + e);
            e.printStackTrace();
            System.exit(-1);
        } catch (Exception e) {
            System.out.println("Exception in controller: retrieveDigest  " + e);
            e.printStackTrace();
            System.exit(-1);
        }
        return password;
    }
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
    public void choosePassword(String playerId, String password) throws RemoteException, PlayerIdAlreadyInUseException {
        if(listCredentials.containsKey(playerId)) {
            throw new PlayerIdAlreadyInUseException();
        } else {
            String temp = calcDigest(password);
            System.out.println("Password: " + temp);
            listCredentials.put(playerId, temp);
        }
    }

    public static boolean isPlayerConnected(String playerId) {
        return listObserver.get(playerId) != null;
    }

    public static List<Game> getGames() {
        return games;
    }

    public static String getCurrentGameFirstPlayerId() {
        return currentGameFirstPlayerId;
    }

    public static HashMap<String, Integer> getAlreadyUsedPlayerIds() {
        return alreadyUsedPlayerIds;
    }

    public static HashMap<String, Observer> getListObserver() {
        return listObserver;
    }

    public static HashMap<String, Boolean> getListConnected() {
        return listConnected;
    }

    public static HashMap<String, String> getListCredentials() {
        return listCredentials;
    }
}
