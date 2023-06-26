package it.polimi.ingsw.Model.Decks;

import it.polimi.ingsw.Model.Goals.CommonGoals.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class represents the concrete CommonGoalDeck.
 * It implements the Deck interface with CommonGoal as the generic type.
 */
public class CommonGoalDeck implements Deck<CommonGoal> {
    /**
     * Constructs a CommonGoalDeck object.
     */
    public CommonGoalDeck() {
    }
    /**
     * Represents the deck of common goals.
     */
    List<CommonGoal> deck;
    private static final String pathToConfigFile = "resources/configuration/configurationFile.json";

    /**
     * Shuffles the deck of common goals.
     */
    @Override
    public void shuffle() {
        Collections.shuffle(deck);
    }

    /**
     * Draws a common goal from the deck and removes it from the deck.
     *
     * @return The drawn common goal.
     */
    @Override
    public CommonGoal draw() {
        CommonGoal commonGoal = deck.get(0);
        deck.remove(0);
        return commonGoal;
    }

    /**
     * Initializes the concrete instance of the deck and shuffles it.
     * Reads the configuration from a JSON file.
     *
     * @throws FileNotFoundException if the configuration file is not found.
     */
    @Override
    public void initializeDeck() throws FileNotFoundException {

        deck = new ArrayList<>();

        deck.add(new CommonGoal01(2, 6));
        deck.add(new CommonGoal01(4, 4));
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
