package it.polimi.ingsw.Model.Decks;

import it.polimi.ingsw.Model.ColorEnum;
import it.polimi.ingsw.Model.Item;

/**
 * This class represents the concrete ItemDeck
 */

public class ItemDeck extends Deck{

    /**
     * This method is used to correctly initialize the concrete instance of the deck
     */
    @Override
    public void initializeDeck() {
        int j = 0;

        for(ColorEnum colorEnum : ColorEnum.values()) {
            for(int i = 0; i < 22; i++) {
                deck.set(i+22*j, new Item(colorEnum, "temp"));
            }
            j++;
        }
    }
}
