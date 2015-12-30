package Rules;

import Board.Property;
import Players.Money;

/**
 * Created by marc on 23/11/2015.
 *
 * This will be the base class, which will allow for new rules regarding transactions will take place.
 * Each class will that inherits will contain the basic convert to money method. But selling and purchasing may change
 * depending on how I implement the rules.
 *
 */
public  class Transactions {

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

   //For Current Testing purposes wont be an abstract class

   // protected abstract Money calculateHousePrice(Property property);

}
