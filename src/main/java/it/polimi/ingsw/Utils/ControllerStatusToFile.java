package it.polimi.ingsw.Utils;

import it.polimi.ingsw.Controller.Observer;
import it.polimi.ingsw.Model.Game;

import java.util.HashMap;
import java.util.List;

/**
 * This class represents the controller status to file, which contains information about the current game ID,
 * already used player IDs, and a list of credentials.
 */
public class ControllerStatusToFile {
    /**
     * The ID of the current game.
     */
    private final Integer currentGameId;
    /**
     * A mapping of player names to their corresponding used IDs.
     */
    private final HashMap<String, Integer> alreadyUsedPlayerIds;
    /**
     * A mapping of player names to their corresponding credentials.
     */
    private final HashMap<String, String> listCredentials;

    /**
     * Constructs a ControllerStatusToFile object with the specified current game ID, already used player IDs,
     * and list of credentials.
     *
     * @param currentGameId         the ID of the current game
     * @param alreadyUsedPlayerIds  a mapping of player names to their corresponding used IDs
     * @param listCredentials       a mapping of player names to their corresponding credentials
     */
    public ControllerStatusToFile(Integer currentGameId, HashMap<String, Integer> alreadyUsedPlayerIds,
                                  HashMap<String, String> listCredentials) {
        this.currentGameId = currentGameId;
        this.alreadyUsedPlayerIds = alreadyUsedPlayerIds;
        this.listCredentials = listCredentials;
    }

    /**
     * Returns the current game ID.
     *
     * @return the current game ID
     */
    public Integer getCurrentGameId() {
        return currentGameId;
    }

    /**
     * Returns a mapping of player names to their corresponding used IDs.
     *
     * @return the mapping of already used player IDs
     */
    public HashMap<String, Integer> getAlreadyUsedPlayerIds() {
        return alreadyUsedPlayerIds;
    }

    /**
     * Returns a mapping of player names to their corresponding credentials.
     *
     * @return the mapping of player credentials
     */
    public HashMap<String, String> getListCredentials() {
        return listCredentials;
    }
}
