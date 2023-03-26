package it.polimi.ingsw.Model.Deck;

/**
 * Concrete class used to dynamically create the ItemDeck class
 */

public class ItemDeckCreator extends Creator {

    /**
     * This method is used to create a concrete instance of a Deck
     *
     * @return a concrete instance of ItemDeck
     */
    @Override
    public Deck factoryMethod() {

        return new ItemDeck();
    }
}
