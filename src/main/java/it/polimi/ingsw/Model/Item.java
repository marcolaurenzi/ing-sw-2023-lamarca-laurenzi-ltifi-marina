package it.polimi.ingsw.Model;

public class Item {
    private final ColorEnum color;
    private final String picture;

    public Item(ColorEnum color, String picture) {
        this.color = color;
        this.picture = picture;
    }

    public ColorEnum getColor() {
        return color;
    }

    public String getPicture() {
        return picture;
    }

}
