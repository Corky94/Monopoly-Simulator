package Rules;

import Players.Player;
import Board.Group;

/**
 * Created by userhp on 30/01/2016.
 */
public class UtilityRules {
    private static UtilityRules instance = new UtilityRules();
    private static int multiplierForOneUtility;
    private static int multiplierForBothUtilities;

    private UtilityRules(){}



    public static UtilityRules getInstance() {
        return instance;
    }
    public static void init(int multiplierForOneUtilityInit, int multiplierForBothUtilitiesInit){
    	multiplierForOneUtility = multiplierForOneUtilityInit;
    	multiplierForBothUtilities = multiplierForBothUtilitiesInit;
    }

    public int calculateRent(Player owner, Player visitor) {
        int rentOwed = 0;
    	if( visitor.getMoveTaken().equals(MoveType.Card)){
    		rentOwed = visitor.rollDice().getSumOfDiceRolls()* multiplierForBothUtilities;
    	}
        else if(owner.ownsSpacesOfGroup(Group.Utility) == 2){
            rentOwed = visitor.getLastDiceRoll().getSumOfDiceRolls()* multiplierForBothUtilities;
        }
        else{
            rentOwed = visitor.getLastDiceRoll().getSumOfDiceRolls()* multiplierForOneUtility;
        }

        return rentOwed;
        
    }
}
