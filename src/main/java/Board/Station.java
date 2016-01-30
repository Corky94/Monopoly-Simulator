package Board;

import Players.Player;
import Rules.Bank;
import Rules.StationRules;

/**
 * Created by userhp on 26/01/2016.
 */
public class Station extends Space {
    private int cost;
    private int mortagagePrice;
    private Player owner;
    private StationRules stationRules = StationRules.getInstance();

    public Station(String name, int loc, Group group, int cost, int mtg ){
        super.setGroup(group);
        super.setLocation(loc);
        super.setName(name);
        this.cost= cost;
        this.mortagagePrice =mtg;
    }

    @Override
    public void onVisit(Player player) {

        if(owner == null){
            if(player.wantsToBuyPropertyForPrice(this, cost)){
                player.spendMoney(cost);
                player.addProperty(this);
                owner = (player);
            }
            else{
                Bank.auctionProperty(this,Bank.getAllPlayersInGame());
            }
        }
        else{
            if(!owner.equals(player)){
                int rentOwed = stationRules.calculateRent(owner,player);
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
