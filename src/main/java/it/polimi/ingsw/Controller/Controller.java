package it.polimi.ingsw.Controller;

import it.polimi.ingsw.Model.Exceptions.*;
import it.polimi.ingsw.Model.Game;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Controller extends UnicastRemoteObject implements ControllerRemoteInterface {
    static List<Game> games = new ArrayList<>();
    static Game currentGame = null;
    static Integer currentGameId = 0;
    static String currentGameFirstPlayerId = null;
    static Set<String> alreadyUsedPlayerIds = new HashSet<>();

    //maybe is useful
    public static Set<String> getAlreadyUsedPlayerIds() {
        return alreadyUsedPlayerIds;
    }

    public Controller() throws RemoteException {
    }

    public synchronized void choosePlayerId(String playerId) throws PlayerIdAlreadyInUseException {
        if(alreadyUsedPlayerIds.contains(playerId))
            throw new PlayerIdAlreadyInUseException();

        else alreadyUsedPlayerIds.add(playerId);
    }

    public synchronized int addPlayerToCreatedGame(String playerId) throws CreateNewGameException, AlreadyStartedGameException {
        if (currentGame == null)
            throw new CreateNewGameException();

        else {
            int currentGameId = currentGame.getId();
            currentGame.addPlayer(playerId);
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
        currentGameId++;
        games.add(game);

        return currentGame.getId();
    }
}
