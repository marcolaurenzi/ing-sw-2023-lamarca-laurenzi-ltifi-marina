package it.polimi.ingsw.Model;

import static it.polimi.ingsw.Model.TypeEnum.*;

/**
 * This enum is used to set the element type of each item
 * In this enum each value has a ColorEnum constant associated
 * so that it is possible to group items based on their color
 */

public enum ElementEnum {
    //modifica fatta da discutere
    CAT1(CATS),
    CAT2(CATS),
    CAT3(CATS),
    BOOK1(BOOKS),
    BOOK2(BOOKS),
    BOOK3(BOOKS),
    GAME1(GAMES),
    GAME2(GAMES),
    GAME3(GAMES),
    FRAME1(FRAMES),
    FRAME2(FRAMES),
    FRAME3(FRAMES),
    TROPHY1(TROPHIES),
    TROPHY2(TROPHIES),
    TROPHY3(TROPHIES),
    PLANT1(PLANTS),
    PLANT2(PLANTS),
    PLANT3(PLANTS);
    private TypeEnum itemType;
    public TypeEnum getItemType(){ return this.itemType;}
    private ElementEnum(TypeEnum itemType){
        this.itemType = itemType;
    }

}
