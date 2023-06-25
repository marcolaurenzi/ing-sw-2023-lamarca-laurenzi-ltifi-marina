package it.polimi.ingsw.Model.Decks;

import it.polimi.ingsw.Model.TypeEnum;
import it.polimi.ingsw.Model.Item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class represents the concrete ItemDeck.
 */
public class ItemDeck implements Deck<Item> {

    private List<Item> deck;

    /**
     * Initializes a new instance of the ItemDeck class.
     * It calls the initializeDeck method to correctly initialize the deck and shuffle it.
     */
    public ItemDeck() {
        initializeDeck();
    }

    /**
     * Shuffles the deck by randomly reordering the elements.
     */
    @Override
    public void shuffle() {
        Collections.shuffle(deck);
    }

    /**
     * Draws an item from the top of the deck.
     *
     * @return The item at the top of the deck.
     */
    @Override
    public Item draw() {
        Item item = deck.get(0);
        deck.remove(0);
        return item;
    }

    /**
     * Initializes the concrete instance of the deck by adding items of each type and shuffling the deck.
     */
    @Override
    public void initializeDeck() {

        deck = new ArrayList<>();

        for (TypeEnum typeEnum : TypeEnum.values()) {
            for (int i = 0; i < 22; i++) {
                deck.add(new Item(typeEnum));
            }
        }

        this.shuffle();
    }

    /**
     * Gets the deck of items.
     *
     * @return The list of items in the deck.
     */
    public List<Item> getDeck() {
        return deck;
    }
}
