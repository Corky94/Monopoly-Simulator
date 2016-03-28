package Cards;

import Board.Board;
import Board.Space;
import Cards.Deck.Deck;
import Players.Player;

import java.util.logging.Logger;

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

    protected Card() {

    }

    public Card(String name, CardAction action) {
        setAction(action);
        setName(name);
    }

    public Card(String name, CardAction action, int feeOrSpaces) {
        setAction(action);
        setName(name);
        if (action.equals(CardAction.GoBackSpaces)) {
            setSpacesToMove(feeOrSpaces);
        } else {
            setFee(feeOrSpaces);
        }
    }

    public Card(String name, CardAction action, Space location) {
        setAction(action);
        setName(name);
        setLocation(location);

    }

    public Card(String name, CardAction action, int feePerHouse, int feePerHotel) {
        setAction(action);
        setName(name);
        setFeePerHouse(feePerHouse);
        setFeePerHotel(feePerHotel);

    }

    protected void setAction(CardAction action) {
        this.action = action;
    }

    public CardAction getAction() {
        return action;
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
        Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).info(player.getName() + " Picked up card " + name + " " + action);
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
                newLocation = board.moveToSpace(player, -spacesToMove);
                player.moveToLocation(newLocation);
                deck.addCard(this);
                break;
            case AdvanceToNearestUtility:
                newLocation = board.moveToNearestUtility(currentLocation);
                player.moveToLocation(newLocation);
                deck.addCard(this);
                break;
            case AdvanceToNearestStation:
                newLocation = board.moveToNearestStation(currentLocation);
                player.moveToLocation(newLocation);
                deck.addCard(this);
                break;
            case PayPlayers:
                player.payOtherPlayers(feeToPlayer);
                deck.addCard(this);
                break;
        }
    }


}
