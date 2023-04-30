package it.polimi.ingsw.Model.Decks;

import it.polimi.ingsw.Model.Goals.PersonalGoals.PersonalGoal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class represents the concrete PersonalGoalDeck
 */
public class PersonalGoalDeck implements Deck<PersonalGoal> {

    List<PersonalGoal> deck;

    @Override
    public void shuffle() {
        Collections.shuffle(deck);
    }

    @Override
    public PersonalGoal draw() {
        PersonalGoal personalGoal = deck.get(0);
        deck.remove(0);
        return personalGoal;
    }

    /**
     * This method is used to correctly initialize the concrete instance of the deck and to shuffle it
     */
    @Override
    public void initializeDeck() throws IOException {

        deck = new ArrayList<>();
        for(int i = 0; i<12; i++) {
            deck.add(i, new PersonalGoal(i));
        }
    }
}
