package it.polimi.ingsw.Controller;

import it.polimi.ingsw.Client.RemoteClient;
import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Model.Exceptions.*;
import it.polimi.ingsw.Model.GameState.GameStateLastTurn;
import it.polimi.ingsw.Utils.GameStatus;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static it.polimi.ingsw.Server.Server.controller;

public class Controller extends UnicastRemoteObject implements ControllerRemoteInterface {
    static List<Game> games = new ArrayList<>();
    static Game currentGame = null;
    static Integer currentGameId = 0;
    static String currentGameFirstPlayerId = null;
    static HashMap<String, Game> alreadyUsedPlayerIds = new HashMap<>();
    static HashMap<String, Observer> listObserver = new HashMap<>();
    static RemoteClient controllerConnection;
    public Controller() throws RemoteException {
        //thread.start();
    }

    public synchronized void choosePlayerId(String playerId) throws PlayerIdAlreadyInUseException {
        if(alreadyUsedPlayerIds.containsKey(playerId))
            throw new PlayerIdAlreadyInUseException();

        else alreadyUsedPlayerIds.put(playerId, null);
    }

    public synchronized int addPlayerToCreatedGame(String playerId) throws CreateNewGameException, AlreadyStartedGameException {
        if (currentGame == null)
            throw new CreateNewGameException();

        else {
            int currentGameId = currentGame.getId();
            currentGame.addPlayer(playerId);
            alreadyUsedPlayerIds.replace(playerId, currentGame);
            if(currentGame.getPlayers().size() == currentGame.getMaxPlayers()) {
                currentGame.startGame();
                GameThread gameThread = new GameThread(currentGame);
                gameThread.start();
                currentGame = null;
            }
            return currentGameId;
        }
    }
    public synchronized int createNewGameAndAddPlayer(String playerId, int maxPlayers) throws MaxNumberOfPlayersException, IOException, AlreadyStartedGameException, GameAlreadyCreatedException {
        if(currentGame != null)
            throw new GameAlreadyCreatedException();
        Game game = new Game(currentGameId, maxPlayers);
        game.addPlayer(playerId);
        currentGame = game;
        alreadyUsedPlayerIds.replace(playerId, currentGame);
        currentGameId++;
        games.add(game);

        return currentGame.getId();
    }
    public void addObserver(Observer observer, String playerId) throws RemoteException{
        listObserver.put(playerId, observer);
    }
    public static void update(int gameID) throws RemoteException, MissingPlayerException {
        try {
            for (Player player : games.get(gameID).getPlayers()) {
                listObserver.get(player.getPlayerID()).update(retrieveGameStatus(games.get(gameID), player.getPlayerID()));
            }
        } catch (Exception e) {
            System.out.println("Exception in controller: update  " + e);
            e.printStackTrace();
            System.exit(-1);
        }
    }
    public static void assignTurn(int game) throws Exception {
        listObserver.get(games.get(game).getCurrentPlayer().getPlayerID()).playTurn();
    }
    protected static GameStatus retrieveGameStatus(Game game, String playerId) throws MissingPlayerException {
        int[]commonGoalPointStacksTops = new int[2];
        String[] commonGoalPointStacksNames = new String[2];
        String[] commonGoalPointStacksDescription = new String[2];

        commonGoalPointStacksTops[0] = game.getCommonGoalPointStacks()[0].getTopPoints();
        commonGoalPointStacksTops[1] = game.getCommonGoalPointStacks()[1].getTopPoints();
        commonGoalPointStacksNames[0] = game.getCommonGoalPointStacks()[0].getCommonGoal().getGoalName();
        commonGoalPointStacksNames[1] = game.getCommonGoalPointStacks()[1].getCommonGoal().getGoalName();
        commonGoalPointStacksDescription[0] = game.getCommonGoalPointStacks()[0].getCommonGoal().getGoalName();
        commonGoalPointStacksDescription[1] = game.getCommonGoalPointStacks()[1].getCommonGoal().getGoalName();

        return new GameStatus(
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
    public static void sendWinnerInfo(int gameId) throws IOException {
        String winnerPlayer = null;
        int tempMaxPoints = 0;
        for(Player player : games.get(gameId).getPlayers()){
            if(player.getTotalPoints() > tempMaxPoints){
                tempMaxPoints = player.getTotalPoints();
                winnerPlayer = player.getPlayerID();
            }
        }
        for (Player player : games.get(gameId).getPlayers()) {
            listObserver.get(player.getPlayerID()).endGame(winnerPlayer);
        }
    }
    public void pickAndInsertInBookshelf(ArrayList<Coordinates> tilesSelection, int column, int[] order, String playerId) throws RemoteException, PlayerIsWaitingException, SelectionIsEmptyException, SelectionNotValidException, ColumnNotValidException, PickedColumnOutOfBoundsException, PickDoesntFitColumnException, TilesSelectionSizeDifferentFromOrderLengthException, VoidBoardTileException, WrongConfigurationException {
        for(Player player : alreadyUsedPlayerIds.get(playerId).getPlayers()) {
            if(player.getPlayerID().equals(playerId)){
                player.pickAndInsertInBookshelf(tilesSelection, column, order);
                break;
            }
        }
    }
    public void riempiTutto() throws PickedColumnOutOfBoundsException, PickDoesntFitColumnException {
        currentGame.getCurrentPlayer().setBookshelf();
    }
}
