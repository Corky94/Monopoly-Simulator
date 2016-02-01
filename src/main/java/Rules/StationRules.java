package Rules;

import Board.Group;
import Players.Player;

/**
 * Created by userhp on 30/01/2016.
 */
public class StationRules {

    private static StationRules instance =  new StationRules();
    private static int oneStationRent;
    private static int twoStationRent;
    private static int threeStationRent;
    private static int fourStationRent;

    private StationRules(){}

    public static StationRules getInstance() {
        return instance;
    }

    public static void init(int one,int two,int three,int four){
        oneStationRent=one;
        twoStationRent=two;
        threeStationRent =three;
        fourStationRent = four;
    }

    public int calculateRent(Player owner, Player visitor) {
        int rentOwed = 0;
        switch(owner.ownsSpacesOfGroup(Group.Station)){

            case 2:
                rentOwed = twoStationRent;
                break;
            case 3:
                rentOwed = threeStationRent;
                break;
            case 4:
                rentOwed = fourStationRent;
                break;
            default:
                rentOwed = oneStationRent;
                break;
        }
        if(visitor.getMoveTaken().equals(MoveType.Card)){
            rentOwed *=2;
        }
        return rentOwed;
    }
}
