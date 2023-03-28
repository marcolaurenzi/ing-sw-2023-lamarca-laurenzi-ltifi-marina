package it.polimi.ingsw.Model.Decks;

import it.polimi.ingsw.Model.TypeEnum;
import it.polimi.ingsw.Model.Item;

import java.util.Collections;
import java.util.List;

/**
 * This class represents the concrete ItemDeck
 */
public class ItemDeck implements Deck{

    List<Item> deck;

    static DeckEnum deckType = DeckEnum.ITEM;

    /**
     *
     */
    @Override
    public void shuffle() {
        Collections.shuffle(deck);
    }

    /**
     *
     *
     * @return
     */
    @Override
    public Object draw() {
        return deck.get(0);
    }

    /**
     * This method is used to correctly initialize the concrete instance of the deck and to shuffle it
     */
    @Override
    public Deck initializeDeck() {

        int j = 0;

        for(TypeEnum typeEnum : TypeEnum.values()) {
            for(int i = 0; i < 22; i++) {
                deck.set(i+22*j, new Item(typeEnum));
            }
            j++;
        }

        this.shuffle();
        return this;
    }

    /**
     *
     * 
     * @return
     */
    public static DeckEnum getDeckType() {
        return deckType;
    }
}
