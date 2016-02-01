package Rules;

import Board.Tax;
import Players.Player;

/**
 * Created by userhp on 29/01/2016.
 */
public class TaxRules {
    private static  TaxRules instance = new TaxRules();
    private static double incomeTaxPercentage;
    private static boolean fixedTaxOption;
    private TaxRules(){}
    public static TaxRules getInstance() {
        return instance;
    }


    public static void init(double taxPercentage, boolean fixedTax){
        incomeTaxPercentage = taxPercentage;
        fixedTaxOption = fixedTax;
    }

    public int calculateIncomeTax(Player player){
        Tax location = (Tax) player.getCurrentLocation();
        int tax = location.getFee();
        int taxablePlayerNetWorth = (int) (player.calculateNetWorth()*incomeTaxPercentage);

        if(taxablePlayerNetWorth<tax || !fixedTaxOption){
            tax= taxablePlayerNetWorth;
        }


        return tax;
    }

    
}