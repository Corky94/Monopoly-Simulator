package Rules;

/**
 * Created by userhp on 29/01/2016.
 */
public class AuctionRules {
    private static  AuctionRules instance = new AuctionRules();
    private AuctionRules(){}
    public static AuctionRules getInstance() {
        return instance;
    }

    public double getStartingPriceMultiplier() {
        return 0;
    }

    public double getIncrementMultiplier() {
        return 0;
    }
}
