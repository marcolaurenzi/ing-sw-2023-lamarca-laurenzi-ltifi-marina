
package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.Exceptions.VoidBoardTileException;
import it.polimi.ingsw.Utils.Utils;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class BoardTest {

    @Test
    public void hasFreeTest() throws IOException, VoidBoardTileException {

        final Board board = Utils.loadBoardFromFile("src/main/resources/configurations/BoardConfiguration.JSON");

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
        unavailableTiles.clear();

        // testing empty tiles
        for(Coordinates c : unavailableTiles) {
            assertThrows(VoidBoardTileException.class, () -> board.hasFree(c.getX(), c.getY()));
        }

        // looking for border tiles
        for (int i = 0; i<board.getGameBoard().getColumnDimension(); i++) {
            for (int j = 0; j<board.getGameBoard().getRowDimension(); j++) {
                if (board.isFreeNorth(i,j) || board.isFreeSouth(i,j) || board.isFreeEast(i,j) || board.isFreeWest(i,j)) {
                    unavailableTiles.add(new Coordinates(i,j));
                }
            }
        }
        unavailableTiles.clear();

        // testing border tiles
        for(Coordinates c : unavailableTiles) {
            assertTrue(board.hasFree(c.getX(), c.getY()) > 0);
        }
    }

    public void toRefreshTest() {
        // TODO
    }
}