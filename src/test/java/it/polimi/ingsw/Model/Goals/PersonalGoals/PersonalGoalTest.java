package it.polimi.ingsw.Model.Goals.PersonalGoals;

import it.polimi.ingsw.Model.Bookshelf;
import it.polimi.ingsw.Model.Exceptions.WrongConfigurationException;
import it.polimi.ingsw.Utils.Utils;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PersonalGoalTest {

    final List<PersonalGoal> goals = new ArrayList<>();

    @Before
    public void setUp() throws IOException {
        for(int i = 0; i<12; i++) {
            goals.add(i, new PersonalGoal(i));
        }
    }

    @Test
    @DisplayName("Testing the corner case where the pointer is null")
    public void nullBookshelfTest() throws IOException{
        Bookshelf bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "nullBookshelfTest.JSON");
        for(int i = 0; i<12; i++) {
            final int j = i;
            assertThrows(NullPointerException.class, ()->goals.get(j).getPoints(bookshelf));
        }
    }

    @Test
    @DisplayName("Testing corner case where the whole matrix is void")
    public void voidBookshelfTest() throws WrongConfigurationException, IOException {
        Bookshelf bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "personalGoalTest.JSON", 0);
        for(int i = 0; i<12; i++) {
            assertEquals(0, goals.get(i).getPoints(bookshelf));
        }
    }

    @Test
    @DisplayName("Testing corner case where the bookshelf is exactly the same as the personal goal")
    public void fullBookshelfTest() throws WrongConfigurationException, IOException {
        for(int i = 0; i<12; i++) {
            Bookshelf bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "personalGoalTest.JSON", i+1);
            assertEquals(12, goals.get(i).getPoints(bookshelf));
        }
    }

    @Test
    @DisplayName("Testing corner case where the bookshelf is exactly the same as the personal goal but all other tiles are full")
    public void completelyFullBookshelfTest() throws IOException, WrongConfigurationException {

        // Cycles through all the personal goals
        for(int i = 0; i<12; i++) {
            Bookshelf bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "personalGoalTest.JSON", i+1);

            // Cycles through all the tiles of the bookshelf
            for(int j = 0; j<6; j++) {
                for(int k = 0; k<5; k++) {

                    if(goals.get(i).getPersonalGoalBookshelf().isEmpty(j,k)) {
                        goals.get(i).getPersonalGoalBookshelf().set(j,k, Utils.getRandomItem());
                    }
                }
            }
            assertEquals(12, goals.get(i).getPoints(bookshelf));
        }
    }
}
