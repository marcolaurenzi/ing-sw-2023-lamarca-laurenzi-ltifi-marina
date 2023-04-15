package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.Exceptions.*;
import it.polimi.ingsw.Model.PlayerStates.*;
import it.polimi.ingsw.Model.PlayerStates.PlayerStateSelecting;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void insertInOrderInBookshelf() throws PlayerIsWaitingException, ColumnNotValidException, PickedColumnOutOfBoundsException, PickDoesntFitColumnException {
        PlayerState playerState = new PlayerStateSelecting();
        Bookshelf bookshelf = new Bookshelf();

        ArrayList<Item> pickedItems = new ArrayList<>();
        pickedItems.add(new Item(TypeEnum.CATS));
        pickedItems.add(new Item(TypeEnum.TROPHIES));
        pickedItems.add(new Item(TypeEnum.PLANTS));
        int[] order = {1, 2, 0};

        playerState.insertPickInBookShelf(pickedItems, bookshelf, 0, order);

        assertEquals(TypeEnum.TROPHIES, bookshelf.get(5, 0).getType());
        assertEquals(TypeEnum.PLANTS, bookshelf.get(4, 0).getType());
        assertEquals(TypeEnum.CATS, bookshelf.get(3, 0).getType());
    }

    @Test
    void changeState() {
        //TODO
    }

    @Test
    void getRewardCommonGoals() {
        //TODO
    }

    @Test
    void getRewardFinalGoals() {
        //TODO
    }
}