package Board;

import Players.Player;

/**
 * Created by userhp on 26/01/2016.
 */
public class Utilities extends Space {
    private int cost;


    public Utilities(String name, int loc, Group group, int cost){
        super.setGroup(group);
        super.setLocation(loc);
        super.setName(name);
        this.cost= cost;
    }

    @Override
    public void onVisit(Player player) {

    }
}
