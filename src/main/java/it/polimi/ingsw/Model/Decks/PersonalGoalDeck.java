package it.polimi.ingsw.Model.Decks;

import it.polimi.ingsw.Model.Goals.PersonalGoals.PersonalGoal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class represents the concrete PersonalGoalDeck.
 * It implements the Deck interface with PersonalGoal type.
 */
public class PersonalGoalDeck implements Deck<PersonalGoal> {
    /**
     * Represents the deck of personal goals.
     */
    List<PersonalGoal> deck;

    /**
     * Shuffles the personal goal deck.
     */
    @Override
    public void shuffle() {
        Collections.shuffle(deck);
    }

    /**
     * Draws a personal goal card from the deck.
     *
     * @return The drawn PersonalGoal card.
     */
    @Override
    public PersonalGoal draw() {
        PersonalGoal personalGoal = deck.get(0);
        deck.remove(0);
        return personalGoal;
    }

    /**
     * Initializes the personal goal deck by creating and adding personal goal cards to the deck.
     *
     * @throws IOException if there is an error during initialization.
     */
    @Override
    public void initializeDeck() throws IOException {
        deck = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            deck.add(i, new PersonalGoal(i));
        }
    }
}
