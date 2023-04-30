package it.polimi.ingsw.Controller;

import it.polimi.ingsw.Model.Exceptions.FinishedGameException;
import it.polimi.ingsw.Model.Exceptions.MissingPlayerException;
import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.GameState.GameStateRunning;

import java.rmi.RemoteException;

public class GameThread extends Thread{
    Game game;
    public GameThread(Game game){
        this.game = game;
    }
    public void run() {
        while(game.getGameState() instanceof GameStateRunning){
            try {
                Controller.update(game.getId());
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            } catch (MissingPlayerException e) {
                throw new RuntimeException(e);
            }
            try {
                Controller.assignTurn(game.getId());
                game.nextTurn();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            } catch (MissingPlayerException e) {
                throw new RuntimeException(e);
            } catch (FinishedGameException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
