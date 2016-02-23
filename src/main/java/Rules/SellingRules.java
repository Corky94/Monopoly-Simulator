package Rules;

import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.JsePlatform;

/**
 * Created by userhp on 30/01/2016.
 */
public class SellingRules {
    private static SellingRules instance = new SellingRules();
    private static double priceReductionForSellingHouse;
    private static double priceReductionForSellingHotel;

    private SellingRules() {

    }

    public static SellingRules getInstance() {
        return instance;
    }

    public static void init(double reductionForSellingHouse, double reductionForSellingHotel) {
        priceReductionForSellingHouse = reductionForSellingHouse;
        priceReductionForSellingHotel = reductionForSellingHotel;
    }

    LuaValue _G;

    public SellingRules(String luaFileLocation) {
        _G = JsePlatform.standardGlobals();
        _G.get("dofile").call(LuaValue.valueOf(luaFileLocation));
    }

    public double priceReductionForSellingOfHouse() {

        LuaValue getPriceReductionForSellingOfHouse = _G.get("getPriceReductionForSellingOfHouse");
        return getPriceReductionForSellingOfHouse.call().todouble();
    }

    public double priceReductionForSellingOfHotel() {

        LuaValue getPriceReductionForSellingOfHotel = _G.get("getPriceReductionForSellingOfHotel");
        return getPriceReductionForSellingOfHotel.call().todouble();
    }
}