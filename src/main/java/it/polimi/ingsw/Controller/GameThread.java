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
                game.refreshBoard();
                game.nextTurn();
                for(int i = 0;i < game.getPlayers().size(); i++){
                    if(!Controller.listConnected.get(game.getCurrentPlayer().getPlayerID())) {
                        game.nextTurn();
                        if(i == game.getMaxPlayers()-1){
                            Thread.sleep(1000);
                        }
                    } else {
                        break;
                    }
                }
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
