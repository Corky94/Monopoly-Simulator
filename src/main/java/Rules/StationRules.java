package Rules;

import Players.Player;

/**
 * Created by userhp on 30/01/2016.
 */
public class StationRules {

    private static StationRules instance =  new StationRules();

    private StationRules(){}

    public static StationRules getInstance() {
        return instance;
    }

    public int calculateRent(Player owner, Player visitor) {
        return 0;
    }
}
