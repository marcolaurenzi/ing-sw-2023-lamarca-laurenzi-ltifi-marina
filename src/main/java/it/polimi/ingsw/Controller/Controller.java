package it.polimi.ingsw.Controller;

import it.polimi.ingsw.Client.RemoteObserver;
import it.polimi.ingsw.Model.Exceptions.*;
import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.Player;
import it.polimi.ingsw.Utils.GameStatus;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Controller extends UnicastRemoteObject implements ControllerRemoteInterface {
    static List<Game> games = new ArrayList<>();
    static Game currentGame = null;
    static Integer currentGameId = 0;
    static String currentGameFirstPlayerId = null;
    static HashMap<String, Game> alreadyUsedPlayerIds = new HashMap<>();
    static HashMap<String, RemoteObserver> listObserver = new HashMap<>();
    public Controller() throws RemoteException {
        //thread.start();
    }

    public synchronized void choosePlayerId(String playerId) throws PlayerIdAlreadyInUseException {
        if(alreadyUsedPlayerIds.keySet().contains(playerId))
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
    public void addObserver(RemoteObserver observer, String playerId) throws RemoteException{
        listObserver.put(playerId, observer);
    }
    public static void update(int gameID) throws RemoteException, MissingPlayerException {
        try {
            for (Player player : games.get(gameID).getPlayers()) {
                listObserver.get(player.getPlayerID()).update(retrieveGameStatus(games.get(gameID), player.getPlayerID()));
            }
        } catch (MissingPlayerException e) {
            System.out.println("Missing player in game " + gameID);
            e.printStackTrace();
            System.exit(-1);
        }
    }
    public static void assignTurn(int game) throws RemoteException, MissingPlayerException, VoidBoardTileException {
        listObserver.get(games.get(game).getCurrentPlayer().getPlayerID()).playTurn();
    }
    private static GameStatus retrieveGameStatus(Game game, String playerId) throws MissingPlayerException {
        GameStatus gameStatus = new GameStatus(
                game.getId(),
                game.getCommonGoalPointStacks(),
                game.getIsCommonGoalAchieved(playerId),
                game.getPersonalGoal(playerId),
                game.getCurrentPlayer().getPlayerID(),
                game.getPlayersId(),
                game.getBookshelves(),
                game.getBoard());
        return gameStatus;
    }
}
