package it.polimi.ingsw.Model.Deck;

/**
 * This abstract class is part of the Design Pattern Factory
 */

public abstract class Creator {

    /**
     * Abstract method re-implemented from each concrete Creator
     *
     * @return the correct Deck instance
     */

    public abstract Deck factoryMethod();

}
