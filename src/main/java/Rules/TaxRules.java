package Rules;

import Board.Tax;
import Players.Player;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import org.luaj.vm2.lib.jse.JsePlatform;

/**
 * Created by userhp on 29/01/2016.
 */
public class TaxRules {
    private static  TaxRules instance = new TaxRules();
    private static double incomeTaxPercentage;
    private static boolean fixedTaxOption;
    private TaxRules(){}
    public static TaxRules getInstance() {
        return instance;
    }


    public static void init(double taxPercentage, boolean fixedTax){
        incomeTaxPercentage = taxPercentage;
        fixedTaxOption = fixedTax;
    }

    private LuaValue _G;

    public TaxRules(String luaFileLocation) {
        _G = JsePlatform.standardGlobals();
        _G.get("dofile").call(LuaValue.valueOf(luaFileLocation));
    }

    public int calculateIncomeTax(Player player){
        Tax location = (Tax) player.getCurrentLocation();
        LuaValue luaLocation = CoerceJavaToLua.coerce(location);
        LuaValue luaPlayer = CoerceJavaToLua.coerce(player);
        LuaValue calculateIncomeTaxMethod = _G.get("calculateIncomeTax");
        int tax = calculateIncomeTaxMethod.call(luaPlayer, luaLocation).toint();

        return tax;
    }

    
}