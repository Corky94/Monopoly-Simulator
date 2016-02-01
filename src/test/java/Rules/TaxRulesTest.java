package Rules;

import Board.Group;
import Board.Tax;
import Players.Player;
import junit.framework.TestCase;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

/**
 * Created by userhp on 01/02/2016.
 */
public class TaxRulesTest extends TestCase {

    public void testCalculateIncomeTax() throws Exception {
        Player player = Mockito.mock(Player.class);
        Tax tax = Mockito.mock(Tax.class);
        when(tax.getFee()).thenReturn(200);
        when(player.getCurrentLocation()).thenReturn(tax);
        when(player.calculateNetWorth()).thenReturn(100);
        TaxRules.init(0.1,true);
        TaxRules rules = TaxRules.getInstance();
        assertEquals(10,rules.calculateIncomeTax(player));
    }
    public void testCalculateIncomeTaxWhereNetWorthIsMoreThanSetTax() throws Exception {
        Player player = Mockito.mock(Player.class);
        Tax tax = Mockito.mock(Tax.class);
        when(tax.getFee()).thenReturn(200);
        when(player.getCurrentLocation()).thenReturn(tax);
        when(player.calculateNetWorth()).thenReturn(4000);
        TaxRules.init(0.1,true);
        TaxRules rules = TaxRules.getInstance();
        assertEquals(200,rules.calculateIncomeTax(player));
    }
    public void testCalculateIncomeTaxWhenNoFixedTaxIsAllowed() throws Exception {
        Player player = Mockito.mock(Player.class);
        Tax tax = Mockito.mock(Tax.class);
        when(tax.getFee()).thenReturn(200);
        when(player.getCurrentLocation()).thenReturn(tax);
        when(player.calculateNetWorth()).thenReturn(4000);
        TaxRules.init(0.1,false);
        TaxRules rules = TaxRules.getInstance();
        assertEquals(400,rules.calculateIncomeTax(player));
    }
    public void testCalculateIncomeTaxWhenNoFixedTaxIsAllowedAndDifferentTaxPercentage() throws Exception {
        Player player = Mockito.mock(Player.class);
        Tax tax = Mockito.mock(Tax.class);
        when(tax.getFee()).thenReturn(200);
        when(player.getCurrentLocation()).thenReturn(tax);
        when(player.calculateNetWorth()).thenReturn(4000);
        TaxRules.init(0.25,false);
        TaxRules rules = TaxRules.getInstance();
        assertEquals(1000,rules.calculateIncomeTax(player));
    }
}