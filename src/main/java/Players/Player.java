package Players;

import Board.Space;
import Cards.Card;
import Dice.Dice;

import java.util.Vector;

/**
 *  TODO a lot of the logic needs to be developed here. A lot of time is needed however the rest of program needs to be done firs
 *
 * Created by marc on 20/11/2015.
 */
public class Player {
    private Space currentLocation;
    private int money;
    private Vector<Space> ownedSpaces;
    private Dice[] dices;
    private boolean inJail = false;



    public Space getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Space currentLocation) {
        this.currentLocation = currentLocation;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void moveToLocation(Space location) {
    }

    public void receiveMoney(int feeToPlayer) {
    }

    public void keepCard(Card card) {
    }

    public void goToJail() {
        
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
}
