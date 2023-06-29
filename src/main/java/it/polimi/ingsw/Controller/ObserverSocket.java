package it.polimi.ingsw.Controller;

import com.google.gson.Gson;
import it.polimi.ingsw.Model.Exceptions.*;
import it.polimi.ingsw.Utils.GameStatusToSend;
import it.polimi.ingsw.Utils.Message;
import it.polimi.ingsw.Utils.MessageEnums.ExceptionEnum;
import it.polimi.ingsw.Utils.MessageEnums.MessageTypeEnum;
import it.polimi.ingsw.Utils.MessageEnums.MethodNameEnum;
import it.polimi.ingsw.Utils.ProxyDataInputStream;
import it.polimi.ingsw.Utils.ProxyDataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *The ObserverSocket class represents an observer that communicates with the server via socket.
 */
public class ObserverSocket implements Observer, Serializable {
    /**
     *The input stream for receiving data.
     */
    private final ProxyDataOutputStream dataOutput;
    /**
     *The output stream for sending data.
     */
    private final ProxyDataInputStream dataInput;
    /**
     *The Gson object for serializing and deserializing objects.
     */
    private final Gson gson = new Gson();

    /**
     * Constructs an ObserverSocket object.
     *
     * @param dataInput  the input stream for receiving data.
     * @param dataOutput the output stream for sending data.
     */
    public ObserverSocket(ProxyDataInputStream dataInput, ProxyDataOutputStream dataOutput) {
        this.dataInput = dataInput;
        this.dataOutput = dataOutput;
    }

    /**
     * Updates the observer with the current game status.
     *
     * @param gameStatus the current game status to be sent to the observer.
     * @throws IOException                   if an I/O error occurs.
     * @throws InterruptedException          if the thread is interrupted.
     * @throws DisconnectedPlayerException    if a player is disconnected.
     * @throws WrongMessageClassEnumException if an incorrect message class enum is received.
     */
    @Override
    public void update(GameStatusToSend gameStatus) throws IOException, InterruptedException, DisconnectedPlayerException, WrongMessageClassEnumException {
        List<Object> parameters = new ArrayList<>();
        parameters.add(gameStatus);
        dataOutput.writeUTF(gson.toJson(new Message(MessageTypeEnum.methodCall, null, null, MethodNameEnum.update, null, gameStatus, null)));

        Message response = gson.fromJson(dataInput.readUTF(), Message.class);

        if (response.getType().equals(MessageTypeEnum.exception)) {
            if (Objects.requireNonNull(response.getException()) == ExceptionEnum.DisconnectedPlayerException) {
                throw new DisconnectedPlayerException();
            }
        }
    }

    /**
     * Plays a turn.
     *
     * @throws VoidBoardTileException                     if a board tile is void.
     * @throws SelectionNotValidException                 if the selection is not valid.
     * @throws PlayerIsWaitingException                   if a player is waiting.
     * @throws TilesSelectionSizeDifferentFromOrderLengthException if the size of the selected tiles is different from the order length.
     * @throws ColumnNotValidException                    if the selected column is not valid.
     * @throws SelectionIsEmptyException                  if the selection is empty.
     * @throws WrongConfigurationException                if the configuration is wrong.
     * @throws PickedColumnOutOfBoundsException           if the picked column is out of bounds.
     * @throws PickDoesntFitColumnException               if the picked tile doesn't fit in the selected column.
     * @throws IOException                                if an I/O error occurs.
     * @throws InterruptedException                       if the thread is interrupted.
     * @throws WrongMessageClassEnumException              if an incorrect message class enum is received.
     * @throws DisconnectedPlayerException                 if a player is disconnected.
     */
    @Override
    public void playTurn() throws VoidBoardTileException, SelectionNotValidException, PlayerIsWaitingException, TilesSelectionSizeDifferentFromOrderLengthException, ColumnNotValidException, SelectionIsEmptyException, WrongConfigurationException, PickedColumnOutOfBoundsException, PickDoesntFitColumnException, IOException, InterruptedException, WrongMessageClassEnumException, DisconnectedPlayerException {
        dataOutput.writeUTF(gson.toJson(new Message(MessageTypeEnum.methodCall, null, null, MethodNameEnum.playTurn, null, null, null)));

        Message response = gson.fromJson(dataInput.readUTF(), Message.class);

        if (response.getType().equals(MessageTypeEnum.exception)) {
            switch (response.getException()) {
                case VoidBoardTileException -> throw new VoidBoardTileException();
                case SelectionNotValidException -> throw new SelectionNotValidException();
                case PlayerIsWaitingException -> throw new PlayerIsWaitingException();
                case TilesSelectionSizeDifferentFromOrderLengthException -> throw new TilesSelectionSizeDifferentFromOrderLengthException();
                case ColumnNotValidException -> throw new ColumnNotValidException();
                case SelectionIsEmptyException -> throw new SelectionIsEmptyException();
                case WrongConfigurationException -> throw new WrongConfigurationException();
                case PickedColumnOutOfBoundsException -> throw new PickedColumnOutOfBoundsException();
                case PickDoesntFitColumnException -> throw new PickDoesntFitColumnException();
                case DisconnectedPlayerException -> throw new DisconnectedPlayerException();
            }
        }
    }

    /**
     * Ends the game and determines the winner player.
     *
     * @param winnerPlayer the name of the winning player.
     * @throws IOException                   if an I/O error occurs.
     * @throws DisconnectedPlayerException    if a player is disconnected.
     * @throws WrongMessageClassEnumException if an incorrect message class enum is received.
     * @throws InterruptedException          if the thread is interrupted.
     */
    @Override
    public void endGame(String winnerPlayer) throws IOException, DisconnectedPlayerException, WrongMessageClassEnumException, InterruptedException {
        List<Object> parameters = new ArrayList<>();
        parameters.add(winnerPlayer);
        dataOutput.writeUTF(gson.toJson(new Message(MessageTypeEnum.methodCall, null, null, MethodNameEnum.endGame, parameters, null, null)));
        Message response = gson.fromJson(dataInput.readUTF(), Message.class);

        if (response.getType().equals(MessageTypeEnum.exception)) {
            if (Objects.requireNonNull(response.getException()) == ExceptionEnum.DisconnectedPlayerException) {
                throw new DisconnectedPlayerException();
            }
        }
    }
}
