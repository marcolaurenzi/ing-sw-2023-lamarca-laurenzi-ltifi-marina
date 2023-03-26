package it.polimi.ingsw.Model.Decks;

/**
 * Concrete class used to dynamically create the PersonalGoalDeck class
 */

public class PersonalGoalDeckCreator extends Creator {

    /**
     * This method is used to create a concrete instance of a Deck
     *
     * @return a concrete instance of PersonalGoalDeck
     */
    @Override
    public Deck factoryMethod() {
        return new PersonalGoalDeck();
    }
}
