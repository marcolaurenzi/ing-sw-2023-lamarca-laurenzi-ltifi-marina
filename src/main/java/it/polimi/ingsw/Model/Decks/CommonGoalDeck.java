package it.polimi.ingsw.Model.Decks;

import it.polimi.ingsw.Model.Goals.CommonGoals.CommonGoal;

import java.util.Collections;
import java.util.List;

/**
 * This class represents the concrete CommonGoalDeck
 */
public class CommonGoalDeck implements Deck<CommonGoal> {

    List<CommonGoal> deck;

    static DeckEnum deckType = DeckEnum.COMMON;

    @Override
    public void shuffle() {
        Collections.shuffle(deck);
    }

    @Override
    public CommonGoal draw() {
        return deck.get(0);
    }

    /**
     * This method is used to correctly initialize the concrete instance of the deck and to shuffle it
     */
    @Override
    public Deck initializeDeck() {
        /*
        deck.add(new CommonGoal01());
         */

        this.shuffle();

        return this;
    }

    public static DeckEnum getDeckType() {
        return deckType;
    }
}
