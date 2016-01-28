package Board;

import Players.Player;
import Rules.Bank;

/**
 * Created by marc on 20/11/2015.
 */
public class Property extends Space {

    private int baseRent;
    private int rent;
    private int oneHouseRent;
    private int twoHouseRent;
    private int threeHouseRent;
    private int fourHouseRent;
    private int hotelRent;
    private int cost;
    private int houses;
    private int hotels;
    private int houseCost;
    private int mortgagePrice;
    private boolean mortgaged;
    private Player owner;

    public Property(String name, Group group, int location, int baseRent, int cost, int mortgagePrice,int houseCost, int oneHouseRent, int twoHouseRent, int threeHouseRent, int fourHouseRent, int hotelRent){
        super.setGroup(group);
        super.setName(name);
        super.setLocation(location);
        this.baseRent = baseRent ;
        this.cost = cost;
        this.mortgagePrice = mortgagePrice;
        this.houseCost = houseCost;
        this.oneHouseRent = oneHouseRent;
        this.twoHouseRent = twoHouseRent;
        this.threeHouseRent = threeHouseRent;
        this.fourHouseRent = fourHouseRent;
        this.hotelRent = hotelRent;
        this.mortgaged = false;
        calculateRent();

    }

    public int getCost() {
        return cost;
    }


    public void addHouse() {
        this.houses++;
    }


    public void addHotel() {
        houses=0;
        this.hotels++;
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
            //Player can buy property, logic still needs to be developed.
        }
        else if(mortgaged){

        }
        else{
            calculateRent();
            Bank.payPlayer(player,owner,rent);
        }

    }

    private void calculateRent(){
       if(hotels>0){
           rent = hotelRent;
       }
       //Should be else if to incorperate the rule that the base rent doubles if owner has all the properties in group.

        else{
           switch(houses){
               case 4:
                   rent = fourHouseRent;
                   break;
               case 3:
                   rent = threeHouseRent;
                   break;
               case 2:
                   rent = twoHouseRent;
                   break;
               case 1:
                   rent = oneHouseRent;
                   break;
               default:
                   rent = baseRent;
                   break;
           }
       }
    }

    public int getHouseCost() {
        return houseCost;
    }

    public int getMortgagePrice() {
        return mortgagePrice;
    }
}
