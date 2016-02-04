package Rules;

/**
 * Created by userhp on 29/01/2016.
 */
public class AuctionRules {
    private static  AuctionRules instance = new AuctionRules();
    private static double startingPirceMultipler;
    private static double incrementMultipler;
    private AuctionRules(){}
    public static AuctionRules getInstance() {
        return instance;
    }
    public static void init(double startingPriceMultiplerInit, double incrementMultiplerInit){
        startingPirceMultipler = startingPriceMultiplerInit;
        incrementMultipler = incrementMultiplerInit;
    }

    public double getStartingPriceMultiplier() {
        return startingPirceMultipler;
    }

    public double getIncrementMultiplier() {
        return incrementMultipler;
    }
}
