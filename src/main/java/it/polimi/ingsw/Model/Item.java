package it.polimi.ingsw.Model;

import it.polimi.ingsw.Utils.Utils;

import java.io.IOException;
import java.io.Serializable;

/**
 * This class represents an item in the game.
 */
public class Item implements Serializable {
    /**
     * Represents the type of the item.
     */
    private final TypeEnum type;
    /**
     * Represents the number associated with the item.
     */
    private final int num;

    /**
     * Constructs a new Item object with the specified item type.
     *
     * @param type the type of the item
     */
    public Item(TypeEnum type) {
        this.type = type;
        num = (int) (Math.random() * 3) + 1;
    }

    /**
     * Returns the type of the item.
     *
     * @return the type of the item
     */
    public TypeEnum getType() {
        return type;
    }

    /**
     * Returns the number associated with the item.
     *
     * @return the number associated with the item
     */
    public int getNum() {
        return num;
    }

    /**
     * Returns a string representation of the item in the matrix.
     * The string consists of '#' characters with the color of the item.
     * The length of the string is defined in the configuration file.
     *
     * @return a string representing the item in the matrix
     */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append("#".repeat(Math.max(0, Utils.getItemDimension())));
        } catch (IOException e) {
            System.err.println("Error while reading the configuration file");
        }
        return Utils.setColor(stringBuilder.toString(), type.toString());
    }

    /**
     * Returns a string representation of a void item in the matrix.
     * The string consists of spaces.
     * The length of the string is defined in the configuration file.
     * This method is static because it is used to print the matrix when the item is null.
     *
     * @return a string representing a void item in the matrix
     */
    public static String voidToString() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append(" ".repeat(Math.max(0, Utils.getItemDimension())));
        } catch (IOException e) {
            System.err.println("Error while reading the configuration file");
        }
        return stringBuilder.toString();
    }
}
