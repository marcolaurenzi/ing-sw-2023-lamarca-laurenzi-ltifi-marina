package it.polimi.ingsw.Utils.MessageEnums;

/**
 * This enumeration represents the different types of exceptions that can occur in the game.
 */
public enum ExceptionEnum {
    AlreadyStartedGameException,
    PlayerIdAlreadyInUseException,
    CreateNewGameException,
    MaxNumberOfPlayersException,
    GameAlreadyCreatedException,
    VoidBoardTileException,
    SelectionNotValidException,
    PlayerIsWaitingException,
    TilesSelectionSizeDifferentFromOrderLengthException,
    ColumnNotValidException,
    SelectionIsEmptyException,
    WrongConfigurationException,
    PickedColumnOutOfBoundsException,
    WrongPasswordException,
    DisconnectedPlayerException,
    PlayerOnlineException,
    PickDoesntFitColumnException
}
