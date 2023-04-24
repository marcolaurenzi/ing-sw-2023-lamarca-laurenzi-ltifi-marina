package it.polimi.ingsw.Utils.MessageEnums;

public enum MessageTypeEnum {
    methodCall,
    returnValue,
    exception,
    success //this one is set when a void method is called and has no return value and no exception is thrown
}
