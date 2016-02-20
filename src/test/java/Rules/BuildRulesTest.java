package Rules;

import Board.*;
import Players.Player;
import junit.framework.TestCase;
import org.mockito.Mockito;

import java.nio.file.Paths;
import java.util.Stack;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

/**
 * Created by userhp on 15/02/2016.
 */
public class BuildRulesTest extends TestCase {

    public void testCanBuildHouse() throws Exception {
        BuildRules rules = new BuildRules(Paths.get("").toAbsolutePath().toString() + "/src/main/LuaFiles/BuildRules.lua");
        Player player = Mockito.mock(Player.class);
        when(player.ownsSpacesOfGroup(any(Group.class))).thenReturn(3);
        Board boardTest = Board.getInstance();
        boardTest.populateBoard("Monopoly Map.csv");
        Property mockProperty = mock(Property.class);
        when(mockProperty.getHouses()).thenReturn(2);
        when(mockProperty.getGroup()).thenReturn(Group.Green);
        Stack<Property> mockStack = new Stack<Property>();
        mockStack.add(mockProperty);
        when(player.getOwnedPropertiesOfGroup(any(Group.class))).thenReturn(mockStack);
        assertTrue(rules.canBuildHouse(mockProperty, player));


    }

    public void testCannotBuildHouse() throws Exception {
        BuildRules rules = new BuildRules(Paths.get("").toAbsolutePath().toString() + "/src/main/LuaFiles/BuildRules.lua");
        Player player = Mockito.mock(Player.class);
        when(player.ownsSpacesOfGroup(any(Group.class))).thenReturn(2);
        Board boardTest = Board.getInstance();
        boardTest.populateBoard("Monopoly Map.csv");
        Property mockProperty = mock(Property.class);
        when(mockProperty.getGroup()).thenReturn(Group.Green);
        Stack<Property> mockStack = new Stack<Property>();
        mockStack.add(mockProperty);
        when(player.getOwnedPropertiesOfGroup(any(Group.class))).thenReturn(mockStack);
        assertFalse(rules.canBuildHouse(mockProperty, player));


    }
}