package it.polimi.ingsw.Model.Deck;

/**
 * Concrete class used to dynamically create the CommonGoalDeck class
 */

public class CommonGoalDeckCreator extends Creator {

    /**
     * This method is used to create a concrete instance of a Deck
     *
     * @return a concrete instance of CommonGoalsDeck
     */
    @Override
    public Deck factoryMethod() {
        return new CommonGoalDeck();
    }
}
