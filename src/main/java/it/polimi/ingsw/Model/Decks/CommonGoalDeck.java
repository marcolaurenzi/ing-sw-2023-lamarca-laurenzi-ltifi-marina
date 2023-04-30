package it.polimi.ingsw.Model.Decks;

import it.polimi.ingsw.Model.Goals.CommonGoals.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class represents the concrete CommonGoalDeck
 */
public class CommonGoalDeck implements Deck<CommonGoal> {

    List<CommonGoal> deck;
    private static final String pathToConfigFile = "resources/configuration/configurationFile.json";
    @Override
    public void shuffle() {
        Collections.shuffle(deck);
    }

    @Override
    public CommonGoal draw() {
        CommonGoal commonGoal = deck.get(0);
        deck.remove(0);
        return commonGoal;
    }

    /**
     * This method is used to correctly initialize the concrete instance of the deck and to shuffle it
     */
    @Override
    public void initializeDeck() throws FileNotFoundException {

        deck = new ArrayList<>();

        deck.add(new CommonGoal01(1,1));
        deck.add(new CommonGoal01(1,1));
        deck.add(new CommonGoal2());
        deck.add(new CommonGoal3());
        deck.add(new CommonGoal4());
        deck.add(new CommonGoal5());
        deck.add(new CommonGoal6());
        deck.add(new CommonGoal7());
        deck.add(new CommonGoal8());
        deck.add(new CommonGoal9());
        deck.add(new CommonGoal10());
        deck.add(new CommonGoal11());

        this.shuffle();
    }
}
