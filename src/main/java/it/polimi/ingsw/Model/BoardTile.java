package it.polimi.ingsw.Model;

import java.io.Serializable;

/**
 * This class represents the single tile of the game board
 * It has methods to check the single tile content and to change it
 */
public class BoardTile implements Serializable {

    /* ************************************************************************************************************
     *                          START OF ATTRIBUTES DECLARATION
     ************************************************************************************************************ */
    /**
     * Represents the number of players who have placed their dice on the board tile.
     */
    private final int numberOfPlayersSign;
    /**
     * Represents the item placed on the tile
     */
    private Item placedItem;

    /* ************************************************************************************************************
     *                          END OF ATTRIBUTES DECLARATION
     *                          START OF CONSTRUCTORS
     ************************************************************************************************************ */


    /**
     * The constructor is used when the board is initialized and
     * all the number of each board tile must be assigned
     * the placed item field is set to null as it is empty at the beginning of the game
     * @param numberOfPlayersSign the value written on the Board for that BoardTile
     */
    public BoardTile(int numberOfPlayersSign){
        this.numberOfPlayersSign = numberOfPlayersSign;
        this.placedItem = null;
    }

    /* ************************************************************************************************************
     *                          END OF CONSTRUCTORS
     *                          START OF CUSTOM METHODS
     ************************************************************************************************************ */


    /**
     * This method tells whether the tile is empty or not
     *
     * @return true if it's empty, false otherwise
     */
    public boolean isEmpty(){
        return placedItem == null;
    }

    /**
     * This method is used to place an item on the tile if it's empty
     * @param itemToPlace the reference to item that must be placed
     */
    public void placeItem(Item itemToPlace){
        if(this.isEmpty()) {
            this.placedItem = itemToPlace;
        }else{
            System.err.println("The tile is not empty!");
        }
    }

    /**
     * This method is used to place an item on the tile without checking if it's empty
     * @param itemToPlace the reference to item that must be placed
     */
    public void brutePlaceItem(Item itemToPlace){
        this.placedItem = itemToPlace;
    }

    /**
     * This method is used to draw the item from the tale if it's present
     * @return returns null if it's empty, returns the item otherwise
     */
    protected Item drawItem(){
        if(!this.isEmpty()){
            Item temp = this.placedItem;
            this.placedItem = null;
            return temp;
        }else{
            System.err.println("The tile is empty!");
            return null;
        }
    }

    /**
     * This method is used to check which item is placed one the tile
     * It is useful in case the game runs in the mode without the GUI
     * Hence this allows the player to check the item without drawing
     *
     * @return the placed item in case it's not empty
     */
    protected Item checkItem(){
        if(this.isEmpty()){
            System.err.println("The tile is empty");
            return null;
        }else{
            return this.placedItem;
        }
    }


    /* ************************************************************************************************************
     *                          END OF CUSTOM METHODS
     *                          START OF GETTER METHODS
     ************************************************************************************************************ */

    /**
     * Returns the number of players who have placed their dice on the board tile.
     *
     * @return the number of players sign on the board tile
     */
    public int getNumberOfPlayersSign() {
        return numberOfPlayersSign;
    }

    /**
     * Returns the item placed on the board tile.
     *
     * @return the item placed on the board tile, or null if no item is placed
     */
    public Item getPlacedItem() {
        return placedItem;
    }


    /* ************************************************************************************************************
     *                          END OF GETTER METHODS
     ************************************************************************************************************ */

}
