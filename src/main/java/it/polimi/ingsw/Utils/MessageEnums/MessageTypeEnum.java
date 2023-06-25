package it.polimi.ingsw.Utils.MessageEnums;

/**
 * Enumeration representing the types of messages exchanged between client and server.
 */
public enum MessageTypeEnum {

    /**
     * Represents a method call message.
     * This type is used when a client sends a request to invoke a method on the server.
     */
    methodCall,

    /**
     * Represents a return value message.
     * This type is used when the server sends a response containing the return value of a method call.
     */
    returnValue,

    /**
     * Represents an exception message.
     * This type is used when an exception is thrown during the execution of a method and is sent to the client.
     */
    exception,

    /**
     * Represents a success message.
     * This type is used when a void method is called and has no return value, and no exception is thrown.
     */
    success
}
