package it.polimi.ingsw.Model.Deck;

import it.polimi.ingsw.Model.Decks.ItemDeck;
import it.polimi.ingsw.Model.Item;
import org.junit.Test;

public class ItemDeckTest {



    @Test
    public void testInitializeDeck() {
        ItemDeck deck = new ItemDeck();
        deck.initializeDeck();
        for(int i = 0; i < 22; i++) {
            assert (deck.draw() != null);
        }
    }

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

    @Test
    public void testGetDeck() {
        ItemDeck deck = new ItemDeck();
        deck.initializeDeck();
        assert (deck.getDeck() != null);
    }
}
