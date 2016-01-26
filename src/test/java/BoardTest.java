import Board.*;
import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Created by marc on 27/12/2015.
 */
public class BoardTest extends TestCase {

    public void testPopulateBoard() throws Exception {
        Board board = new Board("Monopoly Map.csv");
    }
    public void testGoExists() {
        Board board = new Board("Monopoly Map.csv");
        Space[] spaces = board.getAllSpaces();
        Assert.assertTrue(spaces[0].getGroup() == Group.GO);
        for (Space space: spaces) {
            System.out.println(space.getName());
        }

    }
}