package it.polimi.ingsw.Model;

/**
 *
 */
public class Item {
    private final TypeEnum type;

    public Item(TypeEnum type) {
        this.type = type;
    }

    public TypeEnum getType() {
        return type;
    }

}
