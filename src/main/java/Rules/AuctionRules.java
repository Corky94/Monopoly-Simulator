package Rules;

import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.JsePlatform;

/**
 * Created by userhp on 29/01/2016.
 */
public class AuctionRules {
    private static  AuctionRules instance = new AuctionRules();
    private static double startingPriceMultiplier;
    private static double incrementMultiplier;
    private AuctionRules(){}
    public static AuctionRules getInstance() {
        return instance;
    }

    public static void init(double startingPriceMultiplierInit, double incrementMultiplierInit) {
        startingPriceMultiplier = startingPriceMultiplierInit;
        incrementMultiplier = incrementMultiplierInit;
    }

    LuaValue _G;

    public AuctionRules(String luaFileLocation) {
        _G = JsePlatform.standardGlobals();
        _G.get("dofile").call(LuaValue.valueOf(luaFileLocation));
    }



    public double getStartingPriceMultiplier() {
        LuaValue getStartingPriceMultiplier = _G.get("getStartingPriceMultiplier");
        return getStartingPriceMultiplier.call().todouble();
    }

    public double getIncrementMultiplier() {
        LuaValue getIncrementMultiplier = _G.get("getIncrementMultiplier");
        return getIncrementMultiplier.call().todouble();
    }
}
