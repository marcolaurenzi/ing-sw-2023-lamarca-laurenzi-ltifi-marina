package it.polimi.ingsw.Utils;

import it.polimi.ingsw.Controller.Observer;
import it.polimi.ingsw.Model.Game;

import java.util.HashMap;
import java.util.List;

public class ControllerStatusToFile {
    private final Integer currentGameId;
    private final HashMap<String, Integer> alreadyUsedPlayerIds;

    private final HashMap<String, String> listCredentials;

    public ControllerStatusToFile(Integer currentGameId, HashMap<String, Integer> alreadyUsedPlayerIds , HashMap<String, String> listCredentials) {
        this.currentGameId = currentGameId;
        this.alreadyUsedPlayerIds = alreadyUsedPlayerIds;
        this.listCredentials = listCredentials;
    }


    public Integer getCurrentGameId() {
        return currentGameId;
    }

    public HashMap<String, Integer> getAlreadyUsedPlayerIds() {
        return alreadyUsedPlayerIds;
    }

    public HashMap<String, String> getListCredentials() {
        return listCredentials;
    }

}
