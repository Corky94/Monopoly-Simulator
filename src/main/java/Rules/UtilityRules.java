package Rules;

import Players.Player;

/**
 * Created by userhp on 30/01/2016.
 */
public class UtilityRules {
    private static UtilityRules instance = new UtilityRules();

    private UtilityRules(){}



    public static UtilityRules getInstance() {
        return instance;
    }

    public int calculateRent(Player owner, Player visitor) {
        return 0;
    }
}
