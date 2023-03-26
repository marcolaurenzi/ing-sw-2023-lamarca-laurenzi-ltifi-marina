package it.polimi.ingsw.Model.Decks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This abstract class represents the abstract concept of a generic Deck made of Objects
 */

public abstract class Deck {
    List<Object> deck;

    public Deck() {
        deck = new ArrayList<>();

    }

    /**
     * This method uses the Collections class's method shuffle() to shuffle the Deck
     */
    public void shuffle() {
        Collections.shuffle(deck);
    }

    /**
     * This method is used to draw an Object from an already shuffled Deck
     *
     * @return the first Object in the list
     */
    public Object draw() {
        return deck.get(0);
    }

    /**
     * This abstract method is implemented form each concrete class and is used to
     * initialize correctly the concrete instance of the deck
     */
    public abstract void initializeDeck();
}
