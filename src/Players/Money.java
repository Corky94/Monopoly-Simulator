package Players;

/**
 * Created by marc on 20/11/2015.
 */
public class Money {
    private int fiveHundreds;
    private int oneHundreds;
    private int fifties;
    private int twenties;
    private int tens;
    private int fives;
    private int ones;

    public Money(){

    }
    public Money(int fiveHundreds,int oneHundreds,int fifties,int twenties,int tens,int fives, int ones){
        this.fiveHundreds = fiveHundreds;
        this.oneHundreds = oneHundreds;
        this.fifties = fifties;
        this.twenties = twenties;
        this.tens = tens;
        this.fives = fives;
        this.ones = ones;
    }
    public void add(Money money){
        this.fiveHundreds += money.getFiveHundreds();
        this.oneHundreds += money.getOneHundreds();
        this.fifties += money.getFifties();
        this.twenties += money.getTwenties();
        this.tens += money.getTens();
        this.fives += money.getFives();
        this.ones += money.getOnes();
    }
    public void subtract(Money money){
        this.fiveHundreds -= money.getFiveHundreds();
        this.oneHundreds -= money.getOneHundreds();
        this.fifties -= money.getFifties();
        this.twenties -= money.getTwenties();
        this.tens -= money.getTens();
        this.fives -= money.getFives();
        this.ones -= money.getOnes();
    }

    public int getFiveHundreds() {
        return fiveHundreds;
    }

    public int getOneHundreds() {
        return oneHundreds;
    }

    public int getFifties() {
        return fifties;
    }

    public int getTwenties() {
        return twenties;
    }

    public int getTens() {
        return tens;
    }

    public int getFives() {
        return fives;
    }

    public int getOnes() {
        return ones;
    }
}
