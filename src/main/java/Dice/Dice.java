package Dice;


/**
 * Created by marc on 20/11/2015.
 */
public class Dice {
    private final int MIN = 1;
    private final int MAX = 6;


    public int rollTheDice(){

        return (int)(Math.random()*((MAX-MIN)+1) +MIN);


    }
}
