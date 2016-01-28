package Board;

import Players.Player;

/**
 * Created by userhp on 26/01/2016.
 */
public class Utilities extends Space {
    private int cost;
    private int mortgagePrice;

    public Utilities(String name, int loc, Group group, int cost,int mtg){
        super.setGroup(group);
        super.setLocation(loc);
        super.setName(name);
        this.cost= cost;
        this.mortgagePrice = mtg;
    }

    @Override
    public void onVisit(Player player) {
        //Needs implementation of rules on utility rent
    }
}
