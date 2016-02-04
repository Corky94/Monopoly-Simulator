package Rules;

/**
 * Created by userhp on 28/01/2016.
 */
public class GoRules {
    private static int salary;
    private static GoRules instance = new GoRules();

    private GoRules(){
        salary = 200;
    }
    private static void GoRulesInit(int initSalary){
        salary = initSalary;
    }

    public static GoRules getInstance() {
        return instance;
    }
    public int getSalary(){
        return salary;
    }
    public void updateSalary(double percentage){
        salary +=  (int)(salary*percentage);
    }
}
