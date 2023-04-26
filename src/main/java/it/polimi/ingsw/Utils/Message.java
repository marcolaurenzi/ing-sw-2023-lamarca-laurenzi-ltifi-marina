package it.polimi.ingsw.Utils;

import it.polimi.ingsw.Utils.MessageEnums.ExceptionEnum;
import it.polimi.ingsw.Utils.MessageEnums.MessageTypeEnum;
import it.polimi.ingsw.Utils.MessageEnums.MethodNameEnum;

import java.util.List;

public class Message {
    private final MessageTypeEnum type;
    private final ExceptionEnum exception;
    private final Object returnValue;
    private final MethodNameEnum method;
    private final List<Object> parameters;

    public Message(MessageTypeEnum type, ExceptionEnum exception, Object returnValue, MethodNameEnum method, List<Object> parameters) {
        this.type = type;
        this.exception = exception;
        this.returnValue = returnValue;
        this.method = method;
        this.parameters = parameters;
    }

    public MessageTypeEnum getType() {
        return type;
    }
    public ExceptionEnum getException() { return exception; }
    public MethodNameEnum getMethod() {
        return method;
    }
    public Object getReturnValue() { return returnValue; }
    public List<Object> getParameters() {return parameters; }
}
