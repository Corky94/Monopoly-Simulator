package Board;

import Players.Player;

/**
 * Created by userhp on 26/01/2016.
 */
public class Station extends Space {
    private int cost;


    public Station(String name, int loc, Group group, int cost){
        super.setGroup(group);
        super.setLocation(loc);
        super.setName(name);
        this.cost= cost;
    }

    @Override
    public void onVisit(Player player) {

    }
}
