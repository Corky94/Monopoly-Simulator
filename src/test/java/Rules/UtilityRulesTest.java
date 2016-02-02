package Rules;

import Board.Group;
import Dice.DiceRoll;
import Players.Player;
import junit.framework.TestCase;
import org.mockito.*;
import static org.mockito.Mockito.*;
/**
 * Created by userhp on 29/01/2016.
 */
public class UtilityRulesTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();

    }

    public void testCalculateRentWithOneUtility() throws Exception {
        UtilityRules.init(4,10);

        UtilityRules rules = UtilityRules.getInstance();
        Player owner = Mockito.mock(Player.class);
        Player visitor = Mockito.mock(Player.class);
        when(owner.ownsSpacesOfGroup(Group.Utility)).thenReturn(1);
        DiceRoll roll = mock(DiceRoll.class);
        when(roll.getSumOfDiceRolls()).thenReturn(10);
        when(visitor.getLastDiceRoll()).thenReturn(roll);
        when(visitor.getMoveTaken()).thenReturn(MoveType.DiceRoll);
        assertEquals(40,rules.calculateRent(owner,visitor));

        
    }
    public void testCalculateRentWithBothUtilities() throws Exception {
        UtilityRules.init(4,10);
        UtilityRules rules = UtilityRules.getInstance();
        Player owner = Mockito.mock(Player.class);
        Player visitor = Mockito.mock(Player.class);
        when(owner.ownsSpacesOfGroup(Group.Utility)).thenReturn(2);
        DiceRoll roll = mock(DiceRoll.class);
        when(roll.getSumOfDiceRolls()).thenReturn(10);
        when(visitor.getLastDiceRoll()).thenReturn(roll);
        when(visitor.getMoveTaken()).thenReturn(MoveType.DiceRoll);
        assertEquals(100,rules.calculateRent(owner,visitor));


    }
    public void testCalculateRentWithOneUtilityAndDifferentRuleSet() throws Exception {
        UtilityRules.init(20,50);
        UtilityRules rules = UtilityRules.getInstance();
        Player owner = Mockito.mock(Player.class);
        Player visitor = Mockito.mock(Player.class);
        when(owner.ownsSpacesOfGroup(Group.Utility)).thenReturn(1);
        DiceRoll roll = mock(DiceRoll.class);
        when(roll.getSumOfDiceRolls()).thenReturn(10);
        when(visitor.getLastDiceRoll()).thenReturn(roll);
        when(visitor.getMoveTaken()).thenReturn(MoveType.DiceRoll);
        assertEquals(200,rules.calculateRent(owner,visitor));


    }
    public void testCalculateRentWithBothUtilitiesAndDifferentRuleSet() throws Exception {
        UtilityRules.init(20,50);
        UtilityRules rules = UtilityRules.getInstance();
        Player owner = Mockito.mock(Player.class);
        Player visitor = Mockito.mock(Player.class);
        when(owner.ownsSpacesOfGroup(Group.Utility)).thenReturn(2);
        DiceRoll roll = mock(DiceRoll.class);
        when(roll.getSumOfDiceRolls()).thenReturn(10);
        when(visitor.getLastDiceRoll()).thenReturn(roll);
        when(visitor.getMoveTaken()).thenReturn(MoveType.DiceRoll);
        assertEquals(500,rules.calculateRent(owner,visitor));


    }
    public void testCalculateRentWithOneUtilityWhenArrivingViaCard() throws Exception {
        UtilityRules.init(4,10);

        UtilityRules rules = UtilityRules.getInstance();
        Player owner = Mockito.mock(Player.class);
        Player visitor = Mockito.mock(Player.class);
        when(owner.ownsSpacesOfGroup(Group.Utility)).thenReturn(1);
        DiceRoll roll = mock(DiceRoll.class);
        when(roll.getSumOfDiceRolls()).thenReturn(10);
        when(visitor.rollDice()).thenReturn(roll);
        when(visitor.getMoveTaken()).thenReturn(MoveType.Card);
        assertEquals(100,rules.calculateRent(owner,visitor));


    }
    public void testCalculateRentWithBothUtilitiesWhenArrivingByCard() throws Exception {
        UtilityRules.init(4,10);
        UtilityRules rules = UtilityRules.getInstance();
        Player owner = Mockito.mock(Player.class);
        Player visitor = Mockito.mock(Player.class);
        when(owner.ownsSpacesOfGroup(Group.Utility)).thenReturn(2);
        DiceRoll roll = mock(DiceRoll.class);
        when(roll.getSumOfDiceRolls()).thenReturn(10);
        when(visitor.rollDice()).thenReturn(roll);
        when(visitor.getMoveTaken()).thenReturn(MoveType.Card);
        assertEquals(100,rules.calculateRent(owner,visitor));


    }


   


}