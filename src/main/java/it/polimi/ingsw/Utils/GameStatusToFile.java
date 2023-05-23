package it.polimi.ingsw.Utils;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Model.Decks.ItemDeck;
import it.polimi.ingsw.Model.Decks.PersonalGoalDeck;

import java.util.ArrayList;
import java.util.Stack;

public class GameStatusToFile {
    private final int gameID;
    private final ArrayList<Stack<Integer>> commonGoalPointStacks;
    private final String[] commonGoalPointStacksNames;
    private final int currentPlayerIndex;
    private final Board board;
    //game state 0 for Finished, 1 for LastTurn, 2 for Running, 3 for Starting
    private final int gameState;
    private final ItemDeck itemDeck;
    private final PersonalGoalDeck personalGoalDeck;
    private final int maxPlayers;

    public GameStatusToFile(int gameID, ArrayList<Stack<Integer>> commonGoalPointStacks, String[] commonGoalPointStacksNames, int currentPlayerIndex, Board board, int gameState, ItemDeck itemDeck, PersonalGoalDeck personalGoalDeck, int maxPlayers) {
        this.gameID = gameID;
        this.commonGoalPointStacks = commonGoalPointStacks;
        this.commonGoalPointStacksNames = commonGoalPointStacksNames;
        this.currentPlayerIndex = currentPlayerIndex;
        this.board = board;
        this.gameState = gameState;
        this.itemDeck = itemDeck;
        this.personalGoalDeck = personalGoalDeck;
        this.maxPlayers = maxPlayers;
    }

    public ArrayList<Stack<Integer>> getCommonGoalPointStacks() {
        return commonGoalPointStacks;
    }
    public String[] getCommonGoalPointStacksNames() {
        return commonGoalPointStacksNames;
    }
    public int getGameID() {
        return gameID;
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }
    public Board getBoard() {
        return board;
    }

    public int getGameState() {
        return gameState;
    }

    public ItemDeck getItemDeck() {
        return itemDeck;
    }

    public PersonalGoalDeck getPersonalGoalDeck() {
        return personalGoalDeck;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }
}
