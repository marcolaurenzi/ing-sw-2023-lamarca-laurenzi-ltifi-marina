package it.polimi.ingsw.Model;

import static it.polimi.ingsw.Model.ColorEnum.*;

/**
 * This enum is used to set the element type of each item
 * In this enum each value has a ColorEnum constant associated
 * so that it is possible to group items based on their color
 */

public enum ElementEnum {
    FRAMES1(BLUE),
    FRAMES2(BLUE),
    FRAMES3(BLUE),
    CAT1(GREEN),
    CAT2(GREEN),
    CAT3(GREEN),
    TOYS1(YELLOW),
    TOYS2(YELLOW),
    TOYS3(YELLOW),
    BOOKS1(WHITE),
    BOOKS2(WHITE),
    BOOKS3(WHITE),
    PLANT1(PINK),
    PLANT2(PINK),
    PLANT3(PINK),
    AWARDS1(LIGHTBLUE),
    AWARDS2(LIGHTBLUE),
    AWARDS3(LIGHTBLUE);
    private ColorEnum itemColor;
    public ColorEnum getItemColor(){ return this.itemColor;}
    private ElementEnum(ColorEnum itemColor){
        this.itemColor = itemColor;
    }

}
