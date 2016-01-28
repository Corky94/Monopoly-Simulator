package Cards;

import Board.Board;
import Board.Space;
import Cards.Deck.Deck;
import Players.Player;

/**
 *
 * Created by userhp on 26/01/2016.
 */
public class Card {
    private String name;
    private CardAction action;
    private int feeToPlayer;
    private int spacesToMove;
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


    protected void setLocation(Space location) {
        this.location = location;
    }

    protected void setFeePerHouse(int feePerHouse) {
        this.feePerHouse = feePerHouse;
    }

    protected void setFeePerHotel(int feePerHotel) {
        this.feePerHotel = feePerHotel;
    }
    protected void setSpacesToMove(int spacesToMove) {
        this.spacesToMove = spacesToMove;
    }
    public void onDraw(Player player){
        Deck deck = Deck.getInstance();
        Board board = Board.getInstance();
        Space currentLocation = player.getCurrentLocation();
        Space newLocation = null;
        switch (action){

            case AdvanceToLocation:
                player.moveToLocation(location);
                deck.addCard(this);
                break;
            case CollectMoneyFromBank:
                player.receiveMoney(feeToPlayer);
                deck.addCard(this);
                break;
            case GetOutOfJail:
                player.keepCard(this);
                break;
            case GoToJail:
                player.goToJail();
                deck.addCard(this);
                break;
            case PayBank:
                player.giveMoneyToBank(feeToPlayer);
                deck.addCard(this);
                break;
            case CollectFromPlayers:
                player.receiveMoneyFromPlayers(feeToPlayer);
                deck.addCard(this);
                break;
            case PayBankDependingOnHousesAndHotelsOwned:
                int houses = player.calculateHousesOwned();
                int hotels = player.calculateHotelsOwned();
                int payment = (houses*feePerHouse)+(hotels*feePerHotel);
                player.giveMoneyToBank(payment);
                deck.addCard(this);
                break;
            case GoBackSpaces:
                newLocation = board.moveToSpace(currentLocation,-spacesToMove);
                player.moveToLocation(newLocation);
                deck.addCard(this);
                break;
            case AdvanceToNearestUtility:
                newLocation = board.moveToNearestUtility(currentLocation);
                player.moveToLocation(newLocation);
                //Todo Also add the rent rule.
                deck.addCard(this);
                break;
            case AdvanceToNearestStation:
                newLocation = board.moveToNearestUtility(currentLocation);
                player.moveToLocation(newLocation);
                //Todo Also add the rent rule.
                deck.addCard(this);
                break;
            case PayPlayers:
                player.payOtherPlayers(feeToPlayer);
                deck.addCard(this);
                break;
        }
    }


}
