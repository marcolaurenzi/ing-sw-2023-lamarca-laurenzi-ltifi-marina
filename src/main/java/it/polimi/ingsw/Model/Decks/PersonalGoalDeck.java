package it.polimi.ingsw.Model.Decks;

import it.polimi.ingsw.Model.Goals.PersonalGoals.PersonalGoal;

import java.util.Collections;
import java.util.List;

/**
 * This class represents the concrete PersonalGoalDeck
 */
public class PersonalGoalDeck implements Deck<PersonalGoal> {

    List<PersonalGoal> deck;

    static DeckEnum deckType = DeckEnum.PERSONAL;

    @Override
    public void shuffle() {
        Collections.shuffle(deck);
    }

    @Override
    public PersonalGoal draw() {
        return deck.get(0);
    }

    /**
     * This method is used to correctly initialize the concrete instance of the deck and to shuffle it
     */
    @Override
    public Deck initializeDeck() {
        /*
        deck.add(new PersonalGoal0());
        deck.add(new PersonalGoal1());
        deck.add(new PersonalGoal2());
        deck.add(new PersonalGoal3());
        deck.add(new PersonalGoal4());
        deck.add(new PersonalGoal5());
        deck.add(new PersonalGoal6());
        deck.add(new PersonalGoal7());
        deck.add(new PersonalGoal8());
        deck.add(new PersonalGoal9());
        deck.add(new PersonalGoal10());
        deck.add(new PersonalGoal11());
         */

        this.shuffle();

        return this;
    }

    public static DeckEnum getDeckType() {
        return deckType;
    }
}
