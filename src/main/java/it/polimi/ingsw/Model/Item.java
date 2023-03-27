package it.polimi.ingsw.Model;

import java.awt.*;

public class Item {
    private final TypeEnum type;
    //private final PictureEnum picture;

    public Item(TypeEnum type) {
        this.type = type;
        //this.picture = picture;
    }

    public TypeEnum getType() {
        return type;
    }

    /*
    public PictureEnum getPicture() {
        return picture;
    }
    */

}
