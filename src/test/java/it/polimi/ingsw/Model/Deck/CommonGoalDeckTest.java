package it.polimi.ingsw.Model.Deck;

import it.polimi.ingsw.Model.Decks.CommonGoalDeck;
import it.polimi.ingsw.Model.Goals.CommonGoals.CommonGoal;
import org.junit.Test;
import java.io.FileNotFoundException;


/**
 * Unit tests for the CommonGoalDeck class.
 */
public class CommonGoalDeckTest {
    /**
     * Constructor for the CommonGoalDeckTest class.
     */
    public CommonGoalDeckTest() {
    }
    /**
     * CommonGoalDeck instance to test.
     */
    CommonGoalDeck deck = new CommonGoalDeck();

    /**
     * Test of initializeDeck method.
     *
     * @throws FileNotFoundException if there is an I/O error.
     */
    @Test
    public void testInitializeDeck() throws FileNotFoundException {
        deck.initializeDeck();
        assert (deck.draw() instanceof CommonGoal);
        assert (deck.draw() instanceof CommonGoal);
        assert (deck.draw() instanceof CommonGoal);
        assert (deck.draw() instanceof CommonGoal);
        assert (deck.draw() instanceof CommonGoal);
        assert (deck.draw() instanceof CommonGoal);
        assert (deck.draw() instanceof CommonGoal);
        assert (deck.draw() instanceof CommonGoal);
        assert (deck.draw() instanceof CommonGoal);
        assert (deck.draw() instanceof CommonGoal);
        assert (deck.draw() instanceof CommonGoal);
        assert (deck.draw() instanceof CommonGoal);
    }

    /**
     * Test of draw method.
     *
     * @throws FileNotFoundException if there is an I/O error.
     */
    @Test
    public void testShuffle() throws FileNotFoundException {
        deck.initializeDeck();
        CommonGoalDeck deck1 = new CommonGoalDeck();
        CommonGoalDeck deck2 = new CommonGoalDeck();
        deck1.initializeDeck();
        deck2.initializeDeck();
        for (int i = 0; i < 11; i++) {
            assert (!deck1.draw().equals(deck2.draw()));
        }
        deck1.initializeDeck();
        deck2.initializeDeck();
        deck1.shuffle();
        deck2.shuffle();
        for (int i = 0; i < 11; i++) {
            assert (!deck1.draw().equals(deck2.draw()));
        }
    }
}
