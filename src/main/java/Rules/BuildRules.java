package Rules;

import Board.Board;
import Board.Property;
import Players.Player;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import org.luaj.vm2.lib.jse.JsePlatform;

import java.util.Stack;

/**
 * //TODO logic needs to be added to incorporate rules
 * Created by userhp on 29/01/2016.
 */
public class BuildRules {
    private static BuildRules instance  = new BuildRules();

    private BuildRules(){}
    public static BuildRules getInstance() {
        return instance;
    }

    public boolean canBuildHouse(Property property, Player player) {

        Board board = Board.getInstance();
        LuaValue luaBoard = CoerceJavaToLua.coerce(board);
        LuaValue luaProperty = CoerceJavaToLua.coerce(property);
        LuaValue luaPlayer = CoerceJavaToLua.coerce(player);

        Stack<Property> playerOwnedPropertiesOfGroup = player.getOwnedPropertiesOfGroup(property.getGroup());

        LuaTable luaPlayerOwnedProperties = LuaTable.tableOf();


        for (int i = 0; i < playerOwnedPropertiesOfGroup.size(); i++) {
            luaPlayerOwnedProperties.insert(i, CoerceJavaToLua.coerce(playerOwnedPropertiesOfGroup.pop()));
        }


        LuaValue methodCanBuildHouse = _G.get("canBuildHouse");
        LuaValue[] luaArgs = {luaProperty, luaPlayer, luaBoard, luaPlayerOwnedProperties.toLuaValue()};
        Varargs canBuildHouse = methodCanBuildHouse.invoke(luaArgs);
        return canBuildHouse.arg1().toboolean();
    }

    public boolean canBuildHotel(Property property, Player player) {
        return false;
    }

    public int amountOfHousesNeededForHotel() {
        return 0;
    }

    public boolean canSellHouse(Property property, Player player) {
        return false;
    }


    LuaValue _G;

    public BuildRules(String luaFileLocation) {
        _G = JsePlatform.standardGlobals();
        _G.get("dofile").call(LuaValue.valueOf(luaFileLocation));
    }
}
