package it.polimi.ingsw.Model;

import it.polimi.ingsw.Utils.Utils;

import java.io.Serializable;

/**
 *
 */
public class Item implements Serializable {
    private final TypeEnum type;

    public Item(TypeEnum type) {
        this.type = type;
    }

    public TypeEnum getType() {
        return type;
    }

    public String toString() {
        return Utils.setColor("#", type.toString());
    }

}
