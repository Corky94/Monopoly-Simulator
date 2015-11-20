package Dice;


/**
 * Created by marc on 20/11/2015.
 */
public class Dice {
    private final int MIN = 1;
    private final int MAX = 6;
    private boolean repeatRoll = false;

    public int rollTheDice(){

        int diceOne = (int)(Math.random()*((MAX-MIN)+1) +MIN);
        int diceTwo = (int)(Math.random()*((MAX-MIN)+1) +MIN);

        if(diceOne == diceTwo){
            repeatRoll = true;
        }
        else{
            repeatRoll = false;
        }

        return diceOne+diceTwo;


    }
}
