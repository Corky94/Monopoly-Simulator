package Rules;

import Players.Player;

/**
 * Created by userhp on 29/01/2016.
 */
public class BankruptcyRules {
    private static  BankruptcyRules instance = new BankruptcyRules();
    private BankruptcyRules(){}
    public static BankruptcyRules getInstance() {
        return instance;
    }

    public boolean checkForBankruptcy(Player player, int moneyOwed){

        if(player.calculateNetWorth()<moneyOwed){

        }
        return false;
    }
    public void bankruptByPlayer(Player owed, Player bankrupt){

    }

    

    
}