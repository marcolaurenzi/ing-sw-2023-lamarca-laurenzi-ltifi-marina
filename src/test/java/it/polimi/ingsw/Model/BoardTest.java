
package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.Decks.ItemDeck;
import it.polimi.ingsw.Model.Exceptions.VoidBoardTileException;
import it.polimi.ingsw.Utils.Utils;
import jdk.jfr.Name;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class BoardTest {

    private Board board;

    @Before
    @Name("Setting Up Board")
    public void setUp() throws Exception {

        board = Utils.loadBoardFromFile(Utils.getConfigurationPath() + "BoardConfiguration.JSON");
        board.setItemDeck(new ItemDeck());
    }

    @Test
    public void hasFreeTest() throws VoidBoardTileException {

        for(int i = 0; i<board.getGameBoard().getColumnDimension(); i++) {
            for(int j = 0; j<board.getGameBoard().getRowDimension(); j++) {
                board.getGameBoard().get(i,j).placeItem(board.getItemDeck().draw());
            }
        }

        Set<Coordinates> unavailableTiles = new HashSet<>();

        // looking for the tiles that are unavailable for every game
        for (int i = 0; i<board.getGameBoard().getColumnDimension(); i++) {
            for (int j = 0; j<board.getGameBoard().getRowDimension(); j++) {
                if (board.getGameBoard().get(i,j).getNumberOfPlayersSign() == 5) {
                    unavailableTiles.add(new Coordinates(i,j));
                }
            }
        }

        // testing unavailable tiles
        for(Coordinates c : unavailableTiles) {
            assertThrows(VoidBoardTileException.class, () -> board.hasFree(c.getX(), c.getY()));
        }
        unavailableTiles.clear();

        // looking for empty tiles
        for (int i = 0; i<board.getGameBoard().getColumnDimension(); i++) {
            for (int j = 0; j<board.getGameBoard().getRowDimension(); j++) {
                if (board.getGameBoard().get(i,j).isEmpty()) {
                    unavailableTiles.add(new Coordinates(i,j));
                }
            }
        }

        // testing empty tiles
        for(Coordinates c : unavailableTiles) {
            assertThrows(VoidBoardTileException.class, () -> board.hasFree(c.getX(), c.getY()));
        }
        unavailableTiles.clear();

        // looking for border tiles
        for (int i = 0; i<board.getGameBoard().getColumnDimension(); i++) {
            for (int j = 0; j<board.getGameBoard().getRowDimension(); j++) {
                if (board.isFreeNorth(i,j) || board.isFreeSouth(i,j) || board.isFreeEast(i,j) || board.isFreeWest(i,j)) {
                    unavailableTiles.add(new Coordinates(i,j));
                }
            }
        }

        // testing border tiles
        for(Coordinates c : unavailableTiles) {
            assertTrue(board.hasFree(c.getX(), c.getY()) > 0);
        }
        unavailableTiles.clear();
    }


    @Test
    public void toRefreshTest() {

        // void board
        assert !board.toRefresh();

        // board with one item
        board.getGameBoard().get(4,4).placeItem(board.getItemDeck().draw());
        assert board.toRefresh();

        board.removeItem(4,4);

        // full board
        for(int i = 0; i<board.getGameBoard().getColumnDimension(); i++) {
            for(int j = 0; j<board.getGameBoard().getRowDimension(); j++) {
                board.getGameBoard().get(i,j).placeItem(board.getItemDeck().draw());
            }
        }
        assert !board.toRefresh();

        board.removeAllItems();

        // 2 contiguous items
        board.getGameBoard().get(4,4).placeItem(board.getItemDeck().draw());
        board.getGameBoard().get(4,5).placeItem(board.getItemDeck().draw());
        assert !board.toRefresh();
    }
}