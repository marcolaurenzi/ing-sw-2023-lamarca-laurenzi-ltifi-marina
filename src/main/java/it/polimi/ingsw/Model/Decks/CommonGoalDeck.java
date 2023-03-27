package it.polimi.ingsw.Model.Decks;

import it.polimi.ingsw.Model.Goals.CommonGoals.CommonGoal01;

/**
 * This class represents the concrete CommonGoalDeck
 */
public class CommonGoalDeck extends Deck{

    /**
     * This method is used to correctly initialize the concrete instance of the deck and to shuffle it
     */
    @Override
    public void initializeDeck() {
        /*
        deck.add(new CommonGoal01());
         */

        this.shuffle();
    }
}
