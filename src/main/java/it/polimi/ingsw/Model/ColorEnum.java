package it.polimi.ingsw.Model;

/**
 * This enum is used to set the color of the items used in the game
 */
public enum ColorEnum {
    BLUE("FRAME"), GREEN("CAT"), YELLOW("TOYS"), WHITE("BOOKS"), PINK("PLANT"), LIGHTBLUE("AWARDS");

    private String elementType;

    public String getColorItem() {return this.elementType;}
    private ColorEnum(String elementType) { this.elementType = elementType;}

}
