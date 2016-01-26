package Cards;

import Board.Space;
import Players.Player;

/**
 * TODO need to take into consideration the how to incorporate the payment of money for repairs.
 * Created by userhp on 26/01/2016.
 */
public abstract class Card {
    private String name;
    private CardAction action;
    private int fee;
    private Space location;

    protected void setAction(CardAction action) {
        this.action = action;
    }


    protected void setFee(int fee) {
        this.fee = fee;
    }



    protected String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected Space getLocation() {
        return location;
    }

    protected void setLocation(Space location) {
        this.location = location;
    }
    public boolean onPickUp(Player player){
        return false;
    }
}
