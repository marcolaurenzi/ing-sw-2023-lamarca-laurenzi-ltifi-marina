package it.polimi.ingsw.Controller;

import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.GameState.GameStateLastTurn;
import it.polimi.ingsw.Model.GameState.GameStateRunning;
import it.polimi.ingsw.Model.PlayerState.PlayerStateSelecting;

import java.rmi.RemoteException;

public class GameThread extends Thread{
    Game game;
    public GameThread(Game game){
        this.game = game;
    }
    public void run() {
        game.getCurrentPlayer().changeState(new PlayerStateSelecting());
        while (game.getGameState() instanceof GameStateRunning || game.getGameState() instanceof GameStateLastTurn) {
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
        try {
            Controller.sendWinnerInfo(game.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
