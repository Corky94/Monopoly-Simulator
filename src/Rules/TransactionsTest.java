package Rules;

import Players.Money;
import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Created by marc on 20/12/2015.
 */
public class TransactionsTest extends TestCase {

    public void testConvert500ToMoney() throws Exception {
        Transactions t = new Transactions();
        Money m = t.convertToMoney(500);
        Assert.assertEquals(1,m.getFiveHundreds());
        Assert.assertEquals(0,m.getOneHundreds());
        Assert.assertEquals(0,m.getFifties());
        Assert.assertEquals(0,m.getTwenties());
        Assert.assertEquals(0,m.getTens());
        Assert.assertEquals(0,m.getFives());
        Assert.assertEquals(0,m.getOnes());

    }
    public void testConvert3ToMoney() throws Exception {
        Transactions t = new Transactions();
        Money m = t.convertToMoney(3);
        Assert.assertEquals(0,m.getFiveHundreds());
        Assert.assertEquals(0,m.getOneHundreds());
        Assert.assertEquals(0,m.getFifties());
        Assert.assertEquals(0,m.getTwenties());
        Assert.assertEquals(0,m.getTens());
        Assert.assertEquals(0,m.getFives());
        Assert.assertEquals(3,m.getOnes());

    }
    public void testConvert573ToMoney() throws Exception {
        Transactions t = new Transactions();
        Money m = t.convertToMoney(573);
        Assert.assertEquals(1,m.getFiveHundreds());
        Assert.assertEquals(0,m.getOneHundreds());
        Assert.assertEquals(1,m.getFifties());
        Assert.assertEquals(1,m.getTwenties());
        Assert.assertEquals(0,m.getTens());
        Assert.assertEquals(0,m.getFives());
        Assert.assertEquals(3,m.getOnes());

    }
    public void testConvert999ToMoney() throws Exception {
        Transactions t = new Transactions();
        Money m = t.convertToMoney(999);
        Assert.assertEquals(1,m.getFiveHundreds());
        Assert.assertEquals(4,m.getOneHundreds());
        Assert.assertEquals(1,m.getFifties());
        Assert.assertEquals(2,m.getTwenties());
        Assert.assertEquals(0,m.getTens());
        Assert.assertEquals(1,m.getFives());
        Assert.assertEquals(4,m.getOnes());

    }
}