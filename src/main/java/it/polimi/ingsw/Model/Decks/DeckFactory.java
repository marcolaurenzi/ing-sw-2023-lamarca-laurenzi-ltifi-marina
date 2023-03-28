package it.polimi.ingsw.Model.Decks;

import java.io.IOException;

/**
 * This abstract class is part of the Design Pattern Factory
 */
public class DeckFactory {

    /**
     * This method is used to create a different instance of a deck based on the DeckEnum passed as an argument
     * @param deckType the type you want to create the deck of
     * @return returns an instance of the correct type Deck
     *
     * @throws IOException if something different is passed as an argument
     */
    public Deck factoryMethod(DeckEnum deckType) throws IOException {

        if(deckType.equals(DeckEnum.COMMON)){
            return new CommonGoalDeck();
        }
        else if (deckType.equals(DeckEnum.PERSONAL)) {
            return new PersonalGoalDeck();
        }
        else if (deckType.equals(DeckEnum.ITEM)) {
            return new ItemDeck();
        }
        else {
            throw new IOException();
        }
    }

}
