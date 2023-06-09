package it.polimi.ingsw.Controller;

import com.google.gson.Gson;
import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.GameState.GameStateLastTurn;
import it.polimi.ingsw.Model.GameState.GameStateRunning;
import it.polimi.ingsw.Model.Player;
import it.polimi.ingsw.Model.PlayerState.PlayerStateSelecting;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;

public class GameThread extends Thread{

    private final Gson gson = new Gson();
    private final Game game;

    public GameThread(Game game){
        this.game = game;
    }

    private void saveGameStateToFile() throws IOException {
        File file = new File(System.getProperty("user.dir"), "/SavedGames/saved_status_game_" + game.getId());
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter writer = new BufferedWriter(fileWriter);


        //save game status
        writer.write(gson.toJson(game.getGameStatusToFile()));
        //save players status
        for(Player p : game.getPlayers()) {
            writer.newLine();
            String temp = gson.toJson(p.getPlayerStatusToFile());
            writer.write(temp);
        }

        writer.flush();
    }


    public void run() {
        game.getCurrentPlayer().changeState(new PlayerStateSelecting());
        while (game.getGameState() instanceof GameStateRunning || game.getGameState() instanceof GameStateLastTurn) {
            try {
                if(Controller.isPlayerConnected(game.getCurrentPlayer().getPlayerID())) {
                    Controller.update(game.getId());
                    Controller.assignTurn(game.getId());
                    game.refreshBoard();
                }
                    game.nextTurn();

                for(int i = 0; i < game.getPlayers().size(); i++){
                    if(!Controller.isPlayerConnected(game.getCurrentPlayer().getPlayerID())) {
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

            try {
                saveGameStateToFile();
            } catch (IOException e) {
                System.out.println("Unable to save game status");
            }
        }
        try {
            Controller.sendWinnerInfo(game.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
