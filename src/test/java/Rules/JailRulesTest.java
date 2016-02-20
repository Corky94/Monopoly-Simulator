package Rules;

import junit.framework.TestCase;

/**
 * Created by userhp on 09/02/2016.
 */
public class JailRulesTest extends TestCase {

    public void testAmountOfRollsToGetOutOfJail() throws Exception {
        JailRules rules = new JailRules("C:\\Users\\userhp\\Documents\\monopoly-simulator\\src\\main\\LuaFiles\\JailRules.lua");
        assertEquals(3, rules.amountOfRollsToGetOutOfJail());
    }

    public void testFeeToPayToGetOutOfJail() throws Exception {
        JailRules rules = new JailRules("C:\\Users\\userhp\\Documents\\monopoly-simulator\\src\\main\\LuaFiles\\JailRules.lua");
        assertEquals(50, rules.feeToPayToGetOutOfJail());
    }

    public void testCanEarnRent() throws Exception {
        JailRules rules = new JailRules("C:\\Users\\userhp\\Documents\\monopoly-simulator\\src\\main\\LuaFiles\\JailRules.lua");
        assertTrue(rules.canEarnRent());
    }

    public void testAmountOfDoublesToBeSentToJail() throws Exception {
        JailRules rules = new JailRules("C:\\Users\\userhp\\Documents\\monopoly-simulator\\src\\main\\LuaFiles\\JailRules.lua");
        assertEquals(3, rules.amountOfDoublesToBeSentToJail());
    }
}