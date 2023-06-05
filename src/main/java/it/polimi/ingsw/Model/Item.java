package it.polimi.ingsw.Model;

import it.polimi.ingsw.Utils.Utils;

import java.io.IOException;
import java.io.Serializable;

/**
 *
 */
public class Item implements Serializable {
    private final TypeEnum type;

    private final int num;

    public Item(TypeEnum type) {
        this.type = type;
        num = (int) (Math.random() * 3) + 1;
    }

    public TypeEnum getType() {
        return type;
    }

    public int getNum() {
        return num;
    }
    /**
     * This method returns a string representing the item in the matrix (a string of # with the color of the item),
     * the dimension of the string is defined in the configuration file
     * @return a string representing the item in the matrix
     */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append("#".repeat(Math.max(0, Utils.getItemDimension())));
        }
        catch (IOException e) {
            System.err.println("Error while reading the configuration file");
        }
        return Utils.setColor(stringBuilder.toString(), type.toString());
    }

    /**
     * This method returns a string representing a void item in the matrix (a string of spaces),
     * the dimension of the string is defined in the configuration file
     * This method is static because it is used to print the matrix when the item is null
     * @return a string representing a void item in the matrix
     */
    public static String voidToString() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append(" ".repeat(Math.max(0, Utils.getItemDimension())));
        }
        catch (IOException e) {
            System.err.println("Error while reading the configuration file");
        }
        return stringBuilder.toString();
    }

}
