package it.polimi.ingsw.Model.Decks;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This abstract class represents the abstract concept of a generic Deck made of Objects
 */
public interface Deck<T> extends Serializable {

    /**
     * This method uses the Collections class's method shuffle() to shuffle the Deck
     */
    void shuffle();

    /**
     * This method is used to draw an Object from an already shuffled Deck
     *
     * @return the first Object in the list
     */
    T draw();

    /**
     * This abstract method is implemented form each concrete class and is used to
     * initialize correctly the concrete instance of the deck
     */
    void initializeDeck() throws IOException;
}
