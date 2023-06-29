package it.polimi.ingsw.Model.Deck;

import it.polimi.ingsw.Model.Decks.ItemDeck;
import org.junit.Test;

/**
 * Unit tests for the ItemDeck class.
 */
public class ItemDeckTest {

    /**
     * Tests the initializeDeck method.
     */
    @Test
    public void testInitializeDeck() {
        ItemDeck deck = new ItemDeck();
        deck.initializeDeck();
        for(int i = 0; i < 22; i++) {
            assert (deck.draw() != null);
        }
    }

    /**
     * Tests the shuffle method.
     */
    @Test
    public void testShuffle() {
        ItemDeck deck1 = new ItemDeck();
        ItemDeck deck2 = new ItemDeck();
        deck1.initializeDeck();
        deck2.initializeDeck();
        for(int i = 0; i < 22; i++) {
            assert (!deck1.draw().equals(deck2.draw()));
        }
    }

    /**
     * Tests the getDeck method.
     */
    @Test
    public void testGetDeck() {
        ItemDeck deck = new ItemDeck();
        deck.initializeDeck();
        assert (deck.getDeck() != null);
    }
}
