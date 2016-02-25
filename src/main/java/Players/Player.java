package Players;

import Board.*;
import Board.Space;
import Cards.Card;
import Cards.CardAction;
import Cards.Deck.Deck;
import Dice.*;
import Rules.*;

import java.util.*;

/**
 *  TODO a lot of the logic needs to be developed here. A lot of time is needed however the rest of program needs to be done firs
 *
 * Created by marc on 20/11/2015.
 */
public class Player {
    private JailRules jailRules;
    private GoRules goRules;
    private Space currentLocation;
    private int money = 0;
    private MoveType moveTaken;   
    private int turnInJail;    
    private Vector<Ownable> ownedSpaces;
    private Dice[] dices;
    private Vector<Card> cards;
    private boolean inJail = false;
    private Board board =Board.getInstance();
    private DiceRoll lastDiceRoll;

    private BankruptcyRules bankruptcyRules;
    private Bank bankRules;

    public Player (int initialMoney, Dice[] dices){
        moveTaken = MoveType.DiceRoll;
        money = initialMoney;
        ownedSpaces = new Vector<Ownable>();
        this.dices = dices;
        currentLocation = board.getSpaceOnBoard("Go");
        bankruptcyRules = AllRules.getBankruptcyRules();
        goRules = AllRules.getGoRules();
        jailRules = AllRules.getJailRules();
        bankRules = AllRules.getBankRules();

    }

    public void onTurn() {
        if (inJail) {
            this.playTurnInJail();
        } else {
            DiceRoll roll = rollDice();
            int rolls = 1;
            while (roll.isReRoll()) {
                if (rolls >= jailRules.amountOfDoublesToBeSentToJail()) {
                    this.goToJail();
                    turnInJail = 0;
                    break;
                }
                this.moveToLocation(Board.getInstance().moveToSpace(currentLocation, roll.getSumOfDiceRolls()));
                roll = rollDice();
            }
            if (!inJail) {
                this.moveToLocation(Board.getInstance().moveToSpace(currentLocation, roll.getSumOfDiceRolls()));
            }
        }
    }

    //To Be Expanded
    public void betweenTurns() {
        anyHousesOrHotelsToBuild();
    }

    private void anyHousesOrHotelsToBuild() {
        List<Ownable> properties = (List) ownedSpaces;
        Collections.sort(properties, new OwnableComparator());
        Collections.reverse(properties);
        boolean buildingProperties = true;
        while (buildingProperties) {
            if (properties.size() == 0) {
                break;
            }
            for (Ownable space : properties) {
                if (space instanceof Property) {
                    if (money * 0.5 < ((Property) space).getHouseCost()) {
                        buildingProperties = false;
                        break;
                    }
                    if (AllRules.getBuildRules().canBuildHotel((Property) space, this)) {
                        bankRules.buyHotel((Property) space, this);
                    } else if (AllRules.getBuildRules().canBuildHotel((Property) space, this)) {
                        bankRules.buyHouse((Property) space, this);
                    } else {
                        properties.remove(space);
                    }
                }
            }
        }
    }

    private void playTurnInJail() {
        turnInJail++;
        if (cards.size() > 0) {
            for (Card card : cards) {
                if (card.getAction().equals(CardAction.GetOutOfJail)) {
                    inJail = false;
                    turnInJail = 0;
                    cards.remove(card);
                    Deck.getInstance().addCard(card);
                    break;
                }
            }
        } else if (turnInJail > jailRules.amountOfRollsToGetOutOfJail()) {
            inJail = false;
            turnInJail = 0;
        } else if (this.wantsToPayJailFine()) {
            spendMoney(jailRules.feeToPayToGetOutOfJail());
            inJail = false;
            turnInJail = 0;
        } else {
            DiceRoll roll = rollDice();
            if (roll.isReRoll()) {
                moveToLocation(Board.getInstance().moveToSpace(currentLocation, roll.getSumOfDiceRolls()));
                inJail = false;
                turnInJail = 0;
                // }
            }
        }
    }

    private boolean wantsToPayJailFine() {
        boolean payFine = false;
        if (money * 0.1 > AllRules.getJailRules().feeToPayToGetOutOfJail()) {
            payFine = true;
        }
        return payFine;
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
            receiveMoney(goRules.getSalary());
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
        //For now best way to do this is to sell the minimum amount of houses and mortgage as little as possible.
        List<Ownable> sortedProperties = (List) ownedSpaces;
        Collections.sort(sortedProperties, new OwnableComparator());
        while (money < moneyNeeded) {
            for (Ownable space : ownedSpaces) {
                if (space instanceof Property) {
                    if (((Property) space).getHotels() > 0) {
                        bankRules.sellHotel((Property) space, this);
                    } else if (((Property) space).getHouses() > 0) {
                        bankRules.sellHouse((Property) space, this);
                    } else {
                        bankRules.mortgageProperty(space, this);
                    }
                } else {
                    bankRules.mortgageProperty(space, this);
                }
                if (money > moneyNeeded) {
                    break;
                }
            }
        }
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
        boolean willingToBuyProperty = false;
        int amountOfSpacesOwnedOfGroup = ownsSpacesOfGroup(property.getGroup());
        int amountOfSpacesOnBoardOfGroup = board.amountOfSpacesInGroup(property.getGroup());
        int amountOfMoneyWillingToSpend = 0;

        //VERY BASIC HEURISTIC
        switch (amountOfSpacesOnBoardOfGroup - amountOfSpacesOwnedOfGroup) {
            case 1:
                amountOfMoneyWillingToSpend = (int) (money * 0.7);
                break;
            case 2:
                amountOfMoneyWillingToSpend = (int) (money * 0.6);
                break;
            default:
                amountOfMoneyWillingToSpend = (int) (money * 0.5);
                break;
        }
        if (amountOfMoneyWillingToSpend < askingPriceOfProperty) {
            willingToBuyProperty = true;
        }
        return willingToBuyProperty;
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
