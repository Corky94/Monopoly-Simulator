package Players;

import Board.*;
import Board.Space;
import Cards.Card;
import Dice.*;
import Rules.*;

import java.util.Arrays;
import java.util.Stack;
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
    private DiceRoll lastDiceRoll;

    private BankruptcyRules bankruptcyRules = AllRules.getBankruptcyRules();


    public Player (int initialMoney, Dice[] dices){
        moveTaken = MoveType.DiceRoll;
        money = initialMoney;
        ownedSpaces = new Vector<Ownable>();
        this.dices = dices;
        currentLocation = board.getSpaceOnBoard("Go");

    }

    // Todo implement actions for if in jail.
    public void onTurn() {
        if (inJail) {
            this.playTurnInJail();
        } else {
            DiceRoll roll = rollDice();
            int rolls = 1;
            while (roll.isReRoll()) {
//                if (rolls >= JailRules.getInstance().amountOfDoublesToBeSentToJail()) {
//                    this.goToJail();
//                    turnInJail = 0;
//                    break;
//                }
                this.moveToLocation(Board.getInstance().moveToSpace(currentLocation, roll.getSumOfDiceRolls()));
                roll = rollDice();
            }
            if (!inJail) {
                this.moveToLocation(Board.getInstance().moveToSpace(currentLocation, roll.getSumOfDiceRolls()));
            }
        }
    }

    private void playTurnInJail() {
        turnInJail++;
//        if (turnInJail > JailRules.getInstance().amountOfRollsToGetOutOfJail()) {
//            inJail = false;
//            turnInJail = 0;
//        } else if (this.wantsToPayJailFine()) {
//            spendMoney(JailRules.getInstance().feeToPayToGetOutOfJail());
//            inJail = false;
//            turnInJail = 0;
//        } else {
            DiceRoll roll = rollDice();
            if (roll.isReRoll()) {
                moveToLocation(Board.getInstance().moveToSpace(currentLocation, roll.getSumOfDiceRolls()));
                inJail = false;
                turnInJail = 0;
                // }
        }
    }

    private boolean wantsToPayJailFine() {
        return false;
    }

    //Todo methods for allowing players to buy houses and hotels.
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
        lastDiceRoll = roll;
        return roll;



    }

    public Space getCurrentLocation() {
        return currentLocation;
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
        if(this.currentLocation.getLocation()> location.getLocation()){
            //receiveMoney(GoRules.getInstance().getSalary());
        }
        currentLocation = location;
        location.onVisit(this);
    }

    public void receiveMoney(int feeToPlayer) {
        money +=feeToPlayer;
    }

    public void keepCard(Card card) {
        cards.add(card);
    }

    public void goToJail() {
        moveTaken = MoveType.GoToJail;
        this.inJail = true;
        currentLocation=board.getSpaceOnBoard("Jail");
    }

    public void giveMoneyToBank(int feeToPlayer) {
        if(!this.spendMoney(feeToPlayer)){
            if (bankruptcyRules.checkForBankruptcy(this, feeToPlayer)) {
                bankruptcyRules.bankruptByBank(this);
            }
            else{
                this.sellItemsToMakeMoney(feeToPlayer);
                this.spendMoney(feeToPlayer);
            }
        }

    }

    public void sellItemsToMakeMoney(int moneyNeeded) {
        // TODO Need to work out a suitable way of doing this. Using heuristics.
    }

    public void receiveMoneyFromPlayers(int feeToPlayer) {
        for(Player player : AllPlayers.getInstance().getAllPlayers()){
            if(!player.spendMoney(feeToPlayer)){
                if (bankruptcyRules.checkForBankruptcy(player, feeToPlayer)) {
                    bankruptcyRules.bankruptByPlayer(this, player);
                }
                else{
                    player.sellItemsToMakeMoney(feeToPlayer);
                    player.spendMoney(feeToPlayer);
                    this.receiveMoney(feeToPlayer);
                }
            }
            else{
                this.receiveMoney(feeToPlayer);
            }
        }
    }

    public int calculateHotelsOwned() {
        int numberOfHotels = 0;
        for(Ownable property: ownedSpaces){
            if(property instanceof Property){
                numberOfHotels += ((Property) property).getHotels();
            }
        }
        return numberOfHotels;
    }

    public int calculateHousesOwned() {
        int numberOfHouses = 0;
        for(Ownable property: ownedSpaces){
            if(property instanceof Property){
                numberOfHouses += ((Property) property).getHouses();
            }
        }
        return numberOfHouses;
    }

    public void payOtherPlayers(int feeToPlayer) {
        for(Player player : AllPlayers.getInstance().getAllPlayers()){
            if(!this.spendMoney(feeToPlayer)){
                if (bankruptcyRules.checkForBankruptcy(this, feeToPlayer)) {
                    bankruptcyRules.bankruptByPlayer(player, this);
                }
                else{
                    this.sellItemsToMakeMoney(feeToPlayer);
                    this.spendMoney(feeToPlayer);
                    player.receiveMoney(feeToPlayer);
                }
            }
            else{
                player.receiveMoney(feeToPlayer);
            }
        }
    }

    public boolean wantsToBuyPropertyForPrice(Space property, int askingPriceOfProperty) {
        //Todo Implement a decent heuristic to determine whether the player wants to buy the property.
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
                saleableMoney += ((Property) space).getHotels() * ((Property) space).getHouseCost() * AllRules.getSellingRules().priceReductionForSellingOfHotel();
                saleableMoney += ((Property) space).getHouses() * ((Property) space).getHouseCost() * AllRules.getSellingRules().priceReductionForSellingOfHouse();
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

    public DiceRoll getLastDiceRoll() {
        return lastDiceRoll;
    }

    public Stack<Property> getOwnedPropertiesOfGroup(Group group) {
        Stack<Property> propertyStack = new Stack<Property>();
        for (Ownable ownable : ownedSpaces) {
            if (ownable.getGroup().equals(group)) {
                propertyStack.add((Property) ownable);
            }
        }
        return propertyStack;
    }
}
