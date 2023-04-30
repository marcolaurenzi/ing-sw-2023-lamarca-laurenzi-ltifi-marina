package it.polimi.ingsw.Controller;

import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.GameState.GameStateRunning;
import it.polimi.ingsw.Model.PlayerStates.PlayerStateSelecting;

public class GameThread extends Thread{
    Game game;
    public GameThread(Game game){
        this.game = game;
    }
    public void run() {
        game.getCurrentPlayer().changeState(new PlayerStateSelecting());
        while (game.getGameState() instanceof GameStateRunning) {
            try {
                Controller.update(game.getId());
            } catch (Exception e) {
                System.out.println("Exception: " + e);
                e.printStackTrace();
                System.exit(-1);
            }
            try {
                Controller.assignTurn(game.getId());
                game.nextTurn();
            } catch (Exception s) {
                System.out.println("Exception: " + s);
                s.printStackTrace();
                System.exit(-1);
            }
        }
    }
}
