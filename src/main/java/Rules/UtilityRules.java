package Rules;

import Players.Player;
import Board.Group;

/**
 * Created by userhp on 30/01/2016.
 */
public class UtilityRules {
    private static UtilityRules instance = new UtilityRules();
    private static int multiplerForOneUtility;
    private static int multiplerForBothUtilities;

    private UtilityRules(){}



    public static UtilityRules getInstance() {
        return instance;
    }
    public static void init(int multiplerForOneUtilityInit, int multiplerForBothUtilitiesInit){
    	multiplerForOneUtility = multiplerForOneUtilityInit;
    	multiplerForBothUtilities = multiplerForBothUtilitiesInit;
    }

    public int calculateRent(Player owner, Player visitor) {
        int rentOwed = 0;
    	if(owner.ownsSpacesOfGroup(Group.Utility) == 2){
    		rentOwed = visitor.amountRolledOnDice()*multiplerForBothUtilities;
    	}
        else{
            rentOwed = visitor.amountRolledOnDice()*multiplerForOneUtility;
        }
        return rentOwed;
        
    }
}
