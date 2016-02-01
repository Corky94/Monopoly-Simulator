package Players;

import Board.*;
import Board.Space;
import Cards.Card;
import Dice.Dice;
import Rules.MoveType;

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
    private int noOfRolls;
    private int turnInJail;
    private int sumOfDiceRolls;
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
    public int rollDice(){
        noOfRolls++;
        int[] diceResults = new int[dices.length];

        for (int i = 0; i < dices.length; i++) {
            Dice d = dices[i];
            diceResults[i] = d.rollTheDice();
        }
        int firstResult = diceResults[0];
        boolean allTheSame = true;
        for (int result: diceResults) {
            if (result!=firstResult) {
                allTheSame=false;
                break;
            }
        }
        sumOfDiceRolls = Arrays.stream(diceResults).sum();
        if(allTheSame)
        {
            if(noOfRolls>=3){
               this.goToJail();
                sumOfDiceRolls = -1;
            }
            else {
                this.setCurrentLocation(board.moveToSpace(currentLocation, sumOfDiceRolls));
                rollDice();
            }
        }
        else{
            noOfRolls=0;
            this.setCurrentLocation(board.moveToSpace(currentLocation, sumOfDiceRolls));
            return sumOfDiceRolls;
        }
        //FIXME a poor work around I need a way to implement this without being recursive.
        noOfRolls=0;
        return sumOfDiceRolls;



    }

    public Space getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Space currentLocation) {
        currentLocation.onVisit(this);
        this.currentLocation = currentLocation;
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
    public int amountRolledOnDice(){
        return sumOfDiceRolls;
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
}
