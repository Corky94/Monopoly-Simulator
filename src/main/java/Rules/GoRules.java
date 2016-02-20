package Rules;

import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.JsePlatform;

/**
 * Created by userhp on 28/01/2016.
 */
public class GoRules {
    LuaValue _G;

    public GoRules(String luaFileLocation) {
        _G = JsePlatform.standardGlobals();
        _G.get("dofile").call(LuaValue.valueOf(luaFileLocation));
    }
    public int getSalary(){
        LuaValue methodGetSalary = _G.get("getSalary");
        LuaValue salary = methodGetSalary.call();
        return salary.toint();
    }
//    private static int salary;
//    private static GoRules instance = new GoRules();
//
//    private GoRules(){
//        salary = 200;
//    }
//    private static void GoRulesInit(int initSalary){
//        salary = initSalary;
//    }
//
//    public static GoRules getInstance() {
//        return instance;
//    }
//    public int getSalary(){
//        return salary;
//    }
//    public void updateSalary(double percentage){
//        salary +=  (int)(salary*percentage);
//    }
}
