package Board;

import Players.Player;

/**
 * Created by marc on 27/12/2015.
 */
public class GO extends Space {

    public GO(String name,int location,Group group){
        super.setName(name);
        super.setGroup(group);
        super.setLocation(location);
    }
    @Override
    public void onVisit(Player player) {
        //Player gets money X
    }
}