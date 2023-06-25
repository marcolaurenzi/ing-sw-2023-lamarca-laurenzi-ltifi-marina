package it.polimi.ingsw.Utils;

import it.polimi.ingsw.Model.Coordinates;
import it.polimi.ingsw.Utils.MessageEnums.ExceptionEnum;
import it.polimi.ingsw.Utils.MessageEnums.MessageTypeEnum;
import it.polimi.ingsw.Utils.MessageEnums.MethodNameEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a message that is sent between the server and clients during communication.
 * It encapsulates various information such as the message type, exception, return value, method name, parameters, and game status.
 */
public class Message {

    private final MessageTypeEnum type;
    private final ExceptionEnum exception;
    private final Object returnValue;
    private final MethodNameEnum method;
    private final List<Object> parameters;
    private final GameStatusToSend gameStatusParam;
    private final ArrayList<Coordinates> selectionParam;

    /**
     * Constructs a new Message object with the specified parameters.
     *
     * @param type             the message type
     * @param exception        the exception type
     * @param returnValue      the return value
     * @param method           the method name
     * @param parameters       the list of parameters
     * @param gameStatusParam  the game status parameter
     * @param selectionParam   the selection parameter
     */
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

    /**
     * Returns the message type.
     *
     * @return the message type
     */
    public MessageTypeEnum getType() {
        return type;
    }

    /**
     * Returns the exception type.
     *
     * @return the exception type
     */
    public ExceptionEnum getException() {
        return exception;
    }

    /**
     * Returns the method name.
     *
     * @return the method name
     */
    public MethodNameEnum getMethod() {
        return method;
    }

    /**
     * Returns the return value.
     *
     * @return the return value
     */
    public Object getReturnValue() {
        return returnValue;
    }

    /**
     * Returns the list of parameters.
     *
     * @return the list of parameters
     */
    public List<Object> getParameters() {
        return parameters;
    }

    /**
     * Returns the game status parameter.
     *
     * @return the game status parameter
     */
    public GameStatusToSend getGameStatusParam() {
        return gameStatusParam;
    }

    /**
     * Returns the selection parameter.
     *
     * @return the selection parameter
     */
    public ArrayList<Coordinates> getSelectionParam() {
        return selectionParam;
    }

}
