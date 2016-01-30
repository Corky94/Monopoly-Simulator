package Board;

import Players.Player;
import Rules.Bank;
import Rules.UtilityRules;

/**
 * Created by userhp on 26/01/2016.
 */
public class Utilities extends Space {
    private int cost;
    private int mortgagePrice;
    private Player owner;
    private UtilityRules utilityRules = UtilityRules.getInstance();

    public Utilities(String name, int loc, Group group, int cost,int mtg){
        super.setGroup(group);
        super.setLocation(loc);
        super.setName(name);
        this.cost = cost;
        this.mortgagePrice = mtg;
    }

    @Override
    public void onVisit(Player player) {
        if(getOwner() == null){
            if(player.wantsToBuyPropertyForPrice(this, getCost())){
                player.spendMoney(getCost());
                player.addProperty(this);
                setOwner(player);
            }
            else{
                Bank.auctionProperty(this,Bank.getAllPlayersInGame());
            }
        }
        else{
            if(!getOwner().equals(player)){
                int rentOwed = utilityRules.calculateRent(getOwner(),player);
            }
        }

    }

    public int getCost() {
        return cost;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }
}
