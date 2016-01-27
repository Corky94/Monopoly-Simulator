package Board;

import junit.framework.TestCase;

/**
 * Created by userhp on 26/01/2016.
 */
public class BoardTest extends TestCase {
    public void testPopulateBoard() throws Exception {
        Board board = new Board("Monopoly Map.csv");
    }
    public void testRetrieveSpaceFromIntLocation() throws Exception {
        Board board = new Board("Monopoly Map.csv");
        Space go = board.getSpaceOnBoard(1);
        assertTrue(go.getName().equalsIgnoreCase("GO"));
    }
    public void testRetrieveSpaceFromName() throws Exception {
        Board board = new Board("Monopoly Map.csv");
        Space go = board.getSpaceOnBoard("go");
        assertEquals(go.getLocation(), 0);
    }

}