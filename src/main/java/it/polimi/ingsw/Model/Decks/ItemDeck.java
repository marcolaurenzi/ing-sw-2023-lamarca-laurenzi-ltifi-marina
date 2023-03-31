package it.polimi.ingsw.Model.Decks;

import it.polimi.ingsw.Model.TypeEnum;
import it.polimi.ingsw.Model.Item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class represents the concrete ItemDeck
 */
public class ItemDeck implements Deck<Item> {

    List<Item> deck;

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
    public Item draw() {
        return deck.get(0);
    }

    /**
     * This method is used to correctly initialize the concrete instance of the deck and to shuffle it
     */
    @Override
    public void initializeDeck() {

        deck = new ArrayList<>();

        for(TypeEnum typeEnum : TypeEnum.values()) {
            for(int i = 0; i < 22; i++) {
                deck.add((new Item(typeEnum)));
            }
        }

        this.shuffle();
    }
}
