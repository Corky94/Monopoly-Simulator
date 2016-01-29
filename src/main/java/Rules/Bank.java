package Rules;

import Board.Property;
import Board.Station;
import Board.Utilities;
import Players.Player;

/**
 * Created by userhp on 28/01/2016.
 */
public class Bank {
    private  static GoRules goRules = GoRules.getInstance();
    private static BuildRules buildRules = BuildRules.getInstance();
    private static AuctionRules auctionRules = AuctionRules.getInstance();

    private static int hotelsInBank;
    private static int housesInBank;



    public static  void payPlayer(Player playerToSend, Player playerToReceive,int amount){
        playerToSend.spendMoney(amount);
        playerToReceive.gainMoney(amount);

    }
    public static void passGo(Player player){
        player.receiveMoney(goRules.getSalary());
    }

    public static boolean buyHouse(Property property,Player player) {
        boolean houseBuilt;
        if (player.equals(property.getOwner()) && buildRules.canBuildHouse(property, player)) {
            if (housesInBank == 0) {
                houseBuilt = false;
            } else {
                if (player.spendMoney(property.getHouseCost())) {
                    housesInBank--;
                    property.addHouse();
                    houseBuilt = true;
                } else {
                    houseBuilt = false;
                }
            }

        }
        else{
            houseBuilt = false;
        }
        return houseBuilt;
    }

    public static boolean buyHotel(Property property,Player player) {
        boolean hotelBuilt;
        if (player.equals(property.getOwner()) && buildRules.canBuildHotel(property, player)) {
            if (hotelsInBank == 0) {
                hotelBuilt = false;
            } else {
                if (player.spendMoney(property.getHouseCost())) {
                    hotelsInBank--;
                    property.addHotel();
                    hotelBuilt = true;
                } else {
                    hotelBuilt = false;
                }
            }

        }
        else{
            hotelBuilt = false;
        }
        return hotelBuilt;
    }
    public static void auctionProperty(Property property, Player[] players){
        int baseCostOfProperty = property.getCost();
        int startingPriceOfProperty = (int)(baseCostOfProperty * auctionRules.getStartingPriceMultiplier());
        int currentPriceOfProperty = startingPriceOfProperty;
        int askingPriceOfProperty = startingPriceOfProperty;
        int incrementOfAuction = (int)(baseCostOfProperty* auctionRules.getIncrementMultiplier());
        boolean auctionRunning = true;
        Player topBidder = null;
        while(auctionRunning){
            Player oldTopBidder = topBidder;
            for(Player player : players){
                if(player.wantsToBuyPropertyForPrice(property,askingPriceOfProperty) && !player.equals(topBidder)){
                    topBidder = player;
                    currentPriceOfProperty= askingPriceOfProperty;
                    askingPriceOfProperty += incrementOfAuction;
                }
            }
            if(topBidder.equals(oldTopBidder)){
                auctionRunning = false;
            }
        }
        topBidder.spendMoney(currentPriceOfProperty);
        property.setOwner(topBidder);
        topBidder.addProperty(property);
    }
    public static void auctionProperty(Utilities utility, Player[] players){

    }
    public static void auctionProperty(Station property, Player[] players){

    }


}
