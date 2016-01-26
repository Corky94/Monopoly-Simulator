package Cards;

import Board.Space;

/**
 * Created by userhp on 26/01/2016.
 */
public class CommunityChestCard extends Card {

    public CommunityChestCard(String name, CardAction action) {
        super.setAction(action);
        super.setName(name);
    }

    public CommunityChestCard(String name, CardAction action, int fee) {
        super.setAction(action);
        super.setName(name);
        super.setFee(fee);
    }

    public CommunityChestCard(String name, CardAction action, Space location){
        super.setAction(action);
        super.setName(name);
        super.setLocation(location);

    }
}

