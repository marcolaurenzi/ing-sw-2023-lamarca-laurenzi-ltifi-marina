package it.polimi.ingsw.Model.Decks;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This abstract class represents the abstract concept of a generic Deck made of Objects
 * @param <T> the type of the Deck
 */
public interface Deck<T> extends Serializable {

    /**
     * Shuffles the Deck using the Collections class's shuffle() method.
     */

    void shuffle();

    /**
     * Draws an Object from the shuffled Deck.
     *
     * @return the first Object in the list
     */
    T draw();

    /**
     * Initializes the concrete instance of the deck.
     * This method is implemented by each concrete class.
     *
     * @throws IOException if there is an I/O error during initialization
     */
    void initializeDeck() throws IOException;
}
