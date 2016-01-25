package Board;

import Players.Player;

/**
 * Created by marc on 20/11/2015.
 */
public class Tax extends Space {
    private int fee;

    public Tax(int location, int fee){
        super.setGroup(Group.Tax);
        super.setLocation(location);
        this.fee = fee;
    }
    public int getFee(){
        return fee;
    }



    @Override
    public void onVisit(Player player) {

    }
}
