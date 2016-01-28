package Players;

import Board.*;
import junit.framework.TestCase;
import org.mockito.*;
import Dice.Dice;

import static org.mockito.Mockito.*;


/**
 * Created by userhp on 28/01/2016.
 */
public class PlayerTest extends TestCase {

    public void testRollDiceNormally() throws Exception {
        Board board =Board.getInstance();
        board.populateBoard("Monopoly Map.csv");
        Dice d1 = Mockito.mock(Dice.class);
        when(d1.rollTheDice()).thenReturn(2);
        Dice d2 = Mockito.mock(Dice.class);
        when(d2.rollTheDice()).thenReturn(4);
        Dice[] dices = {d1,d2};
        Player player = new Player(1500,dices);
        Player spy = spy(player);
        int result = spy.rollDice();

        verify(spy,times(1)).rollDice();
        verify(spy,times(1)).setCurrentLocation(any(Space.class));
        verify(d1,times(1)).rollTheDice();
        verify(d2,times(1)).rollTheDice();

    }
    public void testRollADouble() throws Exception {
        Board board =Board.getInstance();
        board.populateBoard("Monopoly Map.csv");
        Dice d1 = Mockito.mock(Dice.class);
        when(d1.rollTheDice()).thenReturn(2);
        Dice d2 = Mockito.mock(Dice.class);
        when(d2.rollTheDice()).thenReturn(2).thenReturn(4);
        Dice[] dices = {d1,d2};
        Player player = new Player(1500,dices);
        Player spy = spy(player);
        int result = spy.rollDice();

        verify(spy,times(2)).rollDice();
        verify(spy,times(2)).setCurrentLocation(any(Space.class));
        verify(d1,times(2)).rollTheDice();
        verify(d2,times(2)).rollTheDice();
        assertEquals(6,result);
    }

    public void testRollThreeDoublesInARow() throws Exception {
        Board board =Board.getInstance();
        board.populateBoard("Monopoly Map.csv");
        Dice d1 = Mockito.mock(Dice.class);
        when(d1.rollTheDice()).thenReturn(2);
        Dice d2 = Mockito.mock(Dice.class);
        when(d2.rollTheDice()).thenReturn(2);
        Dice[] dices = {d1,d2};
        Player player = new Player(1500,dices);
        Player spy = spy(player);
        int result = spy.rollDice();

        verify(spy,times(1)).goToJail();
        verify(spy,times(3)).rollDice();
        verify(spy,times(1)).setCurrentLocation(board.getSpaceOnBoard("Jail"));
        verify(spy,atLeast(2)).setCurrentLocation(any(Space.class));

        verify(d1,times(3)).rollTheDice();
        verify(d2,times(3)).rollTheDice();
        assertEquals(-1,result);
    }

}