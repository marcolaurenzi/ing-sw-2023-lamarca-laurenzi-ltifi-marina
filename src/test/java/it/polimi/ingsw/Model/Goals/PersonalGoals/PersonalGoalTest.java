package it.polimi.ingsw.Model.Goals.PersonalGoals;

import it.polimi.ingsw.Model.Bookshelf;
import it.polimi.ingsw.Model.Exceptions.WrongConfigurationException;
import it.polimi.ingsw.Model.Utils;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PersonalGoalTest {

    final List<PersonalGoal> goals = new ArrayList<>();
    @Test
    @DisplayName("Testing the corner case where the pointer is null")
    public void nullBookshelfTest() throws IOException{
        Bookshelf bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "nullBookshelfTest.JSON");
        for(int i = 0; i<12; i++) {
            goals.add(i, new PersonalGoal(i));
            final int j = i;
            assertThrows(NullPointerException.class, ()->goals.get(j).getPoints(bookshelf));
        }
    }

    @Test
    @DisplayName("Testing corner case where the whole matrix is void")
    public void voidBookshelfTest() throws WrongConfigurationException, IOException {
        Bookshelf bookshelf = Utils.loadBookshelfFromFile(Utils.getTestFilesPath() + "personalGoalTest.JSON", 0);
        for(int i = 0; i<12; i++) {
            goals.add(i, new PersonalGoal(i));
            assertEquals(0, goals.get(i).getPoints(bookshelf));
        }
    }
}
