package Rules;

import Board.Property;
import Board.Space;
import Players.Player;

/**
 * Created by userhp on 28/01/2016.
 */
public class Bank {
    private  static GoRules goRules = GoRules.getInstance();

    private static int hotelsInBank;
    private static int housesInBank;



    public static  void payPlayer(Player playerToSend, Player playerToReceive,int amount){
        playerToSend.spendMoney(amount);
        playerToReceive.gainMoney(amount);

    }
    public static void passGo(Player player){
        player.receiveMoney(goRules.getSalary());
    }

    public static void buyHouse(Property property,Player player){
        if (player.equals(property.getOwner()) /*&& matches the house building rules! */)
        if(housesInBank == 0){

        }
        else{
            player.spendMoney(property.getHouseCost());
            housesInBank--;
            property.addHouse();
        }
    }

}
