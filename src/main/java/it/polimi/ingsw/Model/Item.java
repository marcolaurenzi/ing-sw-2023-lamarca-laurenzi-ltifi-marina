package it.polimi.ingsw.Model;

public class Item {
    private final String colour;
    private final String picture;

    public Item(String colour, String picture) {
        this.colour = colour;
        this.picture = picture;
    }

    public String getColour() {
        return colour;
    }

    public String getPicture() {
        return picture;
    }

}
