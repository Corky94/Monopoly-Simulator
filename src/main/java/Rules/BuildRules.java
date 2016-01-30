package Rules;

import Board.Property;
import Players.Player;

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
        return false;
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
}
