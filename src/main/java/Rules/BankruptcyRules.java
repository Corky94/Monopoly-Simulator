package Rules;

import Board.Ownable;
import Players.AllPlayers;
import Players.Player;

import java.util.Vector;

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
        boolean bankrupt = false;
        if(player.calculateSaleableItems()<moneyOwed){
            bankrupt = true;
        }
        return bankrupt;
    }
    public void bankruptByPlayer(Player owedPlayer, Player bankruptPlayer){
        int allBankruptPlayerMoney = bankruptPlayer.getMoney();
        owedPlayer.receiveMoney(allBankruptPlayerMoney);
        Vector<Ownable> ownedSpaces = bankruptPlayer.getOwnedSpaces();
        for(Ownable space : ownedSpaces){
            owedPlayer.addProperty(space);
            bankruptPlayer.removeProperty(space);
            space.setOwner(owedPlayer);
        }
        System.out.println("Player is out the game");
        AllPlayers.getInstance().removePlayer(bankruptPlayer);
    }
    public void bankruptByBank( Player bankruptPlayer){

        Vector<Ownable> ownedSpaces = bankruptPlayer.getOwnedSpaces();
        for(Ownable space : ownedSpaces){
            Bank.getInstance().auctionProperty(space);
            bankruptPlayer.removeProperty(space);
        }
        System.out.println("Player is out the game");
        AllPlayers.getInstance().removePlayer(bankruptPlayer);
    }

    

    
}