package it.polimi.ingsw.Controller;

import com.google.gson.Gson;
import it.polimi.ingsw.Model.Exceptions.*;
import it.polimi.ingsw.Utils.GameStatusToSend;
import it.polimi.ingsw.Utils.Message;
import it.polimi.ingsw.Utils.MessageEnums.MessageTypeEnum;
import it.polimi.ingsw.Utils.MessageEnums.MethodNameEnum;
import it.polimi.ingsw.Utils.ProxyDataInputStream;
import it.polimi.ingsw.Utils.ProxyDataOutputStream;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ObserverSocket implements Observer, Serializable {
    private final ProxyDataOutputStream dataOutput;
    private final ProxyDataInputStream dataInput;

    private final Gson gson = new Gson();

    public ObserverSocket(ProxyDataInputStream dataInput, ProxyDataOutputStream dataOutput) {
        this.dataInput = dataInput;
        this.dataOutput = dataOutput;
    }

    @Override
    public void update(GameStatusToSend gameStatus) throws IOException, InterruptedException, DisconnectedPlayerException, WrongMessageClassEnumException {
        List<Object> parameters = new ArrayList<>();
        parameters.add(gameStatus);
        dataOutput.writeUTF(gson.toJson(new Message(MessageTypeEnum.methodCall, null, null, MethodNameEnum.update, null, gameStatus, null)));

        Message response = gson.fromJson(dataInput.readUTF(), Message.class);

        if(response.getType().equals(MessageTypeEnum.exception)) {
            switch (response.getException()) {
                case DisconnectedPlayerException -> throw new DisconnectedPlayerException();
            }
        }
    }

    @Override
    public void playTurn() throws VoidBoardTileException, SelectionNotValidException, PlayerIsWaitingException, TilesSelectionSizeDifferentFromOrderLengthException, ColumnNotValidException, SelectionIsEmptyException, WrongConfigurationException, PickedColumnOutOfBoundsException, PickDoesntFitColumnException, IOException, InterruptedException, WrongMessageClassEnumException, DisconnectedPlayerException {
        dataOutput.writeUTF(gson.toJson(new Message(MessageTypeEnum.methodCall, null, null, MethodNameEnum.playTurn, null, null, null)));

        Message response = gson.fromJson(dataInput.readUTF(), Message.class);

        if(response.getType().equals(MessageTypeEnum.exception)) {
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

    @Override
    public void endGame(String winnerPlayer) throws IOException, DisconnectedPlayerException, WrongMessageClassEnumException, InterruptedException {
        List<Object> parameters = new ArrayList<>();
        parameters.add(winnerPlayer);
        dataOutput.writeUTF(gson.toJson(new Message(MessageTypeEnum.methodCall, null, null, MethodNameEnum.endGame, parameters, null, null)));
        Message response = gson.fromJson(dataInput.readUTF(), Message.class);

        if(response.getType().equals(MessageTypeEnum.exception)) {
            switch (response.getException()) {
                case DisconnectedPlayerException -> throw new DisconnectedPlayerException();
            }
        }

    }


}
