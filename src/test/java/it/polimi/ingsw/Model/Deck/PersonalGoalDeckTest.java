package it.polimi.ingsw.Model.Deck;

import it.polimi.ingsw.Model.Decks.PersonalGoalDeck;
import it.polimi.ingsw.Model.Goals.PersonalGoals.PersonalGoal;
import org.junit.Test;

/**
 * Unit tests for the PersonalGoalDeck class.
 */
public class PersonalGoalDeckTest {

    /**
     * Tests the initializeDeck method.
     * @throws Exception if an error occurs during the test.
     */
    @Test
    public void testInitializeDeck() throws Exception {
        PersonalGoalDeck personalGoalDeck = new PersonalGoalDeck();
        personalGoalDeck.initializeDeck();
        for (int i = 0; i < 12; i++) {
            assert (personalGoalDeck.draw() instanceof PersonalGoal);
        }
    }

    /**
     * Tests the shuffle method.
     * @throws Exception if an error occurs during the test.
     */
    @Test
    public void testShuffle() throws Exception {
        PersonalGoalDeck personalGoalDeck = new PersonalGoalDeck();
        PersonalGoalDeck personalGoalDeck2 = new PersonalGoalDeck();
        personalGoalDeck.initializeDeck();
        personalGoalDeck2.initializeDeck();
        personalGoalDeck.shuffle();
        personalGoalDeck2.shuffle();
        for(int i = 0; i < 11; i++) {
            assert (!personalGoalDeck.draw().equals(personalGoalDeck2.draw()));
        }
    }

    /**
     * Tests the draw method.
     * @throws Exception if an error occurs during the test.
     */
    @Test
    public void testDraw() throws Exception {
        PersonalGoalDeck personalGoalDeck = new PersonalGoalDeck();
        personalGoalDeck.initializeDeck();
        for (int i = 0; i < 12; i++) {
            assert (personalGoalDeck.draw() instanceof PersonalGoal);
        }
    }
}
