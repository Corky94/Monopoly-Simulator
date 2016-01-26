package Board;

import Players.Player;

/**
 * Created by userhp on 26/01/2016.
 */
public class Station extends Space {
    private int cost;
    private int mortagagePrice;

    public Station(String name, int loc, Group group, int cost, int mtg ){
        super.setGroup(group);
        super.setLocation(loc);
        super.setName(name);
        this.cost= cost;
        this.mortagagePrice =mtg;
    }

    @Override
    public void onVisit(Player player) {

    }
}
