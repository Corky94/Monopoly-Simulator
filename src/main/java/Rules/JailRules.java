package Rules;

/**
 * Created by userhp on 01/02/2016.
 */
public class JailRules {
    private static JailRules instance = new JailRules();
    private static int amountOfRollsToGetOutOfJail;
    private static int amountOfDoublesToBeSentToJail;
    private static int feeToPayToGetOutOfJail;
    private static boolean canEarnRent;

    private JailRules(){};

    public static void init(int rollsToGetOutOfJail, int fee, boolean earnRent, int doublesToBeSentJail) {
        amountOfRollsToGetOutOfJail = rollsToGetOutOfJail;
        feeToPayToGetOutOfJail = fee;
        canEarnRent = earnRent;
        amountOfDoublesToBeSentToJail = doublesToBeSentJail;
    }

    public static JailRules getInstance(){
        return instance;
    }
    public int amountOfRollsToGetOutOfJail(){
        return amountOfRollsToGetOutOfJail;
    }
    public int feeToPayToGetOutOfJail(){
        return feeToPayToGetOutOfJail;
    }
    public boolean canEarnRent(){
        return canEarnRent;
    }

    public int amountOfDoublesToBeSentToJail() {
        return amountOfDoublesToBeSentToJail;
    }
}
