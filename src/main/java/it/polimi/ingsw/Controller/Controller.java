package it.polimi.ingsw.Controller;

import it.polimi.ingsw.Model.Exceptions.*;
import it.polimi.ingsw.Model.Game;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class Controller extends UnicastRemoteObject implements ControllerRemoteInterface {
    static List<Game> games = new ArrayList<>();
    static Game currentGame = null;
    static Integer currentGameId = 0;
    static String currentGameFirstPlayerId = null;
    static HashMap<String, Game> alreadyUsedPlayerIds = new HashMap<>();

    //maybe is useful
    public static Set<String> getAlreadyUsedPlayerIds() {
        return alreadyUsedPlayerIds.keySet();
    }

    public Controller() throws RemoteException {
    }

    public synchronized void choosePlayerId(String playerId) throws PlayerIdAlreadyInUseException {
        if(getAlreadyUsedPlayerIds().contains(playerId))
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
            if(currentGame.getPlayers().size() == currentGame.getMaxPlayers())
                currentGame = null;

            return currentGameId;
        }
    }
    public synchronized int createNewGameAndAddPlayer(String playerId, int maxPlayers) throws MaxNumberOfPlayersException, IOException, AlreadyStartedGameException, GameAlreadyCreatedException {
        if(currentGame != null)
            throw new GameAlreadyCreatedException();
        Game game = new Game(currentGameId, maxPlayers);
        game.initializeGame();
        game.addPlayer(playerId);
        currentGame = game;
        alreadyUsedPlayerIds.replace(playerId, currentGame);
        currentGameId++;
        games.add(game);

        return currentGame.getId();
    }
}
