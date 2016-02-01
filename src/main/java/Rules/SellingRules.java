package Rules;

/**
 * Created by userhp on 30/01/2016.
 */
public class SellingRules {
    private static SellingRules instance = new SellingRules();
    private static double priceReductionForSellingHouse;
    private static double priceReductionForSellingHotel;

    private SellingRules() {

    }


    public static SellingRules getInstance() {
        return instance;
    }
    public static void init(double reductionForSellingHouse, double reductionForSellingHotel){
        priceReductionForSellingHouse = reductionForSellingHouse;
        priceReductionForSellingHotel = reductionForSellingHotel;
    }

    public double priceReductionForSellingOfHouse() {

        return priceReductionForSellingHouse;
    }

    public double priceReductionForSellingOfHotel() {
        return priceReductionForSellingHotel;
    }
}
