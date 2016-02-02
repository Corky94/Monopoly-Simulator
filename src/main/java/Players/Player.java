package Players;

import Board.*;
import Board.Space;
import Cards.Card;
import Dice.*;
import Rules.MoveType;
import Rules.SellingRules;

import java.util.Arrays;
import java.util.Vector;

/**
 *  TODO a lot of the logic needs to be developed here. A lot of time is needed however the rest of program needs to be done firs
 *
 * Created by marc on 20/11/2015.
 */
public class Player {
    private Space currentLocation;
    private int money;
    private MoveType moveTaken;   
    private int turnInJail;    
    private Vector<Ownable> ownedSpaces;
    private Dice[] dices;
    private Vector<Card> cards;
    private boolean inJail = false;
    private Board board =Board.getInstance();


    public Player (int initialMoney, Dice[] dices){
        moveTaken = MoveType.DiceRoll;
        money = initialMoney;
        this.dices = dices;
        currentLocation = board.getSpaceOnBoard("Go");
        noOfRolls=0;
    }
    public DiceRoll rollDice(){        
        Vector<Integer> diceResults = new Vector<Integer>();
        for (Dice d : dices) {
            diceResults.add(d.rollTheDice());
        }
        int sumOfDiceRolls = 0;
        int firstResult = diceResults.get(0);
        boolean allTheSame = true;
        for (int result: diceResults) {
            sumOfDiceRolls += result;
            if (result!=firstResult) {
                allTheSame=false;                
            }
        }        
        DiceRoll roll = new DiceRoll(sumOfDiceRolls,allTheSame);
        return roll;



    }

    public Space getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Space currentLocation) {

        this.currentLocation = currentLocation;
        currentLocation.onVisit(this);
    }

   public void gainMoney(int amount){
       money+=amount;
   }

    public boolean spendMoney(int amount){
        boolean enoughMoney;
        if(money-amount<0){
            enoughMoney= false;
        }
        else{
            money-=amount;
            enoughMoney=true;
        }
        return enoughMoney;

    }

    public void moveToLocation(Space location) {
    }

    public void receiveMoney(int feeToPlayer) {

    }

    public void keepCard(Card card) {
        cards.add(card);
    }

    public void goToJail() {
        moveTaken = MoveType.GoToJail;
        this.inJail = true;
        currentLocation=board.getSpaceOnBoard("Jail");
        board.goToJail(this);
    }

    public void giveMoneyToBank(int feeToPlayer) {
    }

    public void receiveMoneyFromPlayers(int feeToPlayer) {
    }

    public int calculateHotelsOwned() {
        return 0;
    }

    public int calculateHousesOwned() {
        return 0;
    }

    public void payOtherPlayers(int feeToPlayer) {
    }

    public boolean wantsToBuyPropertyForPrice(Space property, int askingPriceOfProperty) {
        return false;
    }

    public void addProperty(Ownable space) {
        ownedSpaces.add(space);
    }
    public void removeProperty(Ownable space) {
        ownedSpaces.remove(space);
    }

    public MoveType getMoveTaken() {
        return moveTaken;
    }
    public int ownsSpacesOfGroup(Group group){
        int amountOfSpacesOwned =0;
        for (Space space : ownedSpaces){
            if(space.getGroup().equals(group)){
                amountOfSpacesOwned++;
            }
        }
        return amountOfSpacesOwned;
    }
    

    public int calculateNetWorth() {
        //TODO test method
        int netWorth = money;

        for(Ownable space : ownedSpaces){
            netWorth += space.getCost();
            if(space instanceof Property){
                netWorth += ((Property) space).getHotels()* ((Property) space).getHouseCost();
                netWorth += ((Property) space).getHouses()* ((Property) space).getHouseCost();
            }

        }
        return netWorth;
    }

    public int calculateSaleableItems() {
        //Todo test method

        int saleableMoney = money;

        for(Ownable space : ownedSpaces){
            saleableMoney += space.getMortgagePrice();
            if(space instanceof Property){
                saleableMoney += ((Property) space).getHotels()* ((Property) space).getHouseCost() * SellingRules.getInstance().priceReductionForSellingOfHotel();
                saleableMoney += ((Property) space).getHouses()* ((Property) space).getHouseCost() * SellingRules.getInstance().priceReductionForSellingOfHouse();
            }
        }



        return saleableMoney;
    }

    public int getMoney(){
        return money;
    }
    public Vector<Ownable> getOwnedSpaces(){
        return ownedSpaces;
    }
}
