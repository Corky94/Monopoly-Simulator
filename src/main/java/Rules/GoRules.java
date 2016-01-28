package Rules;

/**
 * Created by userhp on 28/01/2016.
 */
public class GoRules {
    private int salary;
    private static GoRules instance = new GoRules();

    private GoRules(){
        salary = 200;
    }

    public static GoRules getInstance() {
        return instance;
    }
    public int getSalary(){
        return salary;
    }
    public void setSalary(int newSalary){
        salary = newSalary;
    }
    public void updateSalary(double percentage){
        salary = salary + (int)(salary*percentage);
    }
}
