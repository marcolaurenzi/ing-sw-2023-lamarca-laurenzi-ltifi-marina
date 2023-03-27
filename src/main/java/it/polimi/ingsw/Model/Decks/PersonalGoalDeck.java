package it.polimi.ingsw.Model.Decks;

import it.polimi.ingsw.Model.Goals.PersonalGoals.PersonalGoal;
import it.polimi.ingsw.Model.Goals.PersonalGoals.PersonalGoal0;

/**
 * This class represents the concrete PersonalGoalDeck
 */
public class PersonalGoalDeck extends Deck{

    /**
     * This method is used to correctly initialize the concrete instance of the deck and to shuffle it
     */
    @Override
    public void initializeDeck() {
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
    }
}
