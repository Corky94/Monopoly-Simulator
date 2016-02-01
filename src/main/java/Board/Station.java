package Board;

import Players.Player;
import Rules.Bank;
import Rules.StationRules;

/**
 * Created by userhp on 26/01/2016.
 */
public class Station extends Ownable {
    private StationRules stationRules = StationRules.getInstance();

    public Station(String name, int loc, Group group, int cost, int mtg ){
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
        Player owner = super.getOwner();
        if(owner == null){
            if(player.wantsToBuyPropertyForPrice(this, super.getCost())){
                player.spendMoney(super.getCost());
                player.addProperty(this);
                owner = (player);
            }
            else{
                bank.auctionProperty(this,bank.getAllPlayersInGame());
            }
        }
        else{
            if(!owner.equals(player)){
                int rentOwed = stationRules.calculateRent(owner,player);
            }
        }

    }

}
