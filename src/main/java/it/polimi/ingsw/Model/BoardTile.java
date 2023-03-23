package it.polimi.ingsw.Model;

/**
 * This class represents the single tile of the game board
 * It has methods to check the single tile content and to change it
 */

public class BoardTile {
    /* numberOfPlayersSign attribute is used to determine if the tile
    * must be left empty or not based on the numbers of players at the beginning of the game
    */
    private final int numberOfPlayersSign;
    private Item placedItem;
    /* the constructor is used when the board is initialized and
    * all the number of each board tile must be assigned
    * the placed item field is set to null as it is empty at the beginning of the game*/
    public BoardTile(int numberOfPlayersSign){
        this.numberOfPlayersSign = numberOfPlayersSign;
        this.placedItem = null;
    }
    /* this method tells whether the tile is empty or not */
    private boolean isEmpty(){
        return placedItem == null;
    }
    /* this method is used to place an item on the tile if it's empty*/
    private void placeItem(Item itemToPlace){
        if(this.isEmpty()) {
            this.placedItem = itemToPlace;
        }else{
            System.out.println("The tile is not empty!");
        }
    }
    /* this method is used to draw the item from the tile if it's present*/
    private Item drawItem(){
        if(!this.isEmpty()){
            Item temp = this.placedItem;
            this.placedItem = null;
            return temp;
        }else{
            System.out.println("The tile is empty!");
            return null;
        }
    }
    /* this method is used to check which item is placed one the tile
    * It is useful in case the game runs in the mode without the GUI
    * Hence this allows the player to check the item without drawing it*/
    private Item checkItem(){
        if(this.isEmpty()){
            System.out.println("The tile is empty");
            return null;
        }else{
            return this.placedItem;
        }
    }

}
