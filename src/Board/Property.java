package Board;

import Players.Money;
import Players.Player;

/**
 * Created by marc on 20/11/2015.
 */
public class Property extends Space {

    private int baseRent;
    private int rent;
    private int cost;
    private int houses;
    private int hotels;
    private Player owner;

    public Property(Group group, int location, int baseRent, int cost){
        super.setGroup(group);
        super.setLocation(location);
        this.baseRent = rent = baseRent ;
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    public int getHouses() {
        return houses;
    }

    public void setHouses(int houses) {
        this.houses = houses;
    }

    public int getHotels() {
        return hotels;
    }

    public void setHotels(int hotels) {
        this.hotels = hotels;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }


    @Override
    public void onVisit(Player player) {
        if(owner == player || owner == null){

        }

    }

    private void calculateRent(){

    }
}
