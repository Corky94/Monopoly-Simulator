package Board;

import Players.AllPlayers;
import Players.Player;
import Rules.Bank;
import Rules.UtilityRules;

/**
 * Created by userhp on 26/01/2016.
 */
public class Utilities extends Ownable {

    private UtilityRules utilityRules = UtilityRules.getInstance();

    public Utilities(String name, int loc, Group group, int cost,int mtg){
        super.setGroup(group);
        super.setLocation(loc);
        super.setName(name);
        super.setCost(cost);
        super.setMortgagePrice(mtg);
        super.setMortgaged(false);
    }

    @Override
    public void onVisit(Player player) {
        Bank bank = Bank.getInstance();
        if(super.getOwner() == null){
            if(player.wantsToBuyPropertyForPrice(this, super.getCost())){
                player.spendMoney(super.getCost());
                player.addProperty(this);
                super.setOwner(player);
            }
            else{
                bank.auctionProperty(this);
            }
        }
        else{
            if(!getOwner().equals(player)){
                int rentOwed = utilityRules.calculateRent(getOwner(),player);
                bank.payPlayer(player,super.getOwner(),rentOwed);
            }
        }

    }


}
