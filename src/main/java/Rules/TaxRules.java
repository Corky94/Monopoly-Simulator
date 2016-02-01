package Rules;

/**
 * Created by userhp on 29/01/2016.
 */
public class TaxRules {
    private static  TaxRules instance = new TaxRules();
    private TaxRules(){}
    public static TaxRules getInstance() {
        return instance;
    }

    public double getIncomeTaxPercentage(){
        //Todo need to work a way of calculating the worth of a player 
        return 0;
    }

    
}