package Cards;

import Board.Space;
import Cards.Deck.Deck;
import Players.Player;

/**
 * TODO what about the go back spaces?
 * Created by userhp on 26/01/2016.
 */
public class Card {
    private String name;
    private CardAction action;
    private int feeToPlayer;
    private int feePerHouse;
    private int feePerHotel;
    private Space location;

    protected void setAction(CardAction action) {
        this.action = action;
    }


    protected void setFee(int fee) {
        feeToPlayer = fee;
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

    protected void setFeePerHouse(int feePerHouse) {
        this.feePerHouse = feePerHouse;
    }

    protected void setFeePerHotel(int feePerHotel) {
        this.feePerHotel = feePerHotel;
    }
    public void onDraw(Player player){
        switch (action){

            case AdvanceToLocation:
                player.moveToLocation(location);
                Deck.addCard(this);
                break;
            case CollectMoneyFromBank:
                player.receiveMoney(feeToPlayer);
                Deck.addCard(this);
                break;
            case GetOutOfJail:
                player.keepCard(this);
                break;
            case GoToJail:
                player.goToJail();
                Deck.addCard(this);
                break;
            case PayBank:
                player.giveMoneyToBank(feeToPlayer);
                Deck.addCard(this);
                break;
            case CollectFromPlayers:
                player.receiveMoneyFromPlayers(feeToPlayer);
                Deck.addCard(this);
                break;
            case PayBankDependingOnHousesAndHotelsOwned:
                int houses = player.calculateHousesOwned();
                int hotels = player.calculateHotelsOwned();
                int payment = (houses*feePerHouse)+(hotels*feePerHotel);
                player.giveMoneyToBank(payment);
                Deck.addCard(this);
                break;
            case GoBackSpaces:
                int currentLocation = player.getCurrentLocation();
                //Todo develop logic of the board more
                //Space spaceToMoveTo =
                Deck.addCard(this);
                break;
            case AdvanceToNearestUtility:
                //Difficult logic to work on
                Deck.addCard(this);
                break;
            case AdvanceToNearestStation:
                //Difficult logic to work on
                Deck.addCard(this);
                break;
            case PayPlayers:
                player.payOtherPlayers(feeToPlayer);
                Deck.addCard(this);
                break;
        }
    }
}
