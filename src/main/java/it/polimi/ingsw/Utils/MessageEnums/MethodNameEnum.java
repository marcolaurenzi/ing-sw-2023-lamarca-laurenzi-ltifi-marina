package it.polimi.ingsw.Utils.MessageEnums;

/**
 * Enumeration of method names used in messages.
 * Each method name corresponds to a specific action or operation in the game.
 */
public enum MethodNameEnum {
    /**
     * Method name for choosing a player ID.
     */
    choosePlayerId,

    /**
     * Method name for adding a player to a created game.
     */
    addPlayerToCreatedGame,

    /**
     * Method name for creating a new game and adding a player.
     */
    createNewGameAndAddPlayer,

    /**
     * Method name for adding an observer.
     */
    addObserver,

    /**
     * Method name for updating a game state.
     */
    update,

    /**
     * Method name for playing a turn.
     */
    playTurn,

    /**
     * Method name for ending a game.
     */
    endGame,

    /**
     * Method name for choosing a password.
     */
    choosePassword,

    /**
     * Method name for checking a password.
     */
    checkPassword,

    /**
     * Method name for picking and inserting a dice in the bookshelf.
     */
    pickAndInsertInBookshelf
}
