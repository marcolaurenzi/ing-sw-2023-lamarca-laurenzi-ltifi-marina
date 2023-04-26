package it.polimi.ingsw.Client;

import it.polimi.ingsw.Model.Exceptions.*;

import java.io.IOException;

public interface Client {

    public void choosePlayerId(String playerId) throws PlayerIdAlreadyInUseException, IOException;
    public int addPlayerToCreatedGame(String playerId) throws AlreadyStartedGameException, CreateNewGameException, IOException;
    public int createNewGameAndAddPlayer(String playerId, int maxPlayers) throws MaxNumberOfPlayersException, GameAlreadyCreatedException, AlreadyStartedGameException, IOException;
}
