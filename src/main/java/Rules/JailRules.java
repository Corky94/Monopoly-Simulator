package Rules;

/**
 * Created by userhp on 01/02/2016.
 */
public class JailRules {
    private static JailRules instance = new JailRules();
    private int amountOfRollsToGetOutOfJail;
    private int feeToPayToGetOutOfJail;
    private boolean canEarnRent;

    private JailRules(){};

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
}
