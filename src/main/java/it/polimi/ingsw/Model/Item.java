package it.polimi.ingsw.Model;

/**
 *
 */
public class Item {
    private final TypeEnum type;
    //private final PictureEnum picture;

    public Item(TypeEnum type) {
        this.type = type;
        //this.picture = picture;
    }

    /**
     *
     * @return
     */
    public TypeEnum getType() {
        return type;
    }

    /*
    public PictureEnum getPicture() {
        return picture;
    }
    */

}
