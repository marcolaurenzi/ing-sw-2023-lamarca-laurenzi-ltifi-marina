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

/**
 * The GameThread class represents a thread that handles the execution of a game.
 */
public class GameThread extends Thread {

    private final Gson gson = new Gson();
    private final Game game;

    /**
     * Constructs a GameThread object.
     *
     * @param game the game to be executed by the thread
     */
    public GameThread(Game game) {
        this.game = game;
    }

    /**
     * Saves the current state of the game to a file.
     *
     * @throws IOException if an I/O error occurs while writing to the file
     */
    private void saveGameStateToFile() throws IOException {
        File file = new File(System.getProperty("user.dir"), "/SavedGames/saved_status_game_" + game.getId());
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter writer = new BufferedWriter(fileWriter);

        // save game status
        writer.write(gson.toJson(game.getGameStatusToFile()));
        // save players status
        for (Player p : game.getPlayers()) {
            writer.newLine();
            String temp = gson.toJson(p.getPlayerStatusToFile());
            writer.write(temp);
        }

        writer.flush();
    }

    /**
     * Starts the execution of the game thread.
     */
    public void run() {
        game.getCurrentPlayer().changeState(new PlayerStateSelecting());
        while (game.getGameState() instanceof GameStateRunning || game.getGameState() instanceof GameStateLastTurn) {
            try {
                if (Controller.isPlayerConnected(game.getCurrentPlayer().getPlayerID())) {
                    synchronized (game) {
                        Controller.update(game.getId());
                        Controller.assignTurn(game.getId());
                        game.notifyAll();
                    }
                    game.refreshBoard();
                }
                game.nextTurn();

                for (int i = 0; i < game.getPlayers().size(); i++) {
                    if (!Controller.isPlayerConnected(game.getCurrentPlayer().getPlayerID())) {
                        game.nextTurn();
                        if (i == game.getMaxPlayers() - 1) {
                            System.out.println("Porcozio" + i);
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
