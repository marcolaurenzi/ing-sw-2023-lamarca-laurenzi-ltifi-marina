package it.polimi.ingsw.Controller;

import it.polimi.ingsw.Model.Exceptions.*;
import it.polimi.ingsw.Utils.GameStatus;

import java.rmi.RemoteException;

public class ObserverSocket implements Observer{
    @Override
    public void update(GameStatus game) {

    }

    @Override
    public void playTurn() throws RemoteException, VoidBoardTileException, SelectionNotValidException, PlayerIsWaitingException, TilesSelectionSizeDifferentFromOrderLengthException, ColumnNotValidException, SelectionIsEmptyException, WrongConfigurationException, PickedColumnOutOfBoundsException, PickDoesntFitColumnException {

    }

    @Override
    public void endGame(String winnerPlayer) {

    }
}
