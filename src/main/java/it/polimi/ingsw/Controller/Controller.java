package it.polimi.ingsw.Controller;

import it.polimi.ingsw.Model.Exceptions.AlreadyStartedGameException;
import it.polimi.ingsw.Model.Game;

public class Controller {

    public void addPLayer(Game game, String playerId) throws AlreadyStartedGameException {
        game.addPlayer(playerId);
    }

    public void pickCard() {

    }


}
