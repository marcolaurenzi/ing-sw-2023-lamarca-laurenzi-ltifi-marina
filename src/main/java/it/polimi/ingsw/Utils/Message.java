package it.polimi.ingsw.Utils;

import it.polimi.ingsw.Model.Coordinates;
import it.polimi.ingsw.Utils.MessageEnums.ExceptionEnum;
import it.polimi.ingsw.Utils.MessageEnums.MessageTypeEnum;
import it.polimi.ingsw.Utils.MessageEnums.MethodNameEnum;

import java.util.ArrayList;
import java.util.List;

public class Message {

    //TODO stop using object params and return value and start using StringParameters, IntReturnValue, ecc (for correct json serialization)
    private final MessageTypeEnum type;
    private final ExceptionEnum exception;
    private final Object returnValue;
    private final MethodNameEnum method;
    private final List<Object> parameters;
    private final GameStatusToSend gameStatusParam;

    private final ArrayList<Coordinates> selectionParam;

    public Message(
            MessageTypeEnum type,
            ExceptionEnum exception,
            Object returnValue,
            MethodNameEnum method,
            List<Object> parameters,
            GameStatusToSend gameStatusParam,
            ArrayList<Coordinates> selectionParam) {
        this.type = type;
        this.exception = exception;
        this.returnValue = returnValue;
        this.method = method;
        this.parameters = parameters;
        this.gameStatusParam = gameStatusParam;
        this.selectionParam = selectionParam;
    }

    public MessageTypeEnum getType() {
        return type;
    }
    public ExceptionEnum getException() { return exception; }
    public MethodNameEnum getMethod() {
        return method;
    }
    public Object getReturnValue() { return returnValue; }
    public List<Object> getParameters() { return parameters; }
    public GameStatusToSend getGameStatusParam() { return gameStatusParam;}
    public ArrayList<Coordinates> getSelectionParam() { return selectionParam; }

}
