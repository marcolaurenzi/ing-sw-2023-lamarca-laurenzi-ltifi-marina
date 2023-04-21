package it.polimi.ingsw.Utils;

import java.util.List;

public class Message {
    private String type;
    private String method;
    private List<Object> parameters;

    public String getType() {
        return type;
    }

    public String getMethod() {
        return method;
    }

    public List<Object> getParameters() {
        return parameters;
    }
}
