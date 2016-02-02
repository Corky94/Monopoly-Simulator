package Rules;

import Board.Ownable;
import Board.Property;
import Players.AllPlayers;
import Players.Player;

import java.util.Vector;

/**
 * Created by userhp on 28/01/2016.
 */
public class Bank {
    private  static GoRules goRules;
    private static BuildRules buildRules;
    private static AuctionRules auctionRules;
    private static SellingRules sellingRules;
    private static boolean initialized;
    private static int hotelsInBank;
    private static int housesInBank;

    private static Bank instance = new Bank();

    private  Bank(){

    }
    public static Bank getInstance(){
        if(!initialized){
            System.out.println("Needs to be initialised before creating instance\n Run Bank.initializeBank(args) first");
        }
        return instance;
    }


    //OK need to adjust this class, needs to be a singleton and needs to ensure that initialization is done before the
    // getting of the instance


    public static void initializeBank(GoRules goRulesInit, BuildRules buildRulesInit, AuctionRules auctionRulesInit,
                                      SellingRules sellingRulesInit, int amountOfHouses, int amountOfHotels){
        goRules = goRulesInit;
        buildRules = buildRulesInit;
        auctionRules = auctionRulesInit;
        sellingRules = sellingRulesInit;
        housesInBank = amountOfHouses;
        hotelsInBank = amountOfHotels;
        initialized = true;
    }



    public   void payPlayer(Player playerToSend, Player playerToReceive,int amount){
        if(playerToSend.spendMoney(amount)){
            playerToReceive.gainMoney(amount);
        }

    }
    public  void passGo(Player player){
        player.receiveMoney(goRules.getSalary());
    }

    public  boolean buyHouse(Property property,Player player) {
        boolean houseBuilt;
        if (player.equals(property.getOwner()) && buildRules.canBuildHouse(property, player)) {
            if (getHousesInBank() == 0) {
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

    public  boolean buyHotel(Property property,Player player) {
        boolean hotelBuilt;
        if (player.equals(property.getOwner()) && buildRules.canBuildHotel(property, player)) {
            if (getHotelsInBank() == 0) {
                hotelBuilt = false;
            } else {
                if (player.spendMoney(property.getHouseCost())) {

                    housesInBank += buildRules.amountOfHousesNeededForHotel();
                    hotelsInBank--;
                    property.addHotel();
                    for(int i =0; i < buildRules.amountOfHousesNeededForHotel();i++){
                        property.removeHouse();
                    }
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
    public  void sellHouse(Property property,Player player){
        if (player.equals(property.getOwner()) && buildRules.canSellHouse(property, player)) {

            player.receiveMoney((int) (property.getHouseCost()*sellingRules.priceReductionForSellingOfHouse()));
            property.removeHouse();
            housesInBank++;
        }
    }
    public  void sellHotel(Property property,Player player){
        if (player.equals(property.getOwner()) && buildRules.canSellHouse(property, player)) {

            player.receiveMoney((int) (property.getHouseCost()*sellingRules.priceReductionForSellingOfHotel()));
            property.removeHotel();
            hotelsInBank++;
            for(int i =0; i < buildRules.amountOfHousesNeededForHotel();i++){
                if(housesInBank>0){
                    property.addHouse();
                    housesInBank--;
                }
                else{
                    break;
                }

            }

        }
    }
    public  void auctionProperty(Ownable property){
        Vector<Player> players = AllPlayers.getInstance().getAllPlayers();
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


    public  int getHotelsInBank() {
        return hotelsInBank;
    }


    public  int getHousesInBank() {
        return housesInBank;
    }
}
