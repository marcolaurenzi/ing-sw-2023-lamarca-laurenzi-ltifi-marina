package it.polimi.ingsw.Model;

public class Item {
    private final String type;
    private final String picture;

    public Item(String type, String picture) {
        this.type = type;
        this.picture = picture;
    }

    public String getType() {
        return type;
    }

    public String getPicture() {
        return picture;
    }

}
