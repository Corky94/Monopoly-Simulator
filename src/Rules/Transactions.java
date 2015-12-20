package Rules;

import Players.Money;

/**
 * Created by marc on 23/11/2015.
 */
public class Transactions {

    public Money convertToMoney(int amount){
        int fiveHundreds = amount/500;
        amount = amount%500;
        int oneHundreds = amount/100;
        amount = amount%100;
        int fifties = amount/50;
        amount = (amount%50);
        int twenties = amount/20;
        amount = (amount%20);
        int tens = amount/10;
        amount = (amount%10);
        int fives = amount/5;
        amount = (amount%5);
        int ones = amount;
        Money moneyToReturn = new Money(fiveHundreds,oneHundreds,fifties,twenties,tens,fives,ones);
        return  moneyToReturn;
    }
}
