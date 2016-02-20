package Rules;

import junit.framework.TestCase;

/**
 * Created by userhp on 09/02/2016.
 */
public class GoRulesTest extends TestCase {


    public void testGetSalary() throws Exception {
        GoRules rules = new GoRules("C:\\Users\\userhp\\Documents\\monopoly-simulator\\src\\main\\LuaFiles\\GoRules.lua");
        assertEquals(200, rules.getSalary());
    }
}