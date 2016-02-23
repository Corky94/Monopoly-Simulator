package Board;

import Players.Player;
import Rules.AllRules;
import Rules.Bank;
import Rules.StationRules;

/**
 * Created by userhp on 26/01/2016.
 */
public class Station extends Ownable {
    private StationRules stationRules = AllRules.getStationRules();

    public Station(String name, int loc, Group group, int cost, int mtg ){
        super.setGroup(group);
        super.setLocation(loc);
        super.setName(name);
        super.setCost(cost);
        super.setMortgagePrice(mtg);
        super.setMortgaged(false);
    }

    @Override
    public void onVisit(Player visitor) {
        Bank bank = Bank.getInstance();
        Player owner = super.getOwner();
        if(owner == null){
            if(visitor.wantsToBuyPropertyForPrice(this, super.getCost())){
                visitor.spendMoney(super.getCost());
                visitor.addProperty(this);
               setOwner(visitor);
            }
            else{
                bank.auctionProperty(this);
            }
        }
        else{
            if(!owner.equals(visitor)){
                int rentOwed = stationRules.calculateRent(owner,visitor);
                bank.payPlayer(visitor,super.getOwner(),rentOwed);
            }
        }

    }

}
