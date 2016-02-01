package Rules;

/**
 * Created by userhp on 30/01/2016.
 */
public class SellingRules {
    private static SellingRules instance = new SellingRules();

    private SellingRules() {

    }


    public static SellingRules getInstance() {
        return instance;
    }

    public double priceReductionForSellingOfHouse() {
        return 0;
    }

    public double priceReductionForSellingOfHotel() {
        return 0;
    }
}
