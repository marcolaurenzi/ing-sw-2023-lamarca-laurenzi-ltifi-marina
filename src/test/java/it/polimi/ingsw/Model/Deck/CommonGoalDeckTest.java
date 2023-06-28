package it.polimi.ingsw.Model.Deck;

import it.polimi.ingsw.Model.Decks.CommonGoalDeck;
import it.polimi.ingsw.Model.Goals.CommonGoals.CommonGoal;
import it.polimi.ingsw.Model.Goals.CommonGoals.CommonGoal01;
import it.polimi.ingsw.Model.Goals.CommonGoals.CommonGoal2;
import org.junit.Test;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class CommonGoalDeckTest {
    public CommonGoalDeckTest() {
    }
    CommonGoalDeck deck = new CommonGoalDeck();

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
