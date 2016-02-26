package Rules;

import Board.Ownable;
import Players.AllPlayers;
import Players.Player;

import java.util.Vector;

/**
 * Rules for checking if the player is bankrupt
 */
public class BankruptcyRules {


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
        Vector<Ownable> ownedSpaces = (Vector<Ownable>) bankruptPlayer.getOwnedSpaces().clone();
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
            AllRules.getBankRules().auctionProperty(space);
            bankruptPlayer.removeProperty(space);
        }
        System.out.println("Player is out the game");
        AllPlayers.getInstance().removePlayer(bankruptPlayer);
    }

    

    
}