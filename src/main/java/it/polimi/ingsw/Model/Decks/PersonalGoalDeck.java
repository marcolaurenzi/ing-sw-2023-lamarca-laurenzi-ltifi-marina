package it.polimi.ingsw.Model.Decks;

import it.polimi.ingsw.Model.Goals.PersonalGoals.PersonalGoal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * This class represents the concrete PersonalGoalDeck.
 * It implements the Deck interface with PersonalGoal type.
 */
public class PersonalGoalDeck implements Deck<PersonalGoal> {
    /**
     * Constructs a PersonalGoalDeck instance.
     */
    public PersonalGoalDeck() {
        remaining = 12;
    }
    /**
     * Represents the deck of personal goals.
     */
    List<PersonalGoal> deck;

    public int remaining;

    /**
     * Shuffles the personal goal deck.
     */
    @Override
    public void shuffle() {
        Collections.shuffle(deck);
    }

    /**
     * Generates a random number in a given range.
     *
     * @param min The minimum value of the range.
     * @param max The maximum value of the range.
     * @return The generated random number.
     */
    public int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            return 0;
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    /**
     * Draws a personal goal card from the deck.
     *
     * @return The drawn PersonalGoal card.
     */
    @Override
    public PersonalGoal draw() {
        int i = getRandomNumberInRange(0,remaining-1);
        while(deck.get(i)==null){
            i = getRandomNumberInRange(0,11);
        }
        PersonalGoal personalGoal = deck.get(i);
        remaining--;
        deck.remove(i);
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
